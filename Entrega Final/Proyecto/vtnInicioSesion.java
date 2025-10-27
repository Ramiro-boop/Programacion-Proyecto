package Proyecto;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.security.Key;

import javax.swing.*;

public class vtnInicioSesion extends JFrame {

	public vtnInicioSesion() {
		this.setTitle("Inicio Sesión");
		this.setSize(600, 400);
		this.setLocationRelativeTo(null);
		URL iconURL = getClass().getResource("/Proyecto/logo.png");
		ImageIcon icon = new ImageIcon(iconURL);
		this.setIconImage(icon.getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);

		JPanel pInicio = new JPanel();

		JPanel pDatos = new JPanel();

		JPanel pImagen = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		JPanel pGrid = new JPanel(new GridLayout(1, 2));

		JLabel Bien = new JLabel("Bienvenido a la Biblioteca");
		Bien.setSize(30, 40);
		Bien.setFont(new Font("Arial", Font.BOLD, 40));

		JLabel Ini = new JLabel("Inicio sesión");
		Ini.setSize(30, 40);
		Ini.setFont(new Font("Arial", Font.BOLD, 29));

		ImageIcon imagen = new ImageIcon(getClass().getResource("/Proyecto/logo.png"));
		Image imagen1 = imagen.getImage().getScaledInstance(300, 250, Image.SCALE_SMOOTH);
		ImageIcon imagen2 = new ImageIcon(imagen1);

		JLabel lbl1 = new JLabel(imagen2);

		JLabel Con = new JLabel("                    Contraseña:                    ");
		Con.setSize(100, 120);
		Con.setFont(new Font("Arial", Font.BOLD, 28));

		JLabel V = new JLabel("                                                                                 ");
		JLabel V2 = new JLabel("                                                                                ");
		JLabel V3 = new JLabel("                                                                                ");
		JLabel V4 = new JLabel("                                                                                ");
		JLabel V5 = new JLabel("                                                                                ");
		JLabel V6 = new JLabel("                                                                                   ");

		JPasswordField ConT = new JPasswordField();
		ConT.setPreferredSize(new Dimension(200, 30));

		JButton IniB = new JButton("Iniciar sesión");
		IniB.setFont(new Font("Arial", Font.BOLD, 18));
		IniB.setPreferredSize(new Dimension(160, 40));
		IniB.setBackground(new Color(72, 207, 174));

		this.add(pInicio, BorderLayout.NORTH);

		pInicio.add(Bien);
		this.add(pGrid);

		pGrid.add(pDatos);
		pDatos.add(V4);

		pDatos.add(V5);

		pDatos.add(V6);
		pDatos.add(Con);
		pDatos.add(V2);
		pDatos.add(ConT);
		pDatos.add(V);
		pDatos.add(V3);

		pDatos.add(IniB);

		pGrid.add(pImagen);
		pImagen.add(lbl1);

		ConT.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				char tecla = e.getKeyChar();
				if (tecla == KeyEvent.VK_ENTER) {

					String valorPass = new String(ConT.getPassword());// la encontre en una pagina media rara

					String contraseña = "alvarofigueredo";

					if (valorPass.equals(contraseña)) {
						vtnPaginaPrincipal vtn1 = new vtnPaginaPrincipal();
						vtn1.setVisible(true);

						vtnInicioSesion.this.dispose();

					} else {

						JOptionPane.showMessageDialog(null, "Contraseña Incorrecta");
						ConT.setText(null);

					}

				}

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {

			}
		});

		IniB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String valorPass = new String(ConT.getPassword());// la encontre en una pagina media rara

				String contraseña = "alvarofigueredo";// alvarofigueredo

				if (valorPass.equals(contraseña)) {
					vtnPaginaPrincipal vtn1 = new vtnPaginaPrincipal();
					vtn1.setVisible(true);

					vtnInicioSesion.this.dispose();

				} else {

					JOptionPane.showMessageDialog(null, "Contraseña Incorrecta");
					ConT.setText(null);

				}

			}
		});

	}

}
