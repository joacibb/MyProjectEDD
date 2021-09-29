package Ejercicios;

import TDALista.ListaDE;
import TDALista.PositionList;

public class ListaAlumno{
	
	private ListaDE<Alumno> lista;

    public void ListaAlumnoStock() {
        lista = new ListaDE<Alumno>();
    }

    public void addAlumno(Alumno b) {
        if(lista.size()==0) {
            lista.addFirst(b);
        }else {
            lista.addFirst(b);
        }
    }

    public int size() {
        return lista.size();
    }
    
	/*
	 * Author del tester: Pana Colombiano
	 */
	  public static void main(String[] args) {
	        Alumno b = new Alumno("Joaquin");
	        Alumno d = new Alumno("JuanB");
	        Alumno g = new Alumno("Valentino");

	        b.addCursada(123);
	        b.addCursada(123); //Tiene que dar que ya se curso
	        b.addCursada(456);
	        b.addCursada(789);
	        b.addAprobada(123); //ok porque ya cursé 123.
	        //b = 3 cursadas y 1 aprobada
	        System.out.println(b.toString());


	        d.addCursada(123);
	        d.addCursada(456);	
	        d.addCursada(789);	
	        d.addCursada(0);	
	        d.addAprobada(123); //+1 aprobada
	        d.addAprobada(789);//+1 aprobada
	        d.addAprobada(999); // error porque no la curse
	        
	        //d = 4 cursadas y 2 aprobadas
	        System.out.println(d.toString());

	        g.addCursada(123);
	        g.addCursada(456);
	        g.addCursada(789);
	        g.addAprobada(123);
	        g.addAprobada(456);
	        g.addAprobada(789);
	        // g = 3 cursadas y 3 aprobadas
	        System.out.println(g.toString());

	        PositionList<Alumno> lista = new ListaDE<Alumno>();
	        lista.addLast(b);
	        lista.addLast(d);
	        lista.addLast(g);

	        System.out.println("tamaño de lista de alumnos: "+lista.size());
	    }
}
