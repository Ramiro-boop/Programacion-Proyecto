package Proyecto;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.*;

public class vtnRegistros extends JFrame {

	public vtnRegistros() {
		this.setTitle("Registros");
		this.setSize(1080, 720);
		this.setLocationRelativeTo(null);
		URL iconURL = getClass().getResource("/Proyecto/logo.png");
		ImageIcon icon = new ImageIcon(iconURL);
		this.setIconImage(icon.getImage());

		JPanel panelariba = new JPanel(new BorderLayout());

		ImageIcon imagenVolver = new ImageIcon(getClass().getResource("/Proyecto/Volver.png"));
		Image imagenVolver1 = imagenVolver.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		ImageIcon imagenVolver2 = new ImageIcon(imagenVolver1);

		JButton btnVolver = new JButton("volver", imagenVolver2);
		btnVolver.setBackground(new Color(72, 207, 174));
		panelariba.add(btnVolver, BorderLayout.WEST);
		JLabel text = new JLabel("REGISTROS", SwingConstants.CENTER);
		text.setSize(30, 40);
		text.setFont(new Font("Arial", Font.BOLD, 25));

		panelariba.add(text, BorderLayout.CENTER);

		JPanel panelColumnas = new JPanel(new GridLayout(1, 4, 10, 10));

		JPanel panel1 = new JPanel();
		panel1.setBackground(Color.GRAY);
		panel1.add(new JLabel("Eliminados"));

		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.GRAY);
		panel2.add(new JLabel("Agregados"));

		JPanel panel3 = new JPanel();
		panel3.setBackground(Color.GRAY);
		panel3.add(new JLabel("Editados"));

		JPanel panel4 = new JPanel();
		panel4.setBackground(Color.GRAY);
		panel4.add(new JLabel("Historial de Prestamos Libros"));

		JPanel panel5 = new JPanel();
		panel5.setBackground(Color.GRAY);
		panel5.add(new JLabel("Historial de Prestamos Ceibalitas"));

		panelColumnas.add(panel1);
		panelColumnas.add(panel2);
		panelColumnas.add(panel3);
		panelColumnas.add(panel4);
		panelColumnas.add(panel5);
		this.setLayout(new BorderLayout());
		this.add(panelariba, BorderLayout.NORTH);
		this.add(panelColumnas, BorderLayout.CENTER);

		btnVolver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				vtnRegistros.this.dispose();

			}
		});

	}
}
