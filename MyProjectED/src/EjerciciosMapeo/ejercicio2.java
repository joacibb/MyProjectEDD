package EjerciciosMapeo;

import Excepciones.InvalidKeyException;
import TDALista.ListaDE;
import TDALista.ListaSE;
import TDALista.Position;
import TDALista.PositionList;
import TDAMapeo.Entrada;
import TDAMapeo.Entry;
import TDAMapeo.Map;
import TDAMapeo.MapeoConLista;

public class ejercicio2{
	/*
	 * Se  poseen  dos  correspondencias  (mapeos)  M1  y  M2  cuyas  entradas  tienen  por  dominio  un 
	 * número  de  libreta,  y  por  imagen  la  nota  de  una  materia  (de  0  a  10).  Ambos  mapeos  están 
	 * implementados con una lista simplemente enlazada y sin celda de encabezamiento.
	 * */
	
	public ejercicio2() {}
	/**
	 * Escriba un método que reciba los dos mapeos M1 y M2 y devuelva una lista L con aquellos 
	 * elementos  E1  de  M1  y  E2  de  M2  que  coincidan  en  el  valor  del  dominio,  pero  tengan  una 
	 * imagen  diferente. Por ejemplo, si E1= (LU: 29303, Nota: 8) pertenece a M1 y 
	 * E2= (LU:29303, Nota: 7) pertenece a M2, entonces E1 y E2 serán puestos en L. 
	 * @param m1
	 * @param m2
	 * @return lista de elementos con misma clave pero distinto valor
	 */
	public PositionList<Entry<Integer,Integer>> incisioA(Map<Integer,Integer> m1, Map<Integer,Integer> m2){
		//crea iterable de m1
		ListaDE<Entry<Integer,Integer>> list = new ListaDE<Entry<Integer,Integer>>();
		Integer valorM2 = null;
		Iterable<Entry<Integer,Integer>> itm1 = m1.entries();
		/*
		 * para cada entrada de m1, buscar si existe una entrada con clave igual en m2
		 * si existe, si los valores son distintos entonces los agrega a list*/
		
		
		for(Entry<Integer, Integer> c : itm1) {  //para cada entrada de m1
			try {
				
				valorM2 = m2.get(c.getKey()); //buscar si existe una entrada con clave igual en m2
				if(valorM2!=null && valorM2 != c.getValue()) { //si existe, si los valores son distintos
					
					list.addLast(c);											    //lo agrega a list
					list.addLast(new Entrada<Integer,Integer>(c.getKey(), valorM2));//lo agrega a list
					
				}
				
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			}
			valorM2 = null; //reset
		}
		return list;
	}
	
	public static void main(String[] args) {
		Map<Integer,Integer> m1 = new MapeoConLista<Integer,Integer>();
		Map<Integer,Integer> m2 = new MapeoConLista<Integer,Integer>();
		PositionList<Entry<Integer,Integer>> resultado = new ListaDE<Entry<Integer,Integer>>();
		ejercicio2 k = new ejercicio2();
		try {
			m1.put(123, 0);
			m1.put(456, 5);
			m1.put(789, 10);
			m1.put(1110, 9);
			m2.put(123, 1);
			m2.put(456, 5);
			m2.put(789, 100);
			
			resultado = k.incisioA(m1, m2);
			Iterable<Position<Entry<Integer, Integer>>> it = resultado.positions();
			for(Position<Entry<Integer, Integer>> element : it) {
				System.out.println("<"+element.element().getKey()+","+element.element().getValue()+">");
			}
			
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
	}

}