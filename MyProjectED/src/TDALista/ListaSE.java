package TDALista;

import java.util.Iterator;

import Excepciones.BoundaryViolationException;
import Excepciones.EmptyListException;
import Excepciones.InvalidPositionException;
import TDAIterator.ElementIterator;

public class ListaSE<E> implements PositionList<E>{
	private int size;
	private Nodo<E> head;
	
	public ListaSE() {
		size=0;
		head=null;
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
	public Position<E> first() throws EmptyListException {
		if(size==0)
			throw new EmptyListException("La lista esta vacia");
		return head;
	}

	@Override
	public Position<E> last() throws EmptyListException {
		if(size==0)
			throw new EmptyListException("La lista esta vacia");
		Nodo<E> it = head;
		while(it.getNext()!=null)
			it=it.getNext();
		return it;
	}

	@Override
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		Nodo<E> n = checkPosition(p);
		if(n.getNext()==null)
			throw new BoundaryViolationException("No se puede pedir el siguiente al ultimo elemento");
		return n.getNext();
	}

	@Override
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		if(size==0)
			throw new InvalidPositionException("Lista vacia");
		checkPosition(p);
		try{
			if( p == first() ) throw new BoundaryViolationException("Posición primera");}
		catch (EmptyListException e){
			throw new InvalidPositionException("");
		}
		
		Nodo<E> aux = head;
		while( aux.getNext() != p && aux.getNext() != null )
		aux = aux.getNext();
		if( aux.getNext() == null )
		throw new InvalidPositionException("Posicion no pertenece a la lista");
		return aux;
	}

	@Override
	public void addFirst(E element) {
		Nodo<E> n = new Nodo<E>(element,head);
		head=n;
		size++;
	}

	@Override
	public void addLast(E element) {
		Nodo<E> n = new Nodo<E>(element);
		
		if(size==0) 
			head=n;
		else {
			Nodo<E> it = head;
			while(it.getNext()!=null)
				it=it.getNext();
			it.setNext(n);
			}
			size++;
	}

	@Override
	public void addAfter(Position<E> p, E element) throws InvalidPositionException {
		if(size==0)
			throw new InvalidPositionException("La lista esta vacia");
		Nodo<E> nodo = checkPosition(p);
		Nodo<E> newby = new Nodo<E>(element,nodo.getNext());
		nodo.setNext(newby);
		size++;
		
	}

	@Override
	public void addBefore(Position<E> p, E element) throws InvalidPositionException {
		if(size==0)
			throw new InvalidPositionException("La lista esta vacia");
		checkPosition(p);
		try {
			if(p==first())
				addFirst(element);
			else
				try {
					addAfter(prev(p),element);
				} catch (InvalidPositionException | BoundaryViolationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		} catch (EmptyListException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		size++;
	}

	@Override
	public E remove(Position<E> p) throws InvalidPositionException {
		if(size==0)
			throw new InvalidPositionException("Lista vacia");
		Nodo<E> nodo = checkPosition(p);
		E ret = nodo.element();
		if(nodo==head)
			head=head.getNext();
		else {
			Nodo<E> it = head;
			while(it.getNext()!=nodo)
				it=it.getNext();
			it.setNext(nodo.getNext());
		}
		nodo.setNext(null);
		nodo.setElement(null);
		size--;
		 return ret;
	}

	@Override
	public E set(Position<E> p, E element) throws InvalidPositionException {
		if(size==0)
			throw new InvalidPositionException("Lista vacia.");
		
		Nodo<E> nodo= checkPosition(p);
		E ret= nodo.element();
		nodo.setElement(element);
		
		return ret;
	}
	
	@Override
	public void eliminar(PositionList<E> L) throws InvalidPositionException {
		PositionList<E> aux = new ListaSE<E>();
		for(Position<E> p : L.positions()) {
			for(Position<E> t : positions()) {
				if(t!=null){
					if(t.element().equals(p.element())) {
						aux.addFirst(t.element());
						remove(t);
					}
				}
			}
		}
		
		for(Position<E> a : aux.positions())
				this.addLast(aux.remove(a));
		}
		
	
	public String toString() {
        String listText = "";
        Nodo<E> current = head;
        
        while(current != null) {
            listText = listText + current.element().toString();
            if(current.getNext() != null) {
                listText = listText + " -> ";
            }
            current = current.getNext();
        }
        return listText;
    }
	@Override
	public Iterator<E> iterator() {
		return new ElementIterator<E>(this);
	}
		
	@Override
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> ret= new ListaSE<Position<E>>();
		if(size > 0) {
			Nodo<E> it=head;
			while(it.getNext() != null) {
				ret.addLast(it);
				it= it.getNext();
			}
			ret.addLast(it);//Agrego ultima posicion.
		}
		
		return ret;
		}
	
	private Nodo<E> checkPosition(Position<E> p) throws InvalidPositionException{
			try {
				if(p == null)
					throw new InvalidPositionException("Posicion invalida");
				return (Nodo<E>) p;
			}
			catch (ClassCastException e){
				throw new InvalidPositionException("Posicion invalida");
			}
			
	}	

}
