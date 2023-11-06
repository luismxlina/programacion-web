package es.uco.pw.business.campamento.dto.actividad;

import es.uco.pw.business.campamento.models.actividad.NivelEducativo;

public class ActividadDTO {
    private String nombreActividad;
    private NivelEducativo nivel;
    private String hora;
    private int maxParticipantes;
    private int numMonitores;

    public ActividadDTO() {
    }

    public ActividadDTO(String nombreActividad, NivelEducativo nivel, String hora, int maxParticipantes,
            int numMonitores) {
        this.nombreActividad = nombreActividad;
        this.nivel = nivel;
        this.hora = hora;
        this.maxParticipantes = maxParticipantes;
        this.numMonitores = numMonitores;
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
    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
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