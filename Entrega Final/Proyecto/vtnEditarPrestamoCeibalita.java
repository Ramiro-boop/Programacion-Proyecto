package Proyecto;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class vtnEditarPrestamoCeibalita extends JFrame {

	Logica gestor = new Logica();
	int opcionRegistro = 3; // Identifica la acción como "editar"

	// Constructor: recibe modelo de tabla y datos actuales del préstamo
	public vtnEditarPrestamoCeibalita(DefaultTableModel modeloPresCeibalita, String numeroCeibalita1, String ci1,
			String fechaDevolucion1) {

		// ================= CONFIGURACIÓN DE LA VENTANA =================
		this.setTitle("Editar Prestamo Ceibalita");
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		// ================= PANEL SUPERIOR (Título) =================
		JPanel panelArriba = new JPanel(new BorderLayout());
		panelArriba.setBorder(new EmptyBorder(0, 30, 0, 30));

		JLabel lblTitulo = new JLabel("EDITAR PRESTAMO CEIBALITA", SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 21));
		panelArriba.add(lblTitulo, BorderLayout.CENTER);

		// ================= PANEL CENTRAL (Campos de entrada) =================
		JPanel panelCampos = new JPanel(new GridLayout(3, 2, -90, 30));
		panelCampos.setBorder(new EmptyBorder(20, 10, 20, 20));

		JLabel lblNumCeibalita = new JLabel("Número Ceibalita:");
		lblNumCeibalita.setFont(new Font("Arial", 0, 15));
		JTextField tfNumCeibalita = new JTextField(20);
		tfNumCeibalita.setFont(new Font("Arial", 0, 15));
		tfNumCeibalita.setBackground(new Color(72, 207, 174));

		JLabel lblCi = new JLabel("CI:");
		lblCi.setFont(new Font("Arial", 0, 15));
		JTextField tfCi = new JTextField(20);
		tfCi.setFont(new Font("Arial", 0, 15));
		tfCi.setBackground(new Color(72, 207, 174));

		JLabel lblHoraDev = new JLabel("Hora Devolución:");
		lblHoraDev.setFont(new Font("Arial", 0, 15));
		JTextField tfHoraDev = new PlaceholderTextField("HH:mm");
		tfHoraDev.setFont(new Font("Arial", 0, 15));
		tfHoraDev.setBackground(new Color(72, 207, 174));

		panelCampos.add(lblNumCeibalita);
		panelCampos.add(tfNumCeibalita);
		panelCampos.add(lblCi);
		panelCampos.add(tfCi);
		panelCampos.add(lblHoraDev);
		panelCampos.add(tfHoraDev);

		// ================= PANEL INFERIOR (Botones) =================
		JPanel panelInferior = new JPanel(new BorderLayout());
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBackground(new Color(119, 221, 119));

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(new Color(220, 80, 80));

		JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelBotones.setBorder(new EmptyBorder(0, 0, 5, 5));
		panelBotones.add(btnCancelar);
		panelBotones.add(btnEditar);
		panelInferior.add(panelBotones, BorderLayout.SOUTH);

		// ================= ESTRUCTURA FINAL =================
		this.setLayout(new BorderLayout());
		this.add(panelArriba, BorderLayout.NORTH);
		this.add(panelCampos, BorderLayout.CENTER);
		this.add(panelInferior, BorderLayout.SOUTH);

		// ================= CARGA DE DATOS =================
		DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("HH:mm:ss"); // formato SQL
		DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("HH:mm"); // formato simple
		LocalTime hora = LocalTime.parse(fechaDevolucion1, inputFormat);
		String horaFormateada = hora.format(outputFormat);

		tfNumCeibalita.setText(numeroCeibalita1);
		tfCi.setText(ci1);
		tfHoraDev.setText(horaFormateada);

		// ================= COMPROBACIONES DE TEXTO =================
		tfNumCeibalita.addKeyListener(new KeyListener() {
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

		tfHoraDev.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				char tecla = e.getKeyChar();
				if ((tecla < '0' || tecla > '9') && tecla != KeyEvent.VK_BACK_SPACE)
					e.consume();
				if (tfHoraDev.getText().length() >= 5)
					e.consume();
			}

			public void keyReleased(KeyEvent e) {
				String texto = tfHoraDev.getText().replaceAll("[^0-9]", "");
				if (texto.length() > 2)
					texto = texto.substring(0, 2) + ":" + texto.substring(2);
				if (texto.length() > 5)
					texto = texto.substring(0, 5);
				tfHoraDev.setText(texto);
			}

			public void keyPressed(KeyEvent e) {
			}
		});

		// ================= ACTION LISTENER BOTÓN EDITAR =================
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int numCeibalitaViejo = Integer.parseInt(numeroCeibalita1); // PK original
				int numCeibalitaNuevo = Integer.parseInt(tfNumCeibalita.getText());
				int ci = Integer.parseInt(tfCi.getText());
				String horaDev = tfHoraDev.getText();

				if (horaDev.length() == 5) {
					int horas = Integer.parseInt(horaDev.substring(0, 2));
					int minutos = Integer.parseInt(horaDev.substring(3, 5));

					if (horas < 24 && minutos < 60) {
						DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("HH:mm");
						DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
						LocalTime hora = LocalTime.parse(horaDev, inputFormat);
						String horaSQL = hora.format(outputFormat);

						if (gestor.editPrestamoCeibalita(numCeibalitaViejo, numCeibalitaNuevo, ci, horaSQL)) {
							gestor.RegistroPrestamoCompu(opcionRegistro, numCeibalitaViejo, ci);
							gestor.ListarPrestamoCeibalita(modeloPresCeibalita);
							JOptionPane.showMessageDialog(null, "Prestamo actualizado correctamente");
							vtnEditarPrestamoCeibalita.this.dispose();
						}

					} else {
						JOptionPane.showMessageDialog(null, "Error: 'Hora devolución' invalida");
					}

				} else {
					JOptionPane.showMessageDialog(null, "Error: 'Hora devolución' invalida");
				}
			}
		});

		// ================= ACTION LISTENER BOTÓN CANCELAR =================
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vtnEditarPrestamoCeibalita.this.dispose();
			}
		});
	}
}
