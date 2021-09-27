package TDALista;

public class Nodo<E> implements Position<E>{
	
private E element;
private Nodo<E> next;

public Nodo(E e, Nodo<E> s){
	element = e;;
	next=s;
}
public Nodo(E e) {
	element = e;
	next=null;
}

@Override
public E element() {
	// TODO Auto-generated method stub
	return element;
}

public Nodo<E> getNext(){
return next;
}

public void setNext(Nodo<E> n) {
next=n;
}

public void setElement(E e) {
	element=e;
}

}