package Proyecto;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * Clase Logica - Actúa como puente entre la UI (ventanas) y la capa de
 * persistencia MySQL (BD). - Contiene validaciones de entrada, llamadas a BD
 * para CRUD y métodos para registrar acciones.
 *
 */

public class Logica {

	// Instancia directa de la clase MySQL. Para testabilidad es preferible inyectar
	// una interfaz.
	MySQL BD = new MySQL();

	public static void main(String[] args) {
		// Punto de entrada: abre ventana de inicio de sesión
		vtnInicioSesion vtn1 = new vtnInicioSesion();
		vtn1.setVisible(true);

	}

	/*
	 * ------------------------ Listados (que vienen de la BD)
	 * ------------------------
	 */
	public void ListarLibros(DefaultTableModel modelo) {

		// Llena el DefaultTableModel con los libros desde la BD
		BD.ListarLibros(modelo);

	}

	/*
	 * ------------------------ Altas (comprobaciones y llaman a BD)
	 * ------------------------
	 */

	/**
	 * Alta de libro con comprobaciones. Devuelve true si la operación en BD fue
	 * exitosa.
	 */

	public boolean altaLibro(String isbn, String autor, String titulo, String genero, String materia, int ejemplares,
			String notas) {

		boolean resultado = false;

		if (isbn.length() < 1) {

			JOptionPane.showMessageDialog(null, "Error: 'ISBN' vacío" + "\n" + "\n" + "Complete el campo");

		} else if (isbn.length() < 10) {

			JOptionPane.showMessageDialog(null,
					"Error: 'ISBN' menor a 10 caracteres" + "\n" + "\n" + "Ingrese un número válido");

		} else if (isbn.length() > 13) {

			JOptionPane.showMessageDialog(null, "Error: 'ISBN' muy largo" + "\n" + "\n" + "Ingrese un número válido");

		} else if (ejemplares < 1) {

			JOptionPane.showMessageDialog(null,
					"Error: 'Ejemplares' no puede ser menor a 1" + "\n" + "\n" + "Ingrese un número válido");

		} else if (titulo.length() < 1) {

			JOptionPane.showMessageDialog(null,
					"Error: 'Título' está vacío " + "\n" + "\n" + "Ingrese un título válido");

		} else if (autor.length() < 1) {

			JOptionPane.showMessageDialog(null,
					"Error: 'Autor' está vacío " + "\n" + "\n" + "Ingrese un Autor válido o 'Anónimo'");

		} else {

			if (BD.validarExistenciaLibro(isbn)) {

				JOptionPane.showMessageDialog(null, "El libro ya existe");

			} else {

				if (BD.altaLibro(isbn, autor, titulo, genero, materia, ejemplares, notas)) {

					resultado = true;
				}
			}

		}

		return resultado;
	}

	// Método que lista todas las personas en la tabla de la interfaz gráfica
	public void ListarPersona(DefaultTableModel modelo) {
		// Llama a la capa de datos para poblar el modelo con todas las personas
		BD.ListarPersonas(modelo);
	}

