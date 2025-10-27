package Proyecto;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

// Ventana para agregar una nueva persona
public class vtnGuardarPersona extends JFrame {

	Logica gestor = new Logica(); // Instancia de la clase lógica para manejar operaciones
	int opcionRegistro = 1; // Opción de registro usada en historial

	// Constructor de la ventana, recibe el modelo de tabla y un CI inicial
	public vtnGuardarPersona(DefaultTableModel modeloPersonas, String ci1) {

		// Configuración básica de la ventana
		this.setTitle("Agregar persona");
		this.setSize(500, 500); // ancho x alto
		this.setLocationRelativeTo(null);

		// Panel superior con título
		JPanel panelariba = new JPanel(new BorderLayout());
		JLabel texto = new JLabel("AGREGAR PERSONA", SwingConstants.CENTER);
		texto.setSize(30, 40);
		texto.setFont(new Font("Arial", Font.BOLD, 25));
		panelariba.add(texto, BorderLayout.CENTER);

		// Panel central con campos de entrada
		JPanel panel1 = new JPanel(new GridLayout(6, 2, -150, 20)); // 6 filas, 2 columnas
		panel1.setBorder(new EmptyBorder(20, 70, 20, 70)); // espaciado interno

		// -------------------- CAMPOS DE TEXTO --------------------
		JLabel lblCi = new JLabel("CI:");
		lblCi.setFont(new Font("Arial", 0, 15));
		JTextField tfCi = new JTextField(5); // TextField para la CI
		tfCi.setFont(new Font("Arial", 0, 15));
		tfCi.setBackground(new Color(72, 207, 174));
		tfCi.setText(ci1); // Prellenar con el CI recibido

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Arial", 0, 15));
		JTextField tfNombre = new JTextField(5); // TextField para el nombre
		tfNombre.setFont(new Font("Arial", 0, 15));
		tfNombre.setBackground(new Color(72, 207, 174));

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setFont(new Font("Arial", 0, 15));
		JTextField tfApellido = new JTextField(5); // TextField para el apellido
		tfApellido.setFont(new Font("Arial", 0, 15));
		tfApellido.setBackground(new Color(72, 207, 174));

		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setFont(new Font("Arial", 0, 15));
		JTextField tfTelefono = new JTextField(5); // TextField para el teléfono
		tfTelefono.setFont(new Font("Arial", 0, 15));
		tfTelefono.setBackground(new Color(72, 207, 174));

		JLabel lblGrupo = new JLabel("Grupo:");
		lblGrupo.setFont(new Font("Arial", 0, 15));
		JTextField tfGrupo = new JTextField(5); // TextField para el grupo
		tfGrupo.setFont(new Font("Arial", 0, 15));
		tfGrupo.setBackground(new Color(72, 207, 174));

		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setFont(new Font("Arial", 0, 15));
		String[] tipos = { "Estudiante", "Profesor", "Otro" };
		JComboBox<String> comboTipos = new JComboBox<>(tipos);
		comboTipos.setBackground(new Color(72, 207, 174));

		// -------------------- AGREGAR COMPONENTES AL PANEL --------------------
		panel1.add(lblCi);
		panel1.add(tfCi);
		panel1.add(lblNombre);
		panel1.add(tfNombre);
		panel1.add(lblApellido);
		panel1.add(tfApellido);
		panel1.add(lblTelefono);
		panel1.add(tfTelefono);
		panel1.add(lblTipo);
		panel1.add(comboTipos);
		panel1.add(lblGrupo);
		panel1.add(tfGrupo);

		// -------------------- PANEL INFERIOR CON BOTONES --------------------
		JPanel panelinf = new JPanel(new BorderLayout());
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBackground(new Color(119, 221, 119));
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(new Color(220, 80, 80));

		JPanel panelAbajo2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelAbajo2.add(btnCancelar);
		panelAbajo2.add(btnAgregar);
		panelinf.add(panelAbajo2, BorderLayout.SOUTH);

		// -------------------- CONFIGURAR LAYOUT DE LA VENTANA --------------------
		this.setLayout(new BorderLayout());
		this.add(panelariba, BorderLayout.NORTH);
		this.add(panel1, BorderLayout.CENTER);
		this.add(panelinf, BorderLayout.SOUTH);

		// -------------------- VALIDACIÓN PARA NÚMEROS --------------------
		tfCi.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				char tecla = e.getKeyChar();
				if (tecla < '0' || tecla > '9' && tecla != KeyEvent.VK_BACK_SPACE) {
					e.consume(); // solo permitir números y backspace
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}
		});

		tfTelefono.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				char tecla = e.getKeyChar();
				if (tecla < '0' || tecla > '9' && tecla != KeyEvent.VK_BACK_SPACE) {
					e.consume(); // solo permitir números y backspace
				}
				if (tfTelefono.getText().length() >= 9) {
					e.consume();// Permite hasta 9 numeros
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}
		});

		// -------------------- ACCIONES BOTONES --------------------
		btnAgregar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nombre = tfNombre.getText();
				String apellido = tfApellido.getText();
				String grupo = tfGrupo.getText();
				String tipo = (String) comboTipos.getSelectedItem();

				// Llama a la lógica para agregar la persona
				if (gestor.altaPersona(tfCi, nombre, apellido, tfTelefono, grupo, tipo)) {
					int ci = Integer.parseInt(tfCi.getText());
					gestor.RegistroPersona(opcionRegistro, ci); // registrar acción en historial
					JOptionPane.showMessageDialog(null, "Persona registrada");
					gestor.ListarPersona(modeloPersonas); // actualizar tabla
					vtnGuardarPersona.this.dispose(); // cerrar ventana
				}
			}
		});

		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vtnGuardarPersona.this.dispose(); // cerrar ventana sin guardar
			}
		});
	}
}
