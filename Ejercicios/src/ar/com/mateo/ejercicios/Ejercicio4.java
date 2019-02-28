package ar.com.mateo.ejercicios;

import java.util.Scanner;

public class Ejercicio4 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Ingrese dos enteros: ");
		int i = in.nextInt();
		System.out.println("Ingrese el segundo:");
		int j = in.nextInt();

		if ((i + j) % 2 == 0) {
			System.out.println("Es par");
		} else {
			System.out.println("Es inpar");
		}
	}

}
