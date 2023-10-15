package es.uco.pw.display;

import java.util.Scanner;
import es.uco.pw.gestores.GestorInscripciones;

public class MainInscripciones {

    public static void mostrarMenuInscripciones(Scanner teclado, GestorInscripciones gestor_inscripciones) {

        int opcion;

        System.out.println("·-----------------------------------·");
        System.out.println("|         MENÚ de Inscripciones      |");
        System.out.println("·-----------------------------------·");

        do {
            System.out.println("");
            System.out.println("(1) Mostrar inscripciones");
            System.out.println("(2) Mostrar inscripción");
            System.out.println("(3) Añadir inscripción");
            System.out.println("(4) Editar inscripción");
            System.out.println("(5) Eliminar inscripción");
            System.out.println("(0) Volver al menú principal");
            System.out.println("");

            opcion = teclado.nextInt();

            switch (opcion) {
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                case 1:
                    // Método para mostrar todas las inscripciones
                    gestor_inscripciones.mostrarInscripciones();
                    break;
                case 2:
                    System.out.println("Introduzca id de la inscripción a buscar");
                    teclado.nextLine();
                    int idInscripcion = teclado.nextInt();
                    gestor_inscripciones.mostrarInscripcion(idInscripcion);
                    break;
                case 3:
                    // Método para añadir una nueva inscripción
                    gestor_inscripciones.añadirInscripcion();
                    break;
                case 4:
                    System.out.println("Introduzca id de la inscripción a editar");
                    teclado.nextLine();
                    idInscripcion = teclado.nextInt();
                    gestor_inscripciones.editarInscripcion(idInscripcion);
                    break;
                case 5:
                    System.out.println("Introduzca id de la inscripción a eliminar");
                    teclado.nextLine();
                    idInscripcion = teclado.nextInt();
                    gestor_inscripciones.eliminarInscripcion(idInscripcion);
                    break;
                default:
                    System.out.println("Opción no válida");
            }

        } while (opcion != 0);
    }
}
