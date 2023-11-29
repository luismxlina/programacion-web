
package es.uco.pw.view;

import java.util.Scanner;

import es.uco.pw.business.campamento.handler.GestorCampamentos;
import es.uco.pw.business.inscripcion.handler.GestorInscripciones;
import es.uco.pw.business.users.handler.GestorAsistentes;

/**
 * Clase principal que contiene el punto de entrada del programa Campamentos de
 * programación.
 */
public class MainPrincipal {

	/**
	 * Método principal del programa que inicia la ejecución del sistema Campamentos
	 * de programación.
	 * 
	 * @param args Argumentos de la línea de comandos (no se utilizan).
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		System.out.println("Bienvenido a Campamentos de programanción.");

		Scanner teclado = new Scanner(System.in);
		int opcion;

		// Crear los gestores y repositorios
		GestorAsistentes gestor_asistentes = GestorAsistentes.getInstance();

		GestorCampamentos gestor_campamentos = GestorCampamentos.getInstance();

		GestorInscripciones gestor_inscripciones = GestorInscripciones.getInstance();

		System.out.println("·-----------------------------------·");
		System.out.println("|           MENÚ PRINCIPAL          |");

		do {
			System.out.println("·-----------------------------------·");
			System.out.println("|     (1) Gestor de asistentes      |");
			System.out.println("|     (2) Gestor de campamentos     |");
			System.out.println("|     (3) Gestor de inscripciones   |");
			System.out.println("|     (0) SALIR                     |");
			System.out.println("·-----------------------------------·");

			opcion = teclado.nextInt();

			switch (opcion) {
				case 0:
					System.out.println("Saliendo del programa...");
					break;
				case 1:
					MainAsistentes.mostrarMenuAsistentes(teclado, gestor_asistentes);
					break;
				case 2:
					MainCampamentos.mostrarMenuCampamentos(teclado, gestor_campamentos);
					break;
				case 3:
					MainInscripciones.mostrarMenuInscripciones(teclado, gestor_inscripciones);
					break;
				default:
					System.out.println("Opción no valida...");
			}

		} while (opcion != 0);

		teclado.close();
	}

}