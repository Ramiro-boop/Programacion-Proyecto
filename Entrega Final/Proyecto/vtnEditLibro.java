package Proyecto;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class vtnEditLibro extends JFrame {

	Logica gestor = new Logica();
	int opcionRegistro = 3; // Acción de "editar"

	public vtnEditLibro(DefaultTableModel modeloLibros, String isbn1, String autor1, String titulo1, String genero1,
			String materia1, int ejemplares1, String notas1) {

		// ================= CONFIGURACIÓN DE LA VENTANA =================
		this.setTitle("Editar Libro");
		this.setSize(500, 550);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		// ================= PANEL SUPERIOR (Título) =================
		JPanel panelArriba = new JPanel(new BorderLayout());
		JLabel lblTitulo = new JLabel("EDITAR LIBRO", SwingConstants.CENTER);
		lblTitulo.setSize(30, 40);
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 25));
		panelArriba.add(lblTitulo, BorderLayout.CENTER);

		// ================= PANEL CENTRAL (Campos de entrada) =================
		JPanel panelCampos = new JPanel(new GridLayout(7, 2, -150, 20));
		panelCampos.setBorder(new EmptyBorder(20, 70, 20, 70));

		// Campos
		JLabel lblIsbn = new JLabel("ISBN:");
		lblIsbn.setFont(new Font("Arial", 0, 15));
		JTextField tfIsbn = new JTextField(5);
		tfIsbn.setFont(new Font("Arial", 0, 15));
		tfIsbn.setBackground(new Color(72, 207, 174));

		JLabel lblAutor = new JLabel("Autor:");
		lblAutor.setFont(new Font("Arial", 0, 15));
		JTextField tfAutor = new JTextField(5);
		tfAutor.setFont(new Font("Arial", 0, 15));
		tfAutor.setBackground(new Color(72, 207, 174));

		JLabel lblTituloCampo = new JLabel("Titulo:");
		lblTituloCampo.setFont(new Font("Arial", 0, 15));
		JTextField tfTitulo = new JTextField(5);
		tfTitulo.setFont(new Font("Arial", 0, 15));
		tfTitulo.setBackground(new Color(72, 207, 174));

		JLabel lblGenero = new JLabel("Género:");
		lblGenero.setFont(new Font("Arial", 0, 15));
		String[] generos = { "Ciencias", "Humanidades", "Arte", "Tecnología", "Otros" };
		JComboBox<String> comboGenero = new JComboBox<>(generos);
		comboGenero.setBackground(new Color(72, 207, 174));

		JLabel lblMateria = new JLabel("Materia:");
		lblMateria.setFont(new Font("Arial", 0, 15));
		String[] materias = { "Historia", "Literatura", "Matemática", "Física", "Química", "Biología", "Filosofía",
				"Geografía", "Informática", "Otros" };
		JComboBox<String> comboMateria = new JComboBox<>(materias);
		comboMateria.setBackground(new Color(72, 207, 174));

		JLabel lblEjemplares = new JLabel("Ejemplares:");
		lblEjemplares.setFont(new Font("Arial", 0, 15));
		SpinnerNumberModel modelo = new SpinnerNumberModel(1, 1, 100, 1);
		JSpinner spEjemplares = new JSpinner(modelo);
		DiseñoJSpinner.aplicarEstiloNumerico(spEjemplares, new Color(72, 207, 174), Color.BLACK);

		JLabel lblNotas = new JLabel("Notas:");
		lblNotas.setFont(new Font("Arial", 0, 15));
		JTextField tfNotas = new JTextField(5);
		tfNotas.setFont(new Font("Arial", 0, 15));
		tfNotas.setBackground(new Color(72, 207, 174));

		// Agregar componentes al panel central
		panelCampos.add(lblIsbn);
		panelCampos.add(tfIsbn);
		panelCampos.add(lblAutor);
		panelCampos.add(tfAutor);
		panelCampos.add(lblTituloCampo);
		panelCampos.add(tfTitulo);
		panelCampos.add(lblGenero);
		panelCampos.add(comboGenero);
		panelCampos.add(lblMateria);
		panelCampos.add(comboMateria);
		panelCampos.add(lblEjemplares);
		panelCampos.add(spEjemplares);
		panelCampos.add(lblNotas);
		panelCampos.add(tfNotas);

		// ================= PANEL INFERIOR (Botones) =================
		JPanel panelInferior = new JPanel(new BorderLayout());
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBackground(new Color(119, 221, 119));
		JButton btnDescartar = new JButton("Descartar");
		btnDescartar.setBackground(new Color(220, 80, 80));

		JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelBotones.add(btnDescartar);
		panelBotones.add(btnEditar);
		panelInferior.add(panelBotones, BorderLayout.SOUTH);

		// ================= ESTRUCTURA FINAL =================
		this.setLayout(new BorderLayout());
		this.add(panelArriba, BorderLayout.NORTH);
		this.add(panelCampos, BorderLayout.CENTER);
		this.add(panelInferior, BorderLayout.SOUTH);

		// ================= CARGA DE DATOS EXISTENTES =================
		tfIsbn.setText(isbn1);
		tfAutor.setText(autor1);
		tfTitulo.setText(titulo1);
		comboGenero.setSelectedItem(genero1);
		comboMateria.setSelectedItem(materia1);
		spEjemplares.setValue(ejemplares1);
		tfNotas.setText(notas1);

		// ================= ACTION LISTENER BOTÓN EDITAR =================
		btnEditar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String isbnNuevo = tfIsbn.getText();
				String isbnViejo = isbn1;
				String autor = tfAutor.getText();
				String titulo = tfTitulo.getText();
				String genero = (String) comboGenero.getSelectedItem();
				String materia = (String) comboMateria.getSelectedItem();
				int ejemplares = (int) spEjemplares.getValue();
				String notas = tfNotas.getText();

				// Llama a la lógica para editar el libro
				if (gestor.editLibro(isbnNuevo, isbnViejo, autor, titulo, genero, materia, ejemplares, notas)) {
					
					gestor.RegistroLibro(opcionRegistro, titulo);
					
					gestor.ListarLibros(modeloLibros);
					
					JOptionPane.showMessageDialog(null, "Libro actualizado correctamente");
					vtnEditLibro.this.dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Libro no editado");
				}
			}
		});

		// ================= ACTION LISTENER BOTÓN DESCARTAR =================
		btnDescartar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vtnEditLibro.this.dispose(); // Cierra la ventana
			}
		});
	}
}
