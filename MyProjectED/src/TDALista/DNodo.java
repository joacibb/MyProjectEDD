package TDALista;

public class DNodo<E> implements Position<E>{
private E element;
private DNodo<E> previo,siguiente;

public DNodo(E e) {
	previo=null;
	siguiente=null;
	element = e;
}
public DNodo(DNodo<E>p, DNodo<E> s, E e) {
	previo = p;
	siguiente = s;
	element = e;
}

public E element() {
	return element;
}

public DNodo<E> getNext(){
	return siguiente;
}

public DNodo<E> getPrev(){
	return previo;
}

public void setNext(DNodo<E> s) {
	siguiente = s;
}

public void setPrev(DNodo<E> p) {
	previo = p;
}

public void setElement(E e) {
	element=e;
}
}
