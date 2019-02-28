package ar.com.mateo.PracticoFinal.UI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ar.com.mateo.PracticoFinal.BO.AlumnoBO;
import ar.com.mateo.PracticoFinal.BO.ControlesBO;
import ar.com.mateo.PracticoFinal.Exceptions.ValidarCamposException;
import ar.com.mateo.PracticoFinal.Model.Alumno;
import ar.com.mateo.PracticoFinal.Model.Materia;
import javafx.scene.control.ComboBoxBuilder;
import ar.com.mateo.PracticoFinal.BO.MateriaBO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class AgregraMaterias extends JFrame {

	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textHorario;
	private boolean updateMateria = false;
	private Materia materia = new Materia();
	private MateriaBO mBO = new MateriaBO();
	private AlumnoBO aBO = new AlumnoBO();
	private JComboBox<Alumno> comboAlumno;
	private JTextField textEstado;

	/**
	 * Create the frame.
	 */
	public AgregraMaterias() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelBotones = new JPanel();
		panelBotones.setBounds(0, 218, 450, 70);
		contentPane.add(panelBotones);
		panelBotones.setLayout(null);

		JPanel panelMateria = new JPanel();
		panelMateria.setBounds(0, 12, 450, 203);
		contentPane.add(panelMateria);
		panelMateria.setLayout(null);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(12, 12, 70, 15);
		panelMateria.add(lblNombre);

		JLabel lblHorario = new JLabel("Horario:");
		lblHorario.setBounds(12, 53, 70, 15);
		panelMateria.add(lblHorario);

		textHorario = new JTextField();
		textHorario.setBounds(100, 45, 218, 25);
		panelMateria.add(textHorario);
		textHorario.setColumns(10);

		JLabel lblIdAlumno = new JLabel("Alumno:");
		lblIdAlumno.setBounds(12, 99, 119, 15);
		panelMateria.add(lblIdAlumno);

		textNombre = new JTextField();
		textNombre.setBounds(100, 5, 218, 25);
		panelMateria.add(textNombre);
		textNombre.setColumns(10);

		
		this.comboAlumno = new JComboBox<>();
		comboAlumno.setBounds(84, 94, 255, 24);
		panelMateria.add(comboAlumno);
		List<Alumno> alumnos = aBO.list();
		for (Alumno a : alumnos) {
			comboAlumno.addItem(a);
		}

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaPrincipal.panelGeneral.removeAll();
				VentanaPrincipal.panelGeneral.repaint();
				VentanaPrincipal.panelGeneral.revalidate();
				VentanaPrincipal.actualizar(VentanaPrincipal.panelMaterias);
				dispose();
			}
		});
		btnCancelar.setBounds(171, 12, 117, 25);
		panelBotones.add(btnCancelar);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Boolean error = false;

				try {
					ControlesBO.validarCampoObligatorio((String) textNombre.getText());
					ControlesBO.validarCampoObligatorio((String) textHorario.getText());

				} catch (ValidarCamposException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error ", JOptionPane.ERROR_MESSAGE);
					error = true;
				}

				if (error == false) {

					materia.setNombre(textNombre.getText());
					materia.setHorario(textHorario.getText());
					int index = comboAlumno.getSelectedIndex();
					materia.setAlumno(comboAlumno.getItemAt(index));

					if (updateMateria) {
						System.out.println(materia.getId());
						mBO.update(materia);
					} else {
						mBO.save(materia);
					}

					VentanaPrincipal.panelMaterias = new PanelMateriasUI();
					VentanaPrincipal.panelGeneral.removeAll();
					VentanaPrincipal.panelGeneral.repaint();
					VentanaPrincipal.panelGeneral.revalidate();
					VentanaPrincipal.actualizar(VentanaPrincipal.panelMaterias);

					dispose();

				}
			}
		});
		btnGuardar.setBounds(321, 12, 117, 25);
		panelBotones.add(btnGuardar);
		

	}

	public AgregraMaterias(Materia materia) {
		this();
		updateMateria = true;
		this.materia = materia;
		textNombre.setText(materia.getNombre());
		textHorario.setText(materia.getHorario());
		comboAlumno.setSelectedItem(materia.getAlumno());
		
	}
	
}
