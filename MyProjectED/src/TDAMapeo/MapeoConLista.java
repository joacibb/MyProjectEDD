package TDAMapeo;
import java.util.Iterator;

import Excepciones.InvalidKeyException;
import Excepciones.InvalidPositionException;
import TDALista.ListaSE;
import TDALista.Position;
import TDALista.PositionList;


public class MapeoConLista<K,V> implements Map<K,V> {
	
	
	protected PositionList<Entrada<K, V>> m;

	public MapeoConLista() {
		m = new ListaSE<Entrada<K,V>>();
	}
	
	public int size() {
		return m.size();
	}

	public boolean isEmpty() {
		return m.isEmpty();
	}

	public V get(K key) throws InvalidKeyException {
		if(key == null) {
			throw new InvalidKeyException("se escribio una Key invalida");
		}
		
		Iterator<Position<Entrada<K, V>>> it = m.positions().iterator();
		Position<Entrada<K, V>> p;
		
		V retorno = null;
		
		while(it.hasNext() && retorno == null) {
			p = it.next();
			
			if(p.element().getKey().equals(key))
				retorno = p.element().getValue();
			
		}
		
		return retorno;
	}
	
	public V put(K key, V value) throws InvalidKeyException {
		if(key == null)
			throw new InvalidKeyException("se escribio una Key invalida");
		
		Iterator<Position<Entrada<K, V>>> it = m.positions().iterator();
		Position<Entrada<K, V>> p;
		
		V retorno = null;
		
		while(it.hasNext() && retorno == null) {
			p = it.next();
			
			if(p.element().getKey().equals(key)) {
				retorno = p.element().getValue();
				p.element().setValue(value);
			}
		}
		
		if(retorno == null)
			m.addLast(new Entrada<K, V>(key, value));
		
		return retorno;
	}
	public V remove(K key) throws InvalidKeyException {
		if(key == null)
			throw new InvalidKeyException("se  inserto una Key invalida");
		
		Iterator<Position<Entrada<K, V>>> it = m.positions().iterator();
		Position<Entrada<K, V>> p;
		
		V ret = null;
		try {
			while(it.hasNext() && ret == null) {
				p = it.next();
				
				if(p.element().getKey().equals(key)) {
					ret = p.element().getValue();
					m.remove(p);
				}
			}
		} catch(InvalidPositionException e) {}
		
		return ret;
	}

	public Iterable<K> keys() {
         PositionList<K> l = new ListaSE<K>();
		
		for(Position<Entrada<K, V>> p : m.positions())
			l.addLast(p.element().getKey());
		
		return l;
		}

	public Iterable<V> values() {
        PositionList<V> l = new ListaSE<V>();
		
		for(Position<Entrada<K, V>> p : m.positions())
			l.addLast(p.element().getValue());
		
		return l;
	    }

	public Iterable<Entry<K,V>> entries() {
        PositionList<Entry<K, V>> l = new ListaSE<Entry<K, V>>();
		
		for(Position<Entrada<K, V>> p : m.positions()){
			l.addLast((Entry<K, V>) p.element());
		}
		
		return l;
	}
	
	public String toString() {
		String s = "";
		
		for(Position<Entrada<K, V>> p : m.positions())
			s += p.element().toString() + " ";
		
		return s;
	}

}