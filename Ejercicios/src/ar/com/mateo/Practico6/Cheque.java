package ar.com.mateo.Practico6;

public class Cheque extends Imprimible {
	private Cuenta cuenta;
	private int monto;

	public Cheque(Cuenta cuenta, int monto) {
		this.cuenta = cuenta;
		this.monto = monto;
	}

	public Cuenta getCuenta() {
		return this.cuenta;
	}

	public String getTitularCuenta() {
		return this.cuenta.getTitular();
	}

	public int getMonto() {
		return this.monto;
	}

	@Override
	public String toStringExtraInfo() {
		return String.format(" Titular cuenta: %s, monto: %d", this.getTitularCuenta(), this.monto);
	}
}
