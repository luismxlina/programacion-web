package es.uco.pw.business.campamento.dto.actividad;

import java.time.LocalTime;

import es.uco.pw.business.campamento.models.actividad.NivelEducativo;
import es.uco.pw.business.campamento.models.actividad.Actividad;


public class ActividadDTO {
    private String nombreActividad;
    private NivelEducativo nivel;
    private LocalTime hora;
    private int maxParticipantes;
    private int numMonitores;

    public ActividadDTO() {
        this.nombreActividad = "";
        this.nivel = NivelEducativo.INFANTIL;
        this.hora = LocalTime.now();
        this.maxParticipantes = 0;
        this.numMonitores = 0;
    }

    public ActividadDTO(String nombreActividad, NivelEducativo nivel, LocalTime hora, int maxParticipantes,
            int numMonitores) {
        this.nombreActividad = nombreActividad;
        this.nivel = nivel;
        this.hora = hora;
        this.maxParticipantes = maxParticipantes;
        this.numMonitores = numMonitores;
    }

    public ActividadDTO(Actividad actividad) {
        this.nombreActividad = actividad.getNombreActividad();
        this.nivel = actividad.getNivel();
        this.hora = actividad.getHora();
        this.maxParticipantes = actividad.getMax_participantes();
        this.numMonitores = actividad.getNum_monitores();
    }

    // métodos getter y setter para nombreActividad
    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    // métodos getter y setter para nivel
    public NivelEducativo getNivel() {
        return nivel;
    }

    public void setNivel(NivelEducativo nivel) {
        this.nivel = nivel;
    }

    // métodos getter y setter para hora
    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    // métodos getter y setter para maxParticipantes
    public int getMaxParticipantes() {
        return maxParticipantes;
    }

    public void setMaxParticipantes(int maxParticipantes) {
        this.maxParticipantes = maxParticipantes;
    }

    // métodos getter y setter para numMonitores
    public int getNumMonitores() {
        return numMonitores;
    }

    public void setNumMonitores(int numMonitores) {
        this.numMonitores = numMonitores;
    }
}