package Ejercicios;

import Excepciones.InvalidPositionException;
import TDALista.ListaSE;
import TDALista.PositionList;

public class ejercicio10 {

	public static void main(String [] args) {
		PositionList<Integer> d1 = new ListaSE<Integer>();
		PositionList<Integer> d2 = new ListaSE<Integer>();
		
		d1.addLast(1);
		d1.addLast(2);
		d1.addLast(3);
		d1.addLast(4);
		
		d2.addLast(2);
		d2.addLast(3);
		
		System.out.println("d1: "+d1.toString());
		System.out.println("d2: "+d2.toString());
		
		try {
			d1.eliminar(d2);
		} catch (InvalidPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("d1 luego del remove: "+d1.toString());
	}
}
