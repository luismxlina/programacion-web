package es.uco.pw.display;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

import es.uco.pw.classes.asistente.Asistente;
import es.uco.pw.gestores.GestorAsistentes;

public class MainAsistentes {

	private static HashSet<Integer> idSet = new HashSet<>();

	public static void mostrarMenuAsistentes(Scanner teclado, GestorAsistentes gestor_asistentes) {

		int opcion;

		do {

			System.out.println("*************************************");
			System.out.println("*******   MENÚ de Asistentes  *******");
			System.out.println("*************************************");
			System.out.println("(1) Mostrar asistentes");
			System.out.println("(2) Añadir asistente");
			System.out.println("(3) Borrar asistente");
			System.out.println("(0) Volver al menú principal");

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

				// case 3:
				// 	gestor_asistentes.eliminarAsistente(teclado);
				// 	break;

				default:
					System.out.println("Opción no válida");
			}

		} while (opcion != 0);

		// teclado.close();
	}

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
