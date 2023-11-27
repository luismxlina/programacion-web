package es.uco.pw.view;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.Date;

import es.uco.pw.business.campamento.handler.GestorCampamentos;
import es.uco.pw.business.inscripcion.handler.GestorInscripciones;
import es.uco.pw.business.inscripcion.models.inscripcion.Inscripcion;
import es.uco.pw.business.inscripcion.models.inscripcion.TipoInscripcion;
import es.uco.pw.business.inscripcion.models.inscripcion.factory.InscripcionCreator;
import es.uco.pw.business.inscripcion.models.inscripcion.factory.InscripcionTardia;
import es.uco.pw.business.inscripcion.models.inscripcion.factory.InscripcionTemprana;
import es.uco.pw.business.users.handler.GestorAsistentes;

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
        ArrayList<Inscripcion> inscripciones = new ArrayList<Inscripcion>();
        Inscripcion inscripcion;

        System.out.println("·-----------------------------------·");
        System.out.println("|         MENÚ de Inscripciones      |");
        System.out.println("·-----------------------------------·");

        do {
            System.out.println("|    (1) Mostrar inscripciones      |");
            System.out.println("|    (2) Mostrar inscripción        |");
            System.out.println("|    (3) Añadir inscripción         |");
            System.out.println("|    (4) Eliminar inscripción       |");
            System.out.println("|    (0) Volver al menú principal   |");
            System.out.println("·-----------------------------------·");

            opcion = teclado.nextInt();

            switch (opcion) {
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                case 1:
                    // Método para mostrar todas las inscripciones
                    System.out.println("Introduzca id del campamento");
                    teclado.nextLine();
                    Integer idCampamento = teclado.nextInt();
                    inscripciones = gestor_inscripciones.getInscripcionesCampamento(idCampamento);
                    if (inscripciones.isEmpty()) {
                        System.out.println("No hay inscripciones en el campamento con id " + idCampamento);
                    }
                    mostrarInscripciones(inscripciones);
                    break;
                case 2:
                    System.out.println("Introduzca id del participante a buscar");
                    teclado.nextLine();
                    int idParticipante = teclado.nextInt();
                    System.out.println("Introduzca id del campamento a buscar");
                    teclado.nextLine();
                    int idcampamento = teclado.nextInt();
                    inscripcion = gestor_inscripciones.getInscripcion(idParticipante, idcampamento);
                    inscripciones = new ArrayList<Inscripcion>();
                    inscripciones.add(inscripcion);
                    mostrarInscripcion(inscripciones, idParticipante, idcampamento);
                    break;
                case 3:
                    // Método para añadir una nueva inscripción
                    System.out.println("Introduzca id del participante");
                    teclado.nextLine();
                    idParticipante = teclado.nextInt();
                    System.out.println("Introduzca id del campamento");
                    teclado.nextLine();
                    idCampamento = teclado.nextInt();
                    System.out.println("Introduzca tipo de inscripcion (Parcial/Completa)");
                    teclado.nextLine();
                    String tipoInscripcion = teclado.nextLine();
                    boolean temprana = getEsTemprana(new Date(),
                            GestorCampamentos.getInstance().getCampamento(idCampamento).getFechaInicio());
                    InscripcionCreator creator;

                    if (temprana) {
                        creator = new InscripcionTemprana();
                    } else {
                        creator = new InscripcionTardia();
                    }

                    if (tipoInscripcion.equals("Completa")) {
                        inscripcion = creator.registrarInscripcionCompleta(idParticipante, idCampamento, new Date());
                        inscripcion.setTipoInscripcion(TipoInscripcion.COMPLETA);
                    } else {
                        inscripcion = creator.registrarInscripcionParcial(idParticipante, idCampamento, new Date());
                        inscripcion.setTipoInscripcion(TipoInscripcion.PARCIAL);
                    }
                    gestor_inscripciones.addInscripcion(inscripcion);
                    break;

                case 4:
                    System.out.println("Introduzca id del participante a eliminar");
                    teclado.nextLine();
                    idParticipante = teclado.nextInt();
                    System.out.println("Introduzca id del campamento a eliminar");
                    teclado.nextLine();
                    idCampamento = teclado.nextInt();
                    if (gestor_inscripciones.deleteInscripcion(idParticipante, idCampamento)) {
                        System.out.println("Inscripción del usuario "
                                + GestorAsistentes.getInstance().getAsistente(idParticipante)
                                + " en el campamento " + GestorCampamentos.getInstance().getCampamento(idCampamento)
                                + " eliminada con éxito.");
                    } else {
                        System.out.println(
                                "No se encontró una inscripción con ID " + idParticipante + " en el campamento con ID "
                                        + idCampamento);
                    }

                    break;
                default:
                    System.out.println("Opción no válida");
            }

        } while (opcion != 0);
    }

    /**
     * Muestra la información de las inscripciones.
     *
     * @param inscripciones Lista de inscripciones a mostrar.
     */
    public static void mostrarInscripciones(ArrayList<Inscripcion> inscripciones) {
        for (int i = 0; i < inscripciones.size(); i++) {
            System.out.println("Id de asistente: " + inscripciones.get(i).getId_Participante()
                    + ", Id de campamento: " + inscripciones.get(i).getId_Campamento()
                    + ", Fecha de inscripcion: " + inscripciones.get(i).getFechaInscripcion()
                    + ", Precio: " + inscripciones.get(i).getPrecio()
                    + ", Tipo de inscripcion: " + inscripciones.get(i).getTipoInscripcion());
            System.out.println("");

        }
    }

    /**
     * Muestra la información de una inscripción específica.
     *
     * @param inscripciones Lista de inscripciones donde buscar.
     * @param idAsistente   ID del asistente de la inscripción.
     * @param idCampamento  ID del campamento de la inscripción.
     */
    public static void mostrarInscripcion(ArrayList<Inscripcion> inscripciones, int idAsistente, int idCampamento) {
        for (int i = 0; i < inscripciones.size(); i++) {
            if (inscripciones.get(i).getId_Participante() == idAsistente
                    && inscripciones.get(i).getId_Campamento() == idCampamento) {
                System.out.println("Id de asistente: " + inscripciones.get(i).getId_Participante()
                        + ", Id de campamento: " + inscripciones.get(i).getId_Campamento()
                        + ", Fecha de inscripcion: " + inscripciones.get(i).getFechaInscripcion()
                        + ", Precio: " + inscripciones.get(i).getPrecio()
                        + ", Tipo de inscripcion: " + inscripciones.get(i).getTipoInscripcion());
                System.out.println("");
            }
        }

    }

    public static Boolean getEsTemprana(Date fechaInscripcion, Date fechaInicioCampamento) {
        long diffInMillies = Math.abs(fechaInicioCampamento.getTime() - fechaInscripcion.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        return diff > 15;
    }
}