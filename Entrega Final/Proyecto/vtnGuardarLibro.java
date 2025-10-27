package Proyecto;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

// Ventana para agregar un nuevo libro
public class vtnGuardarLibro extends JFrame {

	int opcionRegLibro = 1; // Opción de registro usada en historial
	Logica gestor = new Logica(); // Instancia de la clase Logica para manejar operaciones

	// Constructor de la ventana, recibe el modelo de tabla y un ISBN inicial
	public vtnGuardarLibro(DefaultTableModel modeloLibros, String isbnCampo) {

		// Configuración básica de la ventana
		this.setTitle("Añadir libro");
		this.setSize(500, 550);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		// Panel superior con título
		JPanel panelariba = new JPanel(new BorderLayout());
		JLabel texto = new JLabel("AÑADIR LIBRO", SwingConstants.CENTER);
		texto.setSize(30, 40);
		texto.setFont(new Font("Arial", Font.BOLD, 25));
		panelariba.add(texto, BorderLayout.CENTER);

		// Panel central con campos de entrada
		JPanel panel1 = new JPanel(new GridLayout(7, 2, -150, 20)); // 7 filas, 2 columnas
		panel1.setBorder(new EmptyBorder(20, 70, 20, 70)); // Espaciado interno del panel

		// Campos de entrada
		JLabel isbn = new JLabel("ISBN:");
		isbn.setFont(new Font("Arial", 0, 15));
		JTextField tfisbn = new JTextField(5);
		tfisbn.setFont(new Font("Arial", 0, 15));
		tfisbn.setBackground(new Color(72, 207, 174));

		JLabel autor = new JLabel("Autor:");
		autor.setFont(new Font("Arial", 0, 15));
		JTextField tfAutor = new JTextField(5);
		tfAutor.setFont(new Font("Arial", 0, 15));
		tfAutor.setBackground(new Color(72, 207, 174));

		JLabel titulo = new JLabel("Titulo:");
		titulo.setFont(new Font("Arial", 0, 15));
		JTextField tfTitulo = new JTextField(5);
		tfTitulo.setFont(new Font("Arial", 0, 15));
		tfTitulo.setBackground(new Color(72, 207, 174));

		// ComboBox de Género
		JLabel genero = new JLabel("Género:");
		genero.setFont(new Font("Arial", 0, 15));
		String[] generos = { "Ciencias", "Humanidades", "Arte", "Tecnología", "Otros" };
		JComboBox<String> comboGenero = new JComboBox<>(generos);
		comboGenero.setBackground(new Color(72, 207, 174));

		// ComboBox de Materia
		JLabel mat = new JLabel("Materia:");
		mat.setFont(new Font("Arial", 0, 15));
		String[] materias = { "Historia", "Literatura", "Matemática", "Física", "Química", "Biología", "Filosofía",
				"Geografía", "Informática", "Otros" };
		JComboBox<String> comboMateria = new JComboBox<>(materias);
		comboMateria.setBackground(new Color(72, 207, 174));

		// Spinner para cantidad de ejemplares
		SpinnerNumberModel modelo = new SpinnerNumberModel(1, 1, 100, 1); // min 1, max 100, paso 1
		JLabel stock = new JLabel("Ejemplares:");
		stock.setFont(new Font("Arial", 0, 15));
		JSpinner spStock = new JSpinner(modelo);
		DiseñoJSpinner.aplicarEstiloNumerico(spStock, new Color(72, 207, 174), Color.BLACK);

		// Campo para notas adicionales
		JLabel nota = new JLabel("Notas:");
		nota.setFont(new Font("Arial", 0, 15));
		JTextField tfNotas = new JTextField(5);
		tfNotas.setFont(new Font("Arial", 0, 15));
		tfNotas.setBackground(new Color(72, 207, 174));

		// Añadir todos los campos al panel central
		panel1.add(isbn);
		panel1.add(tfisbn);
		panel1.add(autor);
		panel1.add(tfAutor);
		panel1.add(titulo);
		panel1.add(tfTitulo);
		panel1.add(genero);
		panel1.add(comboGenero);
		panel1.add(mat);
		panel1.add(comboMateria);
		panel1.add(stock);
		panel1.add(spStock);
		panel1.add(nota);
		panel1.add(tfNotas);

		// Panel inferior con botones
		JPanel panelinf = new JPanel(new BorderLayout());
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBackground(new Color(119, 221, 119));
		JButton btnDescartar = new JButton("Descartar");
		btnDescartar.setBackground(new Color(220, 80, 80));

		JPanel panelAbajo2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelinf.add(panelAbajo2, BorderLayout.SOUTH);
		panelAbajo2.add(btnDescartar);
		panelAbajo2.add(btnGuardar);

		// Configuración layout principal
		this.setLayout(new BorderLayout());
		this.add(panelariba, BorderLayout.NORTH);
		this.add(panelinf, BorderLayout.SOUTH);
		this.add(panel1, BorderLayout.CENTER);

		// Inicializar el campo ISBN
		tfisbn.setText(isbnCampo);

		// Acción del botón "Guardar"
		btnGuardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// Tomar valores de los campos
				String isbn1 = tfisbn.getText();
				String autor1 = tfAutor.getText();
				String titulo1 = tfTitulo.getText();
				String genero1 = (String) comboGenero.getSelectedItem();
				String materia1 = (String) comboMateria.getSelectedItem();
				int ejemplares1 = (int) spStock.getValue();
				String notas1 = tfNotas.getText();

				// Dar de alta el libro en la base de datos
				if (gestor.altaLibro(isbn1, autor1, titulo1, genero1, materia1, ejemplares1, notas1)) {
					gestor.RegistroLibro(opcionRegLibro, titulo1); // Registrar acción
					JOptionPane.showMessageDialog(null, "Libro Registrado");
					gestor.ListarLibros(modeloLibros); // Actualizar tabla
					vtnGuardarLibro.this.dispose(); // Cerrar ventana
				}
			}
		});

		// Acción del botón "Descartar"
		btnDescartar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vtnGuardarLibro.this.dispose(); // Cierra ventana sin guardar
			}
		});

	}
}
