package es.uco.pw.business.users.dto.monitor;

import es.uco.pw.business.users.models.monitor.Monitor;

public class MonitorDTO {
    private int identificador;
    private String nombre;
    private String apellidos;
    private Boolean esEducador;

    public MonitorDTO() {
        this.identificador = 0;
        this.nombre = "";
        this.apellidos = "";
        this.esEducador = false;
    }

    public MonitorDTO(int identificador, String nombre, String apellidos, Boolean esEducador) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.esEducador = esEducador;
    }

    public MonitorDTO(String nombre, String apellidos, Boolean esEducador) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.esEducador = esEducador;
    }

    public MonitorDTO(int identificador) {
        this.identificador = identificador;
    }

    public MonitorDTO(Monitor monitor) {
        this.identificador = monitor.getIdentificador();
        this.nombre = monitor.getNombre();
        this.apellidos = monitor.getApellidos();
        this.esEducador = monitor.getEsEducador();
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Boolean getEsEducador() {
        return esEducador;
    }

    public void setEsEducador(Boolean esEducador) {
        this.esEducador = esEducador;
    }
}
