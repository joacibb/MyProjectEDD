package EjerciciosLista;

import Excepciones.BoundaryViolationException;
import Excepciones.EmptyListException;
import Excepciones.InvalidPositionException;
import TDALista.Dnode;
import TDALista.ListaDE;
import TDALista.ListaSE;
import TDALista.Nodo;
import TDALista.Position;

public class ejpalindroma{
	
public static boolean esPalindroma(ListaDE<String> l1) throws EmptyListException, InvalidPositionException, BoundaryViolationException {
		return esPalindromaR(l1, l1.first(),l1.last());

		}
public static boolean esPalindromaR(ListaDE<String> l1,Position<String> primero,Position<String> ultimo) throws InvalidPositionException, BoundaryViolationException {
	
	boolean sea=true;
	Dnode<String> p = (Dnode<String>) primero;
	Dnode<String> u = (Dnode<String>) ultimo;
	
		if(p==null || p.getSiguiente()==null)
			sea=true;
		else {
		if(!(p.element().equals(u.element()))) {
			sea=false;}
		else {
			try {
				sea=esPalindromaR(l1,p.getSiguiente(),u.getAnterior());
			} catch (InvalidPositionException | BoundaryViolationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
		}
				
	return sea; //CAMBIAR EL RETURN
}

public static void main (String [] args) throws InvalidPositionException, BoundaryViolationException {
	ListaDE<String> l1 = new ListaDE<String>();
	l1.addFirst("a");
	l1.addFirst("d");
	l1.addFirst("a");
	l1.addFirst("n");
	l1.addFirst("a");
	try {
		System.out.println("Es palindroma?: "+esPalindroma(l1));
	} catch (EmptyListException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

}