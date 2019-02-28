package ar.com.mateo.PracticoFinal.BO;

import java.util.List;

import ar.com.mateo.PracticoFinal.DAO.MateriaDAO;
import ar.com.mateo.PracticoFinal.Model.Materia;

public class MateriaBO {

	private MateriaDAO mDAO = new MateriaDAO();

	public List<Materia> list() {
		return mDAO.list();
	}

	public void save(Materia m) {
		mDAO.save(m);
	}

	public void update(Materia m) {
		mDAO.update(m);
	}

	public void eliminar(Materia m) {
		mDAO.delete(m);
	}

}
