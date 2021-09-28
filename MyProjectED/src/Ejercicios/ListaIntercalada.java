package Ejercicios;

import Excepciones.BoundaryViolationException;
import Excepciones.EmptyListException;
import Excepciones.InvalidPositionException;
import TDALista.ListaSE;
import TDALista.Nodo;
import TDALista.Position;
import TDALista.PositionList;

public class ListaIntercalada{
	
	private static PositionList<Integer> comparar(PositionList<Integer> l1, PositionList<Integer> l2, PositionList<Integer> nueva){
		try {
			if(l1.first().element()>l2.first().element()) {
				nueva.addLast(l2.first().element());
				l2.remove(l2.first());
			}
			else
				if(l1.first().element()<l2.first().element()) {
				nueva.addLast(l1.first().element());
				l1.remove(l1.first());}
				else {
					nueva.addLast(l1.first().element());
					l1.remove(l1.first());
					l2.remove(l2.first());
				}
		} catch (EmptyListException | InvalidPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nueva;
	}
	
	public static PositionList<Integer> intercalar(PositionList<Integer> l1, PositionList<Integer> l2){
		PositionList<Integer> l3 = new ListaSE<Integer>();
		while(!l1.isEmpty()&&!l2.isEmpty())
			comparar(l1,l2,l3);
	try {	if(l1.isEmpty()&&!l2.isEmpty())
			while(!l2.isEmpty()) {
				l3.addLast(l2.first().element());
				l2.remove(l2.first());
			}
		
		if(l2.isEmpty()&&!l1.isEmpty()) {
			l3.addLast(l1.first().element());
			l1.remove(l1.first());
		}
	} catch (InvalidPositionException | EmptyListException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return l3;
	}
	
	public static void Elimina_apariciones( Integer x, ListaSE<Integer> l ) {
		
		for(Position<Integer> p : l.positions())
			if(p.element().equals(x))
				try {
					l.remove(p);
				} catch (InvalidPositionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	
	public static PositionList<Integer> Invertir(PositionList<Integer> l) {
		PositionList<Integer> aux = new ListaSE();
		while (!l.isEmpty()) {
			try {
				aux.addLast(l.remove(l.last()));
			} catch (InvalidPositionException | EmptyListException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return aux;
	}
	
	public static <E> PositionList<E> InvertirRecursivo(PositionList<E> listaEntrada) throws EmptyListException{
        PositionList<E> listaInv = new ListaSE<E>();
        
        if(!listaEntrada.isEmpty()) {//Caso base, lista vacia
            invertirList(listaEntrada,listaInv,listaEntrada.first(),listaEntrada.last());
        }else { 
            throw new EmptyListException("Lista vacia 5B");
        }
        return listaInv;    
    }

    private static <E> void invertirList(PositionList<E> LE, PositionList<E> LI, Position<E> primero,Position<E> ultimo) {
        if(primero == ultimo) {
            LI.addLast(primero.element());
        }else {
            LI.addLast(ultimo.element());
            try {
                invertirList(LE,LI,primero,LE.prev(ultimo));
            } catch (InvalidPositionException | BoundaryViolationException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
	
	public static void main(String a[]) {
		PositionList<Integer> l1=new ListaSE();
		PositionList<Integer> l2=new ListaSE();
		PositionList<Integer>l3;
		l2.addLast(1);
		l2.addLast(3);
		l2.addLast(5);
		l2.addLast(7);
		
		l1.addLast(2);
		l1.addLast(4);
		l1.addLast(6);
		l1.addLast(8);
		
		ListaSE<Integer> l4 = new ListaSE();
		l4.addLast(1);
		l4.addLast(3);
		l4.addLast(2);
		l4.addLast(3);
		
	System.out.println("TEST INTERCALAR");
		l3=intercalar(l1,l2);
		
		for(int item:l3) {
			System.out.println(item);
		}
		System.out.println("TEST INVERTIR");
		try {
			l3=InvertirRecursivo(l3);
		} catch (EmptyListException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int item:l3) {
			System.out.println(item);
		}
		
		System.out.println("TEST ELIMINA APARICIONES");
		Elimina_apariciones(3,l4);

		for(int item:l4) {
			System.out.println(item);
		}
	}
}