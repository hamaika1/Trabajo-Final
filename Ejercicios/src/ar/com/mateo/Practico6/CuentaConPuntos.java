package ar.com.mateo.Practico6;

public class CuentaConPuntos extends Cuenta {
	private int monto;
	private int puntos;

	public CuentaConPuntos(String numero, String titular) {
		super(numero, titular);
	}

	public int consultarPuntos() {
		if (depositarCheque(emitirCheque(monto)) == true) {
			return this.puntos += this.monto / 5;
		}
		return this.puntos += this.monto / 10;
		
	}

	public String toString() {
		return toStringExtraInfo() + String.format(", puntos: %d", consultarPuntos());
		
	}
}