	// Método que realiza el alta de una persona en la base de datos
	// Recibe datos desde la interfaz y retorna true si el registro fue exitoso
	public boolean altaPersona(JTextField ci, String nombre, String apellido, JTextField telefono, String grupo,
			String tipo) {

		boolean resultado = false; // Inicializa el indicador de éxito en false

		// =====================
		// COMPROBACIONES DE DATOS
		// =====================

		// Comprobación: CI no vacío
		if (ci.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Error: 'CI' no puede estar vacía");

			// Comprobación: CI mínimo 7 dígitos
		} else if (ci.getText().length() < 7) {
			JOptionPane.showMessageDialog(null, "Error: 'CI' no puede tener menos de 7 dígitos");

			// Comprobación: CI máximo 8 dígitos
		} else if (ci.getText().length() > 8) {
			JOptionPane.showMessageDialog(null, "Error: 'CI' no puede tener más de 8 dígitos");

			// Comprobación: nombre no vacío
		} else if (nombre == null || nombre.equals("")) {
			JOptionPane.showMessageDialog(null, "Error: 'Nombre' no puede estar vacío");

			// Comprobación: apellido no vacío
		} else if (apellido == null || apellido.equals("")) {
			JOptionPane.showMessageDialog(null, "Error: 'Apellido' no puede estar vacío");

			// Comprobación: teléfono no vacío
		} else if (telefono.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Error: 'Teléfono' no puede estar vacío");

			// Comprobación: teléfono máximo 9 dígitos
		} else if (telefono.getText().length() > 9) {
			JOptionPane.showMessageDialog(null, "Error: 'Teléfono' no puede ser mayor a 9 dígitos");

			// Comprobación: teléfono mínimo 9 dígitos
		} else if (telefono.getText().length() < 9) {
			JOptionPane.showMessageDialog(null, "Error: 'Teléfono' no puede ser menor a 9 dígitos");

			// =====================
			// PROCESAMIENTO
			// =====================
		} else {
			// Conversión de CI y teléfono a enteros
			int ci1 = Integer.parseInt(ci.getText());
			int telefono1 = Integer.parseInt(telefono.getText());

			// Comprobación: existencia previa de la persona en la base de datos
			if (BD.validarExistenciaPersona(ci1)) {
				JOptionPane.showMessageDialog(null, "La persona ya existe");

			} else {
				// Inserción en la base de datos, resultado true si se registró correctamente
				if (BD.altaPersona(ci1, nombre, apellido, telefono1, grupo, tipo)) {
					resultado = true;
				}
			}
		}

		return resultado; // Retorna true si el registro fue exitoso, false en caso contrario
	}

	// Método que lista todas las Ceibalitas en la tabla de la interfaz gráfica
	public void ListarCeibalita(DefaultTableModel modelo) {
		// Llama a la capa de datos para poblar el modelo con todas las Ceibalitas
		BD.ListarCeibalita(modelo);
	}

	// Método que realiza el alta de una Ceibalita
	// Recibe el número de Ceibalita y notas, retorna true si el registro fue
	// exitoso
	public boolean altaCeibalita(int numCeibalita, String notas) {

		boolean resultado = false; // Inicializa el indicador de éxito en false

		// Comprobación: número de Ceibalita >= 1
		if (numCeibalita < 1) {
			JOptionPane.showMessageDialog(null, "Error: 'Número de Ceibalita' no puede ser menor que 1");

		} else {
			// Comprobación: existencia previa de la Ceibalita en la base de datos
			if (BD.validarExistenciaCeibalita(numCeibalita)) {
				JOptionPane.showMessageDialog(null, "La Ceibalita ya existe");

			} else {
				// Inserción en la base de datos, resultado true si se registró correctamente
				if (BD.altaCeibalita(numCeibalita, notas)) {
					resultado = true;
				}
			}
		}

		return resultado; // Retorna true si el registro fue exitoso, false en caso contrario
	}

	// ============================
	// MÉTODOS DE PRÉSTAMO DE CEIBALITAS
	// ============================

	// Método que lista todos los préstamos de ceibalitas en la tabla de la interfaz
	// gráfica
	public void ListarPrestamoCeibalita(DefaultTableModel modelo) {
		// Llama a la capa de datos para poblar el modelo con todos los préstamos de
		// ceibalitas
		BD.ListarPrestamoCeibalita(modelo);
	}

