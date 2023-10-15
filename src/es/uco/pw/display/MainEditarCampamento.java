package es.uco.pw.display;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

import es.uco.pw.classes.actividad.Actividad;
import es.uco.pw.classes.actividad.NivelEducativo;
import es.uco.pw.classes.campamento.Campamento;
import es.uco.pw.classes.monitor.Monitor;
import es.uco.pw.gestores.GestorCampamentos;

public class MainEditarCampamento {

    private static HashSet<Integer> idSet = new HashSet<>();

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
                    System.out.print("Dispone del ID del monitor que desea asociar? (s/N): ");
                    String respuesta = teclado.nextLine();

                    if (respuesta.equalsIgnoreCase("s")) { // Caso que dispone de ID
                        System.out.println("Desea mostrar los monitores disponibles? (s/N): ");
                        respuesta = teclado.nextLine();
                        // Caso para listar los monitores disponibles
                        if (respuesta.equalsIgnoreCase("s")) {
                            for (Monitor m : campamento.getMonitoresResponsables()) {
                                System.out.println(m.toString());
                                // Existe el monitor
                            }
                            //
                        } else {
                            System.out.println("Introduzca el ID del monitor que desea asociar");
                            teclado.nextInt();
                            teclado.nextLine();
                            int idMonitor = teclado.nextInt();
                            teclado.nextLine();
                            monitor = campamento.getMonitor(idMonitor);
                        }

                    } else { // Caso que no dispone de ID
                        monitor = new Monitor(generarIDUnico());
                        GestorCampamentos.crearMonitor(teclado, monitor);
                    }

                    System.out.println("Introduzca el nombre de la actividad a la que desea asociar el monitor");
                    teclado.nextLine();
                    nombreActividad = teclado.nextLine();

                    gestor_campamentos.asociarMonitorActividad(monitor, nombreActividad, campamento.getIdentificador());

                    // Dispone del ID del monitor que quiere asociar?
                    // Sí -> Lo buscamos con campamento.getIdMonitor(idMonitor) y lo asociamos a una
                    // actividad existente;
                    // No -> Lo creamos y lo insertamos en la actividad que sea. (
                    // gestor_campamento.asociarMonitorActividad(monitor, nombreActividad) )
                    break;
                default:
                    System.out.println("Opción no válida");
            }

        } while (opcion != 0);
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