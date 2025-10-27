package Proyecto;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

// Ventana para agregar una nueva Ceibalita
public class vtnGuardarCeibalita extends JFrame {

	Logica gestor = new Logica(); // Instancia de la clase lógica para manejar operaciones
	int opcionRegistro = 1; // Opción de registro usada en el historial

	// Constructor de la ventana, recibe el modelo de la tabla y un valor inicial
	// para el número de Ceibalita
	public vtnGuardarCeibalita(DefaultTableModel modeloCeibalitas, String numCeibalitaCampo) {

		// Configuración básica de la ventana
		this.setTitle("Agregar Ceibalita");
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		// Panel superior con título
		JPanel panelariba = new JPanel(new BorderLayout());
		panelariba.setBorder(new EmptyBorder(20, 30, 20, 30));
		JLabel texto = new JLabel("AGREGAR CEIBALITA", SwingConstants.CENTER);
		texto.setFont(new Font("Arial", Font.BOLD, 25));
		panelariba.add(texto, BorderLayout.CENTER);

		// Panel central con campos de entrada
		JPanel panel1 = new JPanel(new GridLayout(2, 2, -10, 60)); // 2 filas, 2 columnas
		panel1.setBorder(new EmptyBorder(40, 10, 60, 10));

		// Campo para número de Ceibalita
		JLabel numCeibalitaLabel = new JLabel("Número Ceibalita");
		numCeibalitaLabel.setFont(new Font("Arial", 0, 15));
		JTextField tfNumCeibalita = new JTextField(20); // JTextField para el número
		tfNumCeibalita.setFont(new Font("Arial", 0, 15));
		tfNumCeibalita.setBackground(new Color(72, 207, 174));

		// Campo para notas
		JLabel notasLabel = new JLabel("Notas");
		notasLabel.setFont(new Font("Arial", 0, 15));
		JTextField tfNotas = new JTextField(20); // JTextField para notas
		tfNotas.setFont(new Font("Arial", 0, 15));
		tfNotas.setBackground(new Color(72, 207, 174));

		// Añadir etiquetas y campos al panel central
		panel1.add(numCeibalitaLabel);
		panel1.add(tfNumCeibalita);
		panel1.add(notasLabel);
		panel1.add(tfNotas);

		// Panel inferior con botones
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

		// Configuración del layout principal
		this.setLayout(new BorderLayout());
		this.add(panelariba, BorderLayout.NORTH);
		this.add(panelinf, BorderLayout.SOUTH);
		this.add(panel1, BorderLayout.CENTER);

		// Inicializar el campo del número con el valor recibido
		tfNumCeibalita.setText(numCeibalitaCampo);

		// KeyListener para permitir solo números en el campo del número de Ceibalita
		tfNumCeibalita.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				char tecla = e.getKeyChar();
				if (tecla < '0' || tecla > '9' && tecla != KeyEvent.VK_BACK_SPACE) {
					e.consume(); // Bloquea caracteres que no sean números o backspace
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}
		});

		// Acción del botón "Guardar"
		btnGuardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tfNumCeibalita.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Ingrese un número de ceibalita válido");
				} else {
					int numCeibalita = Integer.parseInt(tfNumCeibalita.getText());
					String notasCeibalita = tfNotas.getText();

					// Lógica para dar de alta la Ceibalita
					if (gestor.altaCeibalita(numCeibalita, notasCeibalita)) {

						gestor.RegistroCompu(opcionRegistro, numCeibalita); // Registrar acción

						JOptionPane.showMessageDialog(null, "Ceibalita guardada");

						gestor.ListarCeibalita(modeloCeibalitas); // Actualizar tabla
						vtnGuardarCeibalita.this.dispose(); // Cerrar ventana
					}
				}
			}
		});

		// Acción del botón "Cancelar"
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vtnGuardarCeibalita.this.dispose(); // Cierra ventana sin guardar
			}
		});
	}
}
