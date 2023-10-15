package es.uco.pw.gestores;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import es.uco.pw.classes.actividad.Actividad;
import es.uco.pw.classes.actividad.NivelEducativo;
import es.uco.pw.classes.asistente.Asistente;
import es.uco.pw.classes.campamento.Campamento;
import es.uco.pw.classes.monitor.Monitor;

public class GestorCampamentos {

    private ArrayList<Campamento> campamentos;

    private static GestorCampamentos instance = null;

    private GestorCampamentos(ArrayList<Campamento> campamentos) {

        this.campamentos = campamentos;

    }

    public static GestorCampamentos getInstance(ArrayList<Campamento> campamentos) {

        if (instance == null) {

            instance = new GestorCampamentos(campamentos);

        }

        return instance;

    }

    public ArrayList<Campamento> getCampamentos() {

        return this.campamentos;

    }

    public Campamento getCampamento(int id) {
        for (Campamento campamento : campamentos) {
            if (campamento.getIdentificador() == id) {
                return campamento;
            }
        }
        System.out.println("El campamento solicitado no existe...");
        return null;
    }

    public Boolean altaCampamento(Campamento nuevoCampamento) {

        if (buscarCampamento(nuevoCampamento.getIdentificador()) == false) {

            this.campamentos.add(nuevoCampamento);
            return true;

        }

        return false;

    }

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

    public static Monitor getMonitor(int id, ArrayList<Monitor> monitores) {
        for (Monitor monitor : monitores) {
            if (monitor.getIdentificador() == id) {
                return monitor;
            }
        }
        return new Monitor();
    }

    public static Actividad getActividad(String nombreActividad, ArrayList<Actividad> actividades) {
        for (Actividad actividad : actividades) {
            if (actividad.getNombreActividad().equals(nombreActividad)) {
                return actividad;
            }
        }
        return new Actividad();
    }

    // public Date obtenerFecha(int newIdCampamento) {

    // for (Campamento campamentosaux : campamentos) {

    // if (campamentosaux.getIdCampamento() == newIdCampamento) {

    // return campamentosaux.getFechaInicio();

    // }

    // }

    // return new Date();

    // }

    // public double calcularPrecioExtra(int newIdCampamento) {

    // double extra = 0.0;
    // int cont = 0;

    // for (Campamento campamentosAux : campamentos) {

    // if (campamentosAux.getIdCampamento() == newIdCampamento)

    // for (Actividad actividadesAux : campamentosAux.getActividades()) {

    // cont++;

    // }

    // }

    // extra = 20.0 * cont;

    // return extra;
    // }

    // public ArrayList<Campamento> campamentosDisponibles() {

    // ArrayList<Campamento> array = new ArrayList<Campamento>();
    // Date fechaActual = new Date();

    // for (Campamento campamentosAux : campamentos) {

    // long tiempo1 = fechaActual.getTime();
    // long tiempo2 = campamentosAux.getFechaInicio().getTime();

    // // Calcular la diferencia en milisegundos
    // long diferenciaEnMilisegundos = tiempo2 - tiempo1;

    // // Calcular la diferencia en días
    // long diferenciaEnDias = diferenciaEnMilisegundos / (1000 * 60 * 60 * 24);

    // if (diferenciaEnDias > 2) {

    // array.add(campamentosAux);

    // }

    // }

    // return array;
    // }

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

    public Boolean asociarMonitorActividad(Monitor monitor, String nombreActividad, Campamento campamento) {

        int idMonitor = monitor.getIdentificador();
        int idCampamento = campamento.getIdentificador();
        // Ver si existe la actividad en el campamento
        if (!buscarActividadCampamento(nombreActividad, idCampamento)) {
            for (Actividad a : campamento.getActividades()) {
                if (a.getNombreActividad().equals(nombreActividad) && a.getMonitores().size() < a.getNum_monitores()) {
                    // Ver si el monitor ya está asociado a la actividad
                    for (Monitor m : a.getMonitores()) {
                        if (m.getIdentificador() == idMonitor) {
                            return false; // El monitor ya está asociado a la actividad
                        }
                    }
                    // Si no está asociado, se añade
                    a.getMonitores().add(monitor);
                    return true;
                }
                return false; // La actividad no existe en el campamento indicado
            }
        }
        return false; // La actividad ya está asociada al campamento
    }

    // public void asociarActividadCampamento() {

    // Actividad actividadAux = new Actividad();
    // Scanner teclado = new Scanner(System.in);

    // System.out.println("Introduzca el nombre de la nueva actividad: ");

    // }

    // public void asociarMonitorCampameto() {

    // }

    // public void asociarMonitorEspecialCampamento() {

    // }

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

    public Boolean asociarActividadCampamento(Actividad actividad, int idCampamento) {
        Campamento campamento = getCampamento(idCampamento);
        String nombreActividad = actividad.getNombreActividad();
        NivelEducativo nivel = actividad.getNivel();

        if (!buscarActividadCampamento(actividad, idCampamento)) {
            // Si no se encontró una actividad con el mismo nombre
            for (Actividad a : campamento.getActividades()) {
                if (a.getNombreActividad().equals(nombreActividad) || a.getNivel() == nivel) {
                    System.out.println("La actividad no se puede asociar al campamento.");
                    return false;
                }
            }
            campamento.getActividades().add(actividad);
            System.out.println("Actividad asociada con éxito.");
            return true;
        }
        System.out.println("La actividad ya está asociada al campamento.");
        return false;
    }

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

    public Boolean asociarMonitorCampamento(Monitor monitor, int idCampamento) {
        if (!buscarCampamento(idCampamento)) {
            System.out.printf("Error, el campamento solicitado no existe...");
            return false;
        } else if (!buscarMonitorCampamento(monitor.getIdentificador(), idCampamento)) {
            for (Campamento campamento : campamentos) {
                if (campamento.getIdentificador() == idCampamento) {
                    campamento.getMonitoresResponsables().add(monitor);
                    return true;
                }
            }
        }
        return false;
    }

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

    public Boolean borrarActividad(int idCampamento, String nombreActividad) {
        if (!buscarActividadCampamento(nombreActividad, idCampamento)) {
            System.out.println("La actividad no existe en el campamento indicado...");
            return false;
        } else {
            for (Campamento campamento : campamentos) {
                if (campamento.getIdentificador() == idCampamento) {
                    for (Actividad actividad : campamento.getActividades()) {
                        if (actividad.getNombreActividad().equals(nombreActividad)) {
                            campamento.getActividades().remove(actividad);
                            System.out.printf("\nLa actividad ha sido borrada con exito\n");
                            return true;
                        }
                    }

                }
            }
            System.out.println("La actividad no existe en el campamento indicado...");
            return false;
        }

    }

    public static Boolean crearMonitor(Scanner teclado, Monitor nuevoMonitor) {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
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

        nuevoMonitor.setNombre(nombre);
        nuevoMonitor.setApellidos(apellidos);
        nuevoMonitor.setEsEducador(esEducador);
        return true;

    }
}
