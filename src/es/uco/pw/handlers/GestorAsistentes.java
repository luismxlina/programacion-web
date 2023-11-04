package es.uco.pw.handlers;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import es.uco.pw.classes.asistente.Asistente;

/**
 * Clase que gestiona los asistentes.
 */
public class GestorAsistentes {
	// Atributos

	// ArrayList que contiene los asistentes.
	private ArrayList<Asistente> asistentes;

	// Singleton - Instancia única de GestorAsistentes.
	private static GestorAsistentes instance = null;

	/**
	 * Constructor privado para crear una instancia de GestorAsistentes.
	 *
	 * @param arrayNuevo ArrayList de asistentes.
	 */
	private GestorAsistentes(ArrayList<Asistente> arrayNuevo) {
		this.asistentes = arrayNuevo;
	}

	/**
	 * Método estático para obtener la instancia única de GestorAsistentes.
	 *
	 * @param arrayNuevo ArrayList de asistentes.
	 * @return Instancia única de GestorAsistentes.
	 */
	public static GestorAsistentes getInstance(ArrayList<Asistente> arrayNuevo) {
		if (instance == null) {
			instance = new GestorAsistentes(arrayNuevo);
		}
		return instance;
	}

	// Métodos

	/**
	 * Obtiene la lista de asistentes.
	 *
	 * @return ArrayList de asistentes.
	 */
	public ArrayList<Asistente> getAsistentes() {
		return this.asistentes;
	}

	/**
	 * Obtiene un asistente por su identificador.
	 *
	 * @param id Identificador del asistente.
	 * @return Asistente o null si no se encuentra.
	 */
	public Asistente getAsistente(int id) {
		for (Asistente asistente : this.asistentes) {
			if (asistente.getIdentificador() == id) {
				return asistente;
			}
		}
		return null;
	}

	/**
	 * Da de alta a un nuevo asistente.
	 *
	 * @param nuevoAsistente Nuevo asistente a dar de alta.
	 * @return true si se pudo dar de alta, false si el asistente ya existe.
	 */
	public Boolean altaAsistente(Asistente nuevoAsistente) {
		if (buscarAsistente(nuevoAsistente.getIdentificador()) == false) {
			this.asistentes.add(nuevoAsistente);
			return true;
		}
		return false;
	}

