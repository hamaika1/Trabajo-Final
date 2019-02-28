package proyectoejemplo.ui;	

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import proyectoejemplo.ui.personas.PersonasPanelUI;

public class PrincipalUI extends JFrame {

	private static final long serialVersionUID = 1L;
	public static JPanel panelGeneral;
	public static PersonasPanelUI panelPersonas;

	/**
	 * Create the frame.
	 */
	public PrincipalUI() {
		/**
		 * Configuraciones generales de la ventana principal
		 */
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(1169, 703);
		setLocationRelativeTo(null);
		setBackground(new Color(0, 139, 139));
		setFont(new Font("Bradley HandITC", Font.BOLD, 14));
		setTitle("Visualizador de Personas");
		getContentPane().setLayout(null);

		/**
		 * Creacion de la ventana y objetos.
		 */
		panelGeneral = new JPanel();
		panelGeneral.setSize(899, 905);
		panelGeneral.setLayout(null);
		panelGeneral.setBounds(202, 0, 960, 652);
		panelGeneral.setBackground(new Color(102, 102, 102));
		panelGeneral.setVisible(true);
		getContentPane().add(panelGeneral);

		JPanel panelMenu = new JPanel();
		panelMenu.setForeground(new Color(204, 204, 255));
		panelMenu.setBounds(0, 0, 204, 674);
		panelMenu.setLayout(null);
		getContentPane().add(panelMenu);

		JButton btnHome = new JButton("");
		btnHome.setIcon(new ImageIcon(PrincipalUI.class.getResource("/proyectoejemplo/images/home.png")));
		btnHome.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 18));
		btnHome.setBounds(1, 0, 201, 45);
		panelMenu.add(btnHome);

		JButton btnPersonas = new JButton("Personas");
		btnPersonas.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 18));
		btnPersonas.setBounds(1, 45, 201, 45);
		panelMenu.add(btnPersonas);
		
		JLabel lblS = new JLabel("Ejercicio de Ejemplo para el Curso Introducción a la Programación");
		lblS.setHorizontalTextPosition(SwingConstants.CENTER);
		lblS.setForeground(new Color(0, 0, 0));
		lblS.setOpaque(true);
		lblS.setBackground(new Color(220, 220, 220));
		lblS.setHorizontalAlignment(SwingConstants.CENTER);
		lblS.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 13));
		lblS.setBounds(202, 648, 960, 26);
		getContentPane().add(lblS);

		/**
		 * Funciones de los botones
		 */
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HomePanelUI panelInicio = new HomePanelUI();
				actualizar(panelInicio);
			}
		});
		
		
		btnPersonas.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				panelPersonas = new PersonasPanelUI();
				actualizar(panelPersonas);
			}
		});
	}

	/**
	 * Permite remover el panel central del frame para colocar el panel enviado
	 * 
	 * @param panel
	 */
	public static void actualizar(JPanel panel) {
		panelGeneral.removeAll();
		panelGeneral.add(panel);
		panelGeneral.updateUI();
	}

}
