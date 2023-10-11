package es.uco.pw.classes.campamento;

import java.util.ArrayList;
import java.util.Date;

import es.uco.pw.classes.actividad.Actividad;
import es.uco.pw.classes.actividad.NivelEducativo;
import es.uco.pw.classes.monitor.Monitor;

public class Campamento {
	
	private int identificador;
	private Date fechaInicio;
	private Date fechaFin;
	private NivelEducativo nivel;
	private int max_asistentes;
	private ArrayList<Actividad> actividades;
	private ArrayList<Monitor> monitoresResponsables;
	
	public Campamento()
	{
		this.identificador = 0;
		this.fechaInicio = new Date();
		this.fechaFin = new Date();
		this.nivel = NivelEducativo.INFANTIL;
		this.max_asistentes = 0;
		this.actividades = new ArrayList<Actividad>();
		this.monitoresResponsables = new ArrayList<Monitor>();
	}
	
	//Constructor parametrizado estaría pendiente
	
	public Campamento(int identificador, Date fechaInicio, Date fechaFin, NivelEducativo nivel, int max_asistentes)
	{
		this.identificador = 0;
		this.fechaInicio = new Date();
		this.fechaFin = new Date();
		this.nivel = NivelEducativo.INFANTIL;
		this.max_asistentes = 0;
		this.actividades = new ArrayList<Actividad>();
		this.monitoresResponsables = new ArrayList<Monitor>();
	}

	public int getIdentificador() {
		return identificador;
	}

	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public NivelEducativo getNivel() {
		return nivel;
	}

	public void setNivel(NivelEducativo nivel) {
		this.nivel = nivel;
	}

	public int getMax_asistentes() {
		return max_asistentes;
	}

	public void setMax_asistentes(int max_asistentes) {
		this.max_asistentes = max_asistentes;
	}

	public ArrayList<Actividad> getActividades() {
		return actividades;
	}

	public void setActividades(ArrayList<Actividad> actividades) {
		this.actividades = actividades;
	}

	public ArrayList<Monitor> getMonitoresResponsables() {
		return monitoresResponsables;
	}

	public void setMonitoresResponsables(ArrayList<Monitor> monitoresResponsables) {
		this.monitoresResponsables = monitoresResponsables;
	}

	@Override
	public String toString() {
		return "Campamento [identificador=" + identificador + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin
				+ ", nivel=" + nivel + ", max_asistentes=" + max_asistentes + ", actividades=" + actividades
				+ ", monitoresResponsables=" + monitoresResponsables + "]";
	}
	
	
	public Boolean asociarActividad(Actividad nuevaActividad)
	{
		if(nuevaActividad.getNivel() == this.nivel)
		{
			this.actividades.add(nuevaActividad);
			return true;
		}
		return false;
	}
	
	public Boolean asociarMonitor(Monitor nuevoMonitor)
	{
		return false;
	}
	
	public Boolean asociarMonitorEspecial(Monitor nuevoMonitorEspecial)
	{
		return false;
	}
	
	//if (cadena1 == cadena2 )  MAL
	//if (cadena1.equals(cadena2))  BIEN
	
}