package es.uco.pw.gestores;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.time.LocalDate;

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

    private static Monitor getMonitor(int id, ArrayList<Monitor> monitores) {
        for (Monitor monitor : monitores) {
            if (monitor.getIdentificador() == id) {
                return monitor;
            }
        }
        return new Monitor();
    }

    private static Actividad getActividad(String nombreActividad, ArrayList<Actividad> actividades) {
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

    // // Son las mismas que asociar??
    // public void altaActividad() {

    // }

    // public void altaMonitor() {

    // }

    // public void altaCampamento() {

    // }

    // public void asociarMonitorActividad() {

    // }

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
        System.out.print(" ");
        teclado.nextLine();
        fechaInicioTexto = teclado.nextLine();

        System.out.print("Fecha fin (yyyy-mm-dd): ");
        fechaFinTexto = teclado.nextLine();

        Date fechaInicioDate = new Date();
        Date fechaFinDate = new Date();
        try {

            fechaInicioDate = formatoFecha.parse(fechaInicioTexto);
            fechaFinDate = formatoFecha.parse(fechaFinTexto);
            fechaActual = 
            if (fechaFinDate.compareTo(fechaInicioDate) <= 0 && ) {
                System.out.println("La fecha de fin debe ser posterior a la fecha de inicio.");
                return false;
            }

        } catch (ParseException e) {
            System.out.println("Error al convertir la fecha...");
            return false;
        }

        System.out.print("Indique el nivel educativo campamento:");
        System.out.println("(1) INFANTIL");
        System.out.println("(2) JUVENIL");
        System.out.println("(3) ADOLESCENTE");
        int opcion = teclado.nextInt();
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

        while (max_asistentes <= 0) {
            System.out.print("Maximo número de aistentes: ");
            teclado.nextInt();
            max_asistentes = teclado.nextInt();
            if (max_asistentes <= 0) {
                System.out.print("Error al indicar el número máximo de asistentes...");
                return false;
            }
        }

        nuevoCampamento.setFechaInicio(fechaInicioDate);
        nuevoCampamento.setFechaFin(fechaFinDate);
        nuevoCampamento.setNivel(nivel);
        nuevoCampamento.setMax_asistentes(max_asistentes);
        return true;
    }

    // public Boolean asociarActividadCampamento(String nombreActividad, int
    // idCampamento) {
    // if (!buscarActividadCampamento(actividad.getNombreActividad(),
    // campamento.getIdentificador())) {
    // campamento
    // }

    // return false;
    // }
}
