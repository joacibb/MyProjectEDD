package Ejercicios;

import java.util.ArrayList;

import Excepciones.BoundaryViolationException;
import Excepciones.EmptyListException;
import Excepciones.InvalidPositionException;
import TDALista.ListaDE;
import TDALista.Position;
import TDALista.PositionList;

public class AlumnoArrayList {
	protected String nombre;
	ArrayList cursadas;
	ArrayList aprobadas;
	/**
	 * Crea un Alumno con materias null.
	 * @param n Nombre del alumno
	 */
	public AlumnoArrayList(String n) {
		nombre=n;
		cursadas = new ArrayList();
		aprobadas = new ArrayList();
	}
	/*
	 * Agrega una materia como cursada.
	 * Si la materia ya fue cursado, imprime avisando.
	 */
	public void addCursada(int n) {
		if(cursadas.contains(n))
			cursadas.add(n);
		else
			System.out.println("El alumno "+nombre+" ya curso la materia "+n);
	}
	
	/*
	 * Agrega una materia como aprobada.
	 * Si la materia aun no se cursó o ya fue aprobada, imprime avisando la situación.
	 */
	public void addAprobada(int n) {
		if(!aprobadas.contains(n)&&cursadas.contains(n))
			aprobadas.add(n);
		else
			if(aprobadas.contains(n))
				System.out.println("El alumno "+nombre+" ya aprobo la materia "+n);
			else
				System.out.println("El alumno "+nombre+" aun no curso la materia "+n);
	}

	    
	/*
	 * Metodo privado para saber si una materia fue cursada o aprobada.
	 * devuelve true si la materia ya está.
	 */
	
	  public String toString() {
	        return "Nombre: "+nombre+" / Cursadas: "+cursadas.size()+" / Aprobadas: "+aprobadas.size();
	    }
	  
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
