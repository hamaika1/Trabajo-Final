package ar.com.mateo.Practico6;

public class Cuenta extends Imprimible {
	private String numero;
	private String titular;
	private int saldo;

	public Cuenta(String numero, String titular) {
		this.numero = numero;
		this.titular = titular;
		this.saldo = 0;
	}

	public String getNumero() {
		return this.numero;
	}

	public String getTitular() {
		return this.titular;
	}

	public int consultarSaldo() {
		return this.saldo;
	}

	public void realizarDeposito(int monto) {
		this.saldo += monto;
	}

	public boolean realizarExtraccion(int monto) {
		if (this.saldo < monto)
			return false;
		this.saldo -= monto;
		return true;
	}

	public boolean transferir(Cuenta destino, int monto) {
		if (!realizarExtraccion(monto)) {
			return false;
		}
		destino.realizarDeposito(monto);
		return true;
	}

	public Cheque emitirCheque(int monto) {
		return new Cheque(this, monto);
	}

	public boolean depositarCheque(Cheque cheque) {
		return cheque.getCuenta().transferir(this, cheque.getMonto());
	}

	@Override
	public String toStringExtraInfo() {
		return String.format(" Número: %s, titular: %s, saldo: %d", this.numero, this.titular, this.saldo);
	}

	public static void main(String[] args) {
		Cuenta cuenta = new Cuenta("123/4", "Jose");
		System.out.println(cuenta);
	}

}