	/**
	 * Busca un asistente por su identificador.
	 *
	 * @param id Identificador del asistente.
	 * @return true si el asistente existe, false en caso contrario.
	 */
	public Boolean buscarAsistente(int id) {
		if (asistentes.size() == 0)
			return false;

		for (int i = 0; i < asistentes.size(); i++) {
			if (this.asistentes.get(i).getIdentificador() == id) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Muestra la lista de asistentes en consola.
	 */
	public void mostrarAsistentes() {
		if (instance != null) {
			for (int i = 0; i < this.asistentes.size(); i++) {
				System.out.println("ID: " + this.asistentes.get(i).getIdentificador()
						+ ", Nombre: " + this.asistentes.get(i).getNombre()
						+ ", Apellidos: " + this.asistentes.get(i).getApellidos()
						+ ", Fecha nacimiento: " + this.asistentes.get(i).getFechaNacimiento()
						+ ", Necesita atención: " + this.asistentes.get(i).getRequiereAtencion());
			}
		}
	}

	/**
	 * Método que valida el nombre o apellidos de un asistente.
	 *
	 * @param input Nombre o apellidos a validar.
	 * @return Nombre o apellidos si son válidos, cadena vacía si no lo son.
	 */
	private static String validarNombre(String input) {
		// Expresión regular para verificar que el nombre no contiene números
		String regex = "^[A-Za-záéíóúÁÉÍÓÚñÑüÜ\\s]+$";

		if (input.matches(regex)) {
			return input;
		} else {
			System.out.println("El nombre o apellidos no pueden contener números ni caracteres especiales.");
			return "";
		}
	}

	/**
	 * Método que recopila los datos del nuevo asistente desde el teclado.
	 *
	 * @param teclado        Scanner para entrada por teclado.
	 * @param nuevoAsistente Nuevo asistente a crear.
	 * @return true si se pudieron recopilar los datos, false en caso contrario.
	 */
	public static Boolean pedirDatosTeclado(Scanner teclado, Asistente nuevoAsistente) {
		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
		String nombre = "";
		String apellidos = "";
		String fechaTexto;
		int atencionInt;

		System.out.println("Introduzca los datos del nuevo asistente:");
		while (nombre.isEmpty()) {
			System.out.print("Nombre: ");
			teclado.nextLine();
			nombre = teclado.nextLine();
			nombre = validarNombre(nombre);
		}
		while (apellidos.isEmpty()) {

			System.out.print("Apellidos: ");
			apellidos = teclado.nextLine();
			apellidos = validarNombre(apellidos);
		}
		System.out.print("Fecha nacimiento (yyyy-mm-dd): ");
		fechaTexto = teclado.nextLine();
		System.out.print("Requiere atencion especial (Si - 1 / No - 0): ");
		atencionInt = teclado.nextInt();

		Date fecha = new Date();
		try {

			fecha = formatoFecha.parse(fechaTexto);

		} catch (ParseException e) {
			System.out.println("Error al convertir la fecha...");
			return false;
		}

		Boolean atencion = false;
		if (atencionInt == 1) {
			atencion = true;
		}

		nuevoAsistente.setNombre(nombre);
		nuevoAsistente.setApellidos(apellidos);
		nuevoAsistente.setFechaNacimiento(fecha);
		nuevoAsistente.setRequiereAtencion(atencion);
		return true;
	}

	/**
	 * Método que modifica los datos de un asistente.
	 */
	public void modificarAsistente() {

		Scanner teclado = new Scanner(System.in);
		int identificador;

		System.out.print("Escriba el identificador del asistente a modificar: ");
		identificador = teclado.nextInt();

		if (buscarAsistente(identificador)) {

			SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

			String newNombre;
			String newApellidos;
			String newFechaTexto;
			Date newFecha = new Date();
			int newAtencionTexto;
			Boolean newAtencion = false;

			System.out.println("Introduzca los nuevos datos del asistente: ");
			// String saltoDeLinea = teclado.nextLine();

			// Leer los nuevos datos del asistente
			System.out.print("Nuevo nombre: ");
			newNombre = teclado.nextLine();
			System.out.print("Nuevos apellidos: ");
			newApellidos = teclado.nextLine();
			System.out.print("Nueva fecha nacimiento (yyyy-mm-dd): ");
			newFechaTexto = teclado.nextLine();
			System.out.print("Requiere atencion especial (Si - 1 / No - 0): ");
			newAtencionTexto = teclado.nextInt();

			if (newAtencionTexto == 1) {
				newAtencion = true;
			}

			try {

				newFecha = formatoFecha.parse(newFechaTexto);

			} catch (ParseException e) {

				System.out.println("Error al convertir la fecha");

			}

			for (Asistente asistente : this.asistentes) {

				if (asistente.getIdentificador() == identificador) {

					asistente.setNombre(newNombre);
					asistente.setApellidos(newApellidos);
					asistente.setFechaNacimiento(newFecha);
					asistente.setRequiereAtencion(newAtencion);

				}

			}

		}

		else {

			System.out.println("No existe ninun usuario con el ID indicado");

		}
		teclado.close();
	}

	/**
	 * Método que elimina un asistente.
	 *
	 * @param teclado Scanner para entrada por teclado.
	 */
	public void eliminarAsistente(Scanner teclado) {

		int identificador;

		System.out.print("Escriba el identificador del asistente a eliminar: ");
		identificador = teclado.nextInt();

		if (buscarAsistente(identificador)) {

			for (int i = 0; i < this.asistentes.size(); i++) {

				if (this.asistentes.get(i).getIdentificador() == identificador) {

					this.asistentes.remove(i);

				}

			}
		}

		else {

			System.out.println("No existe ningún usuario con el ID indicado");

		}
	}
}
