package es.uco.pw.view;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import es.uco.pw.business.campamento.handler.GestorCampamentos;
import es.uco.pw.business.campamento.models.actividad.Actividad;
import es.uco.pw.business.campamento.models.actividad.Horario;
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
     * @throws Exception
     */

    public static void mostrarMenuEditarCampamento(Scanner teclado, GestorCampamentos gestor_campamentos,
            int idCampamento) throws Exception {

        ArrayList<Integer> ids = GestorCampamentos.getInstance().getAllIdsCampamentos();
        for (int i = 0; i < ids.size(); i++) {
            idSet.add(ids.get(i));
        }

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
            System.out.println("(2) Borrar actividad de un campamento");
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
                    crearActividad(teclado, actividad, nivelEducativo);
                    gestor_campamentos.addActividad(actividad);
                    gestor_campamentos.asociarActividadCampamento(actividad.getNombreActividad(), idCampamento);
                    break;
                case 2:
                    System.out.println("Introduzca nombre de la actividad a borrar");
                    teclado.nextLine();
                    nombreActividad = teclado.nextLine();
                    gestor_campamentos.borrarActividadCampamento(nombreActividad, idCampamento);
                    break;
                case 3:
                    System.out.println("A qué actividad desea asociar el monitor?");
                    nombreActividad = teclado.nextLine(); // Leer la entrada del usuario y asignarla a nombreActividad
                    Monitor monitor = new Monitor(generarIDUnico());
                    monitor = crearMonitor(teclado, monitor);
                    gestor_campamentos.addMonitor(monitor);
                    gestor_campamentos.asociarMonitorActividad(monitor.getIdentificador(), nombreActividad,
                            campamento.getIdentificador());
                    break;
                case 4:
                    campamento = gestor_campamentos.getCampamento(idCampamento);
                    monitor = new Monitor();
                    System.out.print("Dispone del ID del monitor que desea asociar? (si = 1/No = 0): ");
                    teclado.nextLine();
                    int hasMonitor = teclado.nextInt();
                    teclado.nextLine();

                    if (hasMonitor == 1) {
                        System.out.println("Introduzca el ID del monitor que desea asociar");
                        int idMonitor = teclado.nextInt();
                        teclado.nextLine();
                        monitor = gestor_campamentos.getMonitor(idMonitor);

                    } else {
                        monitor = new Monitor(generarIDUnico());
                        monitor = crearMonitor(teclado, monitor);
                    }

                    System.out.println("Introduzca el nombre de la actividad a la que desea asociar el monitor");
                    nombreActividad = teclado.nextLine();

                    gestor_campamentos.asociarMonitorActividad(monitor.getIdentificador(), nombreActividad,
                            idCampamento);
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

    /**
     * Crea un nuevo objeto Monitor con los datos ingresados por el usuario.
     *
     * @param teclado      Scanner para la entrada de datos desde el teclado.
     * @param nuevoMonitor Objeto Monitor que se actualizará con los datos
     *                     ingresados.
     * @return El objeto Monitor actualizado con los datos ingresados.
     */
    public static Monitor crearMonitor(Scanner teclado, Monitor nuevoMonitor) {
        String nombre = "";
        String apellidos = "";
        int atencionInt;

        teclado.nextLine();

        System.out.println("Introduzca los datos del nuevo monitor:");
        while (nombre.isEmpty()) {
            System.out.print("Nombre: ");
            // teclado.nextLine();
            nombre = teclado.nextLine();
            nombre = validarNombre(nombre);
        }
        while (apellidos.isEmpty()) {

            System.out.print("Apellidos: ");
            apellidos = teclado.nextLine();
            apellidos = validarNombre(apellidos);
        }

        System.out.print("¿Es educador de atencion especial? (Si - 1 / No - 0): ");
        atencionInt = teclado.nextInt();

        Boolean esEducador = false;
        if (atencionInt == 1) {
            esEducador = true;
        }
        System.out.println(nombre + " " + apellidos + " " + esEducador);
        nuevoMonitor.setNombre(nombre);
        nuevoMonitor.setApellidos(apellidos);
        nuevoMonitor.setEsEducador(esEducador);
        return nuevoMonitor;
    }

    /**
     * Crea una nueva actividad.
     * 
     * @param teclado        el Scanner para la entrada de datos
     * @param nuevaActividad la nueva actividad a crear
     * @param nivel          el nivel educativo de la actividad
     * @return la actividad creada
     */
    public static Actividad crearActividad(Scanner teclado, Actividad nuevaActividad, NivelEducativo nivel) {

        String nombreActividad;
        String hora;
        int max_participantes = 0;
        int max_monitores = 0;
        Actividad Actividad = new Actividad();

        System.out.println("Introduzca los datos de la nueva actividad:");

        System.out.print("Nombre de la actividad: ");
        teclado.nextLine();
        nombreActividad = teclado.nextLine();

        System.out.print("Introduzca si la actividad es de mañanas o tardes (En el formato: \"Mañana\"/\"Tarde\"): ");
        hora = teclado.nextLine();
        // Parsea el string howa a LocalTime
        Horario horario = hora.equals("Mañana") ? Horario.MAÑANA : Horario.TARDE;

        System.out.print("Introduzca el número máximo de asistentes: ");
        max_participantes = teclado.nextInt();

        System.out.print("Introduzca el número máximo de monitores: ");
        max_monitores = teclado.nextInt();

        nuevaActividad.setNombreActividad(nombreActividad);
        nuevaActividad.setNivel(nivel);
        nuevaActividad.setHora(horario);
        nuevaActividad.setMaxParticipantes(max_participantes);
        nuevaActividad.setNumMonitores(max_monitores);
        return Actividad;
    }

    /**
     * Valida que el nombre no contenga números ni caracteres especiales.
     *
     * @param input Nombre o apellidos a validar.
     * @return El nombre o apellidos si la validación es exitosa, o una cadena vacía
     *         si hay un error.
     */
    private static String validarNombre(String input) {
        // Expresión regular para verificar que el nombre no contiene números
        String regex = "^[A-Za-záéíóúÁÉÍÓÚñÑüÜ\\s]+$";

        if (input.matches(regex)) {
            return input;
        } else {
            System.out.println("El nombre o apellidos no pueden contener números ni caracteres especiales.");
            return "";
        }
    }

}