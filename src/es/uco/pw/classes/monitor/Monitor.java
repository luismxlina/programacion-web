package es.uco.pw.classes.monitor;

public class Monitor {
	private int identificador;
	private String nombre;
	private String apellidos;
	private Boolean esEducador;
	
	//constructor vacio pendiente
	
	public Monitor(int id, String n, String a, Boolean e)
	{
		this.identificador = id;
		this.nombre = n;
		this.apellidos = a;
		this.esEducador = e;
	}

	public Monitor() {
		
	}

	public int getIdentificador() {
		return identificador;
	}

	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Boolean getEsEducador() {
		return esEducador;
	}

	public void setEsEducador(Boolean esEducador) {
		this.esEducador = esEducador;
	}

	@Override
	public String toString() {
		return "Monitor [identificador=" + identificador + ", nombre=" + nombre + ", apellidos=" + apellidos
				+ ", esEducador=" + esEducador + "]";
	}
	
	
}
