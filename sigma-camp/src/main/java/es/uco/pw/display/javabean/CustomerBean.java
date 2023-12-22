package es.uco.pw.display.javabean;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Clase que representa a un cliente en el sistema.
 * Implementa la interfaz Serializable para permitir que los objetos de esta clase se puedan serializar.
 */
public class CustomerBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String email;
	private Integer id;
	private LocalDate fechaNacimiento;
	private LocalDate fechaIncripcion;
	private String nombreCompleto;
	private String rol;
	private String password;
	private boolean mayorEdad;

	 /**
     * Constructor por defecto.
     */
	public CustomerBean() {
	}

	/**
     * Obtiene el email del cliente.
     * @return el email del cliente.
     */
	public String getEmail() {
		return email;
	}

	/**
     * Establece el email del cliente.
     * @param email el nuevo email del cliente.
     */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
     * Obtiene la fecha de nacimiento del cliente.
     * @return la fecha de nacimiento del cliente.
     */
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
     * Establece la fecha de nacimiento del cliente.
     * @param fechaNacimiento la nueva fecha de nacimiento del cliente.
     */
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	/**
     * Obtiene la fecha de inscripción del cliente.
     * @return la fecha de inscripción del cliente.
     */
	public LocalDate getFechaIncripcion() {
		return fechaIncripcion;
	}

	/**
     * Establece la fecha de inscripción del cliente.
     * @param fechaIncripcion la nueva fecha de inscripción del cliente.
     */
	public void setFechaIncripcion(LocalDate fechaIncripcion) {
		this.fechaIncripcion = fechaIncripcion;
	}

	/**
     * Obtiene el nombre completo del cliente.
     * @return el nombre completo del cliente.
     */
	public String getNombreCompleto() {
		return nombreCompleto;
	}

	/**
     * Establece el nombre completo del cliente.
     * @param nombreCompleto el nuevo nombre completo del cliente.
     */
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	/**
     * Obtiene el rol del cliente.
     * @return el rol del cliente.
     */
	public String getRol() {
		return rol;
	}	

	/**
     * Establece el rol del cliente.
     * @param rol el nuevo rol del cliente.
     */
	public void setRol(String rol) {
		this.rol = rol;
	}

	/**
     * Obtiene la contraseña del cliente.
     * @return la contraseña del cliente.
     */
	public String getPassword() {
		return password;
	}

	/**
     * Establece la contraseña del cliente.
     * @param password la nueva contraseña del cliente.
     */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
     * Obtiene el ID del cliente.
     * @return el ID del cliente.
     */
	public Integer getId() {
		return id;
	}

	 /**
     * Establece el ID del cliente.
     * @param id el nuevo ID del cliente.
     */
	public void setId(Integer id) {
		this.id = id;
	}

	 /**
     * Verifica si el cliente es mayor de edad.
     * @return true si el cliente es mayor de edad, false en caso contrario.
     */
	public boolean isMayorEdad() {
		return mayorEdad;
	}

	 /**
     * Establece si el cliente es mayor de edad.
     * @param mayorEdad true si el cliente es mayor de edad, false en caso contrario.
     */
	public void setMayorEdad(boolean mayorEdad) {
		this.mayorEdad = mayorEdad;
	}

	 /**
     * Devuelve una representación en cadena de caracteres del objeto Cliente.
     * @return una cadena de caracteres que representa al objeto Cliente.
     */
	@Override
	public String toString() {
		return "CustomerBean [email=" + email + ", fechaNacimiento=" + fechaNacimiento + ", fechaIncripcion="
				+ fechaIncripcion + ", nombreCompleto=" + nombreCompleto + ", rol=" + rol + ", password=" + password
				+ ", id=" + id + ", MayorEdad=" + mayorEdad + "]";
	}

}
