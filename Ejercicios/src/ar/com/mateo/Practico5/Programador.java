package ar.com.mateo.Practico5;

import java.util.ArrayList;

public class Programador extends Empleado {

	private ArrayList<String> lenjuageDominados = new ArrayList<String>();
	private int lineasDeCodigoPorHora;
	private String java;
	private String mysql;

	public Programador() {

	}

	public Programador(ArrayList<String> lenjuageDominados, int lineasDeCodigoPorHora) {
		super();
		this.lenjuageDominados = lenjuageDominados;
		this.lineasDeCodigoPorHora = lineasDeCodigoPorHora;
	}

	public int getLineasDeCodigoPorHora() {
		return lineasDeCodigoPorHora;
	}

	public void setLineasDeCodigoPorHora(int lineasDeCodigoPorHora) {
		this.lineasDeCodigoPorHora = lineasDeCodigoPorHora;
	}

	public ArrayList<String> getLenjuageDominados() {
		return lenjuageDominados;
	}

	public void setLenjuageDominados(ArrayList<String> lenjuageDominados) {
		this.lenjuageDominados = lenjuageDominados;
	}

	public void llenaLista() {
		lenjuageDominados.add(java);
		lenjuageDominados.add(mysql);

	}
		public String toString(){
			String mensaje = null;
			return mensaje+String.format("\nLenjuagesDominados= "+getLenjuageDominados()+"\nLineasDeCodigoPorHoras= "+getLineasDeCodigoPorHora());	
			
			
		}
}
