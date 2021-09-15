package TDACola;


import Excepciones.EmptyQueueException;
import TDAPila.Nodo;

public class ColaConPila<E> implements Queue<E>{
	
	//Atributos de instancia
	protected int cantElem;
	protected Nodo<E> head,tail;
	
	//Constructor
	
	public ColaConPila() {
		head = tail = null;
		cantElem = 0;
	}
	
	//Comandos
	public void enqueue(E elem) {
		Nodo<E> nodo = new Nodo<E>(elem);
		
		nodo.setElemento(elem);
		nodo.setSiguiente(null);
		
		if (cantElem == 0) {
			head = nodo;
		}else {
			tail.setSiguiente(nodo);
		}
		tail = nodo;
		cantElem++;
	}

	@Override
	public int size() {
		return cantElem;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return cantElem == 0;
	}

	@Override
	public E front() throws EmptyQueueException {
		E salida;
		if (isEmpty()) {
			throw new EmptyQueueException("Cola vacia"); 
		}else {
			salida = head.getElemento();
		}
		return salida;
	}


  public E dequeue() throws EmptyQueueException {
      E aux;
        if (this.isEmpty()) {
            throw new EmptyQueueException("Se intento remover el elemento del frente en una cola vacia.");
        }
         aux = this.head.getElemento();
        this.head = this.head.getSiguiente();
        --this.cantElem;
        return aux;
    }
}