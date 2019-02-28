package ar.com.mateo.PracticoFinal.Exceptions;

public class ValidarCamposException extends Exception {

	private static final long serialVersionUID = 7313413401227982835L;

	public synchronized Throwable getCause() { 
		return super.getCause();
	}

	public String getMessage() {
		return "Faltan completar datos";
	}

}
