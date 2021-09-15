package TDACola;

import Excepciones.EmptyQueueException;

/**
 * TDAQueue, implementacion: ArrayQueue.
 */

public class ColaConArregloCircular<E> implements Queue<E>{
	protected E[] cola;
	protected int front,rear,N,size;
	
	/**
	 * Se inicializa la coleccion vacia con sus atributos correspondientes.
	 */
	public ColaConArregloCircular() {
		cola=(E[])new Object[25];
		front=rear=size=0;
		N=cola.length;
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size==0;
	}

	@Override
	public E front() throws EmptyQueueException {
		if(size==0)
			throw new EmptyQueueException("Cola vacia.");
		
		return cola[front];
	}
	
	/**
	 * Se encarga de redimensionar la coleccion manteniendo los elementos actuales.
	 */
	private void resize() {
		int n= front;
		E[] aux=(E[])new Object[2*N];
		
		for(int i=0; i<size; i++) {
			aux[i]= cola[n];
			n= (n+1) % N;
		}
		
		N= aux.length;
		rear= size;
		front= 0;
		cola= aux;
	}
	
	@Override
	public void enqueue(E element) {
		if(size == (N-1))
			resize();
		
		cola[rear]= element;
		rear= (rear+1) % N;
		size++;
	}

	@Override
	public E dequeue() throws EmptyQueueException {
		if(size==0)
			throw new EmptyQueueException("Cola vacia.");
		
		E ret= cola[front];
		cola[front]=null;
		front= (front+1) % N;
		size--;
		
		return ret;
	}

}