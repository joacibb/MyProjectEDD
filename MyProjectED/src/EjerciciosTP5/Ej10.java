package EjerciciosTP5;

import Excepciones.InvalidKeyException;
import TDADiccionario.DiccionarioConLista;
import TDADiccionario.DiccionarioHashAbierto;
import TDADiccionario.Dictionary;
import TDAMapeo.Entry;
import TDAMapeo.Map;
import TDAMapeo.MapeoConLista;
import TDAMapeo.MapeoHashAbierto;

public class Ej10 {
	/**
	 * 
	 * @param <K> clave
	 * @param <V> valor
	 * @param d diccionario pasado por parametro
	 * @return
	 */
		public static <K,V> Dictionary<K,V> acomodar(Dictionary<K,V> d){
			
			Map<K,V> maux = new MapeoHashAbierto<K,V>();
			
			for (Entry<K,V> i : d.entries()) {
				try {
					maux.put((i.getKey()), i.getValue());
				} catch (InvalidKeyException e) {
					e.printStackTrace();
				}
			}
			
			Dictionary<K,V> dout = new DiccionarioConLista<K,V>();
			for (Entry<K,V> p : maux.entries()) {
				try {
					dout.insert(p.getKey(), p.getValue());
				} catch (InvalidKeyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return dout;
		}

		public static void main(String [] args) {
			Dictionary<Integer, String> d1 = new DiccionarioConLista();
			try {
				d1.insert(1,"a");
				d1.insert(2,"b");
				d1.insert(3, "a");
				d1.insert(2,"c");
				d1.insert(1, "d");
				d1.insert(4, "b");
				
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("--------Valores insertados--------");
			
			for (Entry<Integer,String> i : d1.entries()) {
				System.out.print("("+i.getKey()+","+i.getValue()+")");
				
			}
			System.out.println("");
			System.out.println("------------Devueltos-------------");	
			try {
				d1 = acomodar(d1);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for (Entry<Integer,String> i : d1.entries()) {
				System.out.print("("+i.getKey()+","+i.getValue()+")");}
				
			System.out.println("");
			System.out.println("----------------------------------");	
			
			try {
				d1.eliminarTodos(3);
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//EJERCICIO DE PARCIAL
			System.out.println("");
			System.out.println("----------------------------------");	
			Dictionary<Integer, String> d3 = new DiccionarioHashAbierto();
			try {
				d3.insert(1,"a");
				d3.insert(2,"b");
				d3.insert(3, "a");
				d3.insert(2,"c");
				d3.insert(1, "d");
				d3.insert(4, "b");
				
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (Entry<Integer,String> i : d3.entries()) {
				System.out.print("("+i.getKey()+","+i.getValue()+")");}
				
			System.out.println("");
			System.out.println("----------------------------------");	
			try {
				for (String i : d3.eliminarTodos(1)) {
					System.out.print(i);}
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
}
