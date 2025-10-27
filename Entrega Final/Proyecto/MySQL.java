package Proyecto;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class MySQL {

	/*
	 * private final String DB_URL =
	 * "jdbc:mysql://170.249.219.114/program1_equipo1?useSSL=false"; private final
	 * String DB_USER = "program1_adminEquipo1"; private final String DB_PASS =
	 * "bvnkp3g56p";
	 */

	private final String DB_URL = "jdbc:mysql://localhost/program1_equipo1?useSSL=false";
	private final String DB_USER = "root";
	private final String DB_PASS = "";

	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
	}

	//Listar Libros
	public void ListarLibros(DefaultTableModel modeloL) {
		String sentencia = "SELECT* FROM libros";//Sentencia para listar los libros

		try {
			Connection conexion = getConnection();
			PreparedStatement declaracionSQL = conexion.prepareStatement(sentencia);
			ResultSet rs = declaracionSQL.executeQuery();

			modeloL.setRowCount(0);// Borra cont tabla

			while (rs.next()) {

				//Agarra los datos de la BD
				String isbn = rs.getString(1);
				String autor = rs.getString(2);
				String titulo = rs.getString(3);
				String genero = rs.getString(4);
				String materia = rs.getString(5);
				int ejemplares = rs.getInt(6);
				String notas = rs.getString(7);

				Object[] fila = { isbn, autor, titulo, genero, materia, ejemplares, notas };

				//Carga los datos a la tabla
				modeloL.addRow(fila);

			}

		} catch (SQLException ex) {

			JOptionPane.showMessageDialog(null, "Error -->" + ex.getMessage());
		}

	}

	// ============================
	// MÉTODOS DE INSERCIÓN Y LISTADO
	// ============================

	// Inserta un nuevo libro en la tabla 'libros'
	public boolean altaLibro(String isbn, String autor, String titulo, String genero, String materia, int ejemplares,
	        String notas) {

	    boolean resultado = false;

	    // Construye la sentencia SQL de inserción
	    String sentencia = "INSERT INTO libros VALUES('" + isbn + "','" + autor + "','" + titulo + "','" + genero
	            + "','" + materia + "'," + ejemplares + ",'" + notas + "')";

	    try {
	        Connection conexion = getConnection(); // Conexión a la base de datos
	        PreparedStatement declaracionSQL = conexion.prepareStatement(sentencia);
	        declaracionSQL.executeUpdate(); // Ejecuta la inserción
	        resultado = true; // Si no hay excepción, inserción exitosa
	    } catch (SQLException ex) {
	        resultado = false; // En caso de error
	        JOptionPane.showMessageDialog(null, "Error -->" + ex.getMessage());
	    }

	    return resultado;
	}

	// Lista todas las personas en un DefaultTableModel
	public void ListarPersonas(DefaultTableModel modeloP) {
	    String sentencia = "SELECT * FROM personas";

	    try {
	        Connection conexion = getConnection();
	        PreparedStatement declaracionSQL = conexion.prepareStatement(sentencia);
	        ResultSet rs = declaracionSQL.executeQuery(); // Ejecuta consulta

	        modeloP.setRowCount(0); // Limpia la tabla antes de agregar datos

	        // Recorre los resultados y los agrega fila por fila
	        while (rs.next()) {
	            int ci = rs.getInt(1);
	            String nombre = rs.getString(2);
	            String apellido = rs.getString(3);
	            int telefono = rs.getInt(4);
	            String grupo = rs.getString(5);
	            String tipo = rs.getString(6);

	            Object[] fila = { ci, nombre, apellido, telefono, grupo, tipo };
	            modeloP.addRow(fila);
	        }

	    } catch (SQLException ex) {
	        JOptionPane.showMessageDialog(null, "Error -->" + ex.getMessage());
	    }
	}

	// Inserta una nueva persona en la tabla 'personas'
	public boolean altaPersona(int ci, String nombre, String apellido, int telefono, String grupo, String tipo) {

	    boolean resultado = false;

	    // Construye la sentencia SQL de inserción
	    String sentencia = "INSERT INTO personas VALUES(" + ci + ",'" + nombre + "','" + apellido + "'," + telefono
	            + ",'" + grupo + "','" + tipo + "')";

	    try {
	        Connection conexion = getConnection();
	        PreparedStatement declaracionSQL = conexion.prepareStatement(sentencia);
	        declaracionSQL.executeUpdate();
	        resultado = true;
	    } catch (SQLException ex) {
	        resultado = false;
	        JOptionPane.showMessageDialog(null, "Error -->" + ex.getMessage());
	    }

	    return resultado;
	}

	// Lista todas las Ceibalitas en un DefaultTableModel
	public void ListarCeibalita(DefaultTableModel modeloC) {

	    String sentencia = "SELECT * FROM computadoras";

	    try {
	        Connection conexion = getConnection();
	        PreparedStatement declaracionSQL = conexion.prepareStatement(sentencia);
	        ResultSet rs = declaracionSQL.executeQuery();

	        modeloC.setRowCount(0); // Limpia tabla

	        while (rs.next()) {
	            int numCeibalita = rs.getInt(1);
	            String notas = rs.getString(2);

	            Object[] fila = { numCeibalita, notas };
	            modeloC.addRow(fila);
	        }

	    } catch (SQLException ex) {
	        JOptionPane.showMessageDialog(null, "Error -->" + ex.getMessage());
	    }
	}

	// Inserta una nueva Ceibalita en la tabla 'computadoras'
	public boolean altaCeibalita(int numCeibalita, String notas) {

	    boolean resultado = false;

	    String sentencia = "INSERT INTO computadoras VALUES(" + numCeibalita + ",'" + notas + "')";

	    try {
	        Connection conexion = getConnection();
	        PreparedStatement declaracionSQL = conexion.prepareStatement(sentencia);
	        declaracionSQL.executeUpdate();
	        resultado = true;
	    } catch (SQLException ex) {
	        resultado = false;
	        JOptionPane.showMessageDialog(null, "Error -->" + ex.getMessage());
	    }

	    return resultado;
	}

	// Lista todos los préstamos de Ceibalita con datos de persona
	public void ListarPrestamoCeibalita(DefaultTableModel modeloPC) {

	    // Consulta principal de préstamos
	    String sentencia = "SELECT * FROM piden;";
	    // Consulta adicional para obtener datos de la persona
	    String sentencia2 = "SELECT nombre, apellido, telefono, grupo FROM personas "
	            + "INNER JOIN piden ON personas.ci=piden.ci;";

	    try {
	        Connection conexion = getConnection();
	        PreparedStatement declaracionSQL = conexion.prepareStatement(sentencia);
	        PreparedStatement declaracion = conexion.prepareStatement(sentencia2);
	        ResultSet rs = declaracionSQL.executeQuery();
	        ResultSet rs2 = declaracion.executeQuery();

	        modeloPC.setRowCount(0); // Limpia la tabla antes de agregar datos

	        while (rs.next() && rs2.next()) {

	            int numCeibalita = rs.getInt(1);
	            int ci = rs.getInt(2);

	            String nombre = rs2.getString(1);
	            String apellido = rs2.getString(2);
	            String telefono = rs2.getString(3);
	            String grupo = rs2.getString(4);

	            String fechaPrestamo = rs.getString(3);

	            // Formatea la fecha de la BD a un formato más legible
	            DateTimeFormatter dbFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	            DateTimeFormatter normalFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	            LocalDate fecha1 = LocalDate.parse(fechaPrestamo, dbFormatter);
	            String fechaNormal1 = fecha1.format(normalFormatter);

	            String horaDevolucion = rs.getString(4); // Hora límite de devolución

	            Object[] fila = { numCeibalita, ci, nombre, apellido, telefono, grupo, fechaNormal1, horaDevolucion };
	            modeloPC.addRow(fila);
	        }

	    } catch (SQLException ex) {
	        JOptionPane.showMessageDialog(null, "Error -->" + ex.getMessage());
	    }
	}


	// Método para registrar un préstamo de Ceibalita en la base de datos
	public boolean altaPrestamoCeibalita(int numCeibalita, int ci, String horaLim, String fechaHoy) {
	    boolean resultado = false;

	    // Sentencia SQL de inserción directa (ojo, concatenación puede ser vulnerable a SQL Injection)
	    String sentencia = "INSERT INTO piden VALUES(" + numCeibalita + "," + ci + ",'" + fechaHoy + "','" + horaLim + "')";

	    try {
	        // Obtiene conexión a la base de datos
	        Connection conexion = getConnection();
	        PreparedStatement declaracionSQL = conexion.prepareStatement(sentencia);
	        // Ejecuta la inserción
	        declaracionSQL.executeUpdate();

	        resultado = true; // Se marca éxito
	    } catch (SQLException ex) {
	        resultado = false; // Falló la operación
	        JOptionPane.showMessageDialog(null, "Error -->" + ex.getMessage());
	    }

	    return resultado; // Retorna true si se insertó correctamente, false si hubo error
	}

	// Método para listar los préstamos de libros en un JTable
	public void ListarPrestamoLibro(DefaultTableModel modeloPL) {

	    // Sentencias SQL actuales (tres consultas separadas)
	    String sentencia = "SELECT* FROM solicitan";
	    String sentencia2 = "SELECT nombre, apellido, telefono, grupo FROM personas "
	            + "INNER JOIN solicitan ON personas.ci=solicitan.ci;";
	    String sentencia3 = "SELECT titulo FROM libros INNER JOIN solicitan ON libros.isbn=solicitan.isbn;";

	    try {
	        Connection conexion = getConnection();
	        PreparedStatement declaracionSQL = conexion.prepareStatement(sentencia);
	        PreparedStatement declaracionSQL2 = conexion.prepareStatement(sentencia2);
	        PreparedStatement declaracionSQL3 = conexion.prepareStatement(sentencia3);

	        ResultSet rs = declaracionSQL.executeQuery();
	        ResultSet rs2 = declaracionSQL2.executeQuery();
	        ResultSet rs3 = declaracionSQL3.executeQuery();

	        modeloPL.setRowCount(0); // Limpia la tabla antes de agregar filas

	        // Se recorre mientras haya filas en los tres ResultSet
	        while (rs.next() && rs2.next() && rs3.next()) {

	            // Datos del préstamo
	            String isbn = rs.getString(1);
	            int ci = rs.getInt(2);
	            int cantPrestados = rs.getInt(3);
	            String fechaEntrega = rs.getString(4);
	            String fechaDev = rs.getString(5);

	            // Datos de la persona
	            String nombre = rs2.getString(1);
	            String apellido = rs2.getString(2);
	            String telefono = rs2.getString(3);
	            String grupo = rs2.getString(4);

	            // Datos del libro
	            String titulo = rs3.getString(1);

	            // Convertimos las fechas de formato DB (yyyy-MM-dd) a formato amigable (dd-MM-yyyy)
	            DateTimeFormatter dbFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	            DateTimeFormatter normalFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	            LocalDate fecha1 = LocalDate.parse(fechaEntrega, dbFormatter);
	            String fechaNormal1 = fecha1.format(normalFormatter);

	            LocalDate fecha2 = LocalDate.parse(fechaDev, dbFormatter);
	            String fechaNormal2 = fecha2.format(normalFormatter);

	            // Creamos la fila a agregar al JTable
	            Object[] fila = { isbn, titulo, ci, nombre, apellido, telefono, grupo, cantPrestados, fechaNormal1, fechaNormal2 };
	            modeloPL.addRow(fila);
	        }

	    } catch (SQLException ex) {
	        JOptionPane.showMessageDialog(null, "Error -->" + ex.getMessage());
	    }
	}

	// Método para registrar un préstamo de libro
	public boolean altaPrestamoLibro(String isbn, int ci, int cant, String fechaPrestamo, String fechaLimite) {
	    boolean resultado = false;

	    // Consulta para verificar cuántos ejemplares disponibles hay del libro
	    String consultaCantidad = "SELECT ejemplares FROM libros WHERE ISBN = '" + isbn + "'";
	    // Inserción del préstamo en la tabla
	    String sentencia = "INSERT INTO solicitan VALUES('" + isbn + "'," + ci + "," + cant + ",'" + fechaPrestamo + "','" + fechaLimite + "')";
	    // Actualización de la cantidad de ejemplares disponibles
	    String sentenciaUpdate = "UPDATE libros SET ejemplares = ejemplares - " + cant + " WHERE ISBN = '" + isbn + "'";

	    try {
	        Connection conexion = getConnection();
	        PreparedStatement psConsulta = conexion.prepareStatement(consultaCantidad);
	        ResultSet rs = psConsulta.executeQuery();

	        if (rs.next()) { // Si existe el libro
	            int cantidadDisponible = rs.getInt("ejemplares");

	            if (cant > cantidadDisponible) {
	                // Si no hay suficientes ejemplares, muestra error
	                JOptionPane.showMessageDialog(null,
	                        "No hay suficientes ejemplares disponibles. (" + cantidadDisponible + " restantes)");
	            } else {
	                // Inserta el préstamo
	                PreparedStatement psInsert = conexion.prepareStatement(sentencia);
	                psInsert.executeUpdate();

	                // Actualiza la cantidad de libros
	                PreparedStatement psUpdate = conexion.prepareStatement(sentenciaUpdate);
	                psUpdate.executeUpdate();

	                resultado = true; // Operación exitosa
	            }
	        }

	    } catch (SQLException ex) {
	        resultado = false;
	        JOptionPane.showMessageDialog(null, "Error --> " + ex.getMessage());
	    }

	    return resultado;
	}

	// Método para listar registros de auditoría
	public void ListarRegistros(DefaultTableModel modeloR) {
	    String sentencia = "SELECT * FROM registros ORDER BY idRegistro DESC";

	    try {
	        Connection conexion = getConnection();
	        PreparedStatement declaracionSQL = conexion.prepareStatement(sentencia);
	        ResultSet rs = declaracionSQL.executeQuery();

	        modeloR.setRowCount(0); // Limpia la tabla antes de agregar filas

	        while (rs.next()) {
	            int idRegistro = rs.getInt(1);
	            String accion = rs.getString(2);
	            String fechaRegistro = rs.getString(3);
	            String horaRegistro = rs.getString(4);

	            Object[] fila = { idRegistro, accion, fechaRegistro, horaRegistro };
	            modeloR.addRow(fila);
	        }

	    } catch (SQLException ex) {
	        JOptionPane.showMessageDialog(null, "Error -->" + ex.getMessage());
	    }
	}

	// Método para insertar un registro de acción en la tabla de auditoría
	public void AltaRegistro(String accion, String fechaRegistro, String horaRegistro) {
	    String sentencia = "INSERT INTO registros (accion, fechaRegistro, horaRegistro) VALUES ('" + accion + "','"
	            + fechaRegistro + "','" + horaRegistro + "')";

	    try {
	        Connection conexion = getConnection();
	        PreparedStatement declaracionSQL = conexion.prepareStatement(sentencia);
	        declaracionSQL.executeUpdate();
	    } catch (SQLException ex) {
	        JOptionPane.showMessageDialog(null, "Error -->" + ex.getMessage());
	    }
	}

	// Método para eliminar un libro
	public boolean eliminarLibro(String isbn) {
	    boolean resultado = false;

	    String sentencia = "DELETE FROM libros WHERE isbn = '" + isbn + "'";
	    try {
	        Connection conexion = getConnection();
	        PreparedStatement declaracionSQL = conexion.prepareStatement(sentencia);
	        declaracionSQL.executeUpdate();

	        resultado = true; // Eliminación exitosa

	    } catch (SQLException ex) {
	        resultado = false;

	        // Si hay error por clave foránea, muestra mensaje específico
	        if(ex.getMessage().toLowerCase().contains("foreign key")) {
	            JOptionPane.showMessageDialog(null, "No se puede eliminar el libro, ya que tiene préstamos asociados.");
	        } else {
	            JOptionPane.showMessageDialog(null, "Error --> " + ex.getMessage());
	        }
	    }

	    return resultado;
	}

	public boolean eliminarPersona(int ci) {

		boolean resultado = false;

		String sentencia = "DELETE FROM personas WHERE ci = " + ci;

		try {
			Connection conexion = getConnection();
			PreparedStatement declaracionSQL = conexion.prepareStatement(sentencia);
			declaracionSQL.executeUpdate();

			resultado = true;

		} catch (SQLException ex) {


			if(ex.getMessage().toLowerCase().contains("foreign key")) {
		        JOptionPane.showMessageDialog(null, "No se puede eliminar a la persona, ya que tiene préstamos asociados.");
		    } else {
		        JOptionPane.showMessageDialog(null, "Error --> " + ex.getMessage());
		    }
			
			resultado = false;
			
		}

		return resultado;

	}

	public boolean eliminarCeibalita(int numeroCeibalita) {

		boolean resultado = false;

		String sentencia = "DELETE FROM computadoras WHERE numeroCeibalita = " + numeroCeibalita;

		try {
			Connection conexion = getConnection();
			PreparedStatement declaracionSQL = conexion.prepareStatement(sentencia);
			declaracionSQL.executeUpdate();

			resultado = true;

		} catch (SQLException ex) {
			
			if(ex.getMessage().toLowerCase().contains("foreign key")) {//si hay un error con la clave foranea
		        JOptionPane.showMessageDialog(null, "No se puede eliminar la ceibalita, ya que tiene préstamos asociados.");
		    } else {
		        JOptionPane.showMessageDialog(null, "Error --> " + ex.getMessage());
		    }

			resultado = false;
		}
		return resultado;

	}

	public boolean eliminarPrestamoCeibalita(int numCeibalita) {

		boolean resultado = false;

		String sentencia = "DELETE FROM piden WHERE numeroCeibalita = " + numCeibalita;

		try {
			Connection conexion = getConnection();
			PreparedStatement declaracionSQL = conexion.prepareStatement(sentencia);
			declaracionSQL.executeUpdate();

			resultado = true;

		} catch (SQLException ex) {

			resultado = false;
			JOptionPane.showMessageDialog(null, "Error -->" + ex.getMessage());
		}

		return resultado;

	}

	public boolean eliminarPrestamoLibro(String isbn, int ci) {
		boolean resultado = false;

		// Obtner la cantidad prestada antes de borrar el registro
		String consultaCantidad = "SELECT cantPrestados FROM solicitan WHERE isbn = '" + isbn + "' AND ci = " + ci;

		// Después eliminamos el préstamo
		String sentenciaDelete = "DELETE FROM solicitan WHERE isbn = '" + isbn + "' AND ci = " + ci;

		try {
			Connection conexion = getConnection();

			// Consultar la cantidad prestada
			PreparedStatement psConsulta = conexion.prepareStatement(consultaCantidad);
			ResultSet rs = psConsulta.executeQuery();

			if (rs.next()) {

				int cantidadPrestada = rs.getInt("cantPrestados");

				// Eliminar el préstamo
				PreparedStatement psDelete = conexion.prepareStatement(sentenciaDelete);
				psDelete.executeUpdate();

				// Devolver ejemplares al libro
				String sentenciaUpdate = "UPDATE libros SET ejemplares = ejemplares + " + cantidadPrestada
						+ " WHERE ISBN = '" + isbn + "'";
				PreparedStatement psUpdate = conexion.prepareStatement(sentenciaUpdate);
				psUpdate.executeUpdate();

				resultado = true;
			} else {
				JOptionPane.showMessageDialog(null, "No se encontró el préstamo para eliminar.");
			}

		} catch (SQLException ex) {
			resultado = false;
			JOptionPane.showMessageDialog(null, "Error --> " + ex.getMessage());
		}

		return resultado;
	}

	public boolean editPersona(int ciViejo, int ciNuevo, String nombre, String apellido, int telefono1, String tipo,
			String grupo) {

		boolean resultado = false;

		String sentencia = "UPDATE personas SET ci = " + ciNuevo + " , nombre = '" + nombre + "' , apellido = '"
				+ apellido + "', telefono = " + telefono1 + ", tipo = '" + tipo + "', grupo = '" + grupo + "'"
				+ "WHERE ci = " + ciViejo;

		try {
			Connection conexion = getConnection();
			PreparedStatement declaracionSQL = conexion.prepareStatement(sentencia);
			declaracionSQL.executeUpdate();

			resultado = true;

		} catch (SQLException ex) {

			resultado = false;
			JOptionPane.showMessageDialog(null, "Error -->" + ex.getMessage());
		}

		return resultado;
	}

	public boolean editLibro(String isbnNuevo, String isbnViejo, String autor, String titulo, String genero,
			String materia, int ejemplares, String notas) {

		boolean resultado = false;

		String sentencia = "UPDATE libros SET isbn = '" + isbnNuevo + "' , autor = '" + autor + "' , titulo = '"
				+ titulo + "' , genero = '" + genero + "', materia = '" + materia + "' , ejemplares = " + ejemplares
				+ ", notas = '" + notas + "'" + "WHERE isbn = '" + isbnViejo + "'";

		try {
			Connection conexion = getConnection();
			PreparedStatement declaracionSQL = conexion.prepareStatement(sentencia);
			declaracionSQL.executeUpdate();

			resultado = true;

		} catch (SQLException ex) {

			resultado = false;
			JOptionPane.showMessageDialog(null, "Error -->" + ex.getMessage());
		}

		return resultado;
	}

	public boolean editCeibalita(int numCeibalitaViejo, int numCeibalitaNuevo, String notas) {

		boolean resultado = false;

		String sentencia = "UPDATE computadoras SET numeroCeibalita = " + numCeibalitaNuevo + ", notas = '" + notas
				+ "'" + " WHERE numeroCeibalita = " + numCeibalitaViejo;

		try {
			Connection conexion = getConnection();
			PreparedStatement declaracionSQL = conexion.prepareStatement(sentencia);
			declaracionSQL.executeUpdate();

			resultado = true;

		} catch (SQLException ex) {

			resultado = false;
			JOptionPane.showMessageDialog(null, "Error -->" + ex.getMessage());
		}

		return resultado;
	}

	public boolean editPrestamoCeibalita(int numCeibalitaViejo, int numCeibalitaNuevo, int ci, String horaLim) {

		boolean resultado = false;

		String sentencia = "UPDATE piden SET numeroCeibalita = " + numCeibalitaNuevo + ", ci = " + ci + ""
				+ ", horaLimite = '" + horaLim + "'" + " WHERE numeroCeibalita = " + numCeibalitaViejo;

		try {
			Connection conexion = getConnection();
			PreparedStatement declaracionSQL = conexion.prepareStatement(sentencia);
			declaracionSQL.executeUpdate();

			resultado = true;

		} catch (SQLException ex) {

			resultado = false;
			JOptionPane.showMessageDialog(null, "Error -->" + ex.getMessage());
		}

		return resultado;
	}

	public boolean editPrestamoLibro(String isbnNuevo, String isbnViejo, int ciNuevo, int ciViejo, int cantidad,
			String fechaLimite) {

		boolean resultado = false;

		String sentencia = "UPDATE solicitan SET isbn = '" + isbnNuevo + "' , ci = " + ciNuevo + " , cantPrestados = "
				+ cantidad + ", fechaLimite = '" + fechaLimite + "'" + " WHERE isbn = '" + isbnViejo + "' AND ci = "
				+ ciViejo;

		try {
			Connection conexion = getConnection();
			PreparedStatement declaracionSQL = conexion.prepareStatement(sentencia);
			declaracionSQL.executeUpdate();

			resultado = true;

		} catch (SQLException ex) {

			resultado = false;
			JOptionPane.showMessageDialog(null, "Error -->" + ex.getMessage());
		}

		return resultado;
	}

	public boolean validarExistenciaPersona(int ci) {
		boolean resultado = false;

		String sentencia = "SELECT* FROM personas WHERE ci = " + ci;

		try (Connection conexion = getConnection();
				PreparedStatement declaracionSQL = conexion.prepareStatement(sentencia)) {
			ResultSet rs = declaracionSQL.executeQuery();

			if (rs.next()) {

				resultado = true; // existe

			}

		} catch (SQLException ex) {
			resultado = false;
			System.err.println("Error --> " + ex.getMessage());
		}

		return resultado;
	}

	public boolean validarExistenciaLibro(String isbn) {
		boolean resultado = false;

		String sentencia = "SELECT* FROM libros WHERE isbn = '" + isbn + "'";

		try (Connection conexion = getConnection();
				PreparedStatement declaracionSQL = conexion.prepareStatement(sentencia)) {
			ResultSet rs = declaracionSQL.executeQuery();

			if (rs.next()) {

				resultado = true; // existe

			}

		} catch (SQLException ex) {
			resultado = false;
			System.err.println("Error --> " + ex.getMessage());
		}

		return resultado;
	}

	public boolean validarExistenciaCeibalita(int numCeibalita) {
		boolean resultado = false;

		String sentencia = "SELECT* FROM computadoras WHERE numeroCeibalita = " + numCeibalita;

		try (Connection conexion = getConnection();
				PreparedStatement declaracionSQL = conexion.prepareStatement(sentencia)) {
			ResultSet rs = declaracionSQL.executeQuery();

			if (rs.next()) {

				resultado = true; // existe

			}

		} catch (SQLException ex) {
			resultado = false;
			System.err.println("Error --> " + ex.getMessage());
		}

		return resultado;
	}

}
