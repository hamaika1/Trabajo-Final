package ar.com.mateo.PracticoFinal.UI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ar.com.mateo.PracticoFinal.BO.AlumnoBO;
import ar.com.mateo.PracticoFinal.BO.ControlesBO;
import ar.com.mateo.PracticoFinal.BO.MateriaBO;
import ar.com.mateo.PracticoFinal.BO.ParcialBO;
import ar.com.mateo.PracticoFinal.Exceptions.ValidarCamposException;
import ar.com.mateo.PracticoFinal.Model.Alumno;
import ar.com.mateo.PracticoFinal.Model.Materia;
import ar.com.mateo.PracticoFinal.Model.Parcial;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class AgregraNotaParcial extends JFrame {

	private JPanel contentPane;
	private JTextField textParcial1;
	private JTextField textParcial2;
	private boolean updateParcial = false;
	private Parcial parcial = new Parcial();
	private ParcialBO pBO = new ParcialBO();
	private MateriaBO mBO = new MateriaBO();
	private AlumnoBO aBO = new AlumnoBO();
	private JComboBox<Alumno> comboAlumno;
	private JComboBox<Materia> comboMateria;

	/**
	 * Create the frame.
	 */
	public AgregraNotaParcial() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelMenu = new JPanel();
		panelMenu.setBounds(0, 22, 438, 201);
		contentPane.add(panelMenu);
		panelMenu.setLayout(null);

		JLabel lblNombreAlumno = new JLabel("Alumno:");
		lblNombreAlumno.setBounds(12, 12, 125, 15);
		panelMenu.add(lblNombreAlumno);

		JLabel lblParcial1 = new JLabel("Parcial 1:");
		lblParcial1.setBounds(12, 66, 91, 15);
		panelMenu.add(lblParcial1);

		JLabel lblParcial2 = new JLabel("Parcial 2:");
		lblParcial2.setBounds(217, 66, 91, 15);
		panelMenu.add(lblParcial2);

		JLabel lblMateria = new JLabel("Materia: ");
		lblMateria.setBounds(12, 119, 70, 15);
		panelMenu.add(lblMateria);

		this.textParcial1 = new JTextField();
		textParcial1.setBounds(85, 59, 114, 24);
		panelMenu.add(textParcial1);
		textParcial1.setColumns(10);

		this.textParcial2 = new JTextField();
		textParcial2.setBounds(284, 57, 125, 24);
		panelMenu.add(textParcial2);
		textParcial2.setColumns(10);

		this.comboAlumno = new JComboBox<>();
		comboAlumno.setBounds(73, 3, 193, 24);
		panelMenu.add(comboAlumno);
		List<Alumno> alumnos = aBO.list();
		for (Alumno a : alumnos) {
			comboAlumno.addItem(a);
		}

		this.comboMateria = new JComboBox<>();
		comboMateria.setBounds(71, 114, 195, 24);
		panelMenu.add(comboMateria);
		List<Materia> materias = mBO.list();
		for (Materia m : materias) {
			comboMateria.addItem(m);

		}
		JPanel panelBotones = new JPanel();
		panelBotones.setBounds(0, 226, 450, 74);
		contentPane.add(panelBotones);
		panelBotones.setLayout(null);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaPrincipal.panelGeneral.removeAll();
				VentanaPrincipal.panelGeneral.repaint();
				VentanaPrincipal.panelGeneral.revalidate();
				VentanaPrincipal.actualizar(VentanaPrincipal.panelParcial);
				dispose();
			}
		});
		btnCancelar.setBounds(181, 0, 117, 25);
		panelBotones.add(btnCancelar);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Boolean error = false;

				try {
					ControlesBO.validarCampoObligatorio((String) textParcial1.getText());
					ControlesBO.validarCampoObligatorio((String) textParcial2.getText());
				} catch (ValidarCamposException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error ", JOptionPane.ERROR_MESSAGE);
					error = true;
				}

				if (error == false) {
					parcial.setParcial1(Integer.valueOf(textParcial1.getText()));
					parcial.setParcial2(Integer.valueOf(textParcial2.getText()));
					int index1 = comboAlumno.getSelectedIndex();
					parcial.setAlumno(comboAlumno.getItemAt(index1));
					int index2 = comboMateria.getSelectedIndex();
					parcial.setMateria(comboMateria.getItemAt(index2));

					if (updateParcial) {
						System.out.println(parcial.getId());
						pBO.update(parcial);
					} else {
						pBO.save(parcial);
					}

					VentanaPrincipal.panelParcial = new PanelParcialUI();
					VentanaPrincipal.panelGeneral.removeAll();
					VentanaPrincipal.panelGeneral.repaint();
					VentanaPrincipal.panelGeneral.revalidate();
					VentanaPrincipal.actualizar(VentanaPrincipal.panelParcial);

					dispose();
				}
			}
		});
		btnGuardar.setBounds(310, 0, 117, 25);
		panelBotones.add(btnGuardar);

		JLabel lblNotasParcial = new JLabel("Notas Parcial");
		lblNotasParcial.setBounds(158, 0, 140, 15);
		contentPane.add(lblNotasParcial);
	}

	public AgregraNotaParcial(Parcial parcial) {
		this();
		updateParcial = true;
		this.parcial = parcial;
		textParcial1.setText(String.valueOf(parcial.getParcial1()));
		textParcial2.setText(String.valueOf(parcial.getParcial2()));
		comboAlumno.setSelectedItem(parcial.getAlumno());
		comboMateria.setSelectedItem(parcial.getMateria());

	}
}
