package business.inscripcion.dto.inscripcion;

import java.util.Date;


import business.inscripcion.models.inscripcion.Inscripcion;
import business.inscripcion.models.inscripcion.TipoInscripcion;

public class InscripcionDTO {
    private int asistenteId;
    private int campamentoId;
    private Date fechaInscripcion;
    private double precio;
    private TipoInscripcion tipoInscripcion;

    public InscripcionDTO() {

    }

    public InscripcionDTO(int asistenteId, int campamentoId, Date fechaInscripcion, double precio,
            TipoInscripcion tipoInscripcion) {
        this.asistenteId = asistenteId;
        this.campamentoId = campamentoId;
        this.fechaInscripcion = fechaInscripcion;
        this.precio = precio;
        this.tipoInscripcion = tipoInscripcion;
    }

    public InscripcionDTO(Inscripcion inscripcion) {
        this.asistenteId = inscripcion.getId_Participante();
        this.campamentoId = inscripcion.getId_Campamento();
        this.fechaInscripcion = inscripcion.getFechaInscripcion();
        this.precio = inscripcion.getPrecio();
        this.tipoInscripcion = inscripcion.getTipoInscripcion();
    }

    public int getAsistenteId() {
        return asistenteId;
    }

    public void setAsistenteId(int asistenteId) {
        this.asistenteId = asistenteId;
    }

    public int getCampamentoId() {
        return campamentoId;
    }

    public void setCampamentoId(int campamentoId) {
        this.campamentoId = campamentoId;
    }

    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precioTotal) {
        this.precio = precioTotal;
    }

    public void setTipoInscripcion(TipoInscripcion tipoInscripcion) {
        this.tipoInscripcion = tipoInscripcion;
    }

    public TipoInscripcion getTipoInscripcion() {
        return tipoInscripcion;
    }
}
