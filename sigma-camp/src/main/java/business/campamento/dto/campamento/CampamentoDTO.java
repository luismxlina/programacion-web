package business.campamento.dto.campamento;

import java.util.Date;

import business.campamento.models.campamento.Campamento;

public class CampamentoDTO {
    private int identificador;
    private Date fechaInicio;
    private Date fechaFin;
    private String nivel;
    private int maxAsistentes;

    /**
     * Constructor por defecto.
     * Inicializa el identificador a 0, las fechas a la fecha actual, el nivel a una
     * cadena vacía y maxAsistentes a 0.
     */
    public CampamentoDTO() {
        this.identificador = 0;
        this.fechaInicio = new Date();
        this.fechaFin = new Date();
        this.nivel = "";
        this.maxAsistentes = 0;
    }

    /**
     * Constructor con todos los atributos.
     * 
     * @param identificador el identificador del campamento
     * @param fechaInicio   la fecha de inicio del campamento
     * @param fechaFin      la fecha de fin del campamento
     * @param nivel         el nivel del campamento
     * @param maxAsistentes el número máximo de asistentes al campamento
     */
    public CampamentoDTO(int identificador, Date fechaInicio, Date fechaFin, String nivel, int maxAsistentes) {
        this.identificador = identificador;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.nivel = nivel;
        this.maxAsistentes = maxAsistentes;
    }

    /**
     * Constructor que crea un CampamentoDTO a partir de un Campamento.
     * 
     * @param campamento el campamento
     */
    public CampamentoDTO(Campamento campamento) {
        this.identificador = campamento.getIdentificador();
        ;
        this.fechaInicio = campamento.getFechaInicio();
        this.fechaFin = campamento.getFechaFin();
        this.nivel = campamento.getNivel().toString();
        this.maxAsistentes = campamento.getMaxAsistentes();
    }

    /**
     * Constructor con solo el identificador.
     * 
     * @param identificador el identificador del campamento
     */
    public CampamentoDTO(int identificador) {
        this.identificador = identificador;
    }

    /**
     * Devuelve el identificador del campamento.
     * 
     * @return el identificador del campamento
     */
    public int getIdentificador() {
        return identificador;
    }

    /**
     * Establece el identificador del campamento.
     * 
     * @param identificador el nuevo identificador del campamento
     */
    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    /**
     * Devuelve la fecha de inicio del campamento.
     * 
     * @return la fecha de inicio del campamento
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Establece la fecha de inicio del campamento.
     * 
     * @param fechaInicio la nueva fecha de inicio del campamento
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * Devuelve la fecha de fin del campamento.
     * 
     * @return la fecha de fin del campamento
     */
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * Establece la fecha de fin del campamento.
     * 
     * @param fechaFin la nueva fecha de fin del campamento
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * Devuelve el nivel del campamento.
     * 
     * @return el nivel del campamento
     */
    public String getNivel() {
        return nivel;
    }

    /**
     * Establece el nivel del campamento.
     * 
     * @param nivel el nuevo nivel del campamento
     */
    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    /**
     * Devuelve el número máximo de asistentes al campamento.
     * 
     * @return el número máximo de asistentes al campamento
     */
    public int getMaxAsistentes() {
        return maxAsistentes;
    }

    /**
     * Establece el número máximo de asistentes al campamento.
     * 
     * @param maxAsistentes el nuevo número máximo de asistentes al campamento
     */
    public void setMaxAsistentes(int maxAsistentes) {
        this.maxAsistentes = maxAsistentes;
    }

}
