package EjerciciosLista;

import Excepciones.BoundaryViolationException;
import Excepciones.EmptyListException;
import Excepciones.InvalidPositionException;
import TDALista.ListaDE;
import TDALista.Position;
import TDALista.PositionList;
/*
 * Author: Joaquin Cibanal
 */
public class Alumno {
	
	protected String nombre;
	PositionList<Integer> cursadas,aprobadas;
	/**
	 * Crea un Alumno con materias null.
	 * @param n Nombre del alumno
	 */
	public Alumno(String n) {
		nombre=n;
		cursadas = new ListaDE<Integer>();
		aprobadas = new ListaDE<Integer>();
	}
	/*
	 * Agrega una materia como cursada.
	 * Si la materia ya fue cursado, imprime avisando.
	 */
	public void addCursada(int n) {
		if(!estaR(n,cursadas))
			cursadas.addLast(n);
		else
			System.out.println("El alumno "+nombre+" ya curso la materia "+n);
	}
	
	/*
	 * Agrega una materia como aprobada.
	 * Si la materia aun no se curs� o ya fue aprobada, imprime avisando la situaci�n.
	 */
	public void addAprobada(int n) {
		if(!estaR(n,aprobadas)&&estaR(n,cursadas))
			aprobadas.addLast(n);
		else
			if(estaR(n,aprobadas))
				System.out.println("El alumno "+nombre+" ya aprobo la materia "+n);
			else
				System.out.println("El alumno "+nombre+" aun no curso la materia "+n);
	}

	    
	/*
	 * Metodo privado para saber si una materia fue cursada o aprobada.
	 * devuelve true si la materia ya est�.
	 */
	private boolean estaR(int n, PositionList<Integer> lista) {
		boolean esta = false;
		int i=0;
		try{
			Position<Integer> p = lista.first(); 
		
		while(!esta&&i<lista.size())
			if(p.element()==n)
				esta=true;
			else
				p = lista.next(p);
		}
		catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {
			e.getStackTrace();
		}
			
			return esta;
	}
	
	  public String toString() {
	        return "Nombre: "+nombre+" / Cursadas: "+cursadas.size()+" / Aprobadas: "+aprobadas.size();
	    }
	  

}
