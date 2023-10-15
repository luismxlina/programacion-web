
package es.uco.pw.display;

import java.util.ArrayList;
import java.util.Scanner;

import es.uco.pw.classes.asistente.Asistente;
import es.uco.pw.classes.campamento.Campamento;
import es.uco.pw.data.RepositorioAsistentes;
import es.uco.pw.data.RepositorioCampamentos;
import es.uco.pw.gestores.GestorAsistentes;
import es.uco.pw.gestores.GestorCampamentos;

public class MainPrincipal {

	public static void main(String[] args) {

		System.out.println("Bienvenido UCOCampamentos");

		Scanner teclado = new Scanner(System.in);
		int opcion;

		// Crear los gestores y repositorios
		RepositorioAsistentes ra = new RepositorioAsistentes();
		ArrayList<Asistente> arrayAsistentesFichero = ra.cargarDatosFichero("db/asistentes.txt");
		GestorAsistentes gestor_asistentes = GestorAsistentes.getInstance(arrayAsistentesFichero);
		
		RepositorioCampamentos rc = new RepositorioCampamentos();
		ArrayList<Campamento> arrayCampamentosFichero = rc.cargarDatosFichero("db/campamentos.txt");
		GestorCampamentos gestor_campamentos = GestorCampamentos.getInstance(arrayCampamentosFichero);

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
					break;
				case 1:
					MainAsistentes.mostrarMenuAsistentes(teclado, gestor_asistentes);
					break;
				case 2:
					MainCampamentos.mostrarMenuCampamentos(teclado,gestor_campamentos);
					break;
				default:
					System.out.println("Opción no valida...");
			}

		} while (opcion != 0);

		teclado.close();
	}

}
