package TDAPila;

import Excepciones.EmptyStackException;

public class PilaEnlazada<E> implements Stack<E> {
	protected Nodo<E> head;
	protected int size;
	
	public PilaEnlazada() {
		head = null;
		size = 0;
	}
	
	public void push(E item) {
		head = new Nodo<E>(item,head);
		size++;
	}

	public boolean isEmpty() {
		return head == null;
	}

	public E pop() throws EmptyStackException {
		if (isEmpty())
			throw new EmptyStackException("Pila Vacia.");
		else {
			E aux = head.getElemento();
			head = head.getSiguiente();
			size--;
			return aux;
		}
	}

	public E top() throws EmptyStackException {
		if (isEmpty())
			throw new EmptyStackException("Pila Vacia.");
		else 
			return head.getElemento();
	}

	public int size() {
		return size;
	}
	
}

			
	
