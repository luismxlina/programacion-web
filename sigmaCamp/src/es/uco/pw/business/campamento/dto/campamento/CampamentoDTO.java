package es.uco.pw.business.campamento.dto.campamento;

import java.util.Date;

public class CampamentoDTO {
    private int identificador;
    private Date fechaInicio;
    private Date fechaFin;
    private String nivel;
    private int maxAsistentes;

    public CampamentoDTO() {
    }

    public CampamentoDTO(int identificador, Date fechaInicio, Date fechaFin, String nivel, int maxAsistentes) {
        this.identificador = identificador;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.nivel = nivel;
        this.maxAsistentes = maxAsistentes;
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

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public int getmaxAsistentes() {
        return maxAsistentes;
    }

    public void setmaxAsistentes(int maxAsistentes) {
        this.maxAsistentes = maxAsistentes;
    }
}
