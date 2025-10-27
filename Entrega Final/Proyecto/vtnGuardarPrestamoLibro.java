package Proyecto;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

// Ventana para registrar un préstamo de libro
public class vtnGuardarPrestamoLibro extends JFrame {

	Logica gestor = new Logica(); // Instancia de la lógica
	int opcionRegistro = 1; // Opción para registrar acción en historial

	// Constructor recibe el modelo de la tabla de préstamos
	public vtnGuardarPrestamoLibro(DefaultTableModel modeloPrestLibro) {
		// Configuración de la ventana
		this.setTitle("Guardar préstamo libro");
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);

		// -------------------- PANEL SUPERIOR --------------------
		JPanel panelArriba = new JPanel(new BorderLayout());
		panelArriba.setBorder(new EmptyBorder(10, 30, 0, 30));
		JLabel lblTitulo = new JLabel("PRESTAR LIBRO", SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 25));
		panelArriba.add(lblTitulo, BorderLayout.CENTER);

		// -------------------- PANEL CENTRAL CON CAMPOS --------------------
		JPanel panelCampos = new JPanel(new GridLayout(4, 2, -85, 20));
		panelCampos.setBorder(new EmptyBorder(10, -60, 10, 15));

		// ISBN del libro
		JLabel lblIsbn = new JLabel("ISBN:", SwingConstants.CENTER);
		lblIsbn.setFont(new Font("Arial", 0, 15));
		JTextField tfIsbn = new JTextField(5);
		tfIsbn.setFont(new Font("Arial", 0, 15));
		tfIsbn.setBackground(new Color(72, 207, 174));

		// CI del usuario
		JLabel lblCi = new JLabel("CI:", SwingConstants.CENTER);
		lblCi.setFont(new Font("Arial", 0, 15));
		JTextField tfCi = new JTextField(5);
		tfCi.setFont(new Font("Arial", 0, 15));
		tfCi.setBackground(new Color(72, 207, 174));

		// Cantidad de libros
		JLabel lblCantidad = new JLabel("Cantidad:", SwingConstants.CENTER);
		lblCantidad.setFont(new Font("Arial", 0, 15));
		SpinnerNumberModel modeloCantidad = new SpinnerNumberModel(1, 1, 100, 1);
		JSpinner spCantidad = new JSpinner(modeloCantidad);
		DiseñoJSpinner.aplicarEstiloNumerico(spCantidad, new Color(72, 207, 174), Color.BLACK);

		// Fecha límite de devolución
		JLabel lblFechaLimite = new JLabel("Fecha Limite:", SwingConstants.CENTER);
		lblFechaLimite.setFont(new Font("Arial", 0, 15));
		JTextField tfFechaLimite = new PlaceholderTextField("dd-MM-yyyy");
		tfFechaLimite.setFont(new Font("Arial", 0, 15));
		tfFechaLimite.setBackground(new Color(72, 207, 174));

		// Agregar componentes al panel central
		panelCampos.add(lblIsbn);
		panelCampos.add(tfIsbn);
		panelCampos.add(lblCi);
		panelCampos.add(tfCi);
		panelCampos.add(lblCantidad);
		panelCampos.add(spCantidad);
		panelCampos.add(lblFechaLimite);
		panelCampos.add(tfFechaLimite);

		// -------------------- PANEL INFERIOR CON BOTONES --------------------
		JPanel panelInf = new JPanel(new BorderLayout());
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBackground(new Color(119, 221, 119));
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(new Color(220, 80, 80));

		JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelBotones.add(btnCancelar);
		panelBotones.add(btnGuardar);
		panelInf.add(panelBotones, BorderLayout.SOUTH);

		// -------------------- AGREGAR PANEL A VENTANA --------------------
		this.setLayout(new BorderLayout());
		this.add(panelArriba, BorderLayout.NORTH);
		this.add(panelCampos, BorderLayout.CENTER);
		this.add(panelInf, BorderLayout.SOUTH);

		// -------------------- VALIDACIONES --------------------
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

		// Validación y formateo de la fecha límite
		tfFechaLimite.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				char tecla = e.getKeyChar();
				if ((tecla < '0' || tecla > '9') && tecla != KeyEvent.VK_BACK_SPACE)
					e.consume();
				if (tfFechaLimite.getText().length() >= 10)
					e.consume(); // limitar a 10 caracteres
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// Formatear automáticamente "dd-MM-yyyy"
				String texto = tfFechaLimite.getText().replaceAll("[^0-9]", "");
				if (texto.length() > 2 && texto.length() <= 4)
					texto = texto.substring(0, 2) + "-" + texto.substring(2);
				else if (texto.length() > 4)
					texto = texto.substring(0, 2) + "-" + texto.substring(2, 4) + "-" + texto.substring(4);
				if (texto.length() > 10)
					texto = texto.substring(0, 10);
				tfFechaLimite.setText(texto);
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}
		});

		// -------------------- ACCIONES BOTONES --------------------
		btnGuardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String isbn = tfIsbn.getText();
				int cantidad = (int) spCantidad.getValue();
				String fechaStr = tfFechaLimite.getText();

				if (fechaStr.length() == 10) {
					DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
					DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					LocalDate fecha = LocalDate.parse(fechaStr, inputFormat);
					String fechaSQL = fecha.format(outputFormat);

					LocalDate hoy = LocalDate.now();

					// La fecha límite no puede ser anterior a hoy
					if (fecha.isAfter(hoy) || fecha.isEqual(hoy)) {
						if (gestor.altaPrestamoLibro(isbn, tfCi, cantidad, hoy, fechaSQL)) {
							int ci = Integer.parseInt(tfCi.getText());
							gestor.RegistroPrestamoLibro(opcionRegistro, isbn, ci);
							JOptionPane.showMessageDialog(null, "Préstamo guardado correctamente");
							gestor.ListarPrestamoLibro(modeloPrestLibro);
							vtnGuardarPrestamoLibro.this.dispose();
						}
					} else {
						JOptionPane.showMessageDialog(null, "'Fecha límite inválida'");
					}
				} else {
					JOptionPane.showMessageDialog(null, "'Fecha límite inválida'");
				}
			}
		});

		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vtnGuardarPrestamoLibro.this.dispose();
			}
		});
	}
}
