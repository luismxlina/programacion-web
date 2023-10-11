package es.uco.pw.classes.inscripcion;

import java.util.Date;

public abstract class Inscripcion {
	
	//atributos comunes
	private int id_Participante;
	private int id_Campamento;
	private Date fechaInscripcion;
	private Double precio;
	
	private Boolean cancelable;

	//prototipos de los métodos de una inscripcion que son comunes sean del tipo que sean
	
	public abstract String toString();
	
	public int getId_Participante() {
		return id_Participante;
	}

	public void setId_Participante(int id_Participante) {
		this.id_Participante = id_Participante;
	}

	public int getId_Campamento() {
		return id_Campamento;
	}

	public void setId_Campamento(int id_Campamento) {
		this.id_Campamento = id_Campamento;
	}

	public Date getFechaInscripcion() {
		return fechaInscripcion;
	}

	public void setFechaInscripcion(Date fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Boolean getCancelable() {
		return cancelable;
	}

	public void setCancelable(Boolean cancelable) {
		this.cancelable = cancelable;
	}
	
	
	
	

}