
package es.uco.pw.view;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

import es.uco.pw.business.campamento.handler.GestorCampamentos;
import es.uco.pw.business.campamento.models.campamento.Campamento;
import es.uco.pw.business.inscripcion.handler.GestorInscripciones;
import es.uco.pw.business.inscripcion.models.inscripcion.Inscripcion;
import es.uco.pw.business.users.handler.GestorAsistentes;
import es.uco.pw.business.users.models.asistente.Asistente;
import es.uco.pw.data.RepositorioAsistentes;
import es.uco.pw.data.RepositorioCampamentos;
import es.uco.pw.data.RepositorioInscripciones;

/**
 * Clase principal que contiene el punto de entrada del programa Campamentos de
 * programación.
 */
public class MainPrincipal {

	public static void comprobarFicheros() {
		try {
			comprobarYCrearFichero("db/asistentes.txt");
			comprobarYCrearFichero("db/campamentos.txt");
			comprobarYCrearFichero("db/inscripciones.txt");
		} catch (IOException e) {
			System.out.println("Error al crear los ficheros");
		}
	}

	private static void comprobarYCrearFichero(String rutaFichero) throws IOException {
		File fichero = new File(rutaFichero);

		if (!fichero.exists()) {
			if (fichero.createNewFile()) {
				System.out.println("Fichero " + rutaFichero + " creado correctamente.");
			} else {
				System.out.println("Error al intentar crear el fichero " + rutaFichero);
			}
		}
	}

	/**
	 * Método principal del programa que inicia la ejecución del sistema Campamentos
	 * de programación.
	 * 
	 * @param args Argumentos de la línea de comandos (no se utilizan).
	 */
	public static void main(String[] args) {
		comprobarFicheros();
		System.out.println("Bienvenido a Campamentos de programanción.");

		Scanner teclado = new Scanner(System.in);
		int opcion;

		// Crear los gestores y repositorios
		RepositorioAsistentes ra = new RepositorioAsistentes();
		ArrayList<Asistente> arrayAsistentesFichero = ra.cargarDatosFichero("db/asistentes.txt");
		GestorAsistentes gestor_asistentes = GestorAsistentes.getInstance(arrayAsistentesFichero);

		RepositorioCampamentos rc = new RepositorioCampamentos();
		ArrayList<Campamento> arrayCampamentosFichero = rc.cargarDatosFichero("db/campamentos.txt");
		GestorCampamentos gestor_campamentos = GestorCampamentos.getInstance(arrayCampamentosFichero);

		RepositorioInscripciones ri = new RepositorioInscripciones();
		ArrayList<Inscripcion> arrayInscripcionesFichero = ri.cargarDatosFichero("db/inscripciones.txt");
		GestorInscripciones gestor_inscripciones = GestorInscripciones.getInstance(arrayInscripcionesFichero);

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
					ra.guardarEnFichero(gestor_asistentes.getAsistentes(), "db/asistentes.txt");
					rc.guardarEnFichero(gestor_campamentos.getCampamentos(), "db/campamentos.txt");
					ri.guardarEnFichero(gestor_inscripciones.getInscripciones(), "db/inscripciones.txt");
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
