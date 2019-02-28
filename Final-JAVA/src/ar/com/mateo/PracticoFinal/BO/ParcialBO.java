package ar.com.mateo.PracticoFinal.BO;

import java.util.List;

import ar.com.mateo.PracticoFinal.DAO.ParcialDAO;
import ar.com.mateo.PracticoFinal.Model.Parcial;

public class ParcialBO {
	private ParcialDAO pDAO = new ParcialDAO();
	private String Libre = "Libre";
	private String Regular = "Regular";
	private String Promocionado = "Promocionado";

	public List<Parcial> list() {
		return pDAO.list();
	}

	public void save(Parcial p) {
		if ((p.getParcial1() >= 0 && p.getParcial1() < 4) && (p.getParcial2() >= 0 && p.getParcial2() < 4)) {
			p.setEstadoRegularidad(Libre);
			pDAO.save(p);
		} else if ((p.getParcial1() >= 4 && p.getParcial1() < 7) && (p.getParcial2() >= 4 && p.getParcial2() < 7)) {
			p.setEstadoRegularidad(Regular);
			pDAO.save(p);
		} else if ((p.getParcial1() >= 7 && p.getParcial1() <= 10) && (p.getParcial2() >= 7 && p.getParcial2() <= 10)) {
			p.setEstadoRegularidad(Promocionado);
			pDAO.save(p);
		} else  {
			p.setEstadoRegularidad(Regular);
			pDAO.save(p);
		}
	}

	public void update(Parcial p) {
		pDAO.update(p);
	}

	public void eliminar(Parcial p) {
		pDAO.delete(p);
	}

}
