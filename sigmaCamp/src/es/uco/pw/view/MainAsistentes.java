package es.uco.pw.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;

import es.uco.pw.business.users.handler.GestorAsistentes;
import es.uco.pw.business.users.models.asistente.Asistente;

/**
 * La clase MainAsistentes proporciona un menú de opciones relacionadas con la
 * gestión de asistentes.
 * Se utiliza para interactuar con el usuario a través de la consola y realizar
 * operaciones en el sistema.
 */

public class MainAsistentes {

	private static HashSet<Integer> idSet = new HashSet<>();

	/**
	 * Muestra un menú de opciones relacionadas con la gestión de asistentes.
	 *
	 * @param teclado           Scanner para la entrada del usuario.
	 * @param gestor_asistentes Objeto GestorAsistentes para gestionar las
	 *                          operaciones con asistentes.
	 */

	public static void mostrarMenuAsistentes(Scanner teclado, GestorAsistentes gestor_asistentes) {

		ArrayList<Integer> ids = GestorAsistentes.getInstance().getAllIds();

		for (int i = 0; i < ids.size(); i++) {
			idSet.add(ids.get(i));
		}

		int opcion;

		System.out.println("·-----------------------------------·");
		System.out.println("|         MENÚ de Asistentes        |");
		System.out.println("·-----------------------------------·");

		do {
			System.out.println("");
			System.out.println("(1) Mostrar asistentes");
			System.out.println("(2) Añadir asistente");
			System.out.println("(3) Eliminar asistente");
			System.out.println("(0) Volver al menú principal");
			System.out.println("");

			opcion = teclado.nextInt();

			switch (opcion) {
				case 0:
					System.out.println("Volviendo al menú principal...");
					break;
				case 1:
					mostrarAsistentes(gestor_asistentes.getAsistentes());
					break;
				case 2:
					Asistente nuevoAsistente = pedirDatosTeclado(teclado, new Asistente(generarIDUnico()));
					gestor_asistentes.altaAsistente(nuevoAsistente);
					break;
				case 3:
					int identificador = leerDatosEliminarAsistente(teclado);
					if (gestor_asistentes.eliminarAsistente(identificador)) {
						System.out.println("Asistente eliminado correctamente");
					} else {
						System.out.println("No existe ningún usuario con el ID indicado");
					}
					break;
				default:
					System.out.println("Opción no válida");
			}

		} while (opcion != 0);

	}

	/**
	 * Genera un ID único para un asistente utilizando un conjunto HashSet para
	 * evitar duplicados.
	 *
	 * @return Un ID único generado aleatoriamente.
	 */

	public static int generarIDUnico() {
		Random random = new Random();
		int id;
		do {
			id = random.nextInt(100000);
		} while (idSet.contains(id));
		idSet.add(id);
		return id;
	}

	public static void mostrarAsistentes(ArrayList<Asistente> asistentes) {
		for (int i = 0; i < asistentes.size(); i++) {
			System.out.println("ID: " + asistentes.get(i).getIdentificador()
					+ ", Nombre: " + asistentes.get(i).getNombre()
					+ ", Apellidos: " + asistentes.get(i).getApellidos()
					+ ", Fecha nacimiento: " + asistentes.get(i).getFechaNacimiento()
					+ ", Necesita atención: " + asistentes.get(i).getRequiereAtencion());
		}
	}

	public static Asistente pedirDatosTeclado(Scanner teclado, Asistente nuevoAsistente) {
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
			return null;
		}

		Boolean atencion = false;
		if (atencionInt == 1) {
			atencion = true;
		}

		nuevoAsistente.setNombre(nombre);
		nuevoAsistente.setApellidos(apellidos);
		nuevoAsistente.setFechaNacimiento(fecha);
		nuevoAsistente.setRequiereAtencion(atencion);
		return nuevoAsistente;
	}

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

	public static int leerDatosEliminarAsistente(Scanner teclado) {

		int identificador;

		System.out.print("Escriba el identificador del asistente a eliminar: ");
		identificador = teclado.nextInt();

		return identificador;
	}
}
