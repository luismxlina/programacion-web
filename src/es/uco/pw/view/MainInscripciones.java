package es.uco.pw.view;

import java.util.Scanner;

import es.uco.pw.bussines.inscripcion.handlers.GestorInscripciones;

/**
 * La clase MainInscripciones proporciona un menú de opciones relacionadas con
 * la gestión de inscripciones.
 */

public class MainInscripciones {

    /**
     * Muestra un menú de opciones para la gestión de inscripciones.
     *
     * @param teclado              Scanner para la entrada de usuario.
     * @param gestor_inscripciones El gestor de inscripciones utilizado para
     *                             realizar operaciones.
     */

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
            System.out.println("(4) Eliminar inscripción");
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
                    System.out.println("Introduzca id del participante a buscar");
                    teclado.nextLine();
                    int idParticipante = teclado.nextInt();
                    System.out.println("Introduzca id del campamento a buscar");
                    teclado.nextLine();
                    int idCampamento = teclado.nextInt();
                    gestor_inscripciones.mostrarInscripcion(idParticipante, idCampamento);
                    break;
                case 3:
                    // Método para añadir una nueva inscripción
                    System.out.println("Introduzca id del participante");
                    teclado.nextLine();
                    idParticipante = teclado.nextInt();
                    System.out.println("Introduzca id del campamento");
                    teclado.nextLine();
                    idCampamento = teclado.nextInt();
                    boolean temprana = false;
                    boolean necesidadesEspeciales = false;
                    gestor_inscripciones.addInscripcion(idParticipante, idCampamento, temprana, necesidadesEspeciales);
                    break;
              
                case 4:
                    System.out.println("Introduzca id del participante a eliminar");
                    teclado.nextLine();
                    idParticipante = teclado.nextInt();
                    System.out.println("Introduzca id del campamento a eliminar");
                    teclado.nextLine();
                    idCampamento = teclado.nextInt();
                    gestor_inscripciones.deleteInscripcion(idParticipante, idCampamento);

                    break;
                default:
                    System.out.println("Opción no válida");
            }

        } while (opcion != 0);
    }
}
