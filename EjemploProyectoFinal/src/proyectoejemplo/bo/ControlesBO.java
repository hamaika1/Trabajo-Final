package proyectoejemplo.bo;

import proyectoejemplo.exceptions.ValidarCamposObligatoriosException;
import proyectoejemplo.exceptions.ValidarSeleccionListaException;

/**
 * Clase a fin de hacer controles generales sobre el sistema
 * 
 * @author SGIU2
 *
 */
public class ControlesBO {	
	
	/**
	 * Verifica que se haya seleccionado previamente un item de la lista a fin
	 * de proceder con la accion
	 * 
	 * @param removeRow
	 * @return true en caso que no se entro a la excepcion
	 * @throws ValidarSeleccionListaException
	 */
	public static boolean validarSeleccionLista(int removeRow) throws ValidarSeleccionListaException {
		if (removeRow == -1)
			throw new ValidarSeleccionListaException();
		else
			return true;
	}
	
	/**
	 * Obliga a cargar el valor del campo que recibo como parametro
	 * 
	 * @param valorCampo
	 *            - campo obligatorio que tengo q llenar
	 * @return false si no se tira la excepci�n
	 * @throws ValidarCamposObligatoriosException
	 */
	public static boolean validarCampoObligatorio(String valorCampo) throws ValidarCamposObligatoriosException {
		if (valorCampo == null || valorCampo.isEmpty()) {
			throw new ValidarCamposObligatoriosException();
		}
		return false;
	}
	


}
