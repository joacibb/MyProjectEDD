package EjerciciosMapeo;

import TDALista.Position;
import TDALista.PositionList;

public class Alumno{
	String legajo, nombre;
	PositionList<ExamenFinal> finales;
	
	Alumno(String legajo, String nombre, PositionList<ExamenFinal> finales){
		this.legajo = legajo;
		this.nombre = nombre;
		this.finales = finales;
	}
	
	public void setNombre(String nombre) {this.nombre = nombre;}
	public void setFinal(ExamenFinal e) {
		Iterable<Position<ExamenFinal>> examenes = finales.positions();
		//si ya existe esa materia solo cambia la nota
		for(Position<ExamenFinal> ex : examenes) {
			//si el nombre de la materia es igual
			if(ex.element().getMateria().equals(e.getMateria())) {
				//cambia la nota
				ex.element().setNota(e.getNota());
				return;
			}
		}
		//si no existe, agrega el ExamenFinal al final de la lista
		finales.addLast(e);
	}
	public Integer getNota(String materia) {
		Iterable<Position<ExamenFinal>> examenes = finales.positions();
		//si ya existe esa materia retorna la nota
		for(Position<ExamenFinal> ex : examenes) {
			//si el nombre de la materia es igual
			if(ex.element().getMateria().equals(materia)) {
				//retorna la nota
				return ex.element().getNota();
			}
		}
		//si no existe retorna null
		return null;
	}
	public String getlegajo() {return legajo;}
	public String getNombre() {return nombre;}
	public void print() {
		System.out.println("Nombre: "+nombre+" / Legajo: "+legajo+" / finales: ");
		Iterable<Position<ExamenFinal>>  iterableExamenFinal = finales.positions();
		for(Position<ExamenFinal> i : iterableExamenFinal) {
			System.out.println("Materia: "+i.element().getMateria()+" Nota: "+i.element().getNota());
		}
		
		System.out.println("-----------------------------------------------------------------");
	}
}