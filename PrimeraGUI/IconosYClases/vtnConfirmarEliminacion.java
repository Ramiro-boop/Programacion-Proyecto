package Proyecto;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.*;

public class vtnConfirmarEliminacion extends JFrame {

	public vtnConfirmarEliminacion() {
		this.setTitle("Confirmacion");
		this.setSize(600, 200);
		URL iconURL = getClass().getResource("/Proyecto/logo.png");
		ImageIcon icon = new ImageIcon(iconURL);
		this.setIconImage(icon.getImage());
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		JPanel pInicio = new JPanel();
		pInicio.setBackground(new Color(72, 207, 174));

		JPanel pBoton1 = new JPanel();
		pBoton1.setBackground(new Color(72, 207, 174));

		JPanel pBoton2 = new JPanel();
		pBoton2.setBackground(new Color(72, 207, 174));

		JPanel pGrid = new JPanel(new GridLayout(1, 2));

		JLabel Desea = new JLabel("¿Esta seguro de eliminar ...: “");
		Desea.setSize(30, 40);
		Desea.setFont(new Font("Arial", Font.BOLD, 24));

		JLabel Nombre = new JLabel("");
		Nombre.setSize(30, 40);
		Nombre.setFont(new Font("Arial", Font.BOLD, 24));

		JLabel V = new JLabel("                                 ");
		JLabel V2 = new JLabel("                                ");
		JLabel V3 = new JLabel("                                ");
		JLabel V4 = new JLabel("                                ");
		JLabel V5 = new JLabel("                                ");
		JLabel V6 = new JLabel("                                ");
		JLabel V7 = new JLabel("                    ");

		JLabel iV = new JLabel("                                 ");
		JLabel iV2 = new JLabel("                                ");
		JLabel iV3 = new JLabel("                                ");
		JLabel iV4 = new JLabel("                                ");
		JLabel iV5 = new JLabel("                                ");
		JLabel iV6 = new JLabel("                                ");
		JLabel iV7 = new JLabel("                ");

		JButton btnNo = new JButton("No Eliminar");

		JButton btnSi = new JButton("  Eliminar  ");

		this.add(pInicio, BorderLayout.NORTH);
		pInicio.add(Desea);
		pInicio.add(Nombre);

		this.add(pGrid);
		pGrid.add(pBoton1);
		pBoton1.add(V);
		pBoton1.add(V2);
		pBoton1.add(V3);
		pBoton1.add(V4);
		pBoton1.add(V5);
		pBoton1.add(V6);
		pBoton1.add(V7);
		pBoton1.add(btnNo);

		pGrid.add(pBoton2);
		pBoton2.add(iV);
		pBoton2.add(iV2);
		pBoton2.add(iV3);
		pBoton2.add(iV4);
		pBoton2.add(iV5);
		pBoton2.add(iV6);
		pBoton2.add(iV7);
		pBoton2.add(btnSi);

		btnNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				vtnConfirmarEliminacion.this.dispose();

			}
		});

		btnSi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(null, " Eliminado");

				vtnConfirmarEliminacion.this.dispose();

			}
		});

	}

}
