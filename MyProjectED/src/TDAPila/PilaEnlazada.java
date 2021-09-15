package TDAPila;

import Excepciones.EmptyStackException;

public class PilaEnlazada<E> implements Stack<E> {
	protected Nodo<E> head;
	protected int tamaño;
	
	public PilaEnlazada(){
		head=null;
		tamaño=0;
	}
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return tamaño;
	}

	@Override
	public boolean isEmpty() {
		return (tamaño==0);
	}

	@Override
	public E top() throws EmptyStackException {
		if(tamaño==0) {
			throw new EmptyStackException("Pila vacía");
		}
		else
			return head.getElemento();
			
	}

	@Override
	public void push(E element) {
		Nodo<E> aux = new Nodo<E>(element,head);
		head = aux;
		tamaño++;
		
	}

	@Override
	public E pop() throws EmptyStackException {
		if(tamaño==0) {
			throw new EmptyStackException("Pila vacia");
		}
		else
		{
			E aux = head.getElemento();
			head.getSiguiente();
			tamaño--;
			return aux;
		}
	}

}
