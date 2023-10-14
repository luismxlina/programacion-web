package es.uco.pw.display;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

import es.uco.pw.classes.actividad.Actividad;
import es.uco.pw.classes.campamento.Campamento;
import es.uco.pw.classes.monitor.Monitor;
import es.uco.pw.gestores.GestorCampamentos;
import es.uco.pw.display.MainCampamentos;

public class MainEditarCampamento {

    private static HashSet<Integer> idSet = new HashSet<>();

    public static void mostrarMenuEditarCampamento(Scanner teclado, GestorCampamentos gestor_campamentos) {
        int opcion;

        System.out.println("·-----------------------------------·");
        System.out.println("|       SUB-MENÚ de Campamentos     |");
        System.out.println("·-----------------------------------·");

        do {
            System.out.println("");
            System.out.println("(1) Crear actividad");
            System.out.println("(2) Borrar actividad");
            System.out.println("(3) Crear monitor");
            System.out.println("(4) Asociar monitor a una actividad");
            System.out.println("(5) Asociar monitor a un campamento");
            System.out.println("(0) Volver al menú principal");
            System.out.println("");

            opcion = teclado.nextInt();

            switch (opcion) {
                case 0:
                    System.out.println("Volviendo al menú principal de campamentos...");
                    break;
                case 1:
                    Actividad actividad = new Actividad();
                    GestorCampamentos.crearActividad(teclado, actividad);
                    break;
                case 2:
                    System.out.println("Introduzca nombre de la actividad a borrar");
                    teclado.nextLine();
                    String nombreActividad = teclado.nextLine();
                    System.out.println(
                            "Introduzca el id del campamento donde se encuentra la actividad que quiere borrar");
                    teclado.nextInt();
                    int idCampamento = teclado.nextInt();
                    gestor_campamentos.borrarActividad(idCampamento, nombreActividad);
                    break;
                case 3:
                    Monitor monitor = new Monitor(generarIDUnico());
                    GestorCampamentos.crearMonitor(teclado, monitor);
                    break;
                case 4:
                    System.out.println("Introduzca el ID del campamento donde desea asociar monitor");
                    teclado.nextInt();
                    idCampamento = teclado.nextInt();
                    Campamento campamento = gestor_campamentos.getCampamento(idCampamento);
                    monitor = new Monitor();
                    System.out.print("Dispone del ID del monitor que desea asociar? (s/N): ");
                    String respuesta = teclado.nextLine();
                    // Caso que dispone de ID
                    if (respuesta.equalsIgnoreCase("s")) {
                        System.out.println("Desea mostrar los monitores disponibles? (s/N): ");
                        respuesta = teclado.nextLine();
                        // Caso para listar los monitores disponibles
                        if (respuesta.equalsIgnoreCase("s")) {
                            for (Monitor m : campamento.getMonitoresResponsables()) {
                                System.out.println(m.toString());
                                //Existe el monitor
                                
                            }
                        //
                        } else {
                            System.out.println("Introduzca el ID del monitor que desea asociar");
                            teclado.nextInt();
                            int idMonitor = teclado.nextInt();
                            monitor = campamento.getMonitor(idMonitor);
                        }
                    // Caso que no dispone de ID
                    } else {
                        monitor = new Monitor(generarIDUnico());
                        GestorCampamentos.crearMonitor(teclado, monitor);
                    }

                    System.out.println("Introduzca el nombre de la actividad a la que desea asociar el monitor");
                    teclado.nextLine();
                    nombreActividad = teclado.nextLine();

                    gestor_campamentos.asociarMonitorActividad(monitor, nombreActividad, campamento);

                    // Dispone del ID del monitor que quiere asociar?
                    // Sí -> Lo buscamos con campamento.getIdMonitor(idMonitor) y lo asociamos a una
                    // actividad existente;
                    // No -> Lo creamos y lo insertamos en la actividad que sea. (
                    // gestor_campamento.asociarMonitorActividad(monitor, nombreActividad) )

                case 5:

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