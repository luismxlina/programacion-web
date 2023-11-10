package es.uco.pw.business.inscripcion.dto.inscripcion;

public class InscripcionDTO {
    private int asistenteId;
    private int campamentoId;
    private String fechaInscripcion;
    private double precio;
    private String tipoInscripcion;

    public InscripcionDTO() {
    }

    public InscripcionDTO(int asistenteId, int campamentoId, String fechaInscripcion, double precio,
            String tipoInscripcion) {
        this.asistenteId = asistenteId;
        this.campamentoId = campamentoId;
        this.fechaInscripcion = fechaInscripcion;
        this.precio = precio;
        this.tipoInscripcion = tipoInscripcion;
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

    public String getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(String fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precioTotal) {
        this.precio = precioTotal;
    }

    public void setTipoInscripcion(String tipoInscripcion) {
        this.tipoInscripcion = tipoInscripcion;
    }

    public String getTipoInscripcion() {
        return tipoInscripcion;
    }
}
