package TDAIterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

import Excepciones.BoundaryViolationException;
import Excepciones.EmptyListException;
import Excepciones.InvalidPositionException;
import TDALista.Position;
import TDALista.PositionList;

public class ElementIterator<E> implements Iterator<E> {
protected PositionList<E> lista;
protected Position<E> cursor;

public ElementIterator(PositionList<E> L) {
	try {
		
		lista= L;
		if(lista != null && !lista.isEmpty() )
			cursor= lista.first();
		else
			cursor= null;
		
	} catch(EmptyListException e) {
		cursor= null;
	}
}
public boolean hasNext() {
	return cursor!=null;
}
public E next() throws NoSuchElementException{
	if(cursor==null)
		throw new NoSuchElementException("No hay siguiente");
	E ret= cursor.element();
	
	try {
		cursor=(cursor==lista.last())? null : lista.next(cursor);
	} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {
		e.printStackTrace();
	}
	return ret;
}
}
