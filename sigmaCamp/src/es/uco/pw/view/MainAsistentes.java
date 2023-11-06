package es.uco.pw.view;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

import es.uco.pw.business.users.handler.GestorAsistentes;
import es.uco.pw.business.users.models.asistente.Asistente;

/**
 * La clase MainAsistentes proporciona un menú de opciones relacionadas con la gestión de asistentes.
 * Se utiliza para interactuar con el usuario a través de la consola y realizar operaciones en el sistema.
 */

public class MainAsistentes {

	private static HashSet<Integer> idSet = new HashSet<>();

	 /**
     * Muestra un menú de opciones relacionadas con la gestión de asistentes.
     *
     * @param teclado           Scanner para la entrada del usuario.
     * @param gestor_asistentes Objeto GestorAsistentes para gestionar las operaciones con asistentes.
     */

	public static void mostrarMenuAsistentes(Scanner teclado, GestorAsistentes gestor_asistentes) {

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
					gestor_asistentes.mostrarAsistentes();
					break;
				case 2:
					Asistente nuevoAsistente = new Asistente(generarIDUnico());
					if (!GestorAsistentes.pedirDatosTeclado(teclado, nuevoAsistente)) {
						break;
					}
					gestor_asistentes.altaAsistente(nuevoAsistente);
					break;
				case 3:
					gestor_asistentes.eliminarAsistente(teclado);
					break;
				default:
					System.out.println("Opción no válida");
			}

		} while (opcion != 0);

	}

	 /**
     * Genera un ID único para un asistente utilizando un conjunto HashSet para evitar duplicados.
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
}
