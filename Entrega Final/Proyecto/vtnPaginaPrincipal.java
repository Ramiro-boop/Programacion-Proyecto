package Proyecto;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class vtnPaginaPrincipal extends JFrame {

	// Gestor de la lógica de negocio / acceso a datos
	Logica gestor = new Logica();

	// Indica la vista actual mostrada en pantalla:
	// 0 = Prestamos Ceibalitas, 1 = Prestamos Libros, 2 = Inventario (libros), 3 =
	// Personas, 4 = Ceibalitas
	int opcionTabla = 0;

	// Variable usada para registrar acciones (parece un flag para logs/registro de
	// operaciones)
	int opcionRegistro = 2; // valor por defecto (mantener como en el código original)

	/**
	 * Constructor: crea la ventana principal y todos sus componentes. Aquí se
	 * construyen los paneles, botones, tablas y listeners.
	 */
	public vtnPaginaPrincipal() {

		// Configuración básica de la ventana
		this.setTitle("Menu principal"); // título de la ventana
		this.setSize(600, 400); // tamaño inicial (se maximiza luego)
		URL iconURL = getClass().getResource("/Proyecto/logo.png");
		ImageIcon icon = new ImageIcon(iconURL);
		this.setIconImage(icon.getImage());

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // cerrar la app al cerrar la ventana
		this.setExtendedState(MAXIMIZED_BOTH); // iniciar maximizadas

		// --- Panel superior (barra con botones y título) ---
		JPanel pArriba = new JPanel(new GridLayout(1, 7));
		pArriba.setBackground(Color.WHITE);

		// Paneles y márgenes para organizar los elementos del header
		JPanel pArribaIzq = new JPanel();
		pArribaIzq.setBackground(Color.WHITE);
		pArribaIzq.setBorder(new EmptyBorder(0, 0, 0, 30));

		JPanel pArribaIzq2 = new JPanel();
		pArribaIzq2.setBorder(new EmptyBorder(8, 0, 0, 0));
		pArribaIzq2.setBackground(Color.WHITE);

		JPanel pRegistros = new JPanel();
		pRegistros.setBorder(new EmptyBorder(6, 0, 0, 0));
		pRegistros.setBackground(Color.WHITE);

		// --- Barra lateral (botones de navegación) ---
		JPanel pIzq = new JPanel();
		pIzq.setBackground(Color.WHITE);
		pIzq.setLayout(new GridLayout(11, 1, 5, 10));

		// Contenedor principal con CardLayout para cambiar vistas fácilmente
		JPanel contenedor = new JPanel(new CardLayout());

		// Paneles que se intercambiarán en el CardLayout
		JPanel pPrestamosCompus = new JPanel(new BorderLayout());
		pPrestamosCompus.setBackground(Color.WHITE);

		JPanel pPrestamosLibros = new JPanel(new BorderLayout());
		pPrestamosLibros.setBackground(Color.WHITE);

		JPanel pInventario = new JPanel(new BorderLayout());
		pInventario.setBackground(Color.WHITE);

		JPanel pVerPersonas = new JPanel(new BorderLayout());
		pVerPersonas.setBackground(Color.WHITE);

		JPanel pCeibalitas = new JPanel(new BorderLayout());
		pCeibalitas.setBackground(Color.WHITE);

		// --- Iconos y botones superiores/funciones ---
		ImageIcon imagenRegis = new ImageIcon(getClass().getResource("/Proyecto/Registros.png"));
		Image imagenRegis1 = imagenRegis.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImageIcon imagenRegis2 = new ImageIcon(imagenRegis1);

		JButton btnRegistros = new JButton("Registros", imagenRegis2);
		btnRegistros.setBackground(new Color(72, 207, 174));

		// Botones de editar / eliminar (arriba a la izquierda)
		ImageIcon imagenElimin = new ImageIcon(getClass().getResource("/Proyecto/Eliminar.png"));
		Image imagenElimin1 = imagenElimin.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		ImageIcon imagenElimin2 = new ImageIcon(imagenElimin1);
		JButton btnEliminar = new JButton("Eliminar", imagenElimin2);
		btnEliminar.setBackground(new Color(72, 207, 174));

		ImageIcon imagenEdit = new ImageIcon(getClass().getResource("/Proyecto/Editar.png"));
		Image imagenEdit1 = imagenEdit.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		ImageIcon imagenEdit2 = new ImageIcon(imagenEdit1);
		JButton btnEdit = new JButton("Editar", imagenEdit2);
		btnEdit.setBackground(new Color(72, 207, 174));

		// Logo reducido para la cabecera
		ImageIcon imagen = new ImageIcon(getClass().getResource("/Proyecto/logo.png"));
		Image imagen1 = imagen.getImage().getScaledInstance(70, 47, Image.SCALE_SMOOTH);
		ImageIcon logo = new ImageIcon(imagen1);

		// --- Botones de la barra lateral (navegación principal) ---
		ImageIcon imagenPrestCompu = new ImageIcon(getClass().getResource("/Proyecto/Ojo.png"));
		Image imagenPrestCompu1 = imagenPrestCompu.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		ImageIcon imagenPrestCompu2 = new ImageIcon(imagenPrestCompu1);

		JButton btnPrestamosCompu = new JButton("Ver Prestamos Compu", imagenPrestCompu2);// botón para ver los
																							// prestamos de las
																							// computadoras
		btnPrestamosCompu.setBackground(new Color(255, 192, 103));

		JButton btnPrestamosLibro = new JButton("Ver Prestamos Libro", imagenPrestCompu2);// botón para ver los
																							// prestamos de los libros
		btnPrestamosLibro.setBackground(new Color(255, 192, 103));

		ImageIcon imagenVerLibros = new ImageIcon(getClass().getResource("/Proyecto/VerLibros.png"));
		Image imagenVerLibros1 = imagenVerLibros.getImage().getScaledInstance(56, 56, Image.SCALE_SMOOTH);
		ImageIcon imagenVerLibros2 = new ImageIcon(imagenVerLibros1);

		JButton btnVerLibros = new JButton("Ver Libros", imagenVerLibros2);// botón para ver los libros registrados
		btnVerLibros.setBackground(new Color(255, 192, 103));

		ImageIcon imagenVerPersonas = new ImageIcon(getClass().getResource("/Proyecto/VerPersonas.png"));
		Image imagenVerPersonas1 = imagenVerPersonas.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		ImageIcon imagenVerPersonas2 = new ImageIcon(imagenVerPersonas1);

		JButton btnVerPersonas = new JButton("Ver Personas", imagenVerPersonas2);// Botón para ver a las personas
																					// registradas
		btnVerPersonas.setBackground(new Color(255, 192, 103));

		ImageIcon imagenVerCeibalitas = new ImageIcon(getClass().getResource("/Proyecto/VerCompus.png"));
		Image imagenVerCeibalitas1 = imagenVerCeibalitas.getImage().getScaledInstance(56, 56, Image.SCALE_SMOOTH);
		ImageIcon imagenVerCeibalitas2 = new ImageIcon(imagenVerCeibalitas1);

		JButton btnVerCompus = new JButton("Ver Compus", imagenVerCeibalitas2);// Botón para ver a las ceibalitas
																				// registradas
		btnVerCompus.setBackground(new Color(255, 192, 103));

		// Botones para crear / prestar
		ImageIcon imagenCompu = new ImageIcon(getClass().getResource("/Proyecto/PrestamoCompu.png"));
		Image imagenCompu1 = imagenCompu.getImage().getScaledInstance(56, 56, Image.SCALE_SMOOTH);
		ImageIcon imagenCompu2 = new ImageIcon(imagenCompu1);
		JButton btnPrestCompu = new JButton("Prestar compu", imagenCompu2);
		btnPrestCompu.setBackground(new Color(255, 238, 140));

		ImageIcon imagenLibro = new ImageIcon(getClass().getResource("/Proyecto/PrestamoLibro.png"));
		Image imagenLibro1 = imagenLibro.getImage().getScaledInstance(56, 56, Image.SCALE_SMOOTH);
		ImageIcon imagenLibro2 = new ImageIcon(imagenLibro1);
		JButton btnPrestLibro = new JButton("Prestar Libro", imagenLibro2);
		btnPrestLibro.setBackground(new Color(255, 238, 140));

		ImageIcon imagenAgregarLibro = new ImageIcon(getClass().getResource("/Proyecto/AgregarLibro.png"));
		Image imagenAgregarLibro1 = imagenAgregarLibro.getImage().getScaledInstance(56, 56, Image.SCALE_SMOOTH);
		ImageIcon imagenAgregarLibro2 = new ImageIcon(imagenAgregarLibro1);

		JButton btnAgregLibro = new JButton("Agregar Libro", imagenAgregarLibro2);
		btnAgregLibro.setBackground(new Color(119, 221, 119));

		ImageIcon imagenAgregarPersona = new ImageIcon(getClass().getResource("/Proyecto/AgregarPersona.png"));
		Image imagenAgregarPersona1 = imagenAgregarPersona.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		ImageIcon imagenAgregarPersona2 = new ImageIcon(imagenAgregarPersona1);

		JButton btnAgregPers = new JButton("Agregar Persona", imagenAgregarPersona2);
		btnAgregPers.setBackground(new Color(119, 221, 119));

		ImageIcon imagenAgregarCeibalita = new ImageIcon(getClass().getResource("/Proyecto/AgregarCompu.png"));
		Image imagenAgregarCeibalita1 = imagenAgregarCeibalita.getImage().getScaledInstance(56, 56, Image.SCALE_SMOOTH);
		ImageIcon imagenAgregarCeibalita2 = new ImageIcon(imagenAgregarCeibalita1);
		JButton btnAgregCompu = new JButton("Agregar Compu", imagenAgregarCeibalita2);
		btnAgregCompu.setBackground(new Color(119, 221, 119));

		ImageIcon imagenCerrarSes = new ImageIcon(getClass().getResource("/Proyecto/CerrarSesion.png"));
		Image imagenCerrarSes1 = imagenCerrarSes.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		ImageIcon imagenCerrarSes2 = new ImageIcon(imagenCerrarSes1);

		JButton btnCerrarSesion = new JButton("Cerrar sesion", imagenCerrarSes2);
		btnCerrarSesion.setBackground(new Color(220, 80, 80));

		// --- Etiquetas y tablas ---
		JLabel lbl1 = new JLabel("  ");
		JLabel lblP = new JLabel("Prestamos Ceibalitas", SwingConstants.CENTER); // etiqueta principal dinámica
		lblP.setSize(30, 40);
		lblP.setFont(new Font("Arial", Font.BOLD, 18));
		JLabel lbl3 = new JLabel();
		JLabel lbl4 = new JLabel("MENÚ"); // etiqueta fija del header
		lbl4.setSize(30, 40);
		lbl4.setFont(new Font("Arial", Font.BOLD, 30));
		JLabel lbl5 = new JLabel(logo);

		// Modelos y tablas para cada vista. Se usan DefaultTableModel para luego
		// rellenar las tablas
		String[] ColumnasLibros = { "ISBN", "Autor", "Titulo", "Genero", "Materia", "Ejemplares", "Notas" };
		DefaultTableModel modeloLibros = new DefaultTableModel(null, ColumnasLibros);

		JTable tablaLibros = new JTable(modeloLibros);
		tablaLibros.setBackground(Color.white);
		tablaLibros.getTableHeader().setReorderingAllowed(false); // no permitir reordenar columnas
		JScrollPane panelDesplazableInventario = new JScrollPane(tablaLibros);
		panelDesplazableInventario.setBackground(Color.white);
		tablaLibros.setDefaultEditor(Object.class, null); // tablas no editables desde UI

		String[] ColumnasPrestCeibalitas = { "Numero ceibalita", "CI", "Nombre", "Apellido", "Telefono", "Grupo",
				"Fecha de entrega", "Hora de devolución" };
		DefaultTableModel modeloPrestCeibalitas = new DefaultTableModel(null, ColumnasPrestCeibalitas);
		JTable tablaPrestCeibalitas = new JTable(modeloPrestCeibalitas);
		tablaPrestCeibalitas.getTableHeader().setReorderingAllowed(false);
		JScrollPane panelDesplazablePrestCeibalitas = new JScrollPane(tablaPrestCeibalitas);
		tablaPrestCeibalitas.setDefaultEditor(Object.class, null);

		String[] ColumnasPrestLibros = { "ISBN", "Titulo", "CI", "Nombre", "Apellido", "Telefono", "Grupo",
				"Cant.Presatdos", "Fecha de entrega", "Fecha de Limite" };
		DefaultTableModel modeloPrestLibros = new DefaultTableModel(null, ColumnasPrestLibros);
		JTable tablaPrestLibros = new JTable(modeloPrestLibros);
		tablaPrestLibros.getTableHeader().setReorderingAllowed(false);
		JScrollPane panelDesplazablePrestLibros = new JScrollPane(tablaPrestLibros);
		tablaPrestLibros.setDefaultEditor(Object.class, null);

		String[] ColumnasPersonas = { "CI", "Nombre", "Apellido", "Telefono", "Grupo", "Tipo" };
		DefaultTableModel modeloPersonas = new DefaultTableModel(null, ColumnasPersonas);
		JTable tablaPersonas = new JTable(modeloPersonas);
		tablaPersonas.getTableHeader().setReorderingAllowed(false);
		JScrollPane panelDesplazablePersonas = new JScrollPane(tablaPersonas);
		tablaPersonas.setDefaultEditor(Object.class, null);

		String[] ColumnasCeibalitas = { "Numero ceibalita", "Notas" };
		DefaultTableModel modeloCeibalitas = new DefaultTableModel(null, ColumnasCeibalitas);
		JTable tablaCeibalitas = new JTable(modeloCeibalitas);
		tablaCeibalitas.getTableHeader().setReorderingAllowed(false);
		JScrollPane panelDezplazableCeibalitas = new JScrollPane(tablaCeibalitas);
		tablaCeibalitas.setDefaultEditor(Object.class, null);

		// --- Montaje de la interfaz ---
		this.add(pArriba, BorderLayout.NORTH);
		pArriba.add(pArribaIzq);
		pArribaIzq.add(lbl4);

		pArriba.add(pArribaIzq2);
		pArribaIzq2.add(btnEdit);
		pArribaIzq2.add(btnEliminar);

		pArriba.add(lbl1);
		pArriba.add(lblP);
		pArriba.add(lbl3);
		pArriba.add(pRegistros);
		pRegistros.add(btnRegistros);
		pArriba.add(lbl5);

		this.add(contenedor, BorderLayout.CENTER);
		contenedor.add(pPrestamosCompus, "PrestamosCompus");
		pPrestamosCompus.add(panelDesplazablePrestCeibalitas);

		contenedor.add(pPrestamosLibros, "PrestamosLibros");
		pPrestamosLibros.add(panelDesplazablePrestLibros);

		contenedor.add(pInventario, "Inventario");
		pInventario.add(panelDesplazableInventario);

		contenedor.add(pVerPersonas, "VerPersonas");
		pVerPersonas.add(panelDesplazablePersonas);

		contenedor.add(pCeibalitas, "VerCeibalitas");
		pCeibalitas.add(panelDezplazableCeibalitas);

		this.add(pIzq, BorderLayout.WEST);

		// Añadir botones a la barra lateral
		pIzq.add(btnPrestamosCompu);
		pIzq.add(btnPrestamosLibro);
		pIzq.add(btnVerLibros);
		pIzq.add(btnVerPersonas);
		pIzq.add(btnVerCompus);
		pIzq.add(btnPrestCompu);
		pIzq.add(btnPrestLibro);
		pIzq.add(btnAgregLibro);
		pIzq.add(btnAgregPers);
		pIzq.add(btnAgregCompu);
		pIzq.add(btnCerrarSesion);

		// Cargar inicialmente la lista de préstamos de ceibalitas
		gestor.ListarPrestamoCeibalita(modeloPrestCeibalitas);

		// --- Renderers personalizados para colorear filas según fecha límite ---
		// Prestamos de ceibalitas: compara fecha y hora de devolución
		tablaPrestCeibalitas.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {

				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

				Object fechaLimite = table.getValueAt(row, 6); // columna "Fecha de entrega"
				Object horaLimite = table.getValueAt(row, 7); // columna "Hora de devolución"

				boolean pintarRojo = false;
				boolean pintarAmarillo = false;

				if (fechaLimite != null && horaLimite != null) {
					try {
						String fechaTexto = fechaLimite.toString().trim();
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
						LocalDate fechaPasada = LocalDate.parse(fechaTexto, formatter);

						String horaLimite1 = horaLimite.toString().trim();
						DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("HH:mm:ss");
						LocalTime horaPasada = LocalTime.parse(horaLimite1, formatter1);

						LocalTime horaHoy = LocalTime.now();
						LocalDate fechaHoy = LocalDate.now();

						// Si la fecha/hora límite ya pasó => rojo
						if (fechaPasada.isBefore(fechaHoy)
								|| (fechaPasada.isEqual(fechaHoy) && horaPasada.isBefore(horaHoy))) {
							pintarRojo = true;
							pintarAmarillo = false;

						} else if (fechaPasada.isEqual(fechaHoy) && horaPasada.equals(horaHoy)) {
							// comparación exacta => amarillo (advertencia)
							pintarRojo = false;
							pintarAmarillo = true;
						}

					} catch (Exception e) {
						e.printStackTrace(); // útil para depurar si el formato falla
					}
				}

				if (pintarRojo) {
					c.setBackground(new Color(255, 60, 60));
					c.setForeground(Color.WHITE);
				} else if (pintarAmarillo) {
					c.setBackground(new Color(255, 230, 153));
					c.setForeground(Color.BLACK);
				} else {
					c.setBackground(isSelected ? table.getSelectionBackground() : Color.WHITE);
					c.setForeground(isSelected ? table.getSelectionForeground() : Color.BLACK);
				}

				return c;
			}
		});

		// Prestamos de libros: colorea según fecha de límite (columna 9)
		tablaPrestLibros.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {

				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

				Object fechaValor = table.getValueAt(row, 9); // columna "Fecha de Limite"
				boolean pintarRojo = false;
				boolean pintarAmarillo = false;

				if (fechaValor != null) {
					try {
						String fechaTexto = fechaValor.toString().trim();
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
						LocalDate fechaCelda = LocalDate.parse(fechaTexto, formatter);
						LocalDate hoy = LocalDate.now();

						if (fechaCelda.isBefore(hoy)) {
							pintarRojo = true;
							pintarAmarillo = false;
						} else if (fechaCelda.isEqual(hoy)) {
							pintarRojo = false;
							pintarAmarillo = true;
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				if (pintarRojo) {
					c.setBackground(new Color(255, 60, 60));
					c.setForeground(Color.WHITE);
				} else if (pintarAmarillo) {
					c.setBackground(new Color(255, 230, 153));
					c.setForeground(Color.BLACK);
				} else {
					c.setBackground(isSelected ? table.getSelectionBackground() : Color.WHITE);
					c.setForeground(isSelected ? table.getSelectionForeground() : Color.BLACK);
				}

				return c;
			}
		});

		// --- Listeners de los botones de navegación ---
		btnPrestamosCompu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				opcionTabla = 0;
				lblP.setText("Prestamos Ceibalitas");
				contenedor.setVisible(false);

				// Mostrar diálogo de carga y solicitar datos en el EDT
				JDialog cargando = DialogoCargaUtil.mostrarDialogoCarga(vtnPaginaPrincipal.this);
				SwingUtilities.invokeLater(() -> {
					try {
						gestor.ListarPrestamoCeibalita(modeloPrestCeibalitas);
						contenedor.setVisible(true);
						CardLayout cl = (CardLayout) contenedor.getLayout();
						cl.show(contenedor, "PrestamosCompus");
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(vtnPaginaPrincipal.this, "Error: " + ex.getMessage());
					} finally {
						DialogoCargaUtil.cerrarDialogoCarga(cargando);
					}
				});
			}
		});

		btnPrestamosLibro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				opcionTabla = 1;
				lblP.setText("Prestamos Libros");
				contenedor.setVisible(false);
				JDialog cargando = DialogoCargaUtil.mostrarDialogoCarga(vtnPaginaPrincipal.this);
				SwingUtilities.invokeLater(() -> {
					try {
						gestor.ListarPrestamoLibro(modeloPrestLibros);
						contenedor.setVisible(true);
						CardLayout cl = (CardLayout) contenedor.getLayout();
						cl.show(contenedor, "PrestamosLibros");
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(vtnPaginaPrincipal.this, "Error: " + ex.getMessage());
					} finally {
						DialogoCargaUtil.cerrarDialogoCarga(cargando);
					}
				});
			}
		});

		btnVerLibros.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				opcionTabla = 2;
				lblP.setText("Inventario");
				contenedor.setVisible(false);
				JDialog cargando = DialogoCargaUtil.mostrarDialogoCarga(vtnPaginaPrincipal.this);
				SwingUtilities.invokeLater(() -> {
					try {
						gestor.ListarLibros(modeloLibros);
						contenedor.setVisible(true);
						CardLayout cl = (CardLayout) contenedor.getLayout();
						cl.show(contenedor, "Inventario");
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(vtnPaginaPrincipal.this, "Error: " + ex.getMessage());
					} finally {
						DialogoCargaUtil.cerrarDialogoCarga(cargando);
					}
				});
			}
		});

		btnVerPersonas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				opcionTabla = 3;
				lblP.setText("Personas");
				contenedor.setVisible(false);
				JDialog cargando = DialogoCargaUtil.mostrarDialogoCarga(vtnPaginaPrincipal.this);
				SwingUtilities.invokeLater(() -> {
					try {
						gestor.ListarPersona(modeloPersonas);
						contenedor.setVisible(true);
						CardLayout cl = (CardLayout) contenedor.getLayout();
						cl.show(contenedor, "VerPersonas");
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(vtnPaginaPrincipal.this, "Error: " + ex.getMessage());
					} finally {
						DialogoCargaUtil.cerrarDialogoCarga(cargando);
					}
				});
			}
		});

		btnVerCompus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				opcionTabla = 4;
				lblP.setText("Ceibalitas");
				contenedor.setVisible(false);
				JDialog cargando = DialogoCargaUtil.mostrarDialogoCarga(vtnPaginaPrincipal.this);
				SwingUtilities.invokeLater(() -> {
					try {
						gestor.ListarCeibalita(modeloCeibalitas);
						contenedor.setVisible(true);
						CardLayout cl = (CardLayout) contenedor.getLayout();
						cl.show(contenedor, "VerCeibalitas");
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(vtnPaginaPrincipal.this, "Error: " + ex.getMessage());
					} finally {
						DialogoCargaUtil.cerrarDialogoCarga(cargando);
					}
				});
			}
		});

		// --- Acciones para crear/guardar préstamos y registros ---
		btnPrestCompu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vtnGuardarPrestamoCeibalita vtn2 = new vtnGuardarPrestamoCeibalita(modeloPrestCeibalitas);
				vtn2.setVisible(true);
			}
		});

		btnPrestLibro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vtnGuardarPrestamoLibro vtn3 = new vtnGuardarPrestamoLibro(modeloPrestLibros);
				vtn3.setVisible(true);
			}
		});

		btnAgregLibro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nada = ""; // flag vacío usado por otros formularios
				vtnGuardarLibro vtn4 = new vtnGuardarLibro(modeloLibros, nada);
				vtn4.setVisible(true);
			}
		});

		btnAgregPers.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nada = "";
				vtnGuardarPersona vtn1 = new vtnGuardarPersona(modeloPersonas, nada);
				vtn1.setVisible(true);
			}
		});

		btnAgregCompu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nada = "";
				vtnGuardarCeibalita vtn1 = new vtnGuardarCeibalita(modeloCeibalitas, nada);
				vtn1.setVisible(true);
			}
		});

		// Cerrar sesión: cerrar esta ventana y abrir la de inicio
		btnCerrarSesion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vtnPaginaPrincipal.this.dispose();
				vtnInicioSesion vtn5 = new vtnInicioSesion();
				vtn5.setVisible(true);
			}
		});

		// --- Editar registro según la tabla activa ---
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Dependiendo de la tabla activa (opcionTabla) se abre la ventana de edición

				if (opcionTabla == 0) { // prestamos ceibalitas

					// fila seleccionada (índice de la vista)
					int seleccionada = tablaPrestCeibalitas.getSelectedRow();

					if (seleccionada != -1) {

						// obtiene el valor de la primera columna (número ceibalita) y lo castea a int

						int numeroCeibalita = (int) tablaPrestCeibalitas.getValueAt(seleccionada, 0);

						// convierte a String para pasar al constructor del diálogo

						String numeroCeibalita1 = Integer.toString(numeroCeibalita);

						// obtiene CI de la segunda columna y lo convierte

						int ci = (int) tablaPrestCeibalitas.getValueAt(seleccionada, 1);
						String ci1 = Integer.toString(ci);

						// obtiene horaDevolucion (columna 7) como String

						String horaDevolucion = (String) tablaPrestCeibalitas.getValueAt(seleccionada, 7);

						// crea y muestra la ventana de edición específica para "prestamo ceibalita"
						vtnEditarPrestamoCeibalita vtn1 = new vtnEditarPrestamoCeibalita(modeloPrestCeibalitas,numeroCeibalita1, ci1, horaDevolucion);
						vtn1.setVisible(true);
					} else {
						// no hay fila seleccionada -> aviso al usuario
						JOptionPane.showMessageDialog(null, "Seleccione una fila");
					}

				} else if (opcionTabla == 1) { // prestamos libros
					int seleccionada = tablaPrestLibros.getSelectedRow();
					if (seleccionada != -1) {
						// isbn en columna 0 (String)
						String isbn = (String) tablaPrestLibros.getValueAt(seleccionada, 0);
						// ci en columna 2 (int) -> conv a String
						int ci = (int) tablaPrestLibros.getValueAt(seleccionada, 2);
						String ci1 = Integer.toString(ci);
						// cantidad en columna 7 (int)
						int cantidad = (int) tablaPrestLibros.getValueAt(seleccionada, 7);
						// fecha límite en columna 9 (String)
						String fechaLimite = (String) tablaPrestLibros.getValueAt(seleccionada, 9);

						vtnEditarPrestamoLibro vtn1 = new vtnEditarPrestamoLibro(modeloPrestLibros, isbn, ci1, cantidad,fechaLimite);
						vtn1.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "Seleccione una fila");
					}

				} else if (opcionTabla == 2) { // inventario (libros)
					int seleccionada = tablaLibros.getSelectedRow();
					if (seleccionada != -1) {
						// lectura de varias columnas: isbn, autor, titulo, genero, materia, ejemplares,notas
						String isbn = (String) tablaLibros.getValueAt(seleccionada, 0);
						String autor = (String) tablaLibros.getValueAt(seleccionada, 1);
						String titulo = (String) tablaLibros.getValueAt(seleccionada, 2);
						String genero = (String) tablaLibros.getValueAt(seleccionada, 3);
						String materia = (String) tablaLibros.getValueAt(seleccionada, 4);
						int ejemplares = (int) tablaLibros.getValueAt(seleccionada, 5);
						String notas = (String) tablaLibros.getValueAt(seleccionada, 6);

						vtnEditLibro vtn7 = new vtnEditLibro(modeloLibros, isbn, autor, titulo, genero, materia,ejemplares, notas);
						vtn7.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "Seleccione una fila");
					}

				} else if (opcionTabla == 3) { // personas
					int seleccionada = tablaPersonas.getSelectedRow();
					if (seleccionada != -1) {
						int ci = (int) tablaPersonas.getValueAt(seleccionada, 0);
						String ci1 = Integer.toString(ci);
						String nombre = (String) tablaPersonas.getValueAt(seleccionada, 1);
						String apellido = (String) tablaPersonas.getValueAt(seleccionada, 2);
						int telefono = (int) tablaPersonas.getValueAt(seleccionada, 3);
						String telefono1 = Integer.toString(telefono);
						String grupo = (String) tablaPersonas.getValueAt(seleccionada, 4);
						String tipo = (String) tablaPersonas.getValueAt(seleccionada, 5);

						vtnEditPerson vtn1 = new vtnEditPerson(modeloPersonas, ci1, nombre, apellido, telefono1, grupo,tipo);
						vtn1.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "Seleccione una fila");
					}

				} else if (opcionTabla == 4) { // ceibalitas
					int seleccionada = tablaCeibalitas.getSelectedRow();
					if (seleccionada != -1) {
						int numCeibalita = (int) tablaCeibalitas.getValueAt(seleccionada, 0);
						String numCeibalita1 = Integer.toString(numCeibalita);
						String notas = (String) tablaCeibalitas.getValueAt(seleccionada, 1);

						vtnEditCeibalita vtn1 = new vtnEditCeibalita(modeloCeibalitas, numCeibalita1, notas);
						vtn1.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "Seleccione una fila");
					}
				}
			}
		});
		// --- Eliminar registros según la tabla activa (con confirmación) ---
		btnEliminar.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Si la tabla activa es 0 -> prestamos ceibalitas
		        if (opcionTabla == 0) {
		            // índice de la fila seleccionada en la vista (podría ser -1 si no hay selección)
		        	
		            int seleccionada = tablaPrestCeibalitas.getSelectedRow();
		            
		            if (seleccionada != -1) {
		                // obtiene el nombre desde la columna 2 
		                String nombre = (String) tablaPrestCeibalitas.getValueAt(seleccionada, 2);
		                // confirma con el usuario si desea eliminar (sí/no)
		                int opcion = JOptionPane.showConfirmDialog(null,
		                        "¿Desea eliminar el prestamo a nombre de " + nombre + "?", "Confirmar eliminación",
		                        JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

		                // verificación de la opción elegida (0 es YES)
		                
		                if (opcion == 0) {
		                    // obtiene datos necesarios para eliminar/registrar (col 0 y 1)
		                	
		                    int numCeibalita = (int) tablaPrestCeibalitas.getValueAt(seleccionada, 0);
		                    int ci = (int) tablaPrestCeibalitas.getValueAt(seleccionada, 1);
		                    
		                    // llama al gestor para eliminar el préstamo (presumiblemente en BD)
		                    
		                    if (gestor.eliminarPrestamoCeibalita(numCeibalita)) {
		                        // refresca el modelo de la tabla después de eliminar
		                        gestor.ListarPrestamoCeibalita(modeloPrestCeibalitas);
		                        // registra la acción (log/historial)
		                        gestor.RegistroPrestamoCompu(opcionRegistro, numCeibalita, ci);
		                        JOptionPane.showMessageDialog(null, "Prestamo eliminado");
		                    }
		                } else {
		                    // si el usuario eligió NO
		                    JOptionPane.showMessageDialog(null, "Prestamo no eliminado");
		                }
		            } else {
		                // no hay fila seleccionada
		                JOptionPane.showMessageDialog(null, "Seleccione una fila");
		            }

		        // Si la tabla activa es 1 -> prestamos libros
		        } else if (opcionTabla == 1) {
		            int seleccionada = tablaPrestLibros.getSelectedRow();
		            if (seleccionada != -1) {
		                String nombre = (String) tablaPrestLibros.getValueAt(seleccionada, 3);
		                int opcion = JOptionPane.showConfirmDialog(null,
		                        "¿Desea eliminar el prestamo a nombre de " + nombre + "?", "Confirmar eliminación",
		                        JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		                if (opcion == 0) {
		                    String isbn = (String) tablaPrestLibros.getValueAt(seleccionada, 0);
		                    int ci = (int) tablaPrestLibros.getValueAt(seleccionada, 2);
		                    if (gestor.eliminarPrestamolibro(isbn, ci)) {
		                        gestor.ListarPrestamoLibro(modeloPrestLibros);
		                        gestor.RegistroPrestamoLibro(opcionRegistro, isbn, ci);
		                        JOptionPane.showMessageDialog(null, "Prestamo eliminado");
		                    }
		                } else {
		                    JOptionPane.showMessageDialog(null, "Prestamo no eliminado");
		                }
		            } else {
		                JOptionPane.showMessageDialog(null, "Seleccione una fila");
		            }

		        // opcionTabla == 2 -> inventario (libros)
		        } else if (opcionTabla == 2) {
		            int seleccionada = tablaLibros.getSelectedRow();
		            if (seleccionada != -1) {
		                String titulo = (String) tablaLibros.getValueAt(seleccionada, 2);
		                int opcion = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el libro " + titulo + "?",
		                        "Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		                if (opcion == 0) {
		                    String isbn = (String) tablaLibros.getValueAt(seleccionada, 0);
		                    if (gestor.eliminarLibro(isbn)) {
		                        gestor.ListarLibros(modeloLibros);
		                        gestor.RegistroLibro(opcionRegistro, titulo);
		                        JOptionPane.showMessageDialog(null, "Libro eliminado");
		                    }
		                } else {
		                    JOptionPane.showMessageDialog(null, "Libro no eliminado");
		                }
		            } else {
		                JOptionPane.showMessageDialog(null, "Seleccione una fila");
		            }

		        // opcionTabla == 3 -> personas
		        } else if (opcionTabla == 3) {
		            int seleccionada = tablaPersonas.getSelectedRow();
		            if (seleccionada != -1) {
		                String nombre = (String) tablaPersonas.getValueAt(seleccionada, 1);
		                int opcion = JOptionPane.showConfirmDialog(null, "¿Desea eliminar a " + nombre + "?",
		                        "Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		                if (opcion == 0) {
		                    int ci = (int) tablaPersonas.getValueAt(seleccionada, 0);
		                    if (gestor.eliminarPersona(ci)) {
		                        gestor.ListarPersona(modeloPersonas);
		                        gestor.RegistroPersona(opcionRegistro, ci);
		                        JOptionPane.showMessageDialog(null, "Persona Eliminada");
		                    }
		                } else {
		                    JOptionPane.showMessageDialog(null, "Persona no eliminado");
		                }
		            } else {
		                JOptionPane.showMessageDialog(null, "Seleccione una fila");
		            }

		        // opcionTabla == 4 -> ceibalitas
		        } else if (opcionTabla == 4) {
		            int seleccionada = tablaCeibalitas.getSelectedRow();
		            if (seleccionada != -1) {
		                int num = (int) tablaCeibalitas.getValueAt(seleccionada, 0);
		                int opcion = JOptionPane.showConfirmDialog(null, "¿Desea eliminar la ceibalita N° " + num + "?",
		                        "Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		                if (opcion == 0) {
		                    if (gestor.eliminarCeibalita(num)) {
		                        // registra la eliminación y refresca la lista
		                        gestor.RegistroCompu(opcionRegistro, num);
		                        gestor.ListarCeibalita(modeloCeibalitas);
		                        JOptionPane.showMessageDialog(null, "Ceibalita Eliminada");
		                    }
		                } else {
		                    JOptionPane.showMessageDialog(null, "Ceibalita no eliminada");
		                }
		            } else {
		                JOptionPane.showMessageDialog(null, "Seleccione una fila");
		            }
		        }
		    }
		});

		// Abrir ventana de registros
		btnRegistros.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vtnRegistros vtn8 = new vtnRegistros();
				vtn8.setVisible(true);
			}
		});

	}

}