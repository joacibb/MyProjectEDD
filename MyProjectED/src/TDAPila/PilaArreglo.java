package TDAPila;

import Excepciones.EmptyStackException;

public class PilaArreglo <E> implements Stack<E>{

	protected int tamaño;
	protected E [] datos;
	public PilaArreglo(int max) {
		datos= (E[]) new Object [max];
		tamaño=0;
	}
	@Override
	public void push(E item) {
		datos[tamaño]=item;
		tamaño++;
	}

	@Override
	public boolean isEmpty() {
		return tamaño==0;
	}

	@Override
	public E pop() throws EmptyStackException {
		if(tamaño==0) throw new EmptyStackException("Pila vacía");
			E aux = datos[tamaño-1];
			datos[tamaño-1]=null;
			tamaño--;
			return aux;
	}

	@Override
	public E top() throws EmptyStackException {
		if(tamaño==0) throw new EmptyStackException("Pila vacia");
		
		return datos[tamaño-1];
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return tamaño;
	}
	
	public void invertir() {
		int i = 0;  int f = tamaño-1;
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
		for(int i=0;i<tamaño;i++) {
		st+=datos[i];
		}
	return st;
	}
}
