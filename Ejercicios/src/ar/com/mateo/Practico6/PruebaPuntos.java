package ar.com.mateo.Practico6;

public class PruebaPuntos {

	public static void main(String[] args) {
		CuentaConPuntos c1 = new CuentaConPuntos("1212/3", "Pepe");
		CuentaConPuntos c2 = new CuentaConPuntos("4545/6", "Carla");
		System.out.println("Puntos c1: " + c1.consultarPuntos());
		System.out.println("Puntos c2: " + c2.consultarPuntos());
	
		c1.realizarDeposito(200);
		c2.realizarDeposito(400);
		Cheque cheque=c2.emitirCheque(100);
		if(!c1.depositarCheque(cheque)){
			System.out.println("Saldo insuficiente");
		}
		System.out.println(c1.consultarPuntos());
		System.out.println(c1);
		System.out.println(c2);
		System.out.println(cheque);
		}

}
