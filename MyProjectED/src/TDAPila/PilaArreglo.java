package TDAPila;

import Excepciones.EmptyStackException;

public class PilaArreglo <E> implements Stack<E>{

	protected int tamano;
	protected E [] datos;
	public PilaArreglo(int max) {
		datos= (E[]) new Object [max];
		tamano=0;
	}
	@Override
	public void push(E item) {
		datos[tamano]=item;
		tamano++;
	}

	@Override
	public boolean isEmpty() {
		return tamano==0;
	}

	@Override
	public E pop() throws EmptyStackException {
		if(tamano==0) throw new EmptyStackException("Pila vacía");
			E aux = datos[tamano-1];
			datos[tamano-1]=null;
			tamano--;
			return aux;
	}

	@Override
	public E top() throws EmptyStackException {
		if(tamano==0) throw new EmptyStackException("Pila vacia");
		
		return datos[tamano-1];
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return tamano;
	}
	
	public void invertir() {
		int i = 0;  int f = tamano-1;
		while(i<f) {
			intercambiar(i,f);
			i++;
			f--;
		}
	}
	
	public void intercambiar(int i, int f) {
	E aux = datos[i];
	datos[i]=datos[f];
	datos[f]=aux;
	}
	
	public String toString() {
		String st="";
		for(int i=0;i<tamano;i++) {
		st+=datos[i];
		}
	return st;
	}
}
