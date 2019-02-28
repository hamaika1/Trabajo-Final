package ar.com.mateo.PracticoFinal.Model;

import ar.com.mateo.PracticoFinal.Model.Alumno;
import ar.com.mateo.PracticoFinal.Model.Materia;

public class Parcial {

	private int id;
	private int Parcial1;
	private int Parcial2;
	private Alumno alumno;
	private Materia materia;
	private String estadoRegularidad;

	public String getEstadoRegularidad() {
		return estadoRegularidad;
	}

	public void setEstadoRegularidad(String estadoRegularidad) {
		this.estadoRegularidad = estadoRegularidad;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Parcial() {

	}

	public Parcial(int parcial1, int parcial2, Alumno alumno, Materia materia, String estadoRegularidad) {
		this.Parcial1 = parcial1;
		this.Parcial2 = parcial2;
		this.alumno = alumno;
		this.materia = materia;
		this.estadoRegularidad = estadoRegularidad;
	}

	public int getParcial1() {
		return Parcial1;
	}

	public void setParcial1(int parcial1) {
		Parcial1 = parcial1;
	}

	public int getParcial2() {
		return Parcial2;
	}

	public void setParcial2(int parcial2) {
		Parcial2 = parcial2;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public String toString(){
		return String.format(getAlumno()+" "+getMateria());
	}

}
