package Proyecto;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class vtnEditPerson extends JFrame {

	Logica gestor = new Logica(); // Instancia de la lógica de negocio
	int opcionRegistro = 3; // Opción de registro/edición para auditoría

	public vtnEditPerson(DefaultTableModel modeloPersonas, String ci1, String nombre1, String apellido1,
			String telefono1, String grupo1, String tipo1) {

		// ================= CONFIGURACIÓN DE LA VENTANA =================
		this.setTitle("Editar Persona");
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);

		// ================= PANEL SUPERIOR (Título) =================
		JPanel panelArriba = new JPanel(new BorderLayout());
		JLabel lblTitulo = new JLabel("EDITAR PERSONA", SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 25));
		panelArriba.add(lblTitulo, BorderLayout.CENTER);

		// ================= PANEL CENTRAL (Campos de entrada) =================
		JPanel panelCampos = new JPanel(new GridLayout(6, 2, -150, 20));
		panelCampos.setBorder(new EmptyBorder(20, 70, 20, 70));

		// Campos
		JLabel lblCi = new JLabel("CI:");
		lblCi.setFont(new Font("Arial", 0, 15));
		JTextField tfCi = new JTextField(5);
		tfCi.setFont(new Font("Arial", 0, 15));
		tfCi.setBackground(new Color(72, 207, 174));

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Arial", 0, 15));
		JTextField tfNombre = new JTextField(5);
		tfNombre.setFont(new Font("Arial", 0, 15));
		tfNombre.setBackground(new Color(72, 207, 174));

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setFont(new Font("Arial", 0, 15));
		JTextField tfApellido = new JTextField(5);
		tfApellido.setFont(new Font("Arial", 0, 15));
		tfApellido.setBackground(new Color(72, 207, 174));

		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setFont(new Font("Arial", 0, 15));
		JTextField tfTelefono = new JTextField(5);
		tfTelefono.setFont(new Font("Arial", 0, 15));
		tfTelefono.setBackground(new Color(72, 207, 174));

		JLabel lblGrupo = new JLabel("Grupo:");
		lblGrupo.setFont(new Font("Arial", 0, 15));
		JTextField tfGrupo = new JTextField(5);
		tfGrupo.setFont(new Font("Arial", 0, 15));
		tfGrupo.setBackground(new Color(72, 207, 174));

		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setFont(new Font("Arial", 0, 15));
		String[] tipos = { "Estudiante", "Profesor", "Otro" };
		JComboBox<String> comboTipos = new JComboBox<>(tipos);
		comboTipos.setBackground(new Color(72, 207, 174));

		// Agregar campos al panel central
		panelCampos.add(lblCi);
		panelCampos.add(tfCi);
		panelCampos.add(lblNombre);
		panelCampos.add(tfNombre);
		panelCampos.add(lblApellido);
		panelCampos.add(tfApellido);
		panelCampos.add(lblTelefono);
		panelCampos.add(tfTelefono);
		panelCampos.add(lblTipo);
		panelCampos.add(comboTipos);
		panelCampos.add(lblGrupo);
		panelCampos.add(tfGrupo);

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

		// ================= CARGA DE DATOS INICIALES =================
		tfCi.setText(ci1);
		tfNombre.setText(nombre1);
		tfApellido.setText(apellido1);
		tfTelefono.setText(telefono1);
		comboTipos.setSelectedItem(tipo1);
		tfGrupo.setText(grupo1);

		// ================= VALIDACIÓN DE CAMPOS =================
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

		// Solo números en Teléfono, máximo 9 dígitos
		tfTelefono.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				char tecla = e.getKeyChar();
				if ((tecla < '0' || tecla > '9') && tecla != KeyEvent.VK_BACK_SPACE)
					e.consume();
				if (tfTelefono.getText().length() >= 9)
					e.consume();
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}
		});

		// ================= ACTION LISTENER BOTÓN EDITAR =================
		btnEditar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String ciText = tfCi.getText();
				String telefonoText = tfTelefono.getText();

				// Validaciones
				if (ciText == null || ciText.isEmpty()) {
					JOptionPane.showMessageDialog(null, "La CI no puede estar vacía");
				} else if (telefonoText == null || telefonoText.isEmpty()) {
					JOptionPane.showMessageDialog(null, "El teléfono no puede estar vacío");
				} else if (telefonoText.length() > 9) {
					JOptionPane.showMessageDialog(null, "Error: 'Teléfono' no puede ser mayor a 9 dígitos");
				} else if (telefonoText.length() < 8) {
					JOptionPane.showMessageDialog(null, "Error: 'Teléfono' no puede ser menor a 8 dígitos");
				} else {

					// Convertir a números y obtener otros valores

					int ciNuevo = Integer.parseInt(ciText);

					int ciViejo = Integer.parseInt(ci1);

					String nombre = tfNombre.getText();
					String apellido = tfApellido.getText();

					int telefonoNuevo = Integer.parseInt(telefonoText);

					String tipo = (String) comboTipos.getSelectedItem();

					String grupo = tfGrupo.getText();

					// Lógica de actualización
					if (gestor.editPersona(ciViejo, ciNuevo, nombre, apellido, telefonoNuevo, tipo, grupo)) {
						gestor.RegistroPersona(opcionRegistro, ciViejo);
						gestor.ListarPersona(modeloPersonas);
						JOptionPane.showMessageDialog(null, "Persona actualizada correctamente");
						vtnEditPerson.this.dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Persona no editada");
					}
				}
			}
		});

		// ================= ACTION LISTENER BOTÓN CANCELAR =================
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vtnEditPerson.this.dispose();
			}
		});
	}
}
