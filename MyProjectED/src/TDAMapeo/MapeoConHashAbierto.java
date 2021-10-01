package TDAMapeo;

import Excepciones.InvalidKeyException;
import TDALista.ListaDE;
import TDALista.PositionList;

public class MapeoConHashAbierto<K, V>  implements Map<K,V>{
	protected int tamaño;
	protected int maximo;
	protected MapeoConLista<K,V>[] buckets;
	
	@SuppressWarnings("unchecked")
	public MapeoConHashAbierto() {
		tamaño=0;
		maximo=13;
		buckets = (MapeoConLista<K,V>[]) new MapeoConLista[13];
		for(int i=0; i<maximo; i++) {
		  buckets[i]= new MapeoConLista<K,V>();
		}
	}
	private int hash(K key) {
		return (key.hashCode() % maximo);
	}
	
	private void rehash() throws InvalidKeyException{
		maximo = siguientePrimo(2*maximo);
		@SuppressWarnings("unchecked")
		MapeoConLista<K,V>[] mapeoNuevo =(MapeoConLista<K,V>[]) new MapeoConLista[maximo];
		for(int i=0; i<maximo;i++) {
			mapeoNuevo[i]=new MapeoConLista<K,V>();
		}
		for(Entry<K,V> e : this.entries()) {
			mapeoNuevo[hash(e.getKey())].put(e.getKey(), e.getValue());
		}
		buckets=mapeoNuevo;
	}
	
	private int siguientePrimo(int x) {
		if(maximo<=1) {
			return 2;
		}
		int retorno=maximo;
		boolean encontre =false;
		while(!encontre) {
			retorno++;
			if(esPrimo(retorno))
				encontre=true;
			
		}
		return retorno;
	}
	
	private boolean esPrimo(int tamaño) {
		if(tamaño<=1) {
			return false;
		}
		if(tamaño<=3) {
			return true;
		}
		if (tamaño%2==0|| tamaño%3==0) {
			return false;
		}
		 for (int i = 5; i * i <= tamaño; i = i + 6) {
	            if (tamaño % i == 0 || tamaño % (i + 2) == 0) {
	              return false;
	            }
		 }
	     return true;
		
	}
	
	public int size() {
		
		return tamaño;
	}
	
	public boolean isEmpty() {
		
		return (tamaño==0);
	}

	public V get(K key) throws InvalidKeyException {
		if(key==null) {
			  throw new InvalidKeyException("La key no puede ser nula.");	
		}
		return buckets[hash(key)].get(key);
	}

	public V put(K key, V value) throws InvalidKeyException {
		if(key==null) {
			  throw new InvalidKeyException("La key no puede ser nula.");	
		}
		V retorno =buckets[hash(key)].put(key, value);
		if(retorno==null) {
			tamaño++;
		}
		if((float)tamaño / maximo>=0.9f) {
			rehash();
		}
		return retorno;
	}
	
	public V remove(K key) throws InvalidKeyException {
		if(key==null) {
		  throw new InvalidKeyException("La key no puede ser nula.");	
		}
		V retorno= buckets[hash(key)].remove(key);
		if(retorno!=null) {
		  tamaño--;
		}
		return retorno;
	}
	
	public Iterable<K> keys() {
		PositionList<K> l =  new ListaDE<K>();
		for(int i = 0; i < buckets.length; i++) {
			if(!buckets[i].isEmpty())
				for(K e : buckets[i].keys())
					l.addLast(e);
		}
		
		return l;
			
		
	}
	
	public Iterable<V> values() {
		PositionList<V> l = new ListaDE<V>();
		for(int i = 0; i < buckets.length; i++) {
			if(!buckets[i].isEmpty())
				for(V e : buckets[i].values())
					l.addLast(e);
		}
		
		return l;
	}
	
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K,V>> l = new ListaDE<Entry<K,V>>();
		for(int i = 0; i < buckets.length; i++) {
			if(!buckets[i].isEmpty())
				for(Entry<K,V> e : buckets[i].entries())
					l.addLast(e);
		}
		
		return l;
	}
	
	
}