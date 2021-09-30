package TDAMapeo;

public class Entrada<K,V> implements Entry<K,V>{
	private K clave;
	private V valor;
	
	public Entrada(K c, V v){
		clave=c;
		valor=v;
	}
	
	@Override
	public K getKey() {
		// TODO Auto-generated method stub
		return clave;
	}

	@Override
	public V getValue() {
		// TODO Auto-generated method stub
		return valor;
	}

	public void setKey(K k) {
		clave=k;
	}
	
	public void setValue(V v) {
		valor=v;
	}
	
	public String toString() {
		return "("+getKey()+","+getValue()+")";
	}
}
