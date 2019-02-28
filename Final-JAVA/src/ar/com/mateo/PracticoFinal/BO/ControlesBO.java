package ar.com.mateo.PracticoFinal.BO;

import ar.com.mateo.PracticoFinal.Exceptions.ValidarCamposException;
import ar.com.mateo.PracticoFinal.Exceptions.ValidarSeleccionException;

public class ControlesBO {

	public static boolean validarSeleccionLista(int removeRow) throws ValidarSeleccionException {
		if (removeRow == -1)
			throw new ValidarSeleccionException();
		else
			return true;
	}

	public static boolean validarCampoObligatorio(String valorCampo) throws ValidarCamposException {
		if (valorCampo == null || valorCampo.isEmpty()) {
			throw new ValidarCamposException();
		}
		return false;
	}
	
}
