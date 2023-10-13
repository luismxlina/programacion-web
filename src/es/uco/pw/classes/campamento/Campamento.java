package es.uco.pw.classes.campamento;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

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

	public Campamento() {
		this.identificador = 0;
		this.fechaInicio = new Date();
		this.fechaFin = new Date();
		this.nivel = NivelEducativo.INFANTIL;
		this.max_asistentes = 0;
		this.actividades = new ArrayList<Actividad>();
		this.monitoresResponsables = new ArrayList<Monitor>();
	}

	public Campamento(int identificador, Date fechaInicio, Date fechaFin, NivelEducativo nivel, int max_asistentes) {
		this.identificador = identificador;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.nivel = nivel;
		this.max_asistentes = max_asistentes;
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

	public Boolean asociarActividad(Actividad nuevaActividad) {
		
		/*for(int numero : vector)
		
		for(Actividad actividad : this.actividades)
		{
			if(actividad.getNombreActividad() == nuevaActividad.getNombreActividad())
			{
				return false;
			}
		}*/
		
		if(this.buscarActividad(nuevaActividad.getNombreActividad())==true)
			return false;
		
		if (nuevaActividad.getNivel() == this.nivel) {
			this.actividades.add(nuevaActividad);
			return true;
		}
		
		return false;
	}

	public Boolean buscarActividad(String nombreActividad) {
		if (actividades.size() == 0)
			return false;

		for (int i = 0; i < actividades.size(); i++) {
			if (this.actividades.get(i).getNombreActividad() == nombreActividad) {
				return true;
			}
		}

		return false;
	}
	
	public Actividad obtenerActividad(String nombreActividad) {
		
		if(this.buscarActividad(nombreActividad)==false)
			return null;

		for (int i = 0; i < actividades.size(); i++) {
			if (this.actividades.get(i).getNombreActividad() == nombreActividad) {
				return this.actividades.get(i);
			}
		}
		
		return null;
	}

	public Boolean asociarMonitor(Monitor nuevoMonitor) {
		for (Actividad actividad : this.actividades) {
			for (Monitor monitor : actividad.getMonitores()) {
				if (monitor.getIdentificador() == nuevoMonitor.getIdentificador()) {
					this.monitoresResponsables.add(nuevoMonitor);
					return true;
				}
			}
		}
		return false;
	}

	public Boolean asociarMonitorEspecial(Monitor nuevoMonitorEspecial) {
		if (nuevoMonitorEspecial.getEsEducador()) {
			for (Actividad actividad : actividades) {
				if (actividad.getMonitores().contains(nuevoMonitorEspecial)) {
					return false;
				}
			}
			monitoresResponsables.add(nuevoMonitorEspecial);
			return true;
		}
		return false;
	}
}