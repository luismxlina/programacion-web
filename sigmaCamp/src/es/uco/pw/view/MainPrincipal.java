
// package es.uco.pw.view;

// import java.io.File;
// import java.io.IOException;
// import java.util.Scanner;

// import es.uco.pw.business.campamento.handler.GestorCampamentos;
// import es.uco.pw.business.inscripcion.handler.GestorInscripciones;
// import es.uco.pw.business.users.handler.GestorAsistentes;
// import es.uco.pw.data.RepositorioAsistentes;
// import es.uco.pw.data.RepositorioCampamentos;
// import es.uco.pw.data.RepositorioInscripciones;

// /**
//  * Clase principal que contiene el punto de entrada del programa Campamentos de
//  * programación.
//  */
// public class MainPrincipal {

// 	/**
// 	 * Comprueba la existencia de los ficheros necesarios y los crea si no existen.
// 	 */
// 	public static void comprobarFicheros() {
// 		try {
// 			comprobarYCrearFichero("db/asistentes.txt");
// 			comprobarYCrearFichero("db/campamentos.txt");
// 			comprobarYCrearFichero("db/inscripciones.txt");
// 		} catch (IOException e) {
// 			System.out.println("Error al crear los ficheros");
// 		}
// 	}

// 	/**
// 	 * Comprueba y crea un fichero en la ruta especificada si no existe.
// 	 *
// 	 * @param rutaFichero Ruta del fichero a comprobar/crear.
// 	 * @throws IOException Si hay un error al intentar crear el fichero.
// 	 */
// 	private static void comprobarYCrearFichero(String rutaFichero) throws IOException {
// 		File fichero = new File(rutaFichero);

// 		if (!fichero.exists()) {
// 			if (fichero.createNewFile()) {
// 				System.out.println("Fichero " + rutaFichero + " creado correctamente.");
// 			} else {
// 				System.out.println("Error al intentar crear el fichero " + rutaFichero);
// 			}
// 		}
// 	}

// 	/**
// 	 * Método principal del programa que inicia la ejecución del sistema Campamentos
// 	 * de programación.
// 	 * 
// 	 * @param args Argumentos de la línea de comandos (no se utilizan).
// 	 * @throws Exception
// 	 */
// 	public static void main(String[] args) throws Exception {
// 		comprobarFicheros();
// 		System.out.println("Bienvenido a Campamentos de programanción.");

// 		Scanner teclado = new Scanner(System.in);
// 		int opcion;

// 		// Crear los gestores y repositorios
// 		RepositorioAsistentes ra = new RepositorioAsistentes();
// 		GestorAsistentes gestor_asistentes = GestorAsistentes.getInstance();

// 		RepositorioCampamentos rc = new RepositorioCampamentos();
// 		GestorCampamentos gestor_campamentos = GestorCampamentos.getInstance();

// 		RepositorioInscripciones ri = new RepositorioInscripciones();
// 		GestorInscripciones gestor_inscripciones = GestorInscripciones.getInstance();

// 		System.out.println("·-----------------------------------·");
// 		System.out.println("|           MENÚ PRINCIPAL          |");

// 		do {
// 			System.out.println("·-----------------------------------·");
// 			System.out.println("|     (1) Gestor de asistentes      |");
// 			System.out.println("|     (2) Gestor de campamentos     |");
// 			System.out.println("|     (3) Gestor de inscripciones   |");
// 			System.out.println("|     (0) SALIR                     |");
// 			System.out.println("·-----------------------------------·");

// 			opcion = teclado.nextInt();

// 			switch (opcion) {
// 				case 0:
// 					System.out.println("Saliendo del programa...");
// 					ra.guardarEnFichero(gestor_asistentes.getAsistentes(), "db/asistentes.txt");
// 					rc.guardarEnFichero(gestor_campamentos.getCampamentos(), "db/campamentos.txt");
// 					ri.guardarEnFichero(gestor_inscripciones.getInscripciones(), "db/inscripciones.txt");
// 					break;
// 				case 1:
// 					MainAsistentes.mostrarMenuAsistentes(teclado, gestor_asistentes);
// 					break;
// 				case 2:
// 					MainCampamentos.mostrarMenuCampamentos(teclado, gestor_campamentos);
// 					break;
// 				case 3:

// 					MainInscripciones.mostrarMenuInscripciones(teclado, gestor_inscripciones);
// 					break;
// 				default:
// 					System.out.println("Opción no valida...");
// 			}

// 		} while (opcion != 0);

// 		teclado.close();
// 	}

// }