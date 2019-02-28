package ar.com.mateo.PracticoFinal.BO;

import java.util.List;

import ar.com.mateo.PracticoFinal.DAO.AlumnoDAO;
import ar.com.mateo.PracticoFinal.Model.Alumno;

public class AlumnoBO {

	private AlumnoDAO aDAO = new AlumnoDAO();

	public List<Alumno> list() {
		return aDAO.list();
	}

	public void save(Alumno a) {
		aDAO.save(a);
	}

	public void update(Alumno a) {
		aDAO.update(a);
	}

	public void eliminar(Alumno a) {
		aDAO.delete(a);
	}
}
