package TDAPila;

import Excepciones.EmptyQueueException;
import Excepciones.EmptyStackException;
import TDACola.ColaEnlazada;

public class LectorCaracteres {

	public static boolean LectorCaracteres(PilaEnlazada<Character> c1) throws EmptyStackException, EmptyQueueException {
		
		ColaEnlazada<Character> col1 = new ColaEnlazada<Character>();
		ColaEnlazada<Character> col2 = new ColaEnlazada<Character>();
		PilaEnlazada<Character> pilAux = new PilaEnlazada<Character>();
		
		boolean cumple=true;
		
		//encolo la primera parte en una cola
		while(c1.top()!='z') 
			col1.enqueue(c1.pop());
		
		//Descarto la z
			c1.pop();
		
			//Encolo la segunda parte en otra
		while(c1.top()!='x')
			col2.enqueue(c1.pop());
		
		//Si no termine y hay una X (es decir que sigue) descarto la X.
			if((!c1.isEmpty())&&(c1.top()=='x'))
				c1.pop();
			//si sigue habiendo chars
		while(!c1.isEmpty())
			if(c1.top()=='z')
				c1.pop();
			else {
				
			if(c1.top()!='x')
				pilAux.push(c1.pop());
			else {
				c1.pop();
				cumple=LectorCaracteres(c1);}
			}	
		while(!col1.isEmpty()&&cumple) {
			int resCompare = Character.compare(col1.dequeue(), pilAux.pop());
			if(resCompare!=0) {
				cumple=false;
		}
			}
		while(!col2.isEmpty()&&cumple) {
			int resCompare = Character.compare(col2.dequeue(), pilAux.pop());
			if(resCompare!=0) {
				cumple=false;
			}
		}
		
	
			if(!pilAux.isEmpty()||!col1.isEmpty()||!col2.isEmpty()) {
				cumple=false;
			}
			else
				cumple=true;
			
			
		return cumple;
	}

	public static void main (String a[]){
		
		PilaEnlazada<Character> entrada = new PilaEnlazada<Character>();
		
		entrada.push('b');
		entrada.push('z');
		entrada.push('b');
		entrada.push('b');
		entrada.push('x');
		entrada.push('b');
		entrada.push('b');
		entrada.push('z');
		entrada.push('b');
		entrada.push('x');
		entrada.push('b');
		entrada.push('z');
		entrada.push('b');
		entrada.push('b');
		entrada.push('x');
		entrada.push('b');
		entrada.push('b');
		entrada.push('z');
		entrada.push('b');

		boolean resultado;
		try {
			resultado = LectorCaracteres(entrada);
			System.out.println("¿Es valida?: "+resultado);
		} catch (EmptyStackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EmptyQueueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}