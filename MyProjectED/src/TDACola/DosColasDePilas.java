package TDACola;

import Excepciones.EmptyQueueException;

public class DosColasDePilas{
	/**
	 * 
	 * @param cin1 Cinta 1 que ingresa por parametro
	 * @param cin2 Cinta 2 que ingresa por parametro
	 * @return cinta Cout, union de ambas cintas ordenada de menor a mayor
	 * @throws EmptyQueueException La pila esta vacia
	 */
	public static ColaEnlazada<String> ordenarPRO(ColaEnlazada<String> cin1, ColaEnlazada<String> cin2) throws EmptyQueueException{
		
		ColaEnlazada<String> Cout = new ColaEnlazada<String>();
		int resCompare = 0;
		while(!cin1.isEmpty() && !cin2.isEmpty()) {
			resCompare=(cin1.front().compareTo(cin2.front()));
			if (resCompare>0)
				Cout.enqueue(cin2.dequeue());
			else
				Cout.enqueue(cin1.dequeue());
		}
		
		if(cin1.isEmpty()) 
			while(!cin2.isEmpty()) {
				Cout.enqueue(cin2.dequeue());
			}
		else 
			while(!cin1.isEmpty()) {
			Cout.enqueue(cin1.dequeue());
			}
		
		return Cout;
	}
	
	public static void main(String a[]) throws EmptyQueueException {
        ColaEnlazada<String> cola1 = new ColaEnlazada<String>();
        ColaEnlazada<String> cola2 = new ColaEnlazada<String>();
        ColaEnlazada<String> SALIDA = new ColaEnlazada<String>();

        cola1.enqueue("a");
        cola1.enqueue("d");
        cola1.enqueue("h");

        cola2.enqueue("f");
        cola2.enqueue("r");
        cola2.enqueue("z");

       SALIDA = ordenarPRO(cola1,cola2);

       while (!SALIDA.isEmpty()) {
           System.out.println(" |"+SALIDA.dequeue()+"|");
           System.out.println(" ---");
       }
    }

}
