package proyectoejemplo.bo;

import java.util.List;

import proyectoejemplo.dao.PersonaDAO;
import proyectoejemplo.modelo.Persona;

public class PersonaBO {
	private PersonaDAO pDAO = new PersonaDAO();
	
	public List<Persona> list() {
		return pDAO.list();
	}
	
	public void save(Persona p) {
		pDAO.save(p);
	}
	
	public void update(Persona p) {
		pDAO.update(p);
	}
	
	public void eliminar(Persona p) {
		pDAO.delete(p);
	}
}
