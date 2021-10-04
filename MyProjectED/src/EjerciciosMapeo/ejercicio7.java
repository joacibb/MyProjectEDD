package EjerciciosMapeo;

import Excepciones.InvalidKeyException;
import TDAMapeo.Entry;
import TDAMapeo.Map;
import TDAMapeo.MapeoConLista;

public class ejercicio7 {

	public static <K,V> boolean contiene(Map<K,V> m1, Map<K,V> m2) {
		
		
		boolean contiene=true;   //c1
		boolean tamaño = true;
		
		if(m1.size()>m2.size()) { //c2
			contiene=false; //c3
			tamaño=false; //c4
		}
		if(tamaño) {
		for (Entry<K,V> i : m1.entries()) {  // n
			try {
				if((!(m2.get(i.getKey())!=null))&&contiene) { //n porque recorro todo m2 para saber si existe i.getKey
					contiene=false;				}				//c5
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
		
		return contiene; //c6
	}
	
	/*
	 * Tcontiene = c1+c2+c3+c4+n(n+c5)+c6
	 * Tcontiene = c+n^2
	 * O(Tcontiene)n^2
	 */

	public static void main(String [] args) {
		Map<Integer,Integer> m1 = new MapeoConLista<Integer,Integer>();
		Map<Integer,Integer> m2 = new MapeoConLista<Integer,Integer>();
		try {
			m1.put(5,2);
			m1.put(2,5);
			m1.put(3,4);
			m1.put(1,2);
			m1.put(4,7);
			
			m2.put(7,2);
			m2.put(2,5);
			m2.put(3,4);
			m2.put(1,2);
			m2.put(4,7);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Coinciden todas?: "+contiene(m1,m2));
	} 
	
}
