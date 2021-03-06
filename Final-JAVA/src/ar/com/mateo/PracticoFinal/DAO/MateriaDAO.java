package ar.com.mateo.PracticoFinal.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import ar.com.mateo.PracticoFinal.Model.Materia;
import ar.com.mateo.PracticoFinal.DAO.AlumnoDAO;

public class MateriaDAO {

	Conector conector;
	Connection conexion = null;
	Statement stmt = null;
	ResultSet rs = null;
	AlumnoDAO aDAO = new AlumnoDAO();

	public Materia save(Materia m) {
		try {
			conector = new Conector();
			conexion = conector.getConexion();
			String sql = "INSERT INTO Materia (nombre,horario,idAlumno) VALUES (?,?,?)";
			PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, m.getNombre());
			ps.setString(2, m.getHorario());
			ps.setInt(3, m.getAlumno().getId());
			ps.execute();

			ResultSet tableKeys = ps.getGeneratedKeys();
			tableKeys.next();
			int autoGeneratedID = tableKeys.getInt(1);

			ps.close();
			conector.closeConexion();
			m.setId(autoGeneratedID);
			return m;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Se produjo un error al querer insertar la Materia: " + m.getNombre(),
					"Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}

	}

	public Materia load(int id) {
		try {
			conector = new Conector();
			conexion = conector.getConexion();
			String sql = "SELECT * FROM Materia " + " WHERE Materia.idMateria like ?";

			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setString(1, "%" + id + "%");
			ResultSet rs = ps.executeQuery();
			Materia m = new Materia();

			if (rs.next()) {
				m.setId(rs.getInt("Materia.idMateria"));
				m.setNombre(rs.getString("Materia.nombre"));
				m.setHorario(rs.getString("Materia.horario"));
				m.setAlumno(aDAO.load(rs.getInt("Materia.idAlumno")));

			}
			rs.close();
			ps.close();
			conector.closeConexion();
			return m;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Se produjo un error al querer obtener el alumno especificado", "Error",
					JOptionPane.WARNING_MESSAGE);
		}
		return null;
	}

	public void update(Materia m) {
		try {
			conector = new Conector();
			conexion = conector.getConexion();
			String sql = "UPDATE Materia SET nombre = ?, horario = ?, idAlumno = ? WHERE idMateria = ?";
			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setString(1, m.getNombre());
			ps.setString(2, m.getHorario());
			ps.setInt(3, m.getAlumno().getId());
			ps.setInt(4, m.getId());
			ps.execute();
			ps.close();
			conector.closeConexion();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Se produjo un error al querer actualizar un Alumno", "Error",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	public void delete(Materia m) {

		String sql = "DELETE FROM Materia WHERE idMateria = ?";
		PreparedStatement ps;
		try {
			conector = new Conector();
			conexion = conector.getConexion();
			ps = conexion.prepareStatement(sql);
			ps.setInt(1, m.getId());
			ps.execute();
			ps.close();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Se produjo un error al querer eliminar el Alumno especificada.",
					"Error", JOptionPane.ERROR_MESSAGE);
		} finally {
			conector.closeConexion();
		}
	}

	public List<Materia> list() {

		List<Materia> listaMateria = new ArrayList<Materia>();
		String sql = "SELECT * FROM Materia ORDER BY nombre";
		PreparedStatement ps;

		try {
			conector = new Conector();
			conexion = conector.getConexion();
			ps = conexion.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Materia m = new Materia();
				m.setId(rs.getInt("Materia.idMateria"));
				m.setNombre(rs.getString("Materia.nombre"));
				m.setHorario(rs.getString("Materia.horario"));
				m.setAlumno(aDAO.load(rs.getInt("Materia.idAlumno")));
				listaMateria.add(m);
			}
			rs.close();
			ps.close();
			return listaMateria;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Se produjo un error al querer acceder a los alumnos especificados",
					"Error", JOptionPane.ERROR_MESSAGE);
		} finally {
			conector.closeConexion();
		}

		return null;
	}

}
