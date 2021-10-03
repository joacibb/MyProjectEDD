package TDADiccionario;

import java.util.Iterator;
import java.util.Random;

import Excepciones.InvalidEntryException;
import Excepciones.InvalidKeyException;
import Excepciones.InvalidPositionException;
import TDALista.ListaDE;
import TDALista.Position;
import TDALista.PositionList;
import TDAMapeo.Entrada;
import TDAMapeo.Entry;

/**
 * TDADictionary, implementacion: Usando un tabla hash abierta donde cada bucket hace referencia a una lista de entradas.
 * @author Jerez, M.
 */

public class DiccionarioHashAbierto<K,V> implements Dictionary<K,V> {
	protected PositionList<Entry<K,V>>[] buckets;
	protected int size, prime, capacidad;
	protected long scale, shift;
	
	/**
	 * Se instancia la tabla hash vacia y se inicializan sus atributos correspondientes
	 */
	@SuppressWarnings("unchecked")
	public DiccionarioHashAbierto() {
		capacidad= 29;
		size=0;
		prime= 104729;
		buckets=(PositionList<Entry<K,V>>[]) new PositionList[capacidad];
		for(int i=0; i<buckets.length; i++) {
			buckets[i]= new ListaDE<Entry<K,V>>();
		}
		Random rdm= new Random();
		scale= rdm.nextInt(prime-1) +1;
		shift= rdm.nextInt(prime);
	}
	
	/**
	 * Consulta la posicion del bucket a la cual debera ir la clave parametrizada.
	 * @param key Clave a procesar.
	 * @return Un entero que indicara la posicion del bucket.
	 */
	protected int hash(K key) {
		return(int) ( (Math.abs(scale*key.hashCode() + shift ) % prime) % capacidad);
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size==0;
	}

	@Override
	public Entry<K, V> find(K key) throws InvalidKeyException {
		checkKey(key);
		Entry<K,V> retEntry= null;
		int bucketPos= hash(key);
		boolean seguir= (buckets[bucketPos].isEmpty()) ? false : true;
		
		if(seguir) {//Si hay entradas en el bucket seleccionado
			Iterator<Entry<K,V>> it= buckets[bucketPos].iterator();
			Entry<K,V> entradaActual= null;
			
			while(it.hasNext() && seguir) {//Recorro la lista buscando si existe una entrada con la misma clave parametrizada
				entradaActual= it.next();
				if(entradaActual.getKey().equals(key)) {
					retEntry= entradaActual;
					seguir= false;
				}
			}
		}
		
		return retEntry;
	}

	@Override
	public Iterable<Entry<K, V>> findAll(K key) throws InvalidKeyException {
		checkKey(key);
		PositionList<Entry<K,V>> entriesList= new ListaDE<Entry<K,V>>();
		int bucketPos= hash(key);
		boolean seguir= (buckets[bucketPos].isEmpty()) ? false : true;
		
		if(seguir) {
			Iterator<Entry<K,V>> it= buckets[bucketPos].iterator();
			Entry<K,V> entradaActual= null;
			
			while(it.hasNext()) {//Recorro la lista buscando si existen entradas con la misma clave parametrizada
				entradaActual= it.next();
				if(entradaActual.getKey().equals(key)) {//Si existe una entrada con la misma clave entonces la agrego a la coleccion
					entriesList.addLast(entradaActual);
				}
			}
		}
		
		return entriesList;
	}

	@Override
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		checkKey(key);
		Entry<K,V> retEntry= new Entrada<K,V>(key, value);
		int bucketPos= hash(key);
		
		buckets[bucketPos].addLast(retEntry);
		size++;
		
		if(size >= capacidad/2)
			rehash();
		
