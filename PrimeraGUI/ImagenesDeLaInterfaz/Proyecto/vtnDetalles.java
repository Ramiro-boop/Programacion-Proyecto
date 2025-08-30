package Proyecto;

import java.awt.*;
import java.net.URL;

import javax.swing.*;

public class vtnDetalles extends JFrame {

	public vtnDetalles() {
		this.setTitle("VENTANA DETALLES");
		this.setSize(700, 520);
		URL iconURL = getClass().getResource("/Proyecto/logo.png");
		ImageIcon icon = new ImageIcon(iconURL);
		this.setIconImage(icon.getImage());
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		JPanel panelariba = new JPanel(new BorderLayout());
		JLabel texto = new JLabel("Detalles", SwingConstants.CENTER);
		texto.setSize(30, 40);
		texto.setFont(new Font("Arial", Font.BOLD, 18));

		panelariba.add(texto, BorderLayout.CENTER);

		JPanel panelColumnas = new JPanel(new GridLayout(1, 2, 10, 10));

		JPanel panel1 = new JPanel(new GridLayout(4, 1, 10, 10));
		panel1.setBackground(Color.GRAY);
		JLabel nombrelibro = new JLabel("Nombre Del Libro");
		JLabel autor = new JLabel("Autor");
		JLabel Descripcion = new JLabel("Descripcion");
		JLabel notas = new JLabel("Notas");

		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.GRAY);

		panelColumnas.add(panel1);
		panel1.add(nombrelibro);
		panel1.add(autor);
		panel1.add(Descripcion);
		panel1.add(notas);

		panelColumnas.add(panel2);
		JLabel masdetalles = new JLabel("MasDetalles");
		panel2.add(masdetalles);

		this.setLayout(new BorderLayout());
		this.add(panelariba, BorderLayout.NORTH);
		this.add(panelColumnas, BorderLayout.CENTER);

	}
}