	// Método que realiza el alta de un préstamo de ceibalita
	// Recibe número de ceibalita, CI del usuario, fecha actual y hora límite de
	// devolución
	// Retorna true si el préstamo se registra correctamente
	public boolean altaPrestamoCeibalita(JTextField numCeibalita, JTextField ci, LocalDate fechaHoy, String horaLim) {

		boolean resultado = false; // Inicializa indicador de éxito

		// =====================
		// COMPROBACIONES DE DATOS
		// =====================

		// Comprobación: número de Ceibalita no vacío
		if (numCeibalita.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Error: 'Número Ceibalita' no puede estar vacío");

			// Comprobación: CI no vacío
		} else if (ci.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Error: 'CI' no puede estar vacía");

			// Comprobación: CI mínimo 7 dígitos
		} else if (ci.getText().length() < 7) {
			JOptionPane.showMessageDialog(null, "Error: 'CI' no puede tener menos de 7 dígitos");

			// Comprobación: CI máximo 8 dígitos
		} else if (ci.getText().length() > 8) {
			JOptionPane.showMessageDialog(null, "Error: 'CI' no puede tener más de 8 dígitos");

			// Comprobación: hora de devolución no vacía
		} else if (horaLim.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Error: 'Fecha devolución' no puede estar vacía");

			// Comprobación: formato de hora de devolución (hh:mm:ss) -> 8 caracteres
		} else if (horaLim.length() != 8) {
			JOptionPane.showMessageDialog(null, "Error: 'Hora devolución' inválida");

			// =====================
			// PROCESAMIENTO
			// =====================
		} else {
			// Conversión de número de ceibalita y CI a enteros
			int numCeibalita1 = Integer.parseInt(numCeibalita.getText());
			int ci1 = Integer.parseInt(ci.getText());

			// Comprobación: existencia de la ceibalita
			if (BD.validarExistenciaCeibalita(numCeibalita1)) {

				// Comprobación: existencia de la persona
				if (BD.validarExistenciaPersona(ci1)) {

					// Formato de fecha actual como String
					String fechaHoy1 = fechaHoy.toString();

					// Registro del préstamo en la base de datos
					if (BD.altaPrestamoCeibalita(numCeibalita1, ci1, horaLim, fechaHoy1)) {
						resultado = true; // Préstamo registrado correctamente
					}

					// Si la persona no existe
				} else if (!BD.validarExistenciaPersona(ci1)) {
					int opcion = JOptionPane.showConfirmDialog(null,
							"La persona con la CI: '" + ci1 + "', no existe.\n¿Quieres agregar a la persona?", "",
							JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

					if (opcion == 0) {
						// Abre ventana para registrar persona nueva
						DefaultTableModel modelo = new DefaultTableModel();
						String ci2 = Integer.toString(ci1);
						vtnGuardarPersona vtn1 = new vtnGuardarPersona(modelo, ci2);
						vtn1.setVisible(true);
					}
				}

				// Si la ceibalita no existe
			} else if (!BD.validarExistenciaCeibalita(numCeibalita1)) {
				int opcion = JOptionPane.showConfirmDialog(null,
						"La ceibalita con el N°: '" + numCeibalita1 + "', no existe.\n¿Quieres agregar la ceibalita?",
						"", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

				if (opcion == 0) {
					// Abre ventana para registrar ceibalita nueva
					DefaultTableModel modelo = new DefaultTableModel();
					String numCeibalitaStr = numCeibalita.getText();
					vtnGuardarCeibalita vtn1 = new vtnGuardarCeibalita(modelo, numCeibalitaStr);
					vtn1.setVisible(true);
				}
			}
		}

		return resultado; // Devuelve true si el préstamo se registró correctamente
	}

	// ============================
	// MÉTODOS DE PRÉSTAMO DE LIBROS
	// ============================

	// Método que lista todos los préstamos de libros en la tabla de la interfaz
	// gráfica
	public void ListarPrestamoLibro(DefaultTableModel modelo) {
		BD.ListarPrestamoLibro(modelo);
	}

	// Método que realiza el alta de un préstamo de libro
	// Recibe ISBN, CI del usuario, cantidad, fecha actual y fecha límite de
	// devolución
	// Retorna true si el préstamo se registra correctamente
	public boolean altaPrestamoLibro(String isbn, JTextField ci, int cant, LocalDate fechaHoy, String fechaLimite) {

		boolean resultado = false; // Inicializa indicador de éxito
		String fechaHoy1 = fechaHoy.toString(); // Convierte fecha actual a String

		// =====================
		// COMPROBACIONES DE DATOS
		// =====================

		// Comprobación: CI no vacío
		if (ci.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Error: 'CI' no puede estar vacía");

			// Comprobación: CI mínimo 7 dígitos
		} else if (ci.getText().length() < 7) {
			JOptionPane.showMessageDialog(null, "Error: 'CI' no puede tener menos de 7 dígitos");

			// Comprobación: CI máximo 8 dígitos
		} else if (ci.getText().length() > 8) {
			JOptionPane.showMessageDialog(null, "Error: 'CI' no puede tener más de 8 dígitos");

			// Comprobación: ISBN no vacío
		} else if (isbn.length() < 1) {
			JOptionPane.showMessageDialog(null, "Error: 'ISBN' vacío\nComplete el campo");

			// Comprobación: ISBN mínimo 10 caracteres
		} else if (isbn.length() < 10) {
			JOptionPane.showMessageDialog(null, "Error: 'ISBN' menor a 10 caracteres\nIngrese un número válido");

			// Comprobación: ISBN máximo 13 caracteres
		} else if (isbn.length() > 13) {
			JOptionPane.showMessageDialog(null, "Error: 'ISBN' muy largo\nIngrese un número válido");

			// Comprobación: cantidad >= 1
		} else if (cant < 1) {
			JOptionPane.showMessageDialog(null, "Error: 'Cantidad' no puede ser menor a 1");

			// Comprobación: fecha límite no vacía
		} else if (fechaLimite.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Error: 'Fecha límite' no puede estar vacía");

			// Comprobación: formato de fecha límite (aaaa-mm-dd) -> 10 caracteres
		} else if (fechaLimite.length() != 10) {
			JOptionPane.showMessageDialog(null, "Error: 'Fecha límite' inválida");

			// =====================
			// PROCESAMIENTO
			// =====================
		} else {
			int ci1 = Integer.parseInt(ci.getText()); // Conversión de CI a entero

			// Comprobación: existencia del libro
			if (BD.validarExistenciaLibro(isbn)) {

				// Comprobación: existencia de la persona
				if (BD.validarExistenciaPersona(ci1)) {

					// Registro del préstamo en la base de datos
					if (BD.altaPrestamoLibro(isbn, ci1, cant, fechaHoy1, fechaLimite)) {
						resultado = true;
					}

					// Si la persona no existe
				} else if (!BD.validarExistenciaPersona(ci1)) {
					int opcion = JOptionPane.showConfirmDialog(null,
							"La persona con la CI: '" + ci1 + "', no existe.\n¿Quieres agregar a la persona?", "",
							JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

					if (opcion == 0) {
						DefaultTableModel modelo = new DefaultTableModel();
						String ci2 = Integer.toString(ci1);
						vtnGuardarPersona vtn1 = new vtnGuardarPersona(modelo, ci2);
						vtn1.setVisible(true);
					}
				}

				// Si el libro no existe
			} else if (!BD.validarExistenciaLibro(isbn)) {
				int opcion = JOptionPane.showConfirmDialog(null,
						"El libro con el ISBN: '" + isbn + "', no existe.\n¿Quieres agregar el libro?", "",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

				if (opcion == 0) {
					DefaultTableModel modelo = new DefaultTableModel();
					vtnGuardarLibro vtn1 = new vtnGuardarLibro(modelo, isbn);
					vtn1.setVisible(true);
				}
			}
		}

		return resultado; // Devuelve true si el préstamo se registró correctamente
	}

	// ============================
	// MÉTODOS DE REGISTRO DE ACCIONES
	// ============================

	// Método que lista todos los registros en la tabla de la interfaz gráfica
	public void ListarRegistros(DefaultTableModel modelo) {
		// Llama a la capa de datos para poblar la tabla con todos los registros
		BD.ListarRegistros(modelo);
	}

	// ----------------------------
	// Registro de acciones sobre LIBROS
	// ----------------------------
	public void RegistroLibro(int opcion, String datoAdicional) {
		String accion;
		LocalDate fechReg = LocalDate.now(); // Fecha actual
		String fechaRegistro = fechReg.toString();

		LocalTime horaReg = LocalTime.now(); // Hora actual
		String horaRegistro = horaReg.toString();

		// Comprobación de la acción según opción
		if (opcion == 1) { // Libro agregado
			accion = "Libro Registrado con el título: " + datoAdicional;
			BD.AltaRegistro(accion, fechaRegistro, horaRegistro);

		} else if (opcion == 2) { // Libro eliminado
			accion = "Libro con el título: " + datoAdicional + " eliminado";
			BD.AltaRegistro(accion, fechaRegistro, horaRegistro);

		} else if (opcion == 3) { // Libro editado
			accion = "Libro con el título: " + datoAdicional + " editado"; // Nota: no se especifica qué se editó
			BD.AltaRegistro(accion, fechaRegistro, horaRegistro);
		}
	}

	// ----------------------------
	// Registro de acciones sobre PERSONAS
	// ----------------------------
	public void RegistroPersona(int opcion, int datoAdicional) {
		String accion;
		LocalDate fechReg = LocalDate.now();
		String fechaRegistro = fechReg.toString();

		LocalTime horaReg = LocalTime.now();
		String horaRegistro = horaReg.toString();

		if (opcion == 1) { // Persona agregada
			accion = "Persona registrada con la CI: " + datoAdicional;
			BD.AltaRegistro(accion, fechaRegistro, horaRegistro);

		} else if (opcion == 2) { // Persona eliminada
			accion = "Persona con la CI: " + datoAdicional + " eliminada";
			BD.AltaRegistro(accion, fechaRegistro, horaRegistro);

		} else if (opcion == 3) { // Persona editada
			accion = "Persona con la CI: " + datoAdicional + " editada";
			BD.AltaRegistro(accion, fechaRegistro, horaRegistro);
		}
	}

	// ----------------------------
	// Registro de acciones sobre CEIBALITAS (computadoras)
	// ----------------------------
	public void RegistroCompu(int opcion, int datoAdicional) {
		String accion;
		LocalDate fechReg = LocalDate.now();
		String fechaRegistro = fechReg.toString();

		LocalTime horaReg = LocalTime.now();
		String horaRegistro = horaReg.toString();

		if (opcion == 1) { // Ceibalita agregada
			accion = "Ceibalita registrada con el N°: " + datoAdicional;
			BD.AltaRegistro(accion, fechaRegistro, horaRegistro);

		} else if (opcion == 2) { // Ceibalita eliminada
			accion = "Ceibalita N°: " + datoAdicional + " eliminada";
			BD.AltaRegistro(accion, fechaRegistro, horaRegistro);

		} else if (opcion == 3) { // Ceibalita editada
			accion = "Ceibalita N°: " + datoAdicional + " editada"; // Nota: no se especifica qué se editó
			BD.AltaRegistro(accion, fechaRegistro, horaRegistro);
		}
	}

	// ----------------------------
	// Registro de acciones sobre PRÉSTAMOS DE CEIBALITAS
	// ----------------------------
	public void RegistroPrestamoCompu(int opcion, int datoAdicional, int datoAdicional1) {
		String accion;
		LocalDate fechReg = LocalDate.now();
		String fechaRegistro = fechReg.toString();

		LocalTime horaReg = LocalTime.now();
		String horaRegistro = horaReg.toString();

		if (opcion == 1) { // Préstamo agregado
			accion = "Préstamo de ceibalita N°: " + datoAdicional + ", a la CI: " + datoAdicional1;
			BD.AltaRegistro(accion, fechaRegistro, horaRegistro);

		} else if (opcion == 2) { // Préstamo eliminado
			accion = "Préstamo de ceibalita N°: " + datoAdicional + ", a la CI: " + datoAdicional1 + " eliminado";
			BD.AltaRegistro(accion, fechaRegistro, horaRegistro);

		} else if (opcion == 3) { // Préstamo editado
			accion = "Préstamo de ceibalita N°: " + datoAdicional + ", a la CI: " + datoAdicional1 + " editado";
			BD.AltaRegistro(accion, fechaRegistro, horaRegistro);
		}
	}

	// ----------------------------
	// Registro de acciones sobre PRÉSTAMOS DE LIBROS
	// ----------------------------
	public void RegistroPrestamoLibro(int opcion, String datoAdicional, int datoAdicional1) {
		// datoAdicional = ISBN del libro, datoAdicional1 = CI del usuario
		String accion;
		LocalDate fechReg = LocalDate.now();
		String fechaRegistro = fechReg.toString();

		LocalTime horaReg = LocalTime.now();
		String horaRegistro = horaReg.toString();

		if (opcion == 1) { // Préstamo agregado
			accion = "Préstamo de libro: " + datoAdicional + ", a la CI: " + datoAdicional1;
			BD.AltaRegistro(accion, fechaRegistro, horaRegistro);

		} else if (opcion == 2) { // Préstamo eliminado
			accion = "Préstamo de libro: " + datoAdicional + ", a la CI: " + datoAdicional1 + " eliminado";
			BD.AltaRegistro(accion, fechaRegistro, horaRegistro);

		} else if (opcion == 3) { // Préstamo editado
			accion = "Préstamo de libro: " + datoAdicional + ", a la CI: " + datoAdicional1 + " editado";
			BD.AltaRegistro(accion, fechaRegistro, horaRegistro);
		}
	}

	// ============================
	// MÉTODOS DE ELIMINACIÓN
	// ============================

	// Elimina un libro de la base de datos según su ISBN
	public boolean eliminarLibro(String isbn) {
		boolean resultado = false;
		if (BD.eliminarLibro(isbn)) { // Llama a la BD para eliminar
			resultado = true;
		}
		return resultado;
	}

	// Elimina una persona según su CI
	public boolean eliminarPersona(int ci) {
		boolean resultado = false;
		if (BD.eliminarPersona(ci)) {
			resultado = true;
		}
		return resultado;
	}

	// Elimina una Ceibalita según su número
	public boolean eliminarCeibalita(int numeroCeibalita) {
		boolean resultado = false;
		if (BD.eliminarCeibalita(numeroCeibalita)) {
			resultado = true;
		}
		return resultado;
	}

	// Elimina un préstamo de libro
	public boolean eliminarPrestamolibro(String isbn, int ci) {
		boolean resultado = false;
		if (BD.eliminarPrestamoLibro(isbn, ci)) {
			resultado = true;
		}
		return resultado;
	}

	// Elimina un préstamo de Ceibalita
	public boolean eliminarPrestamoCeibalita(int numCeibalita) {
		boolean resultado = false;
		if (BD.eliminarPrestamoCeibalita(numCeibalita)) {
			resultado = true;
		}
		return resultado;
	}

	// ============================
	// MÉTODOS DE EDICIÓN
	// ============================

	// Edita los datos de una persona
	public boolean editPersona(int ciViejo, int ciNuevo, String nombre, String apellido, int telefono1, String tipo,
			String grupo) {
		boolean resultado = false;

		// Comprobación: CI válido (7-8 dígitos)
		int cantidadDigitosCI = String.valueOf(ciNuevo).length();
		if (cantidadDigitosCI < 7) {
			JOptionPane.showMessageDialog(null, "Error: 'CI' no puede tener menos de '7' dígitos");
		} else if (cantidadDigitosCI > 8) {
			JOptionPane.showMessageDialog(null, "Error: 'CI' no puede tener más de '8' dígitos");
		} else if (nombre == null || nombre.equals("")) { // Comprobación: Nombre no vacío
			JOptionPane.showMessageDialog(null, "Error: 'Nombre' no puede estar vacío ");
		} else if (apellido == null || apellido.equals("")) { // Comprobación: Apellido no vacío
			JOptionPane.showMessageDialog(null, "Error: 'Apellido' no puede estar vacío ");
		} else {
			// Si pasa las comprobaciones, llama a la BD para editar
			if (BD.editPersona(ciViejo, ciNuevo, nombre, apellido, telefono1, tipo, grupo)) {
				resultado = true;
			}
		}
		return resultado;
	}

	// Edita los datos de un libro
	public boolean editLibro(String isbnNuevo, String isbnViejo, String autor, String titulo, String genero,
			String materia, int ejemplares, String notas) {
		boolean resultado = false;

		// Comprobaciones técnicas
		if (isbnNuevo.length() < 1) {
			JOptionPane.showMessageDialog(null, "Error: 'ISBN' vacío");
		} else if (isbnNuevo.length() < 10) {
			JOptionPane.showMessageDialog(null, "Error: 'ISBN' menor a 10 caracteres");
		} else if (isbnNuevo.length() > 13) {
			JOptionPane.showMessageDialog(null, "Error: 'ISBN' muy largo");
		} else if (ejemplares < 1) {
			JOptionPane.showMessageDialog(null, "Error: 'Ejemplares' no puede ser menor a 1");
		} else if (titulo.length() < 1) {
			JOptionPane.showMessageDialog(null, "Error: 'Título' vacío");
		} else if (autor.length() < 1) {
			JOptionPane.showMessageDialog(null, "Error: 'Autor' vacío");
		} else if (BD.editLibro(isbnNuevo, isbnViejo, autor, titulo, genero, materia, ejemplares, notas)) {
			resultado = true;
		}
		return resultado;
	}

	// Edita los datos de una Ceibalita
	public boolean editCeibalita(int numCeibalitaViejo, int numCeibalitaNuevo, String notas) {
		boolean resultado = false;

		// Comprobación: número de Ceibalita válido
		if (numCeibalitaNuevo < 1) {
			JOptionPane.showMessageDialog(null, "Error: 'Numero de Ceibalita' no puede ser menos que '1'");
		} else {
			if (BD.editCeibalita(numCeibalitaViejo, numCeibalitaNuevo, notas)) {
				resultado = true;
			}
		}
		return resultado;
	}

	// Edita un préstamo de Ceibalita
	public boolean editPrestamoCeibalita(int numCeibalitaViejo, int numCeibalitaNuevo, int ci, String horaLim) {
		boolean resultado = false;

		// Comprobaciones: CI válido y fecha/hora no vacía
		int cantidadDigitosCI = String.valueOf(ci).length();
		if (cantidadDigitosCI < 7) {
			JOptionPane.showMessageDialog(null, "Error: 'CI' no puede tener menos de '7' dígitos");
		} else if (cantidadDigitosCI > 8) {
			JOptionPane.showMessageDialog(null, "Error: 'CI' no puede tener más de '8' dígitos");
		} else if (horaLim.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Error: 'Fecha devolución' no puede estar vacía");
		} else {
			// Validaciones de existencia en BD
			if (BD.validarExistenciaCeibalita(numCeibalitaNuevo)) {
				if (BD.validarExistenciaPersona(ci)) {
					if (BD.editPrestamoCeibalita(numCeibalitaViejo, numCeibalitaNuevo, ci, horaLim)) {
						resultado = true;
					}
				} else { // Si la persona no existe, ofrece agregarla
					int opcion = JOptionPane.showConfirmDialog(null,
							"La persona con la CI: '" + ci + "', no existe. ¿Desea agregarla?", "",
							JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
					if (opcion == 0) {
						DefaultTableModel modelo = new DefaultTableModel();
						String ci2 = Integer.toString(ci);
						vtnGuardarPersona vtn1 = new vtnGuardarPersona(modelo, ci2);
						vtn1.setVisible(true);
					}
				}
			} else { // Si la Ceibalita no existe, ofrece agregarla
				int opcion = JOptionPane.showConfirmDialog(null,
						"La Ceibalita con el N°: '" + numCeibalitaNuevo + "', no existe. ¿Desea agregarla?", "",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (opcion == 0) {
					DefaultTableModel modelo = new DefaultTableModel();
					String numCeibalitaStr = Integer.toString(numCeibalitaNuevo);
					vtnGuardarCeibalita vtn1 = new vtnGuardarCeibalita(modelo, numCeibalitaStr);
					vtn1.setVisible(true);
				}
			}
		}
		return resultado;
	}

	// Edita un préstamo de libro
	public boolean editPrestamolibro(String isbnNuevo, String isbnViejo, int ciNuevo, int ciViejo, int cantidad,
			String fechaLimite) {
		boolean resultado = false;

		// Comprobaciones: CI válido, ISBN válido, cantidad > 0, fecha límite no vacía
		int cantidadDigitosCI = String.valueOf(ciNuevo).length();
		if (cantidadDigitosCI < 7) {
			JOptionPane.showMessageDialog(null, "Error: 'CI' no puede tener menos de '7' dígitos");
		} else if (cantidadDigitosCI > 8) {
			JOptionPane.showMessageDialog(null, "Error: 'CI' no puede tener más de '8' dígitos");
		} else if (isbnNuevo.length() < 1) {
			JOptionPane.showMessageDialog(null, "Error: 'ISBN' vacío");
		} else if (isbnNuevo.length() < 10) {
			JOptionPane.showMessageDialog(null, "Error: 'ISBN' menor a 10 caracteres");
		} else if (isbnNuevo.length() > 13) {
			JOptionPane.showMessageDialog(null, "Error: 'ISBN' muy largo");
		} else if (cantidad < 1) {
			JOptionPane.showMessageDialog(null, "Error: 'Cantidad' no puede ser menor a 1");
		} else if (fechaLimite.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Error: 'Hora devolución' no puede estar vacía");
		} else {
			// Validaciones de existencia en BD
			if (BD.validarExistenciaLibro(isbnNuevo)) {
				if (BD.validarExistenciaPersona(ciNuevo)) {
					if (BD.editPrestamoLibro(isbnNuevo, isbnViejo, ciNuevo, ciViejo, cantidad, fechaLimite)) {
						resultado = true;
					}
				} else { // Persona no existe
					int opcion = JOptionPane.showConfirmDialog(null,
							"La persona con la CI: '" + ciNuevo + "', no existe. ¿Desea agregarla?", "",
							JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
					if (opcion == 0) {
						DefaultTableModel modelo = new DefaultTableModel();
						String ci2 = Integer.toString(ciNuevo);
						vtnGuardarPersona vtn1 = new vtnGuardarPersona(modelo, ci2);
						vtn1.setVisible(true);
					}
				}
			} else { // Libro no existe
				int opcion = JOptionPane.showConfirmDialog(null,
						"El libro con el ISBN: '" + isbnNuevo + "', no existe. ¿Desea agregarlo?", "",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (opcion == 0) {
					DefaultTableModel modelo = new DefaultTableModel();
					vtnGuardarLibro vtn1 = new vtnGuardarLibro(modelo, isbnNuevo);
					vtn1.setVisible(true);
				}
			}
		}
		return resultado;
	}
}
