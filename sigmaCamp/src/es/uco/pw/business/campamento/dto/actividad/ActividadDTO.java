package es.uco.pw.business.campamento.dto.actividad;

import es.uco.pw.business.campamento.models.actividad.NivelEducativo;
import es.uco.pw.business.campamento.models.actividad.Actividad;
import es.uco.pw.business.campamento.models.actividad.Horario;

public class ActividadDTO {
    private String nombreActividad;
    private NivelEducativo nivel;
    private Horario hora;
    private int maxParticipantes;
    private int numMonitores;

    public ActividadDTO() {
        this.nombreActividad = "";
        this.nivel = NivelEducativo.INFANTIL;
        this.hora = Horario.MAÑANA;
        this.maxParticipantes = 0;
        this.numMonitores = 0;
    }

    public ActividadDTO(String nombreActividad, NivelEducativo nivel, Horario hora, int maxParticipantes,
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
        this.maxParticipantes = actividad.getMaxParticipantes();
        this.numMonitores = actividad.getNumMonitores();
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

    public Horario getHora() {
        return hora;
    }

    public void setHora(Horario hora) {
        this.hora = hora;
    }

    public int getMaxParticipantes() {
        return maxParticipantes;
    }

    public void setMaxParticipantes(int maxParticipantes) {
        this.maxParticipantes = maxParticipantes;
    }

    public int getNumMonitores() {
        return numMonitores;
    }

    public void setNumMonitores(int numMonitores) {
        this.numMonitores = numMonitores;
    }
}