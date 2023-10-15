
package es.uco.pw.display;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import es.uco.pw.classes.actividad.Actividad;
import es.uco.pw.classes.actividad.NivelEducativo;
import es.uco.pw.classes.asistente.Asistente;
import es.uco.pw.classes.campamento.Campamento;
import es.uco.pw.classes.inscripcion.Inscripcion;
import es.uco.pw.classes.inscripcion.InscripcionCampamento;
import es.uco.pw.classes.inscripcion.InscripcionCompleta;
import es.uco.pw.data.RepositorioAsistentes;
import es.uco.pw.data.RepositorioCampamentos;
import es.uco.pw.data.RepositoryInscripciones;
import es.uco.pw.gestores.GestorAsistentes;
import es.uco.pw.gestores.GestorCampamentos;
import es.uco.pw.gestores.GestorInscripciones;
import es.uco.pw.classes.monitor.Monitor;

public class MainPrincipal {

	public static void main(String[] args) {

		System.out.println("Bienvenido UCOCampamentos");
		// SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		// Date fechaEspecifica = null;
		// try {
		// 	fechaEspecifica = formato.parse("15/10/2023");
		// 	System.out.println(fechaEspecifica);
		// } catch (Exception e) {
		// 	e.printStackTrace();
		// }
		
		// Campamento campamento = new Campamento(1, fechaEspecifica, fechaEspecifica, NivelEducativo.ADOLESCENTE, 4);
		// Actividad actividad = new Actividad("Prueba", NivelEducativo.ADOLESCENTE, "2023-01-12", 20, 3);
		// Monitor director = new Monitor(1, "MontiorPrueba", "Centro", false);
		// ArrayList<Monitor> monitores = new ArrayList<Monitor>();
		// monitores.add(director);
		
		// GestorCampamentos gestor = GestorCampamentos.getInstance(new ArrayList<Campamento>());
		// gestor.altaCampamento(campamento);
		// gestor.asociarActividadCampamento(actividad, 1);
		// gestor.asociarMonitorActividad(director, actividad.getNombreActividad(), 1);
		// gestor.mostrarCampamento(1);
		// gestor.borrarActividad(1, actividad.getNombreActividad());
		// gestor.mostrarCampamento(1);

		// int a = 0;
		// if (a == 0)
		// 	return;

		Scanner teclado = new Scanner(System.in);
		int opcion;

		// Crear los gestores y repositorios
		RepositorioAsistentes ra = new RepositorioAsistentes();
		ArrayList<Asistente> arrayAsistentesFichero = ra.cargarDatosFichero("db/asistentes.txt");
		GestorAsistentes gestor_asistentes = GestorAsistentes.getInstance(arrayAsistentesFichero);

		RepositorioCampamentos rc = new RepositorioCampamentos();
		ArrayList<Campamento> arrayCampamentosFichero = rc.cargarDatosFichero("db/campamentos.txt");
		GestorCampamentos gestor_campamentos = GestorCampamentos.getInstance(arrayCampamentosFichero);

		RepositoryInscripciones ri = new RepositoryInscripciones();
		ArrayList<InscripcionCampamento> arrayInscripcionesFichero = ri.cargarDatosFichero("db/inscripciones.txt");
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
					break;
				case 1:
					MainAsistentes.mostrarMenuAsistentes(teclado, gestor_asistentes);
					break;
				case 2:
					MainCampamentos.mostrarMenuCampamentos(teclado, gestor_campamentos);
					break;
				default:
					System.out.println("Opción no valida...");
			}

		} while (opcion != 0);

		teclado.close();
	}

}
