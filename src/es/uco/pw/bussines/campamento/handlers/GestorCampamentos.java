package es.uco.pw.bussines.campamento.handlers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import es.uco.pw.bussines.campamento.models.actividad.Actividad;
import es.uco.pw.bussines.campamento.models.actividad.NivelEducativo;
import es.uco.pw.bussines.campamento.models.campamento.Campamento;
import es.uco.pw.bussines.users.models.monitor.Monitor;

/**
 * El GestorCampamentos gestiona las operaciones relacionadas con los
 * campamentos.
 */

public class GestorCampamentos {

    private ArrayList<Campamento> campamentos;

    private static GestorCampamentos instance = null;

    private GestorCampamentos(ArrayList<Campamento> campamentos) {

        this.campamentos = campamentos;

    }

    /**
     * Obtiene la instancia única del GestorCampamentos.
     *
     * @param campamentos Lista de campamentos.
     * @return Instancia única del GestorCampamentos.
     */

    public static GestorCampamentos getInstance(ArrayList<Campamento> campamentos) {

        if (instance == null) {

            instance = new GestorCampamentos(campamentos);

        }

        return instance;

    }

    /**
     * Obtiene la lista de campamentos.
     *
     * @return Lista de campamentos.
     */

    public ArrayList<Campamento> getCampamentos() {

        return this.campamentos;

    }

    /**
     * Obtiene un campamento por su identificador.
     *
     * @param id Identificador del campamento.
     * @return Campamento correspondiente al identificador, o null si no existe.
     */
    public Campamento getCampamento(int id) {
        for (Campamento campamento : campamentos) {
            if (campamento.getIdentificador() == id) {
                return campamento;
            }
        }
        System.out.println("El campamento solicitado no existe...");
        return null;
    }

    /**
     * Realiza el alta de un nuevo campamento.
     *
     * @param nuevoCampamento Nuevo campamento a dar de alta.
     * @return true si el alta se realiza con éxito, false si el campamento ya
     *         existe.
     */
    public Boolean altaCampamento(Campamento nuevoCampamento) {

        if (buscarCampamento(nuevoCampamento.getIdentificador()) == false) {

            this.campamentos.add(nuevoCampamento);
            return true;

        }

        return false;

    }

    /**
     * Busca un campamento por su identificador.
     *
     * @param id Identificador del campamento.
     * @return true si el campamento existe, false si no existe.
     */
    public Boolean buscarCampamento(int id) {

        if (campamentos.size() == 0) {

            return false;

        }

        for (int i = 0; i < campamentos.size(); i++) {

            if (this.campamentos.get(i).getIdentificador() == id) {

                return true;

            }

        }

        return false;

    }

