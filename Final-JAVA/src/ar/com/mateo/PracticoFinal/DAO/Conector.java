package ar.com.mateo.PracticoFinal.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Conector {
	
	Connection conexion;
	String error;

	public Conector() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost/Plante?useSSL=false";
			String user =  "root";
			String password = "root";
			conexion = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			error = e.getMessage();
			JOptionPane.showMessageDialog(null, e, "ATENCION", javax.swing.JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public Connection getConexion() {
		return conexion;
	}

	public void closeConexion() {
		try {
			conexion.close();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex, "ATENCION", javax.swing.JOptionPane.INFORMATION_MESSAGE);
		}
	}

}