		return retEntry;
	}

	@Override
	public Entry<K, V> remove(Entry<K, V> e) throws InvalidEntryException {
		if(e==null)
			throw new InvalidEntryException("Entrada invalida.");
		K key= e.getKey();
		V value= e.getValue();
		int bucketPos= hash(key);
		boolean seguir= (buckets[bucketPos].isEmpty()) ? false : true;
		
		if(seguir) {
			Iterator<Position<Entry<K,V>>> it= buckets[bucketPos].positions().iterator();
			Position<Entry<K,V>> entradaActual= null;
			
			while(it.hasNext() && seguir) {
				entradaActual= it.next();
				K actualKey= entradaActual.element().getKey();
				V actualValue= entradaActual.element().getValue();
				
				if(actualKey.equals(key) && actualValue.equals(value)) {//Si existe una entrada igual a la parametrizada
					seguir= false;
					try {
						buckets[bucketPos].remove(entradaActual);
					} catch (InvalidPositionException e1) {
						e1.printStackTrace();
					}
					size--;
				}
				
			}//Fin while
		}
		else {
			throw new InvalidEntryException("La entrada solicitada no existe en el diccionario.");
		}
		
		return e;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K,V>> entriesList= new ListaDE<Entry<K,V>>();
		
		for(int i=0; i<buckets.length; i++) {
			for(Entry<K,V> entradaActual: buckets[i]) {
				entriesList.addLast(entradaActual);
			}
		}
		
		return entriesList;
	}
	
	/**
	 * Se encarga de redimensionar la tabla manteniento en el proceso las entradas actuales
	 */
	@SuppressWarnings("unchecked")
	protected void rehash() {
		PositionList<Entry<K,V>>[] vieja= buckets;
		capacidad= proximoPrimo(capacidad *2);
		buckets =(PositionList<Entry<K,V>>[]) new ListaDE[capacidad];
		Random rdm= new Random();
		scale= rdm.nextInt(prime -1) +1;
		shift= rdm.nextInt(prime);
		
		for(int i=0; i<buckets.length; i++) {
			buckets[i]= new ListaDE<Entry<K,V>>();
		}
		
		for(int j=0; j<vieja.length; j++) {//Recorro la vieja tabla hash
			if( !vieja[j].isEmpty()) {
				Iterator<Position<Entry<K,V>>> it= vieja[j].positions().iterator();
				Position<Entry<K,V>> entradaActual=null;
				
				while(it.hasNext()) {//Agrego las entradas viejas en la nueva tabla a la vez que las elimino de las viejas
					entradaActual= it.next();
					int nuevaBucketPos= hash(entradaActual.element().getKey());
					try {
						buckets[nuevaBucketPos].addLast( vieja[j].remove(entradaActual) );
					} catch(InvalidPositionException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	/**
	 * Consulta el numero primo mas cercano al numero parametrizado.
	 * @param primo Numero a consultar.
	 * @return El numero primo mas cercano al numero parametrizado, en caso de que el numero pasado por parametro sea primo entonces se retornara el mismo.	
	 */
	protected int proximoPrimo(int primo) {
		boolean encontre = false;
		
		while(!encontre) {
			if(esPrimo(primo))
				encontre = true;
			else
				primo++;
		}
		
		return primo;
	}
	
	/**
	 * Consulta si el numero parametrizado es primo.
	 * @param primo Numero a verificar.
	 * @return Verdadero en caso de que el numero parametrizado sea primo, falso en caso contrario.
	 */
	protected boolean esPrimo(int primo) {
		boolean encontre = true;
		
		for(int i = 2; i<primo && encontre; i++) {
			if((primo % i) == 0)
				encontre = false;
		}
		
		return encontre;
	}
	
	/**
	 * Consulta si la clave parametrizada es invalida.
	 * @param key Clave a consultar.
	 * @throws InvalidKeyException Se lanza si la clave pasada por parametro es invalida.
	 */
	protected void checkKey(K key) throws InvalidKeyException {
		if(key==null)
			throw new InvalidKeyException("Clave invalida.");
	}
	
	@Override
	public Iterable<V> eliminarTodos(K key) throws InvalidKeyException{
		PositionList<V> entriesList= new ListaDE<V>();
		
		for(int i=0; i<buckets.length; i++) {
			for(Entry<K,V> entradaActual: buckets[i]) {
				if(!((entradaActual.getKey()).equals(key)))
				entriesList.addLast(entradaActual.getValue());
			}
		}
			return entriesList;
	}
}