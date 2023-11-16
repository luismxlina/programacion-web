package es.uco.pw.business.users.dto.asistente;

import java.util.Date;
import es.uco.pw.business.users.models.asistente.Asistente;

public class AsistenteDTO {
    private int identificador;
    private String nombre;
    private String apellidos;
    private Date fechaNacimiento;
    private Boolean requiereAtencion;

    public AsistenteDTO() {
        this.identificador = 0;
        this.nombre = "";
        this.apellidos = "";
        this.fechaNacimiento = new Date();
        this.requiereAtencion = false;
    }

    public AsistenteDTO(int identificador, String nombre, String apellidos, Date fechaNacimiento,
            Boolean requiereAtencion) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.requiereAtencion = requiereAtencion;
    }

    public AsistenteDTO(Asistente asistente) {
        this.identificador = asistente.getIdentificador();
        this.nombre = asistente.getNombre();
        this.apellidos = asistente.getApellidos();
        this.fechaNacimiento = asistente.getFechaNacimiento();
        this.requiereAtencion = asistente.getRequiereAtencion();
    }

    public AsistenteDTO(int identificador) {
        this.identificador = identificador;
    }

    public AsistenteDTO(String nombre, String apellidos, Date fecha, Boolean atencion) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fecha;
        this.requiereAtencion = atencion;
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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fecha) {
        this.fechaNacimiento = fecha;
    }

    public Boolean getRequiereAtencion() {
        return requiereAtencion;
    }

    public void setRequiereAtencion(Boolean requiereAtencion) {
        this.requiereAtencion = requiereAtencion;
    }

}
