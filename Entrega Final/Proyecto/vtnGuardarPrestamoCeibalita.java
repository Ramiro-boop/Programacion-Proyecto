package Proyecto;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

// Ventana para registrar un préstamo de Ceibalita
public class vtnGuardarPrestamoCeibalita extends JFrame {

	Logica gestor = new Logica(); // Instancia de la lógica
	int opcionRegistro = 1; // Opción para registrar acción en historial

	// Constructor de la ventana, recibe el modelo de la tabla
	public vtnGuardarPrestamoCeibalita(DefaultTableModel modeloPrestCeibalita) {

		// Configuración básica de la ventana
		this.setTitle("Prestar ceibalita");
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		// -------------------- PANEL SUPERIOR --------------------
		JPanel panelariba = new JPanel(new BorderLayout());
		panelariba.setBorder(new EmptyBorder(0, 30, 0, 30));
		JLabel lblTitulo = new JLabel("PRESTAR CEIBALITA", SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 25));
		panelariba.add(lblTitulo, BorderLayout.CENTER);

		// -------------------- PANEL CENTRAL CON CAMPOS --------------------
		JPanel panel1 = new JPanel(new GridLayout(3, 2, -90, 30));
		panel1.setBorder(new EmptyBorder(20, 10, 20, 20));

		// Número de Ceibalita
		JLabel lblNumCeibalita = new JLabel("Número Ceibalita:");
		lblNumCeibalita.setFont(new Font("Arial", 0, 15));
		JTextField tfNumCeibalita = new JTextField(20);
		tfNumCeibalita.setFont(new Font("Arial", 0, 15));
		tfNumCeibalita.setBackground(new Color(72, 207, 174));

		// CI del usuario
		JLabel lblCi = new JLabel("CI:");
		lblCi.setFont(new Font("Arial", 0, 15));
		JTextField tfCi = new JTextField(20);
		tfCi.setFont(new Font("Arial", 0, 15));
		tfCi.setBackground(new Color(72, 207, 174));

		// Hora de devolución
		JLabel lblHoraDev = new JLabel("Hora Devolucion:");
		lblHoraDev.setFont(new Font("Arial", 0, 15));
		JTextField tfHoraDev = new PlaceholderTextField("HH:mm");
		tfHoraDev.setText("18:25");
		tfHoraDev.setFont(new Font("Arial", 0, 15));
		tfHoraDev.setBackground(new Color(72, 207, 174));

		panel1.add(lblNumCeibalita);
		panel1.add(tfNumCeibalita);
		panel1.add(lblCi);
		panel1.add(tfCi);
		panel1.add(lblHoraDev);
		panel1.add(tfHoraDev);

		// -------------------- PANEL INFERIOR CON BOTONES --------------------
		JPanel panelinf = new JPanel(new BorderLayout());
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBackground(new Color(119, 221, 119));
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(new Color(220, 80, 80));

		JPanel panelAbajo2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelAbajo2.setBorder(new EmptyBorder(0, 0, 5, 5));
		panelAbajo2.add(btnCancelar);
		panelAbajo2.add(btnGuardar);
		panelinf.add(panelAbajo2, BorderLayout.SOUTH);

		// -------------------- AGREGAR PANEL A VENTANA --------------------
		this.setLayout(new BorderLayout());
		this.add(panelariba, BorderLayout.NORTH);
		this.add(panel1, BorderLayout.CENTER);
		this.add(panelinf, BorderLayout.SOUTH);

		// -------------------- VALIDACIÓN DE TEXTO --------------------
		// Solo números en Número Ceibalita
		tfNumCeibalita.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				char tecla = e.getKeyChar();
				if (tecla < '0' || tecla > '9' && tecla != KeyEvent.VK_BACK_SPACE)
					e.consume();
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}
		});

		// Solo números en CI
		tfCi.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				char tecla = e.getKeyChar();
				if (tecla < '0' || tecla > '9' && tecla != KeyEvent.VK_BACK_SPACE)
					e.consume();
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}
		});

		// Validación de hora de devolución
		tfHoraDev.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				char tecla = e.getKeyChar();
				if ((tecla < '0' || tecla > '9') && tecla != KeyEvent.VK_BACK_SPACE)
					e.consume();
				if (tfHoraDev.getText().length() >= 5)
					e.consume();
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// Formateo automático HH:mm
				String texto = tfHoraDev.getText().replaceAll("[^0-9]", "");
				if (texto.length() > 2)
					texto = texto.substring(0, 2) + ":" + texto.substring(2);
				if (texto.length() > 5)
					texto = texto.substring(0, 5);
				tfHoraDev.setText(texto);
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}
		});

		// -------------------- ACCIONES BOTONES --------------------
		btnGuardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String horaDev = tfHoraDev.getText();

				if (horaDev.length() == 5) {

					String primerosDos = horaDev.substring(0, 2); // índice 0 inclusive a 2 exclusive
					int numPrimeros = Integer.parseInt(primerosDos); // convertimos a número

					// Últimos dos dígitos
					String ultimosDos = horaDev.substring(3, 5); // del índice 3 al 4
					int numUltimos = Integer.parseInt(ultimosDos);

					if (numPrimeros < 24 && numUltimos < 60) {

						DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("HH:mm");
						DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
						LocalTime hora = LocalTime.parse(horaDev, inputFormat);
						String horaSQL = hora.format(outputFormat);
						LocalDate hoy = LocalDate.now();

						if (gestor.altaPrestamoCeibalita(tfNumCeibalita, tfCi, hoy, horaSQL)) {
							int numCompu = Integer.parseInt(tfNumCeibalita.getText());
							int ci = Integer.parseInt(tfCi.getText());
							gestor.RegistroPrestamoCompu(opcionRegistro, numCompu, ci);

							JOptionPane.showMessageDialog(null, "Prestamo guardado correctamente");
							gestor.ListarPrestamoCeibalita(modeloPrestCeibalita);
							vtnGuardarPrestamoCeibalita.this.dispose();

						}
					} else {

						JOptionPane.showMessageDialog(null, "Error: 'Hora devolución' invalida");

					}
				} else {
					JOptionPane.showMessageDialog(null, "Error: 'Hora devolución' invalida");
				}
			}
		});

		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vtnGuardarPrestamoCeibalita.this.dispose();
			}
		});
	}
}
