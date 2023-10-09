package Practica1;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Campamento extends Actividad {
	// Atributos
    private int id;
    private Date fechaInicio;
    private Date fechaFin;
    private NivelEducativo nivelEducativo;
    private int maxAsistentes;
    private List<Actividad> actividades;
    private List<Monitor> monitores;
    private Monitor monitorEspecial;

    // Constructor vacío
    public Campamento() {
        this.actividades = new ArrayList<>();
        this.monitores = new ArrayList<>();
    }

    // Constructor parametrizado
    public Campamento(int id, Date fechaInicio, Date fechaFin, NivelEducativo nivelEducativo, int maxAsistentes) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.nivelEducativo = nivelEducativo;
        this.maxAsistentes = maxAsistentes;
        this.actividades = new ArrayList<>();
        this.monitores = new ArrayList<>();
    }

    // Métodos get/set
    public int getIdCampamento() {
        return id;
    }

    public void setIdCampamento(int id) {
        this.id = id;
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

    public NivelEducativo getNivelEducativo() {
        return nivelEducativo;
    }

    public void setNivelEducativo(NivelEducativo nivelEducativo) {
        this.nivelEducativo = nivelEducativo;
    }

    public int getMaxAsistentes() {
        return maxAsistentes;
    }

    public void setMaxAsistentes(int maxAsistentes) {
        this.maxAsistentes = maxAsistentes;
    }

    public List<Actividad> getActividades() {
        return actividades;
    }

    public void setActividades(List<Actividad> actividades) {
        this.actividades = actividades;
    }

    public List<Monitor> getMonitores() {
        return monitores;
    }

    public void setMonitores(List<Monitor> monitores) {
        this.monitores = monitores;
    }

    public Monitor getMonitorEspecial() {
        return monitorEspecial;
    }

    public void setMonitorEspecial(Monitor monitorEspecial) {
        this.monitorEspecial = monitorEspecial;
    }

    // Método toString
    @Override
    public String toString() {
        return "Campamento{" +
                "id=" + id +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", nivelEducativo=" + nivelEducativo +
                ", maxAsistentes=" + maxAsistentes +
                ", actividades=" + actividades +
                ", monitores=" + monitores +
                ", monitorEspecial=" + monitorEspecial +
                '}';
    }

    // Método asociarActividad
    public boolean asociarActividad(Actividad actividad) {
        if (actividad.getNivelEducativo() == this.nivelEducativo) {
            actividades.add(actividad);
            return true;
        }
        return false;
    }

    // Método asociarMonitor
    public boolean asociarMonitor(Monitor monitor) {
        for (Actividad actividad : actividades) {
            if (Actividad.getmonitoresAsignados(monitor).contains(monitor)) {
                monitores.add(monitor);
                return true;
            }
        }
        return false;
    }

    // Método asociarMonitorEspecial
    public void asociarMonitorEspecial(Monitor monitor) {
        if (monitor.isEsEducadorEspecial()) {
            monitorEspecial = monitor;
        }
    }
}

