package EjerciciosLista;

import Excepciones.BoundaryViolationException;
import Excepciones.EmptyListException;
import Excepciones.InvalidPositionException;
import TDALista.ListaDE;
import TDALista.Position;
import TDALista.PositionList;

public class Comp {

		public static boolean Comparar(PositionList<Integer> l1, PositionList<Integer> l2) {
			boolean cumple = true;
			try {
				Position<Integer> l1Frente = l1.first();
				Position<Integer> l1Cola = l1.last();
				Position<Integer> l2puntero = l2.first();
				
				if(l2.size()*2 % l1.size() != 0)
					cumple = false;
				else {	
					while(cumple && l2puntero != l2.last()) {
						if(l1Frente.element().equals(l2puntero.element()) && l1Cola.element().equals(l2puntero.element())) {
							l1Frente = l1.next(l1Frente);
							l1Cola = l1.prev(l1Cola);
							if(l2puntero.equals(l2.last()))
								l2puntero=null;
							else
								l2puntero = l2.next(l2puntero);
							}
						else {
							cumple = false;
							System.out.println("No cumple");
							}
					}
				}
				
			}catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        return cumple;
		}
		
		 public static void main(String[] args) {
		        PositionList<Integer> l1 = new ListaDE<Integer>();
		        PositionList<Integer> l2 = new ListaDE<Integer>();
		        
		        // <1,2,3,4,4,3,2,1>

		        l1.addLast(1);
		        l1.addLast(2);
		        l1.addLast(3);
		        l1.addLast(4);
		        l1.addLast(4);
		        l1.addLast(2);
		        l1.addLast(1);
		        //l1.addLast(3);
		        
		        l2.addLast(1);
		        l2.addLast(2);
		        l2.addLast(3);
		        l2.addLast(4);
		        
		        
		        System.out.println(Comparar(l1,l2));//Espero True
		    }
}