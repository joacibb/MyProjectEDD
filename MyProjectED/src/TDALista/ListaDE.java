package TDALista;

import java.util.Iterator;

import Excepciones.BoundaryViolationException;
import Excepciones.EmptyListException;
import Excepciones.InvalidPositionException;
import TDAIterator.ElementIterator;

public class ListaDE<E> implements PositionList<E> {
	protected DNodo<E> header, trailer;
	protected int size;
	
	public ListaDE() {
		header = new DNodo<E>(null);
		trailer = new DNodo<E>(null);	
		header.setNext(trailer);
		trailer.setPrev(header);
		size=0;
	}
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size==0;
	}

	@Override
	public Position<E> first() throws EmptyListException {
		if(size==0)
			throw new EmptyListException("Lista vacia");
		return header.getNext();
	}

	@Override
	public Position<E> last() throws EmptyListException {
		if(size==0)
			throw new EmptyListException("Lista vacia");
		return trailer.getPrev();
	}

	@Override
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		if(size==0)
			throw new InvalidPositionException("La lista esta vacia");
		DNodo<E> n = checkPosition(p);
		if(n.getNext()==trailer)
			throw new BoundaryViolationException("No se puede pedir el siguiente del ultimo elemento de la lista");
		return n.getNext();
	}

	@Override
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		if(size==0)
			throw new InvalidPositionException("La lista esta vacia");
		DNodo<E> n = checkPosition(p);
		if(n.getPrev()==header)
			throw new BoundaryViolationException("No se puede pedir el anterior del primer elemento de la lista");
		return n.getPrev();
	}

	@Override
	public void addFirst(E element) {
		DNodo<E> sgte = header.getNext();
		DNodo<E> n = new DNodo<E>(header,sgte,element);
		header.setNext(n);
		sgte.setPrev(n);
		size++;
	}

	@Override
	public void addLast(E element) {
		DNodo<E> prev = trailer.getPrev();
		DNodo<E> n = new DNodo<E>(prev,trailer,element);
		trailer.setPrev(n);
		prev.setNext(n);
		size++;
	}

	@Override
	public void addAfter(Position<E> p, E element) throws InvalidPositionException {
		if(size==0)
			throw new InvalidPositionException("La lista esta vacia");
		DNodo<E> n = checkPosition(p);
		DNodo<E> aux = n.getNext();
		DNodo<E> r = new DNodo<E>(n,aux,element);
		n.setNext(r);
		aux.setPrev(r);
		size++;
	}

	@Override
	public void addBefore(Position<E> p, E element) throws InvalidPositionException {
		if(size==0)
			throw new InvalidPositionException("La lista esta vacia");
		DNodo<E> n = checkPosition(p);
		DNodo<E> aux = n.getPrev();
		DNodo<E> r = new DNodo<E>(aux,n,element);
		n.setPrev(r);
		aux.setNext(r);
		size++;
		
	}

	@Override
	public E remove(Position<E> p) throws InvalidPositionException {
		if(size==0)
			throw new InvalidPositionException("La lista esta vacia");
		
		DNodo<E> n = checkPosition(p);
		DNodo<E> pre = n.getPrev();
		DNodo<E> sig = n.getNext();
		E ret = n.element();
		
		pre.setNext(sig);
		sig.setPrev(pre);
		n.setNext(null);
		n.setPrev(null);
		n.setElement(null);
		size--;
		
		return ret;
	}

	@Override
	public E set(Position<E> p, E element) throws InvalidPositionException {
		if(size==0)
			throw new InvalidPositionException("La lista esta vacia");
		DNodo<E> n = checkPosition(p);
		E ret = n.element();
		n.setElement(element);
		
		return ret;
	}

	@Override
	public Iterator<E> iterator() {
		return new ElementIterator<E>(this);
	}

	@Override
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> list= new ListaDE<Position<E>>();
		if(size > 0) {
			DNodo<E> it= header.getNext();
			
			while(it.getNext() != trailer) {
				list.addLast(it);
				it= it.getNext();
			}
			list.addLast(it);
		}
		
		return list;
	}
	
	private DNodo<E> checkPosition(Position<E> p) throws InvalidPositionException {
		try {
			if( p == null || p == header || p == trailer)
				throw new InvalidPositionException("Posicion invalida.");
			
			return (DNodo<E>) p;
		} catch(ClassCastException e) {
			throw new InvalidPositionException("Posicion invalida.");
		}
	}
	
}
