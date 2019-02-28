package proyectoejemplo.ui.personas;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.shaper.StandardButtonShaper;

import proyectoejemplo.bo.ControlesBO;
import proyectoejemplo.bo.PersonaBO;
import proyectoejemplo.exceptions.ValidarSeleccionListaException;
import proyectoejemplo.modelo.Persona;
import proyectoejemplo.ui.PrincipalUI;

import javax.swing.UIManager;

public class PersonasPanelUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private List<Persona> listaPesonas = new ArrayList<Persona>();
	private PersonaBO pBO = new PersonaBO();
	private TableRowSorter<TableModel> trsfiltro;
	private JTextField textFieldBuscar;
	
	public PersonasPanelUI() {
		setLayout(null);
		setBounds(2, 0, 960, 626);
		setBackground(new Color(102, 102, 102));
		
		
		JLabel lblPersonas = new JLabel("PERSONAS");
		lblPersonas.setForeground(Color.WHITE);
		lblPersonas.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 22));
		lblPersonas.setBounds(31, 42, 268, 32);
		add(lblPersonas);
		
		
		/**
		 * Defino mis botones
		 */
		JButton btnAgregar = new JButton("Agregar Persona");
		btnAgregar.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 15));
		btnAgregar.setBounds(157, 529, 173, 48);
		btnAgregar.putClientProperty(SubstanceLookAndFeel.BUTTON_SHAPER_PROPERTY, new StandardButtonShaper());
		add(btnAgregar);

		JButton btnEliminar = new JButton("Eliminar Persona");
		btnEliminar.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 15));
		btnEliminar.setBounds(634, 529, 173, 48);
		btnEliminar.putClientProperty(SubstanceLookAndFeel.BUTTON_SHAPER_PROPERTY, new StandardButtonShaper());
		add(btnEliminar);

		JButton btnModificar = new JButton("Modificar Persona");
		btnModificar.putClientProperty(SubstanceLookAndFeel.BUTTON_SHAPER_PROPERTY, new StandardButtonShaper());
		btnModificar.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 15));
		btnModificar.setBounds(393, 529, 173, 48);
		add(btnModificar);
		
		
		/**
		 * Defino mi tabla donde se listaran los datos
		 */
		String[] columnas = { "Nombre", "Apellido", "DNI", "Edad", "Casado"};
		JTable tabla = new JTable() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false;
			}
		};
		tabla.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.setColumnIdentifiers(columnas);
		tabla.setModel(modelo);
		
		listaPesonas = pBO.list();
		for (Persona p : listaPesonas) {
			modelo.addRow(new Object[] { p.getNombre(), p.getApellidos(), p.getDni(), p.getEdad(), 
					p.isCasado() ? "Si" : "No" });
		}
		
		JScrollPane scrollPane = new JScrollPane(tabla, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 13));
		scrollPane.setBounds(20, 85, 919, 423);
		add(scrollPane);
		scrollPane.setViewportView(tabla);
		
		
		/**
		 * Filtro
		 */
		textFieldBuscar = new JTextField((String) null);
		textFieldBuscar.setBackground(Color.WHITE);
		textFieldBuscar.setCaretColor(Color.WHITE);
		textFieldBuscar.setHorizontalAlignment(SwingConstants.LEFT);
		textFieldBuscar.setColumns(10);
		textFieldBuscar.setBorder(UIManager.getBorder("Menu.border"));
		textFieldBuscar.setBounds(564, 38, 304, 20);
		add(textFieldBuscar);
		textFieldBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(final KeyEvent e) {
				String cadena = (textFieldBuscar.getText());
				textFieldBuscar.setText(cadena);
				repaint();
				filtrar();
			}
		});

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(PersonasPanelUI.class.getResource("/proyectoejemplo/images/lupa (1).png")));
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 14));
		label.setBounds(533, 28, 38, 46);
		add(label);
		trsfiltro = new TableRowSorter<TableModel>(tabla.getModel());
		tabla.setRowSorter(trsfiltro);
		
		/**
		 * Funcionalidades
		 */
		
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PrincipalUI.panelGeneral.removeAll();
				PrincipalUI.panelGeneral.updateUI();

				AddPersonaUI frame = new AddPersonaUI();
				frame.setVisible(true);
				PrincipalUI.panelGeneral.add(frame);
			}
		});
		
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				int row;
				try {
					row = tabla.getSelectedRow();
					ControlesBO.validarSeleccionLista(row);
					int modelRow = tabla.convertRowIndexToModel(row);
					Persona persona = new Persona();

					String personaModificar = (String) modelo.getValueAt(modelRow, 2);
					for (Persona p : listaPesonas) {
						if (p.getDni().equals(personaModificar)) {
							persona = p;
						}
					}
					System.out.println(persona.getId());
					PrincipalUI.panelGeneral.removeAll();
					PrincipalUI.panelGeneral.updateUI();

					AddPersonaUI frame = new AddPersonaUI(persona);
					frame.setVisible(true);
					PrincipalUI.panelGeneral.add(frame);

				} catch (ValidarSeleccionListaException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage() + " la persona a modificar", "",
							JOptionPane.WARNING_MESSAGE);
				}

			}
		});
		
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row;
				try {
					row = tabla.getSelectedRow();
					ControlesBO.validarSeleccionLista(row);
					int modelRow = tabla.convertRowIndexToModel(row);
					Persona persona = new Persona();

					String dniPersonaEliminar = (String) modelo.getValueAt(modelRow, 2);

					for (Persona p : listaPesonas) {
						if (p.getDni().equals(dniPersonaEliminar)) {
							persona = p;
						}
					}

					int opcion = JOptionPane.showConfirmDialog(null,
							"¿Está seguro que deseas eliminar la persona " + persona + "?", "",
							JOptionPane.YES_NO_OPTION);
					if (opcion == JOptionPane.OK_OPTION) {
							pBO.eliminar(persona);
							modelo.removeRow(tabla.getSelectedRow());
					}

				} catch (ValidarSeleccionListaException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage() + "la persona que desea eliminar.", "",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});

	}
	public void filtrar() {
		trsfiltro.setRowFilter(RowFilter.regexFilter("(?i)"+textFieldBuscar.getText(), 0));
	}

}
