
package TDALista;

public class Dnode<E> implements Position<E>
{
    private E elemento;
    private Dnode<E> anterior;
    private Dnode<E> siguiente;
    
    public Dnode() {
        this(null, null, null);
    }
    
    public Dnode(final E elemento, final Dnode<E> anterior, final Dnode<E> siguiente) {
        this.elemento = elemento;
        this.anterior = anterior;
        this.siguiente = siguiente;
    }
    
    public Dnode<E> getAnterior() {
        return this.anterior;
    }
    
    public void setAnterior(final Dnode<E> anterior) {
        this.anterior = anterior;
    }
    
    public Dnode<E> getSiguiente() {
        return this.siguiente;
    }
    
    public void setSiguiente(final Dnode<E> siguiente) {
        this.siguiente = siguiente;
    }
    
    public void setElemento(final E elemento) {
        this.elemento = elemento;
    }
    
    @Override
    public E element() {
        return this.elemento;
    }
}