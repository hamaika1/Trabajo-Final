package ar.com.mateo.Practico5;

public class Empleado {

	private String nombre;
	private int edad;
	private String dni;
	private double salarioBase;
	private boolean casado;
	private double salarioConAumento;

	public Empleado() {

	}

	public Empleado(String nombre, int edad, double salario, String dni, boolean casado) {
		this.nombre = nombre;
		this.salarioBase = salario;
		this.casado = casado;
		this.dni = dni;
		this.edad = edad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public double getSalario() {
		return salarioBase;
	}

	public void setSalario(double salario) {
		this.salarioBase = salario;
	}

	public boolean isCasado() {
		return casado;
	}

	public void setCasado(boolean casado) {
		this.casado = casado;
	}

	public void clasificacionEdad() {
		if (edad <= 21) {
			System.out.println("Principiante");

		} else if (edad >= 22 && edad <= 35) {
			System.out.println("Intermedio");

		} else if (edad > 35) {
			System.out.println("Senior");
		}
	}

	public String toString() {
		String mensaje = null;
		return mensaje + String
				.format("\nNombre= " + getNombre() + "\nEdad= " + getEdad() + "\nDNI= " + getDni() + "\nSalarioBase= "
						+ getSalario() + "\nCasado= " + isCasado() + "\nSalarioConAumento= " + salarioConAumento);

	}

	public double aumentaSalario(double aumento) {
		return salarioConAumento = this.salarioBase + aumento;

	}
}
