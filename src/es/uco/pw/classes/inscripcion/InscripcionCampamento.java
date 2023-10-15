package es.uco.pw.classes.inscripcion;

import es.uco.pw.classes.asistente.Asistente;

import java.util.Date;

public class InscripcionCampamento extends Asistente {
    private int idCampamento;

    public InscripcionCampamento() {
        super();
        this.idCampamento = -1;
    }

    public InscripcionCampamento(int identificador, String nombre, String apellidos, Date fechaNacimiento,
                               Boolean requiereAtencion, int idCampamento) {
        super(identificador, nombre, apellidos, fechaNacimiento, requiereAtencion);
        this.idCampamento = idCampamento;
    }

    public int getIdCampamento() {
        return idCampamento;
    }

    public void setIdCampamento(int idCampamento) {
        this.idCampamento = idCampamento;
    }

    @Override
    public String toString() {
        return super.toString() + ", idCampamento=" + idCampamento;
    }
}
