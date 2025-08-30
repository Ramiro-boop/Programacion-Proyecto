package Proyecto;

public class Persona {

	String nombre;
	String apellido;
	int ci;
	
	public Persona(String nombre, String apellido, int ci) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.ci = ci;
	}

	protected String getNombre() {
		return nombre;
	}

	protected void setNombre(String nombre) {
		this.nombre = nombre;
	}

	protected String getApellido() {
		return apellido;
	}

	protected void setApellido(String apellido) {
		this.apellido = apellido;
	}

	protected int getCi() {
		return ci;
	}

	protected void setCi(int ci) {
		this.ci = ci;
	}
	
	
}
