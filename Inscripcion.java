/**
 * 
 */
package Practica1;

import java.util.Date;

/**
 * 
 */
public abstract class Inscripcion extends Campamento {
	
	private int idParticipante;
	private int idCampamento;
	private Date fechaInscripcion;
	private float precioInscripcion;
	/**
	 * 
	 */
	public Inscripcion() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param fechaInicio
	 * @param fechaFin
	 * @param nivelEducativo
	 * @param maxAsistentes
	 */
	public Inscripcion(int idParticipante, int idCampamento, Date fechaInscripcion, int precioInscripcion) {
		this.idParticipante = idParticipante;
		this.idCampamento = idCampamento;
		this.fechaInscripcion = fechaInscripcion;
		this.precioInscripcion = precioInscripcion;
		// TODO Auto-generated constructor stub
	}

	public Date getFechaInscripcion() {
		return fechaInscripcion;
	}

	public void setFechaInscripcion(Date fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}

	public float getPrecioInscripcion() {
		return precioInscripcion;
	}

	public void setPrecioInscripcion(float precioInscripcion) {
		this.precioInscripcion = precioInscripcion;
	}
	
	public int getIdParticipante() {
		return idParticipante;
	}

	public void setIdPArticipante(int idPArticipante) {
		this.idParticipante = getIdAsistente();
	}

	public int getIdCampamento() {
		return idCampamento;
	}

	public void setIdCampamento(int idCampamento) {
		this.idCampamento = getIdCampamento();
	}

	@Override
	public String toString() {
		return "Inscripcion [idParticipante=" + idParticipante + ", idCampamento=" + idCampamento
				+ ", fechaInscripcion=" + fechaInscripcion + ", precioInscripcion=" + precioInscripcion + "]";
	}
		
}
