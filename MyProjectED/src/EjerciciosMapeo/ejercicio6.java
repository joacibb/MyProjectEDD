package EjerciciosMapeo;

import Excepciones.InvalidKeyException;
import TDALista.Position;
import TDAMapeo.Entry;
import TDAMapeo.Map;
import TDAMapeo.MapeoConLista;
import TDAMapeo.MapeoHashAbierto;

public class ejercicio6 {

	public static <V,K> Map<V,K> CMInverso(Map<K,V> d) {
		
		Map<V,K> m1 = new MapeoConLista<V,K>(); //C1
		
		for (Entry<K,V> i : d.entries()) {  //N
			try {
				m1.put(i.getValue(), i.getKey()); //C2
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return m1;
	}
	
	public static void main (String [] args) {
		Map<Integer,String> d1 = new MapeoConLista<Integer,String>();
		try {
			d1.put(1, "Lucre");
			d1.put(3, "Joa");
			d1.put(5, "Marime");
			d1.put(7, "Juli");
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Entry<Integer, String> element : d1.entries()) {
			System.out.println("<"+element.getKey()+","+element.getValue()+">");
		}
		System.out.println("--------");
		
		Map<String,Integer> d2 = new MapeoConLista<String,Integer>();
		d2=CMInverso(d1);
		
		for(Entry<String, Integer> element : d2.entries()) {
			System.out.println("<"+element.getKey()+","+element.getValue()+">");
		}
	}
	
}