    /**
     * Busca una actividad en un campamento por su nombre.
     *
     * @param nombreActividad Nombre de la actividad.
     * @param idCampamento    Identificador del campamento.
     * @return true si la actividad existe en el campamento, false si no existe.
     */
    public Boolean buscarActividadCampamento(String nombreActividad, int idCampamento) {
        for (Campamento campamento : campamentos) {
            if (campamento.getIdentificador() == idCampamento) {
                for (Actividad actividad : campamento.getActividades()) {
                    if (actividad.getNombreActividad().equals(nombreActividad)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Busca una actividad en un campamento por objeto Actividad.
     *
     * @param actividad    Actividad a buscar.
     * @param idCampamento Identificador del campamento.
     * @return true si la actividad existe en el campamento, false si no existe.
     */
    public Boolean buscarActividadCampamento(Actividad actividad, int idCampamento) {
        for (Campamento campamento : campamentos) {
            if (campamento.getIdentificador() == idCampamento) {
                for (Actividad a : campamento.getActividades()) {
                    if (a.getNombreActividad().equals(actividad.getNombreActividad())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Obtiene un monitor por su identificador.
     *
     * @param id        Identificador del monitor.
     * @param monitores Lista de monitores.
     * @return Monitor correspondiente al identificador, o un nuevo monitor si no
     *         existe.
     */
    public static Monitor getMonitor(int id, ArrayList<Monitor> monitores) {
        for (Monitor monitor : monitores) {
            if (monitor.getIdentificador() == id) {
                return monitor;
            }
        }
        return new Monitor();
    }

    /**
     * Obtiene una actividad por su nombre.
     *
     * @param nombreActividad Nombre de la actividad.
     * @param actividades     Lista de actividades.
     * @return Actividad correspondiente al nombre, o null si no existe.
     */
    public static Actividad getActividad(String nombreActividad, ArrayList<Actividad> actividades) {
        for (Actividad actividad : actividades) {
            if (actividad.getNombreActividad().equals(nombreActividad)) {
                return actividad;
            }
        }
        System.out.println("La actividad solicitada no existe (getActividad) ...");
        return null;
    }

    /**
     * Muestra la información detallada de todos los campamentos en el sistema.
     * Imprime en la consola los detalles de cada campamento, incluyendo ID, fechas,
     * nivel educativo, participantes máximos, lista de actividades y lista de
     * monitores.
     */
    public void mostrarCampamentos() {
        for (Campamento campamento : campamentos) {

            System.out.println("ID: " + campamento.getIdentificador()
                    + ", Fecha de inicio: " + campamento.getFechaInicio()
                    + ", Fecha de fin: " + campamento.getFechaFin()
                    + ", Nivel: " + campamento.getNivel()
                    + ", Participantes maximos: " + campamento.getMax_asistentes()
                    + ", Lista de actividades: " + campamento.getActividades()
                    + ", Lista de monitores: " + campamento.getMonitoresResponsables());
            System.out.println("");

        }
    }

    /**
     * Muestra la información detallada de un campamento específico.
     * Imprime en la consola los detalles del campamento con el ID proporcionado,
     * incluyendo fechas, nivel educativo, participantes máximos, lista de
     * actividades y lista de monitores.
     *
     * @param idCampamento El ID del campamento que se desea mostrar.
     */
    public void mostrarCampamento(int idCampamento) {

        for (Campamento campamento : campamentos) {

            if (campamento.getIdentificador() == idCampamento) {

                System.out.println("ID: " + campamento.getIdentificador()
                        + ", Fecha de inicio: " + campamento.getFechaInicio()
                        + ", Fecha de fin: " + campamento.getFechaFin()
                        + ", Nivel: " + campamento.getNivel()
                        + ", Participantes maximos: " + campamento.getMax_asistentes()
                        + ", Lista de actividades: " + campamento.getActividades()
                        + ", Lista de monitores: " + campamento.getMonitoresResponsables());

            }

        }

    }

    /**
     * Asocia un monitor a una actividad en un campamento.
     *
     * @param monitor         El monitor que se desea asociar.
     * @param nombreActividad El nombre de la actividad a la que se desea asociar el
     *                        monitor.
     * @param idCampamento    El ID del campamento al que pertenece la actividad.
     * @return {@code true} si la asociación fue exitosa, {@code false} en caso
     *         contrario.
     */
    public Boolean asociarMonitorActividad(Monitor monitor, String nombreActividad, int idCampamento) {

        if (monitor.getEsEducador()) {
            System.out.println("El monitor de educacion especial no se puede asociar a una actividad...");
            return false;
        }

        Actividad actividad = getActividad(nombreActividad, getCampamento(idCampamento).getActividades());
        if (actividad == null) {
            System.out.println("La actividad no existe en el campamento indicado...");
            return false;
        }
        if (actividad.getMonitores().size() >= actividad.getNum_monitores()) {
            System.out.println("La actividad ya tiene el número máximo de monitores...");
            return false;
        }
        if (actividad.getMonitores().contains(monitor)) {
            System.out.println("El monitor ya está asociado a la actividad...");
            return false;
        }
        System.out.println("Monitor asociado con éxito.");
        actividad.getMonitores().add(monitor);
        getCampamento(idCampamento).getMonitoresResponsables().add(monitor);
        return true;

    }

    /**
     * Valida un nombre o apellidos asegurando que no contengan números ni
     * caracteres especiales.
     *
     * @param input El nombre o apellidos a validar.
     * @return El nombre o apellidos si la validación es exitosa, una cadena vacía
     *         si no lo es.
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

    /**
     * Solicita datos al usuario desde el teclado para crear un nuevo campamento.
     * Verifica y asigna los datos al objeto Campamento proporcionado.
     *
     * @param teclado         El objeto Scanner para la entrada del teclado.
     * @param nuevoCampamento El objeto Campamento que se actualizará con los datos
     *                        introducidos.
     * @return {@code true} si se introducen y asignan los datos correctamente,
     *         {@code false} en caso contrario.
     */
    public static Boolean pedirDatosTeclado(Scanner teclado, Campamento nuevoCampamento) {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        String fechaInicioTexto;
        String fechaFinTexto;
        NivelEducativo nivel = NivelEducativo.INFANTIL;
        int max_asistentes = 0;

        System.out.println("Introduzca los datos del nuevo campamento:");

        System.out.print("Fecha inicio (yyyy-mm-dd): ");
        teclado.nextLine();
        fechaInicioTexto = teclado.nextLine();

        System.out.print("Fecha fin (yyyy-mm-dd): ");
        fechaFinTexto = teclado.nextLine();

        Date fechaInicioDate = new Date();
        Date fechaFinDate = new Date();
        Date fechaActual = new Date();
        try {

            fechaInicioDate = formatoFecha.parse(fechaInicioTexto);
            fechaFinDate = formatoFecha.parse(fechaFinTexto);
            if (fechaFinDate.compareTo(fechaInicioDate) <= 0 || fechaInicioDate.before(fechaActual)
                    || fechaFinDate.before(fechaActual)) {
                System.out.println("La fecha de fin debe ser posterior a la fecha de inicio.");
                return false;
            }

        } catch (ParseException e) {
            System.out.println("Error al convertir la fecha...");
            return false;
        }

        System.out.print("Indique el nivel educativo del campamento:\n");
        System.out.println("(1) INFANTIL");
        System.out.println("(2) JUVENIL");
        System.out.println("(3) ADOLESCENTE");
        System.out.print("");
        int opcion;
        do {
            opcion = teclado.nextInt();

            switch (opcion) {
                case 1:
                    nivel = NivelEducativo.INFANTIL;
                    break;
                case 2:
                    nivel = NivelEducativo.JUVENIL;
                    break;
                case 3:
                    nivel = NivelEducativo.ADOLESCENTE;
                    break;
                default:
                    System.out.println("Opción no válida. Escriba otro número válido:");
                    break;
            }
        } while (opcion < 1 || opcion > 3);

        System.out.print("Máximo número de asistentes: ");
        max_asistentes = teclado.nextInt();
        while (max_asistentes <= 0) {
            System.out.print("Error al indicar el número máximo de asistentes...");
            return false;
        }

        nuevoCampamento.setFechaInicio(fechaInicioDate);
        nuevoCampamento.setFechaFin(fechaFinDate);
        nuevoCampamento.setNivel(nivel);
        nuevoCampamento.setMax_asistentes(max_asistentes);
        return true;
    }

    /**
     * Asocia una actividad a un campamento.
     *
     * @param actividad    La actividad que se desea asociar.
     * @param idCampamento El ID del campamento al que se desea asociar la
     *                     actividad.
     * @return {@code true} si la asociación fue exitosa, {@code false} en caso
     *         contrario.
     */
    public Boolean asociarActividadCampamento(Actividad actividad, int idCampamento) {
        Campamento campamento = getCampamento(idCampamento);

        NivelEducativo nivel = actividad.getNivel();

        if (buscarActividadCampamento(actividad, idCampamento)) {
            return false;
        }
        if (campamento.getNivel().equals(nivel) == false) {
            System.out.println("La actividad no se puede asociar al campamento.");
            return false;
        }

        campamento.getActividades().add(actividad);

        System.out.println("Actividad asociada con éxito.");
        return true;
    }

    /**
     * Busca si un monitor está asociado a un campamento.
     *
     * @param idMonitor    El ID del monitor que se desea buscar.
     * @param idCampamento El ID del campamento en el que se desea buscar al
     *                     monitor.
     * @return {@code true} si el monitor está asociado al campamento, {@code false}
     *         en caso contrario.
     */
    public Boolean buscarMonitorCampamento(int idMonitor, int idCampamento) {
        for (Campamento campamento : campamentos) {
            if (campamento.getIdentificador() == idCampamento) {
                for (Monitor monitor : campamento.getMonitoresResponsables()) {
                    if (monitor.getIdentificador() == idMonitor) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Asocia un monitor a un campamento.
     *
     * @param monitor      El monitor que se desea asociar al campamento.
     * @param idCampamento El ID del campamento al que se desea asociar el monitor.
     * @return {@code true} si la asociación fue exitosa, {@code false} en caso
     *         contrario.
     */
    public Boolean asociarMonitorCampamento(Monitor monitor, int idCampamento) {

        if (!buscarCampamento(idCampamento)) {
            System.out.printf("Error, el campamento solicitado no existe...");
            return false;
        }
        if (buscarMonitorCampamento(monitor.getIdentificador(), idCampamento)) {
            return false;
        }

        getCampamento(idCampamento).getMonitoresResponsables().add(monitor);
        return true;

    }

    /**
     * Crea una nueva actividad solicitando datos al usuario desde el teclado.
     *
     * @param teclado        El objeto Scanner para la entrada del teclado.
     * @param nuevaActividad El objeto Actividad que se actualizará con los datos
     *                       introducidos.
     * @param nivel          El nivel educativo de la actividad.
     * @return {@code true} si se introducen y asignan los datos correctamente,
     *         {@code false} en caso contrario.
     */
    public static Boolean crearActividad(Scanner teclado, Actividad nuevaActividad, NivelEducativo nivel) {

        String nombreActividad;
        String hora;
        int max_participantes = 0;
        int max_monitores = 0;

        System.out.println("Introduzca los datos de la nueva actividad:");

        System.out.print("Nombre de la actividad: ");
        teclado.nextLine();
        nombreActividad = teclado.nextLine();

        System.out.print("Introduzca el horario (Mañana o Tarde): ");
        hora = teclado.nextLine();

        System.out.print("Introduzca el número máximo de asistentes: ");
        max_participantes = teclado.nextInt();

        System.out.print("Introduzca el número máximo de monitores: ");
        max_monitores = teclado.nextInt();

        nuevaActividad.setNombreActividad(nombreActividad);
        nuevaActividad.setNivel(nivel);
        nuevaActividad.setHora(hora);
        nuevaActividad.setMax_participantes(max_participantes);
        nuevaActividad.setNum_monitores(max_monitores);
        return true;
    }

    /**
     * Borra una actividad de un campamento.
     *
     * @param idCampamento    El ID del campamento al que pertenece la actividad.
     * @param nombreActividad El nombre de la actividad que se desea borrar.
     * @return {@code true} si se borra la actividad correctamente, {@code false} en
     *         caso contrario.
     */
    public Boolean borrarActividad(int idCampamento, String nombreActividad) {
        return getCampamento(idCampamento).borrarActividad(nombreActividad);

    }

    /**
     * Crea un nuevo monitor solicitando datos al usuario desde el teclado.
     *
     * @param teclado      El objeto Scanner para la entrada del teclado.
     * @param nuevoMonitor El objeto Monitor que se actualizará con los datos
     *                     introducidos.
     * @return {@code true} si se introducen y asignan los datos correctamente,
     *         {@code false} en caso contrario.
     */
    public static Boolean crearMonitor(Scanner teclado, Monitor nuevoMonitor) {
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
        return true;

    }
}