package ar.com.mateo.PracticoFinal.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.shaper.StandardButtonShaper;

import ar.com.mateo.PracticoFinal.BO.MateriaBO;
import ar.com.mateo.PracticoFinal.BO.ControlesBO;
import ar.com.mateo.PracticoFinal.Exceptions.ValidarSeleccionException;
import ar.com.mateo.PracticoFinal.Model.Materia;

public class PanelMateriasUI extends JPanel {
	private static final long serialVersionUID = 1L;
	private List<Materia> listaMaterias = new ArrayList<Materia>();
	private MateriaBO mBO = new MateriaBO();
	private TableRowSorter<TableModel> trsfiltro;
	private JTextField textFieldBuscar;

	public PanelMateriasUI() {
		setLayout(null);
		setBounds(2, 0, 960, 626);
		setBackground(new Color(102, 102, 102));

		JLabel lblPersonas = new JLabel("MATERIAS");
		lblPersonas.setForeground(Color.WHITE);
		lblPersonas.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 22));
		lblPersonas.setBounds(31, 42, 268, 32);
		add(lblPersonas);

		/**
		 * Defino mis botones
		 */
		JButton btnAgregar = new JButton("Agregar Materia");
		btnAgregar.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 15));
		btnAgregar.setBounds(157, 529, 173, 48);
		btnAgregar.putClientProperty(SubstanceLookAndFeel.BUTTON_SHAPER_PROPERTY, new StandardButtonShaper());
		add(btnAgregar);

		JButton btnEliminar = new JButton("Eliminar Materia");
		btnEliminar.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 15));
		btnEliminar.setBounds(634, 529, 173, 48);
		btnEliminar.putClientProperty(SubstanceLookAndFeel.BUTTON_SHAPER_PROPERTY, new StandardButtonShaper());
		add(btnEliminar);

		JButton btnModificar = new JButton("Modificar Materia");
		btnModificar.putClientProperty(SubstanceLookAndFeel.BUTTON_SHAPER_PROPERTY, new StandardButtonShaper());
		btnModificar.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 15));
		btnModificar.setBounds(393, 529, 173, 48);
		add(btnModificar);

		/**
		 * Defino mi tabla donde se listaran los datos
		 */
		String[] columnas = { "Materia", "Alumno", "Horario"};
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

		listaMaterias = mBO.list();
		for (Materia m : listaMaterias) {
			modelo.addRow(new Object[] { m.getNombre(), m.getAlumno(), m.getHorario() });
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
		textFieldBuscar.setVisible(true);
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
				VentanaPrincipal.panelGeneral.removeAll();
				VentanaPrincipal.panelGeneral.updateUI();

				AgregraMaterias frame = new AgregraMaterias();
				frame.setVisible(true);
				VentanaPrincipal.panelGeneral.add(frame);
			}
		});

		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int row;
				try {
					row = tabla.getSelectedRow();
					ControlesBO.validarSeleccionLista(row);
					int modelRow = tabla.convertRowIndexToModel(row);
					Materia materia = new Materia();

					String materiaModificar = (String) modelo.getValueAt(modelRow, 0);
					for (Materia m : listaMaterias) {
						if (m.getNombre().equals(materiaModificar)) {
							materia = m;
						}
					}
					System.out.println(materia.getId());
					VentanaPrincipal.panelGeneral.removeAll();
					VentanaPrincipal.panelGeneral.updateUI();

					AgregraMaterias frame = new AgregraMaterias(materia);
					frame.setVisible(true);
					VentanaPrincipal.panelGeneral.add(frame);

				} catch (ValidarSeleccionException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage() + " la materia a modificar", "",
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
					Materia materia = new Materia();

					String nombreMateriaEliminar = (String) modelo.getValueAt(modelRow, 0);

					for (Materia m : listaMaterias) {
						if (m.getNombre().equals(nombreMateriaEliminar)) {
							materia = m;
						}
					}

					int opcion = JOptionPane.showConfirmDialog(null,
							"¿Está seguro que deseas eliminar la materia " + materia + "?", "",
							JOptionPane.YES_NO_OPTION);
					if (opcion == JOptionPane.OK_OPTION) {
						mBO.eliminar(materia);
						modelo.removeRow(tabla.getSelectedRow());
					}

				} catch (ValidarSeleccionException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage() + "la persona que desea eliminar.", "",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});

	}

	public void filtrar() {
		trsfiltro.setRowFilter(RowFilter.regexFilter("(?i)" + textFieldBuscar.getText(), 1));
	}

}
