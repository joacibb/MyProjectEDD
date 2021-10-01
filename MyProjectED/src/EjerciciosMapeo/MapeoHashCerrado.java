package EjerciciosMapeo;

import java.util.Random;

import Excepciones.InvalidKeyException;
import TDALista.ListaDE;
import TDALista.PositionList;
import TDAMapeo.Entrada;
import TDAMapeo.Entry;
import TDAMapeo.Map;

/**
 * TDAMap, implementacion: Usando un tabla hash cerrada donde cada bucket hace referencia a una entrada<K,V>.
 * @author Jerez, M.
 */

public class MapeoHashCerrado<K,V> implements Map<K,V>{
	protected Entrada<K,V>[] bucket;
	protected int size, capacidad, prime;
	protected long scale, shift;
	protected final Entrada<K,V> disponible= new Entrada<K,V>(null,null);
	
	/**
	 * Se instancia la tabla hash vacia y se inicializan sus atributos correspondientes
	 */
	public MapeoHashCerrado() {
		capacidad= 29;
		size=0;
		prime= 104729;
		Random rdm= new Random();
		scale= rdm.nextInt(prime-1)+1;
		shift= rdm.nextInt(prime);
		bucket=(Entrada<K,V>[])new Entrada[capacidad];
	}
	
	/**
	 * Consulta la posicion del bucket a la cual debera ir la clave parametrizada.
	 * @param key Clave a procesar.
	 * @return Un entero que indicara la posicion del bucket.
	 */
	protected int hash(K key) {
		return(int) ( (Math.abs( scale*key.hashCode() + shift ) % prime) % capacidad);
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
	public V get(K key) throws InvalidKeyException {
		checkKey(key);
		V retValue= null;
		int insertBucket= hash(key);
		int	posBucket= searchKey(insertBucket, key);
		
		if( bucket[posBucket]!=disponible && bucket[posBucket]!=null) {
			retValue= bucket[posBucket].getValue();
		}
		
		return retValue;
	}
	
	@Override
	public V put(K key, V value) throws InvalidKeyException {
		checkKey(key);
		V retValue= null;
		int insertBucket= hash(key);
		int	posBucket= searchKey(insertBucket, key);
		Entrada<K,V> nuevaEntrada= new Entrada<K,V>(key, value);
		
		if(bucket[posBucket]==disponible || bucket[posBucket]==null) {
			bucket[posBucket]= nuevaEntrada;
			size++;
		}
		else {
			retValue= bucket[posBucket].getValue();
			bucket[posBucket].setValue(value);
		}
		
		if(size >= capacidad/2)
			rehash();
		
		return retValue;
	}

	@Override
	public V remove(K key) throws InvalidKeyException {
		checkKey(key);
		V retValue= null;
		int insertBucket= hash(key);
		int	posBucket= searchKey(insertBucket, key);
		
		if(bucket[posBucket]!=null && bucket[posBucket]!=disponible) {
			retValue= bucket[posBucket].getValue();
			bucket[posBucket]= disponible;
			size--;
		}
		
		return retValue;
	}

	@Override
	public Iterable<K> keys() {
		PositionList<K> keysList= new ListaDE<K>();
		
		for(int i=0; i<capacidad; i++) {
			if(bucket[i]!=null && bucket[i]!=disponible)
				keysList.addLast(bucket[i].getKey());
		}
		
		return keysList;
	}

	@Override
	public Iterable<V> values() {
		PositionList<V> valuesList= new ListaDE<V>();
		
		for(int i=0; i<capacidad; i++) {
			if(bucket[i]!=null && bucket[i]!=disponible)
				valuesList.addLast(bucket[i].getValue());
		}
		
		return valuesList;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K,V>> entriesList= new ListaDE<Entry<K,V>>();
		
		for(int i=0; i<capacidad; i++) {
			if(bucket[i]!=null && bucket[i]!=disponible)
				entriesList.addLast(bucket[i]);
		}
		
		return entriesList;
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
	
	/**
	 * Consulta si la clave parametrizada se encuentra en la tabla hash.
	 * @param pos Ubicacion actual de la busqueda.
	 * @param key Clave a buscar.
	 * @return Retorna un entero entre [0, capacidad), hay tres posibilidades: 
	 * 		Que retorne la referencia a una celda vacia, en ese caso la clave no existia.
	 * 		Que retorne la referencia a la ultima celda disponible visitada, en este caso la clave no existia.
	 * 		Que retorne la referencia a una entrada, en este caso la clave existia y se retornara la ubicacion de la misma.
	 */
	protected int searchKey(int pos, K key) {
		int retPos= pos;
		int cantIt= 0;//Bandera para darme cuenta cuando termine de recorrer todos los elementos
		boolean seguir= (bucket[pos]==null) ? false : true;
		
		while(seguir && cantIt<capacidad) {
			if(bucket[pos]==disponible) {
				retPos= pos;//Guardo la referencia al ultimo bucket disponible visitado
				pos= (pos+1) % capacidad;//Aumento pos para repetar la politica de resolucion lineal
				cantIt++;
			}
			else if(bucket[pos]==null) {
				retPos= pos;
				seguir= false;
			}
			else {
				if(bucket[pos].getKey().equals(key)) {//Si encontre la clave
					seguir= false;
					retPos= pos;
				}
				else {//Si la entrada situada en el bucket tenia una clave distinta a la buscada entonces sigo buscando
					pos= (pos+1) % capacidad;
					cantIt++;
				}
			}
		}
		
		return retPos;
	}
	
	/**
	 * Se encarga de redimensionar la tabla manteniento en el proceso las entradas actuales
	 */
	protected void rehash() {
		capacidad= buscarPrimo(capacidad*2);
		Entrada<K,V>[] vieja= bucket;
		bucket=(Entrada<K,V>[]) new Entrada[capacidad];
		Random rdm= new Random();
		scale= rdm.nextInt(prime-1) +1;
		shift= rdm.nextInt(prime);
		
		for(int i=0; i<vieja.length; i++) {
			Entrada<K,V> entradaActual= vieja[i];
			
			if(entradaActual!=null && entradaActual!=disponible) {
				int hashEntradaActual= hash(entradaActual.getKey());
				int insertarEntrada= searchKey(hashEntradaActual, entradaActual.getKey());//Retorna la ubicacion de una celda vacia
				bucket[insertarEntrada]= entradaActual;
				vieja[i]= null;
			}
			else if(entradaActual==disponible) {
				vieja[i]= null;
			}
		}
		
		
	}
	
	/**
	 * Consulta el numero primo mas cercano al numero parametrizado.
	 * @param primo Numero a consultar.
	 * @return El numero primo mas cercano al numero parametrizado, en caso de que el numero pasado por parametro sea primo entonces se retornara el mismo.	
	 */
	protected int buscarPrimo(int primo) {
		boolean encontre= false;
		
		while(!encontre) {
			if(esPrimo(primo))
				encontre=true;
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
		boolean es=true;
		
		for(int i=2; i<primo && es; i++)
			if(primo % i == 0)
				es= false;
		
		return es;
	}
}