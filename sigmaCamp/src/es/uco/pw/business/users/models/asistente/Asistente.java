package es.uco.pw.business.users.models.asistente;

import java.io.Serializable;
import java.util.Date;
import es.uco.pw.business.users.dto.asistente.AsistenteDTO;

/**
 * La clase Asistente representa a una persona asistente con información básica.
 */
public class Asistente implements Serializable {

    private int identificador;
    private String nombre;
    private String apellidos;
    private Date fechaNacimiento;
    private Boolean requiereAtencion;

    /**
     * Constructor por defecto que inicializa un objeto Asistente con valores
     * predeterminados.
     */
    public Asistente() {
        this.identificador = 0;
        this.nombre = "";
        this.apellidos = "";
        this.fechaNacimiento = new Date();
        this.requiereAtencion = false;
    }

    /**
     * Constructor que permite crear un objeto Asistente con valores específicos.
     *
     * @param identificador
     *                         El identificador del asistente.
     * @param nombre
     *                         El nombre del asistente.
     * @param apellidos
     *                         Los apellidos del asistente.
     * @param fechaNacimiento
     *                         La fecha de nacimiento del asistente.
     * @param requiereAtencion
     *                         Indica si el asistente requiere atención especial.
     */
    public Asistente(int identificador, String nombre, String apellidos, Date fechaNacimiento,
            Boolean requiereAtencion) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.requiereAtencion = requiereAtencion;
    }

    /**
     * Constructor que inicializa un nuevo Asistente con un identificador
     * específico.
     *
     * @param identificador El identificador único del asistente.
     */
    public Asistente(int identificador) {
        this.identificador = identificador;
    }

    /**
     * Constructor que inicializa un nuevo Asistente con los detalles
     * proporcionados.
     *
     * @param nombre    El nombre del asistente.
     * @param apellidos Los apellidos del asistente.
     * @param fecha     La fecha de nacimiento del asistente.
     * @param atencion  Indica si el asistente requiere atención especial.
     */
    public Asistente(String nombre, String apellidos, Date fecha, Boolean atencion) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fecha;
        this.requiereAtencion = atencion;
    }

    /**
     * Constructor que inicializa un nuevo Asistente a partir de un objeto
     * AsistenteDTO.
     *
     * @param asistente Un objeto AsistenteDTO que contiene los detalles del
     *                  asistente.
     */
    public Asistente(AsistenteDTO asistente) {
        this.identificador = asistente.getIdentificador();
        this.nombre = asistente.getNombre();
        this.apellidos = asistente.getApellidos();
        this.fechaNacimiento = asistente.getFechaNacimiento();
        this.requiereAtencion = asistente.getRequiereAtencion();
    }

    /**
     * Obtiene el identificador del asistente.
     *
     * @return El identificador del asistente.
     */
    public int getIdentificador() {
        return identificador;
    }

    /**
     * Establece el identificador del asistente.
     *
     * @param identificador
     *                      El identificador a establecer.
     */
    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    /**
     * Obtiene el nombre del asistente.
     *
     * @return El nombre del asistente.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del asistente.
     *
     * @param nombre
     *               El nombre a establecer.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene los apellidos del asistente.
     *
     * @return Los apellidos del asistente.
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Establece los apellidos del asistente.
     *
     * @param apellidos
     *                  Los apellidos a establecer.
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Obtiene la fecha de nacimiento del asistente.
     *
     * @return La fecha de nacimiento del asistente.
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Establece la fecha de nacimiento del asistente.
     *
     * @param fechaNacimiento
     *                        La fecha de nacimiento a establecer.
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Indica si el asistente requiere atención especial.
     *
     * @return true si el asistente requiere atención especial, false en caso
     *         contrario.
     */
    public Boolean getRequiereAtencion() {
        return requiereAtencion;
    }

    /**
     * Establece si el asistente requiere atención especial.
     *
     * @param requiereAtencion
     *                         true si el asistente requiere atención especial,
     *                         false en caso
     *                         contrario.
     */
    public void setRequiereAtencion(Boolean requiereAtencion) {
        this.requiereAtencion = requiereAtencion;
    }

    /**
     * Devuelve una representación de cadena del objeto Asistente.
     *
     * @return Una cadena que representa el objeto Asistente.
     */
    @Override
    public String toString() {
        return "Asistente [identificador=" + identificador + ", nombre=" + nombre + ", apellidos=" + apellidos
                + ", fechaNacimiento=" + fechaNacimiento + ", requiereAtencion=" + requiereAtencion + "]";
    }
}
