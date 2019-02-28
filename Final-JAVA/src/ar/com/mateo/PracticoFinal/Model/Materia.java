package ar.com.mateo.PracticoFinal.Model;

public class Materia {

	private int id;
	private String nombre;
	private String horario;
	private Alumno alumno;

	public Materia() {

	}

	public Materia(String nombre, String horario, Alumno alumno) {
		this.nombre = nombre;
		this.horario = horario;
		this.alumno = alumno;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public String toString() {
		return String.format(getNombre());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Materia other = (Materia) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
