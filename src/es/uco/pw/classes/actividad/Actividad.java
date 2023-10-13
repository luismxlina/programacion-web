package es.uco.pw.classes.actividad;

import java.util.ArrayList;

import es.uco.pw.classes.monitor.Monitor;

public class Actividad {
	
	private String nombreActividad;
	private NivelEducativo nivel;
	private String hora;
	private int max_participantes;
	private int num_monitores;
	private ArrayList<Monitor> monitores;
	
	
	public Actividad()
	{
		this.nombreActividad = "";
		this.nivel= NivelEducativo.INFANTIL;
		this.hora = "Mañana";
		this.max_participantes = 200;
		this.num_monitores = 20;
		this.monitores=new ArrayList<Monitor>();
		
	}
	
	public Actividad(String nombre, NivelEducativo nivel, String hora, int max_participantes, int num_monitores)
	{
		this.nombreActividad = nombre;
		this.nivel = nivel;
		this.hora = hora;
		this.max_participantes = max_participantes;
		this.num_monitores = num_monitores;
		this.monitores = new ArrayList<Monitor>();
	}

	public String getNombreActividad() {
		return nombreActividad;
	}

	public void setNombreActividad(String nombreActividad) {
		this.nombreActividad = nombreActividad;
	}

	public NivelEducativo getNivel() {
		return nivel;
	}

	public void setNivel(NivelEducativo nivel) {
		this.nivel = nivel;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public int getMax_participantes() {
		return max_participantes;
	}

	public void setMax_participantes(int max_participantes) {
		this.max_participantes = max_participantes;
	}

	public int getNum_monitores() {
		return num_monitores;
	}

	public void setNum_monitores(int num_monitores) {
		this.num_monitores = num_monitores;
	}

	public ArrayList<Monitor> getMonitores() {
		return monitores;
	}

	public void setMonitores(ArrayList<Monitor> monitores) {
		this.monitores = monitores;
	}

	@Override
	public String toString() {
		return "Actividad [nombreActividad=" + nombreActividad + ", nivel=" + nivel + ", hora=" + hora
				+ ", max_participantes=" + max_participantes + ", num_monitores=" + num_monitores + ", monitores="
				+ monitores + "]";
	}
	
	public Boolean asociarMonitor(Monitor m)
	{
		if(this.monitores.size()==this.num_monitores)
		{
			return false;
		}
		
		//Busco el monitor por si estuviese
		/*
		 * 
		 * this.monitores.get(i)  =      this.monitores[i]
		 */
		for(int i=0; i<this.monitores.size(); i++)
		{
			if(this.monitores.get(i).getIdentificador() == m.getIdentificador())
			{
				return false;
			}
		}
		
		this.monitores.add(m);
		return true;
	}
}
