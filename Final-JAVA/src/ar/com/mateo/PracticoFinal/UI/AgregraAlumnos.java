package ar.com.mateo.PracticoFinal.UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ar.com.mateo.PracticoFinal.BO.AlumnoBO;
import ar.com.mateo.PracticoFinal.BO.ControlesBO;
import ar.com.mateo.PracticoFinal.Exceptions.ValidarCamposException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import ar.com.mateo.PracticoFinal.Model.Alumno;

public class AgregraAlumnos extends JFrame {

	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textApellido;
	private JTextField textDNI;
	private Alumno alumno = new Alumno();
	private AlumnoBO aBO = new AlumnoBO();
	private boolean updatePerson = false;

	/**
	 * Create the frame.
	 */
	public AgregraAlumnos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);

		JPanel panelBotones = new JPanel();
		panelBotones.setBounds(6, 222, 438, 59);
		getContentPane().add(panelBotones);
		panelBotones.setLayout(null);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Boolean error = false;

				try {
					ControlesBO.validarCampoObligatorio((String) textNombre.getText());
					ControlesBO.validarCampoObligatorio((String) textApellido.getText());
					ControlesBO.validarCampoObligatorio((String) textDNI.getText());

				} catch (ValidarCamposException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error ", JOptionPane.ERROR_MESSAGE);
					error = true;
				}

				if (error == false) {

					alumno.setNombre(textNombre.getText());
					alumno.setApellido(textApellido.getText());
					alumno.setDni(textDNI.getText());

					if (updatePerson) {
						System.out.println(alumno.getId());
						aBO.update(alumno);
					} else {
						aBO.save(alumno);
					}

					VentanaPrincipal.panelAlumnos = new PanelAlumnoUI();
					VentanaPrincipal.panelGeneral.removeAll();
					VentanaPrincipal.panelGeneral.repaint();
					VentanaPrincipal.panelGeneral.revalidate();
					VentanaPrincipal.actualizar(VentanaPrincipal.panelAlumnos);

					dispose();

				}
			}

		});

		btnGuardar.setBounds(297, 17, 117, 25);
		panelBotones.add(btnGuardar);

		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaPrincipal.panelGeneral.removeAll();
				VentanaPrincipal.panelGeneral.repaint();
				VentanaPrincipal.panelGeneral.revalidate();
				VentanaPrincipal.actualizar(VentanaPrincipal.panelAlumnos);
				dispose();
			}
		});
		btnNewButton.setBounds(150, 17, 117, 25);
		panelBotones.add(btnNewButton);

		JPanel panelAlumno = new JPanel();
		panelAlumno.setBounds(6, 22, 438, 201);
		getContentPane().add(panelAlumno);
		panelAlumno.setLayout(null);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(6, 6, 70, 28);
		panelAlumno.add(lblNombre);

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(6, 64, 70, 26);
		panelAlumno.add(lblApellido);

		JLabel lblDni = new JLabel("DNI:");
		lblDni.setBounds(6, 133, 70, 26);
		panelAlumno.add(lblDni);

		textNombre = new JTextField();
		textNombre.setBounds(88, 4, 216, 30);
		panelAlumno.add(textNombre);
		textNombre.setColumns(10);

		textApellido = new JTextField();
		textApellido.setBounds(88, 62, 216, 28);
		panelAlumno.add(textApellido);
		textApellido.setColumns(10);

		textDNI = new JTextField();
		textDNI.setBounds(54, 131, 158, 28);
		panelAlumno.add(textDNI);
		textDNI.setColumns(10);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
	}

	public AgregraAlumnos(Alumno alumno) {
		this();
		updatePerson = true;
		this.alumno = alumno;
		textNombre.setText(alumno.getNombre());
		textApellido.setText(alumno.getApellido());
		textDNI.setText(alumno.getDni());
	}
}
