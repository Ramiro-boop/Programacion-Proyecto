package Proyecto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class vtnGuardarPrestamoCeibalita extends JFrame {

	public vtnGuardarPrestamoCeibalita() {

		this.setTitle("Guardar prestamo ceibalita");// Establece el título de la ventana
		this.setSize(1200, 700);// Tamaño en PX. Primero el ancho y luego el largo
		this.setLocationRelativeTo(null);
		URL iconURL = getClass().getResource("/Proyecto/logo.png");
		ImageIcon icon = new ImageIcon(iconURL);
		this.setIconImage(icon.getImage());
		this.setResizable(false);

		JPanel panelArriba = new JPanel(new GridLayout(1, 3));// North

		JPanel panelIzq = new JPanel();

		JPanel panelCentro = new JPanel(new GridLayout(9, 4));// Centro

		JPanel panelAbajo = new JPanel(new BorderLayout());// Sur

		JPanel panelAbajo2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		JLabel lbl0 = new JLabel("");

		JLabel lbl1 = new JLabel("Prestar Ceibalita", SwingConstants.CENTER);
		lbl1.setOpaque(true);
		lbl1.setBackground(new Color(72, 207, 174));
		lbl1.setSize(30, 40);
		lbl1.setFont(new Font("Arial", Font.BOLD, 30));

		JLabel lblHora = new JLabel("Hora:", SwingConstants.RIGHT);

		JLabel lbl2 = new JLabel("Nombre:  *");
		lbl2.setFont(new Font("Arial", 0, 25));
		JLabel lbl3 = new JLabel("CI:  *");
		lbl3.setFont(new Font("Arial", 0, 25));
		JLabel lbl4 = new JLabel("Codigo compu:  *");
		lbl4.setFont(new Font("Arial", 0, 25));
		JLabel lbl5 = new JLabel("Fecha prestamo:  *");
		lbl5.setFont(new Font("Arial", 0, 25));
		JLabel lbl6 = new JLabel("Fecha limite:  *");
		lbl6.setFont(new Font("Arial", 0, 25));
		JLabel lbl7 = new JLabel("Estado:");
		lbl7.setFont(new Font("Arial", 0, 25));

		JTextField tf1 = new JTextField();
		tf1.setBackground(new Color(72, 207, 174));
		JTextField tf2 = new JTextField();
		tf2.setBackground(new Color(72, 207, 174));
		JTextField tf3 = new JTextField();
		tf3.setBackground(new Color(72, 207, 174));
		JTextField tf4 = new JTextField();
		tf4.setBackground(new Color(72, 207, 174));
		JTextField tf5 = new JTextField();
		tf5.setBackground(new Color(72, 207, 174));
		JComboBox cb1 = new JComboBox();
		cb1.setBackground(new Color(72, 207, 174));

		JButton btn1 = new JButton("Descartar");// TEngo que cambiar el tamaño

		JButton btn2 = new JButton("Aplicar");

		ImageIcon imagen = new ImageIcon(getClass().getResource("/Proyecto/PrestamoPC.png"));
		JLabel lblImage = new JLabel(imagen);
		lblImage.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(227, 204, Image.SCALE_SMOOTH)));

		this.add(panelArriba, BorderLayout.NORTH);

		panelArriba.add(lbl0);
		panelArriba.add(lbl1);
		panelArriba.add(lblHora);

		this.add(panelIzq, BorderLayout.WEST);
		panelIzq.add(lblImage);

		this.add(panelCentro, BorderLayout.CENTER);

		panelCentro.add(lbl2);

		panelCentro.add(lbl3);
		panelCentro.add(tf1);
		panelCentro.add(tf2);
		panelCentro.add(lbl4);

		panelCentro.add(lbl5);
		panelCentro.add(tf3);
		panelCentro.add(tf4);
		panelCentro.add(lbl6);

		panelCentro.add(lbl7);
		panelCentro.add(tf5);
		panelCentro.add(cb1);

		this.add(panelAbajo, BorderLayout.SOUTH);

		panelAbajo.add(panelAbajo2, BorderLayout.SOUTH);
		panelAbajo2.add(btn1);
		panelAbajo2.add(btn2);

		btn1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				vtnGuardarPrestamoCeibalita.this.dispose();

			}
		});

		btn2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				vtnGuardarPrestamoCeibalita.this.dispose();

			}
		});
	}

}
