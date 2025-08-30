package Proyecto;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.*;

public class vtnInicioSesion extends JFrame {

	public vtnInicioSesion() {
		this.setTitle("Inicie Sesión");
		this.setSize(600, 400);
		this.setLocationRelativeTo(null);
		URL iconURL = getClass().getResource("/Proyecto/logo.png");
		ImageIcon icon = new ImageIcon(iconURL);
		this.setIconImage(icon.getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);

		JPanel pInicio = new JPanel();
		// pInicio.setBackground(Color.LIGHT_GRAY);

		JPanel pDatos = new JPanel();
		// pDatos.setLayout(new BoxLayout(pDatos, BoxLayout.Y_AXIS));
		// pDatos.setBackground(Color.LIGHT_GRAY);

		JPanel pImagen = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		// pImagen.setBackground(Color.LIGHT_GRAY);

		JPanel pGrid = new JPanel(new GridLayout(1, 2));

		JLabel Bien = new JLabel("Bienvenido a la Bibloteca");
		Bien.setSize(30, 40);
		Bien.setFont(new Font("Arial", Font.BOLD, 40));

		JLabel Ini = new JLabel("Inicio sesión");
		Ini.setSize(30, 40);
		Ini.setFont(new Font("Arial", Font.BOLD, 29));

		JLabel Usu = new JLabel("                    Usuario:                    ");
		Usu.setSize(30, 40);
		Usu.setFont(new Font("Arial", Font.BOLD, 18));

		JLabel Con = new JLabel("                    Contraseña:                    ");
		Con.setSize(30, 40);
		Con.setFont(new Font("Arial", Font.BOLD, 18));

		JLabel V = new JLabel("                   ");
		JLabel V2 = new JLabel("                   ");
		JLabel V3 = new JLabel("                                                                                ");
		JLabel V4 = new JLabel("                                                                                ");
		JLabel V5 = new JLabel("                                                                                ");
		JLabel V6 = new JLabel("                                                                                ");

		// JLabel Hora = new JLabel("Hora: ");

		JTextField UsuT = new JTextField(10);
		// UsuT.setBackground(new Color(72,207,174));

		JPasswordField ConT = new JPasswordField(10);
		// ConT.setBackground(new Color(72,207,174));

		JButton IniB = new JButton("Iniciar sesión");
		IniB.setBackground(new Color(72, 207, 174));

		this.add(pInicio, BorderLayout.NORTH);

		pInicio.add(Bien);
		// pInicio.add(Hora,BorderLayout.EAST);
		this.add(pGrid);

		pGrid.add(pDatos);
		pDatos.add(V4);
		pDatos.add(Ini);
		pDatos.add(V5);
		pDatos.add(Usu);
		pDatos.add(UsuT);
		pDatos.add(V6);
		pDatos.add(Con);
		pDatos.add(V2);
		pDatos.add(ConT);
		pDatos.add(V);
		pDatos.add(V3);

		pDatos.add(IniB);

		pGrid.add(pImagen);// falta agregar imagen

		IniB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				vtnPaginaPrincipal vtn1 = new vtnPaginaPrincipal();
				vtn1.setVisible(true);

				vtnInicioSesion.this.dispose();

			}
		});

	}

}
