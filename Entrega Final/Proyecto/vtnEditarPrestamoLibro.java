package Proyecto;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class vtnEditarPrestamoLibro extends JFrame {

	Logica gestor = new Logica();
	int opcionRegistro = 3; // Identifica la acción como "editar"

	public vtnEditarPrestamoLibro(DefaultTableModel modeloPrestLibro, String isbn1, String ci1, int canidad1,
			String fechaLimite1) {

		// ================= CONFIGURACIÓN DE LA VENTANA =================
		this.setTitle("Editar Prestamo Libro");
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);

		// ================= PANEL SUPERIOR (Título) =================
		JPanel panelArriba = new JPanel(new BorderLayout());
		panelArriba.setBorder(new EmptyBorder(10, 30, 0, 30));
		JLabel lblTitulo = new JLabel("EDITAR PRESTAMO LIBRO", SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 25));
		panelArriba.add(lblTitulo, BorderLayout.CENTER);

		// ================= PANEL CENTRAL (Campos de entrada) =================
		JPanel panelCampos = new JPanel(new GridLayout(4, 2, -85, 20));
		panelCampos.setBorder(new EmptyBorder(10, -60, 10, 15));

		JLabel lblIsbn = new JLabel("ISBN:", SwingConstants.CENTER);
		lblIsbn.setFont(new Font("Arial", 0, 15));
		JTextField tfIsbn = new JTextField(5);
		tfIsbn.setFont(new Font("Arial", 0, 15));
		tfIsbn.setBackground(new Color(72, 207, 174));

		JLabel lblCi = new JLabel("CI:", SwingConstants.CENTER);
		lblCi.setFont(new Font("Arial", 0, 15));
		JTextField tfCi = new JTextField(5);
		tfCi.setFont(new Font("Arial", 0, 15));
		tfCi.setBackground(new Color(72, 207, 174));

		JLabel lblCantidad = new JLabel("Cantidad:", SwingConstants.CENTER);
		lblCantidad.setFont(new Font("Arial", 0, 15));
		SpinnerNumberModel modeloSpinner = new SpinnerNumberModel(1, 1, 100, 1);
		JSpinner spCantidad = new JSpinner(modeloSpinner);
		DiseñoJSpinner.aplicarEstiloNumerico(spCantidad, new Color(72, 207, 174), Color.BLACK);

		JLabel lblFechaLimite = new JLabel("Fecha Limite:", SwingConstants.CENTER);
		lblFechaLimite.setFont(new Font("Arial", 0, 15));
		JTextField tfFechaLimite = new PlaceholderTextField("dd-MM-yyyy");
		tfFechaLimite.setFont(new Font("Arial", 0, 15));
		tfFechaLimite.setBackground(new Color(72, 207, 174));

		panelCampos.add(lblIsbn);
		panelCampos.add(tfIsbn);
		panelCampos.add(lblCi);
		panelCampos.add(tfCi);
		panelCampos.add(lblCantidad);
		panelCampos.add(spCantidad);
		panelCampos.add(lblFechaLimite);
		panelCampos.add(tfFechaLimite);

		// ================= PANEL INFERIOR (Botones) =================
		JPanel panelInferior = new JPanel(new BorderLayout());
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBackground(new Color(119, 221, 119));
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(new Color(220, 80, 80));

		JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelBotones.add(btnCancelar);
		panelBotones.add(btnEditar);
		panelInferior.add(panelBotones, BorderLayout.SOUTH);

		// ================= ESTRUCTURA FINAL =================
		this.setLayout(new BorderLayout());
		this.add(panelArriba, BorderLayout.NORTH);
		this.add(panelCampos, BorderLayout.CENTER);
		this.add(panelInferior, BorderLayout.SOUTH);

		// ================= CARGA DE DATOS =================
		tfIsbn.setText(isbn1);
		tfCi.setText(ci1);
		spCantidad.setValue(canidad1);
		tfFechaLimite.setText(fechaLimite1);

		// ================= COMPROBACIONES DE TEXTO =================
		// Solo números en CI
		tfCi.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				char tecla = e.getKeyChar();
				if (tecla < '0' || tecla > '9' && tecla != KeyEvent.VK_BACK_SPACE)
					e.consume();
			}

			public void keyReleased(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
			}
		});

		// Formato de fecha con guiones automáticos
		tfFechaLimite.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				char tecla = e.getKeyChar();
				if ((tecla < '0' || tecla > '9') && tecla != KeyEvent.VK_BACK_SPACE)
					e.consume();
				if (tfFechaLimite.getText().length() >= 10)
					e.consume();
			}

			public void keyReleased(KeyEvent e) {
				String texto = tfFechaLimite.getText().replaceAll("[^0-9]", "");
				if (texto.length() > 2 && texto.length() <= 4)
					texto = texto.substring(0, 2) + "-" + texto.substring(2);
				else if (texto.length() > 4)
					texto = texto.substring(0, 2) + "-" + texto.substring(2, 4) + "-" + texto.substring(4);
				if (texto.length() > 10)
					texto = texto.substring(0, 10);
				tfFechaLimite.setText(texto);
			}

			public void keyPressed(KeyEvent e) {
			}
		});

		// ================= ACTION LISTENER BOTÓN EDITAR =================
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String isbnNuevo = tfIsbn.getText();
				String isbnViejo = isbn1;
				String ci = tfCi.getText();

				if (ci == null || ci.isEmpty()) {
					JOptionPane.showMessageDialog(null, "'CI' no puede estar vacía");
				} else {
					int ciNuevo = Integer.parseInt(ci);
					int ciViejo = Integer.parseInt(ci1);
					int cantidad = (int) spCantidad.getValue();
					String fechaLimite = tfFechaLimite.getText();

					if (fechaLimite.length() == 10) {
						DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
						DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
						LocalDate fecha = LocalDate.parse(fechaLimite, inputFormat);
						String fechaSQL = fecha.format(outputFormat);

						LocalDate fechaHoy = LocalDate.now();
						if (fecha.isAfter(fechaHoy) || fecha.isEqual(fechaHoy)) {

							if (gestor.editPrestamolibro(isbnNuevo, isbnViejo, ciNuevo, ciViejo, cantidad, fechaSQL)) {

								gestor.RegistroPrestamoLibro(opcionRegistro, isbnViejo, ciViejo);
								gestor.ListarPrestamoLibro(modeloPrestLibro);
								JOptionPane.showMessageDialog(null, "Prestamo de Libro actualizado correctamente");
								vtnEditarPrestamoLibro.this.dispose();
							}
						} else {
							JOptionPane.showMessageDialog(null, "'Fecha limite invalida'");
						}
					} else {
						JOptionPane.showMessageDialog(null, "'Fecha limite invalida'");
					}
				}
			}
		});

		// ================= ACTION LISTENER BOTÓN CANCELAR =================
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vtnEditarPrestamoLibro.this.dispose();
			}
		});
	}
}
