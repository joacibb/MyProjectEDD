package TDACola;

import Excepciones.EmptyQueueException;
import TDAPila.Nodo;
import TDAPila.PilaEnlazada;
import TDAPila.Stack;

public class ColaEnlazada<E> implements Queue<E> {
protected int cantElem;
protected Nodo<E> head,tail;

public ColaEnlazada() {
	head = tail = null;
	cantElem=0;
}

@Override
public int size() {
	return cantElem;
}

@Override
public boolean isEmpty() {
	return cantElem==0;
}

@Override
public E front() throws EmptyQueueException {
	E salida;
	if(isEmpty()) {
		throw new EmptyQueueException("Cola vacía");
	}
	else {
		salida = head.getElemento();
		return salida;
	}
}

@Override
public void enqueue(E element) {
	Nodo<E> nodo = new Nodo<E>(element);
	nodo.setElemento(element);
	nodo.setSiguiente(null);
	if(cantElem==0)
		head = nodo;
	else
		tail.setSiguiente(nodo);
	tail=nodo;
	cantElem++;
}

@Override
public E dequeue() throws EmptyQueueException {
	if(cantElem==0) throw new EmptyQueueException("La cola está vacía");
	
	E temp = head.getElemento();
	head = head.getSiguiente();
	cantElem--;
	if(cantElem==0)
		tail=null;
	return temp;
}

}
