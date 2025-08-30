package Proyecto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class vtnNotificaciones extends JFrame {

	public vtnNotificaciones() {
		this.setSize(400, 450);
		this.setLocationRelativeTo(null);
		URL iconURL = getClass().getResource("/Proyecto/logo.png");
		ImageIcon icon = new ImageIcon(iconURL);
		this.setIconImage(icon.getImage());
		this.setResizable(false);

		JPanel pInicio = new JPanel();
		pInicio.setBackground(new Color(72, 207, 174));

		JPanel pDatos = new JPanel();
		pDatos.setBackground(new Color(72, 207, 174));

		JLabel Not = new JLabel("Notificaciones");
		Not.setSize(30, 40);
		Not.setFont(new Font("Arial", Font.BOLD, 40));

		this.add(pInicio, BorderLayout.NORTH);
		pInicio.add(Not);

		this.add(pDatos);

	}
}