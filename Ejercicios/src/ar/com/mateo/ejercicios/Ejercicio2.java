package ar.com.mateo.ejercicios;

import java.util.Scanner;

public class Ejercicio2 {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		System.out.println("Ingrese tres enteros, el 1ro:  ");
		int i = in.nextInt();
		System.out.println("Ingrese el 2do:  ");
		int j = in.nextInt();
		System.out.println("Ingrese el 3ro:   ");
		int f = in.nextInt();

		float promedio = (i + j + f) / 3.0f;
		
		
		
		System.out.println("Promedio: "+promedio);
	}
}
