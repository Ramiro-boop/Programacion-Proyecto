package Proyecto;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class vtnEditCeibalita extends JFrame {

	Logica gestor = new Logica();
	int opcionRegistro = 3; // Indica la acción de "editar"

	public vtnEditCeibalita(DefaultTableModel modeloCeibalitas, String numCeibalita1, String notas1) {

		// ================= CONFIGURACIÓN DE LA VENTANA =================
		this.setTitle("Editar Ceibalita");
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		// ================= PANEL SUPERIOR (Título) =================
		JPanel panelArriba = new JPanel(new BorderLayout());
		panelArriba.setBorder(new EmptyBorder(20, 30, 20, 30));
		JLabel lblTitulo = new JLabel("EDITAR CEIBALITA", SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 25));
		panelArriba.add(lblTitulo, BorderLayout.CENTER);

		// ================= PANEL CENTRAL (Campos de entrada) =================
		JPanel panelCampos = new JPanel(new GridLayout(2, 2, -10, 60));
		panelCampos.setBorder(new EmptyBorder(40, 10, 60, 10));

		JLabel lblNumCeibalita = new JLabel("Número Ceibalita");
		lblNumCeibalita.setFont(new Font("Arial", 0, 15));
		JTextField tfNumCeibalita = new JTextField(20);
		tfNumCeibalita.setFont(new Font("Arial", 0, 15));
		tfNumCeibalita.setBackground(new Color(72, 207, 174));

		JLabel lblNotas = new JLabel("Notas");
		lblNotas.setFont(new Font("Arial", 0, 15));
		JTextField tfNotas = new JTextField(20);
		tfNotas.setFont(new Font("Arial", 0, 15));
		tfNotas.setBackground(new Color(72, 207, 174));

		panelCampos.add(lblNumCeibalita);
		panelCampos.add(tfNumCeibalita);
		panelCampos.add(lblNotas);
		panelCampos.add(tfNotas);

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

		// ================= CARGA DE DATOS EXISTENTES =================
		tfNumCeibalita.setText(numCeibalita1);
		tfNotas.setText(notas1);

		// ================= VALIDACIÓN DEL NÚMERO =================
		tfNumCeibalita.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				char tecla = e.getKeyChar();
				if (tecla < '0' || tecla > '9' && tecla != KeyEvent.VK_BACK_SPACE) {
					e.consume(); // Solo permite números y retroceso
				}
			}

			public void keyReleased(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
			}
		});

		// ================= ACTION LISTENER BOTÓN EDITAR =================
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int numCeibalitaViejo = Integer.parseInt(numCeibalita1); // Valor original
				int numCeibalitaNuevo = Integer.parseInt(tfNumCeibalita.getText()); // Valor editado
				String notas = tfNotas.getText();

				// Llama a la lógica para editar ceibalita
				if (gestor.editCeibalita(numCeibalitaViejo, numCeibalitaNuevo, notas)) {
					gestor.RegistroCompu(opcionRegistro, numCeibalitaViejo); // Registro de la acción
					gestor.ListarCeibalita(modeloCeibalitas); // Actualiza tabla
					JOptionPane.showMessageDialog(null, "Ceibalita actualizada correctamente");
					vtnEditCeibalita.this.dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Ceibalita no editada");
				}
			}
		});

		// ================= ACTION LISTENER BOTÓN CANCELAR =================
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vtnEditCeibalita.this.dispose(); // Cierra la ventana
			}
		});
	}
}
