package ar.com.mateo.PracticoFinal.App;

import java.awt.EventQueue;

import javax.swing.JFrame;

import org.jvnet.substance.SubstanceLookAndFeel;

import ar.com.mateo.PracticoFinal.UI.VentanaPrincipal;

public class AppAlumnos {

	public static void main(String[] args) {

		JFrame.setDefaultLookAndFeelDecorated(true);

		/**
		 * Launch the application.
		 */
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.ChallengerDeepSkin");
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
