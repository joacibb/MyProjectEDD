package TDAPila;

import Excepciones.EmptyStackException;

public class PPE {

	public static PilaEnlazada<Integer> PiEn(PilaEnlazada<PilaEnlazada> p3) throws EmptyStackException{
		
		PilaEnlazada<Integer> Pout = new PilaEnlazada<Integer>();
		PilaEnlazada<Integer> Temp = new PilaEnlazada<Integer>();
		
		while(!p3.isEmpty()) {
			Temp = p3.top();	
			while(!Temp.isEmpty()){
				Pout.push(Temp.pop());
			}		
			p3.pop();
		}
		return Pout;
	}
	
	public static void main(String [] args) throws EmptyStackException {
		PilaEnlazada<Integer> p1 = new PilaEnlazada<Integer>();
		PilaEnlazada<Integer> p2 = new PilaEnlazada<Integer>();
		
		p1.push(1);
		p1.push(2);
		p1.push(3);
		p2.push(4);
		p2.push(5);
		p2.push(6);
		//Pila de Pilas de enteros
		
		PilaEnlazada<PilaEnlazada> p3 = new PilaEnlazada<PilaEnlazada>();
		p3.push(p1);
		p3.push(p2);
		
		PilaEnlazada<Integer> R = PiEn(p3);

		while(!R.isEmpty()) {
			System.out.println("|"+R.pop()+"|");
			System.out.println("___");}
		}
	
}
