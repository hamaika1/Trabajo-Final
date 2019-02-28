package proyectoejemplo.ui.personas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.shaper.StandardButtonShaper;

import proyectoejemplo.bo.ControlesBO;
import proyectoejemplo.bo.PersonaBO;
import proyectoejemplo.exceptions.ValidarCamposObligatoriosException;
import proyectoejemplo.modelo.Persona;
import proyectoejemplo.ui.PrincipalUI;

public class AddPersonaUI extends JInternalFrame {

	private static final long serialVersionUID = 1L;

	private JTextField txtApellidos;
	private JTextField txtDNI;
	private JTextField txtEdad;
	private JTextField txtNombre;
	private JCheckBox chckbxcasado;
	private PersonaBO pBO = new PersonaBO();
	private boolean updatePerson = false;
	private Persona persona = new Persona();

	public AddPersonaUI() {
		getContentPane().setForeground(Color.WHITE);
		setClosable(true);
		getContentPane().setEnabled(false);

		setOpaque(true);
		setForeground(new Color(0, 0, 0));
		setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 12));
		setTitle("Agregar Persona");
		setBounds(150, 100, 662, 264);
		getContentPane().setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setBounds(12, 12, 279, 14);
		lblNombre.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 13));
		lblNombre.setBackground(Color.WHITE);
		getContentPane().add(lblNombre);
		
		txtNombre = new JTextField((String) null);
		txtNombre.setBounds(12, 29, 279, 28);
		txtNombre.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		txtNombre.setColumns(10);
		txtNombre.setBorder(null);
		getContentPane().add(txtNombre);
		
		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setForeground(Color.WHITE);
		lblApellidos.setFont(new Font("Dialog", Font.BOLD, 13));
		lblApellidos.setBackground(Color.WHITE);
		lblApellidos.setBounds(330, 12, 279, 14);
		getContentPane().add(lblApellidos);
		
		txtApellidos = new JTextField((String) null);
		txtApellidos.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtApellidos.setColumns(10);
		txtApellidos.setBorder(null);
		txtApellidos.setBounds(330, 29, 279, 28);
		getContentPane().add(txtApellidos);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setForeground(Color.WHITE);
		lblDni.setFont(new Font("Dialog", Font.BOLD, 13));
		lblDni.setBackground(Color.WHITE);
		lblDni.setBounds(12, 88, 213, 14);
		getContentPane().add(lblDni);
		
		txtDNI = new JTextField((String) null);
		txtDNI.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtDNI.setColumns(10);
		txtDNI.setBorder(null);
		txtDNI.setBounds(12, 105, 213, 28);
		getContentPane().add(txtDNI);
		
		chckbxcasado = new JCheckBox("¿Casado?");
		chckbxcasado.setFont(new Font("Dialog", Font.BOLD, 13));
		chckbxcasado.setForeground(Color.WHITE);
		chckbxcasado.setBounds(498, 107, 129, 23);
		getContentPane().add(chckbxcasado);
		
		JLabel lblEdad = new JLabel("Edad");
		lblEdad.setForeground(Color.WHITE);
		lblEdad.setFont(new Font("Dialog", Font.BOLD, 13));
		lblEdad.setBackground(Color.WHITE);
		lblEdad.setBounds(334, 88, 131, 14);
		getContentPane().add(lblEdad);
		
		txtEdad = new JTextField((String) null);
		txtEdad.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtEdad.setColumns(10);
		txtEdad.setBorder(null);
		txtEdad.setBounds(334, 105, 131, 28);
		txtEdad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (c < '0' || c > '9')
					e.consume();
			}
		});
		getContentPane().add(txtEdad);
		
		JButton btnSave = new JButton("Guardar");
		btnSave.putClientProperty(SubstanceLookAndFeel.BUTTON_SHAPER_PROPERTY, new StandardButtonShaper());
		btnSave.setBounds(427, 197, 89, 23);
		getContentPane().add(btnSave);

		JButton btnCancel = new JButton("Cancelar");
		btnCancel.setBounds(526, 197, 89, 23);
		btnCancel.putClientProperty(SubstanceLookAndFeel.BUTTON_SHAPER_PROPERTY, new StandardButtonShaper());
		getContentPane().add(btnCancel);
		
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Boolean error = false;

				try {
					ControlesBO.validarCampoObligatorio((String) txtNombre.getText());
					ControlesBO.validarCampoObligatorio((String) txtApellidos.getText());
					ControlesBO.validarCampoObligatorio((String) txtDNI.getText());
					ControlesBO.validarCampoObligatorio((String) txtEdad.getText());
					
				} catch (ValidarCamposObligatoriosException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error ", JOptionPane.ERROR_MESSAGE);
					error = true;
				}

				if (error == false) {

					persona.setNombre(txtNombre.getText());
					persona.setApellidos(txtApellidos.getText());
					persona.setDni(txtDNI.getText());
					persona.setEdad(Integer.parseInt(txtEdad.getText()));
					persona.setCasado(chckbxcasado.isSelected());
					
					if(updatePerson) {
						System.out.println(persona.getId());
						pBO.update(persona);
					} else {
						pBO.save(persona);
					}
					
					
					PrincipalUI.panelPersonas = new PersonasPanelUI();
					PrincipalUI.panelGeneral.removeAll();
					PrincipalUI.panelGeneral.repaint();
					PrincipalUI.panelGeneral.revalidate();
					PrincipalUI.actualizar(PrincipalUI.panelPersonas);

					dispose();

				}
			}

		});
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrincipalUI.panelGeneral.removeAll();
				PrincipalUI.panelGeneral.repaint();
				PrincipalUI.panelGeneral.revalidate();
				PrincipalUI.actualizar(PrincipalUI.panelPersonas);
				dispose();
			}
		});
		
	}
	
	public AddPersonaUI(Persona persona) {
		this();
		updatePerson = true;
		this.persona = persona;
		txtNombre.setText(persona.getNombre());
		txtApellidos.setText(persona.getApellidos());
		txtEdad.setText(Integer.toString(persona.getEdad()));
		txtDNI.setText(persona.getDni());
		chckbxcasado.setSelected(persona.isCasado());
	}
}
