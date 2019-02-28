package proyectoejemplo.exceptions;


public class ValidarCamposObligatoriosException extends Exception{
	
	private static final long serialVersionUID = 7313413401227982835L;

	public synchronized Throwable getCause() { //Causa en forma tecnica
		return super.getCause();
	}
	
	public String getMessage()
	{
		return "Faltan completar datos";
	}

}
