package TDADiccionario;

import TDALista.*;
import TDAMapeo.Entry;
import java.util.Iterator;
import Excepciones.InvalidEntryException;
import Excepciones.InvalidKeyException;
import Excepciones.InvalidPositionException;

public class DiccionarioConLista<K,V> implements Dictionary<K,V>{
	
	protected PositionList<Entry<K,V>> D;	
	
	private class Entrada<Ke,Va> implements Entry<Ke,Va>{
		private Ke clave;
		private Va value;
		public Entrada(Ke clave, Va value) {
			this.clave = clave;
			this.value = value;
		}
		
		@Override
		public Ke getKey() {
			return clave;
		}

		@Override
		public Va getValue() {
			return value;
		}
		
		public String toString() {
			return "("+clave+","+value+")";
		}
		
		public void setKey(Ke k) {
			clave = k;
		}
		
		public void setValue(Va v) {
			value = v;
		}
		
	}
	
	public DiccionarioConLista() {
		D = new ListaDE<Entry<K,V>>();
	}

	@Override
	public int size() {
		return D.size();
	}

	@Override
	public boolean isEmpty() {
		return D.isEmpty();
	}

	@Override
	public Entry<K,V> find(K key) throws InvalidKeyException {
		if(key == null) throw new InvalidKeyException("Clave Inv·lida");
		Iterator<Position<Entry<K,V>>> it = D.positions().iterator();
		Position<Entry<K,V>> pos;
		boolean encontre = false;
		Entry<K,V> toRet = null;
		while(it.hasNext() && !encontre) {
			pos = it.next();
			if(pos.element().getKey().equals(key)) {
				toRet = pos.element();
				encontre = true;
			}
		}
		
		return toRet;
	}

	@Override
	public Iterable<Entry<K,V>> findAll(K key) throws InvalidKeyException {
		if(key == null) throw new InvalidKeyException("Clave inv·lida");
		PositionList<Entry<K,V>> toRet = new ListaDE<Entry<K,V>>();
		for(Position<Entry<K,V>> ent : D.positions()) {
			if(ent.element().getKey().equals(key))
				toRet.addLast(ent.element());
		}
		
		return toRet;
	}

	@Override
	public Entry<K,V> insert(K key, V value) throws InvalidKeyException {
		if(key == null) throw new InvalidKeyException("Clave inv·lida");
		Entry<K,V> nueva = new Entrada<K,V>(key,value);
		D.addLast(nueva);
		return nueva;
	}

	@Override
	public Entry<K,V> remove(Entry<K,V> e) throws InvalidEntryException {
		Entry<K,V> toRet = null;
		try {
			boolean encontre = false;
			Iterator<Position<Entry<K,V>>> it = D.positions().iterator();
			Position<Entry<K,V>> p;
			while(it.hasNext() && !encontre) {
				p = it.next();
				if(p.element().equals(e)) {
					encontre = true;
					toRet = p.element();
					D.remove(p);
				}
				
			}
			
			if(!encontre) throw new InvalidEntryException("Entrada inv·lida, no pertenece al diccionario");
		}
		catch(InvalidPositionException err) {
			err.printStackTrace();
		}
		return toRet;
	}

	@Override
	public Iterable<Entry<K,V>> entries() {
		return D;
	}

	@Override
	public Iterable<V> eliminarTodos(K key) throws InvalidKeyException {
		// TODO Auto-generated method stub
		return null;
	}

}
