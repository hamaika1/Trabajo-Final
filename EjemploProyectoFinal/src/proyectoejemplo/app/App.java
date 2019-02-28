package proyectoejemplo.app;

import java.awt.EventQueue;

import javax.swing.JFrame;

import org.jvnet.substance.SubstanceLookAndFeel;

import proyectoejemplo.ui.PrincipalUI;

public class App {

	public static void main(String[] args) {

		JFrame.setDefaultLookAndFeelDecorated(true);

		/**
		 * Launch the application.
		 */
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.MagmaSkin");
					PrincipalUI frame = new PrincipalUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

}
