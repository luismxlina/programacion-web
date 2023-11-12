package es.uco.pw.view;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

import es.uco.pw.business.campamento.handler.GestorCampamentos;
import es.uco.pw.business.campamento.models.actividad.Actividad;
import es.uco.pw.business.campamento.models.actividad.NivelEducativo;
import es.uco.pw.business.campamento.models.campamento.Campamento;
import es.uco.pw.business.campamento.models.monitor.Monitor;

/**
 * La clase MainEditarCampamento proporciona un submenú de opciones para editar
 * un campamento específico.
 * Se utiliza para interactuar con el usuario a través de la consola y realizar
 * operaciones de edición en el sistema.
 */

public class MainEditarCampamento {

    private static HashSet<Integer> idSet = new HashSet<>();

    /**
     * Muestra un submenú de edición para un campamento específico.
     *
     * @param teclado            Scanner para la entrada del usuario.
     * @param gestor_campamentos Objeto GestorCampamentos para gestionar las
     *                           operaciones con campamentos.
     * @param idCampamento       Identificador del campamento que se desea editar.
     */

    public static void mostrarMenuEditarCampamento(Scanner teclado, GestorCampamentos gestor_campamentos,
            int idCampamento) {
        int opcion;
        Campamento campamento = gestor_campamentos.getCampamento(idCampamento);
        String nombreActividad;
        NivelEducativo nivelEducativo = gestor_campamentos.getCampamento(idCampamento).getNivel();

        System.out.println("·-----------------------------------·");
        System.out.println("|       SUB-MENÚ de Campamentos     |");
        System.out.println("·-----------------------------------·");

        do {
            System.out.println("");
            System.out.println("(1) Crear actividad");
            System.out.println("(2) Borrar actividad");
            System.out.println("(3) Crear monitor");
            System.out.println("(4) Asociar monitor a una actividad");
            System.out.println("(0) Volver al menú principal");
            System.out.println("");

            opcion = teclado.nextInt();

            switch (opcion) {
                case 0:
                    System.out.println("Volviendo al menú principal de campamentos...");
                    break;
                case 1:
                    Actividad actividad = new Actividad();
                    GestorCampamentos.crearActividad(teclado, actividad, nivelEducativo);
                    gestor_campamentos.asociarActividadCampamento(actividad, idCampamento);
                    break;
                case 2:
                    System.out.println("Introduzca nombre de la actividad a borrar");
                    teclado.nextLine();
                    nombreActividad = teclado.nextLine();
                    gestor_campamentos.borrarActividad(idCampamento, nombreActividad);
                    break;
                case 3:
                    System.out.println("A qué actividad desea asociar el monitor?");
                    nombreActividad = teclado.nextLine(); // Leer la entrada del usuario y asignarla a nombreActividad
                    Monitor monitor = new Monitor(generarIDUnico());
                    GestorCampamentos.crearMonitor(teclado, monitor);
                    monitor.toString();
                    campamento = gestor_campamentos.getCampamento(idCampamento);
                    gestor_campamentos.asociarMonitorActividad(monitor, nombreActividad, campamento.getIdentificador());

                    break;
                case 4:
                    campamento = gestor_campamentos.getCampamento(idCampamento);
                    monitor = new Monitor();
                    System.out.print("Dispone del ID del monitor que desea asociar? (si = 1/No = 0): ");
                    int hasMonitor = teclado.nextInt();
                    teclado.nextLine();

                    if (hasMonitor == 1) { 
                        for (Monitor m : campamento.getMonitoresResponsables()) {
                            System.out.println(m.toString());
                        }
                        System.out.println("Introduzca el ID del monitor que desea asociar");
                        teclado.nextInt();
                        teclado.nextLine();
                        int idMonitor = teclado.nextInt();
                        teclado.nextLine();
                        monitor = campamento.getMonitor(idMonitor);

                    } else {
                        monitor = new Monitor(generarIDUnico());
                        GestorCampamentos.crearMonitor(teclado, monitor);
                    }

                    System.out.println("Introduzca el nombre de la actividad a la que desea asociar el monitor");
                    teclado.nextLine();
                    nombreActividad = teclado.nextLine();

                    gestor_campamentos.asociarMonitorActividad(monitor, nombreActividad, campamento.getIdentificador());
                    break;
                default:
                    System.out.println("Opción no válida");
            }

        } while (opcion != 0);
    }

    /**
     * Genera un ID único para un monitor utilizando un conjunto HashSet para evitar
     * duplicados.
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