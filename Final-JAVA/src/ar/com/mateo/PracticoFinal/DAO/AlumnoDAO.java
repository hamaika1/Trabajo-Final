package ar.com.mateo.PracticoFinal.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import ar.com.mateo.PracticoFinal.Model.Alumno;

public class AlumnoDAO {

	Conector conector;
	Connection conexion = null;
	Statement stmt = null;
	ResultSet rs = null;

	public Alumno save(Alumno a) {
		try {
			conector = new Conector();
			conexion = conector.getConexion();
			String sql = "INSERT INTO Alumno (nombre,apellido,dni) VALUES (?,?,?)";
			PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, a.getNombre());
			ps.setString(2, a.getApellido());
			ps.setString(3, a.getDni());
			ps.execute();

			ResultSet tableKeys = ps.getGeneratedKeys();
			tableKeys.next();
			int autoGeneratedID = tableKeys.getInt(1);

			ps.close();
			conector.closeConexion();

			a.setId(autoGeneratedID);
			return a;

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Se produjo un error al querer insertar el alumno " + a.getNombre(),
					"Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}

	}

	public Alumno load(int id) {
		try {
			conector = new Conector();
			conexion = conector.getConexion();
			String sql = "SELECT * FROM Alumno " + " WHERE Alumno.idAlumno like ?";

			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setString(1, "%" + id + "%");
			ResultSet rs = ps.executeQuery();
			Alumno a = new Alumno();

			if (rs.next()) {
				a.setId(rs.getInt("Alumno.idAlumno"));
				a.setNombre(rs.getString("Alumno.nombre"));
				a.setApellido(rs.getString("Alumno.apellido"));
				a.setDni(rs.getString("Alumno.dni"));
			}
			rs.close();
			ps.close();
			conector.closeConexion();
			return a;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Se produjo un error al querer obtener el alumno especificado", "Error",
					JOptionPane.WARNING_MESSAGE);
		}
		return null;
	}

	public void update(Alumno a) {
		try {
			conector = new Conector();
			conexion = conector.getConexion();
			String sql = "UPDATE Alumno SET nombre = ?, apellido = ?, dni = ? WHERE idAlumno = ?";
			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setInt(1, a.getId());
			ps.setString(2, a.getNombre());
			ps.setString(3, a.getApellido());
			ps.setString(4, a.getDni());
			ps.execute();
			ps.close();
			conector.closeConexion();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Se produjo un error al querer actualizar un Alumno", "Error",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	public void delete(Alumno a) {

		String sql = "DELETE FROM Alumno WHERE idAlumno = ?";
		PreparedStatement ps;
		try {
			conector = new Conector();
			conexion = conector.getConexion();
			ps = conexion.prepareStatement(sql);
			ps.setInt(1, a.getId());
			ps.execute();
			ps.close();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Se produjo un error al querer eliminar el Alumno especificada.",
					"Error", JOptionPane.ERROR_MESSAGE);
		} finally {
			conector.closeConexion();
		}
	}

	public List<Alumno> list() {

		List<Alumno> listaAlumno = new ArrayList<Alumno>();
		String sql = "SELECT * FROM Alumno ORDER BY nombre";
		PreparedStatement ps;

		try {
			conector = new Conector();
			conexion = conector.getConexion();
			ps = conexion.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Alumno a = new Alumno();
				a.setId(rs.getInt("Alumno.idAlumno"));
				a.setNombre(rs.getString("Alumno.nombre"));
				a.setApellido(rs.getString("Alumno.apellido"));
				a.setDni(rs.getString("Alumno.dni"));
				listaAlumno.add(a);
			}
			rs.close();
			ps.close();
			return listaAlumno;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Se produjo un error al querer acceder a los alumnos especificados",
					"Error", JOptionPane.ERROR_MESSAGE);
		} finally {
			conector.closeConexion();
		}

		return null;
	}

}
