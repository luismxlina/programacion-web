package es.uco.pw.gestores;

import java.io.File;
import java.io.FileNotFoundException;
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

    private static Monitor buscarMonitorID(int id, ArrayList<Monitor> monitores) {
        for (Monitor monitor : monitores) {
            if (monitor.getIdentificador() == id) {
                return monitor;
            }
        }
        return new Monitor();
    }

    private static Actividad buscarActividad(String nombreActividad, ArrayList<Actividad> actividades) {
        for (Actividad actividad : actividades) {
            if (actividad.getNombreActividad().equals(nombreActividad)) {
                return actividad;
            }
        }
        return new Actividad();
    }

    // public Date obtenerFecha(int newIdCampamento) {

    //     for (Campamento campamentosaux : campamentos) {

    //         if (campamentosaux.getIdCampamento() == newIdCampamento) {

    //             return campamentosaux.getFechaInicio();

    //         }

    //     }

    //     return new Date();

    // }

    // public double calcularPrecioExtra(int newIdCampamento) {

    //     double extra = 0.0;
    //     int cont = 0;

    //     for (Campamento campamentosAux : campamentos) {

    //         if (campamentosAux.getIdCampamento() == newIdCampamento)

    //             for (Actividad actividadesAux : campamentosAux.getActividades()) {

    //                 cont++;

    //             }

    //     }

    //     extra = 20.0 * cont;

    //     return extra;
    // }

    // public ArrayList<Campamento> campamentosDisponibles() {

    //     ArrayList<Campamento> array = new ArrayList<Campamento>();
    //     Date fechaActual = new Date();

    //     for (Campamento campamentosAux : campamentos) {

    //         long tiempo1 = fechaActual.getTime();
    //         long tiempo2 = campamentosAux.getFechaInicio().getTime();

    //         // Calcular la diferencia en milisegundos
    //         long diferenciaEnMilisegundos = tiempo2 - tiempo1;

    //         // Calcular la diferencia en días
    //         long diferenciaEnDias = diferenciaEnMilisegundos / (1000 * 60 * 60 * 24);

    //         if (diferenciaEnDias > 2) {

    //             array.add(campamentosAux);

    //         }

    //     }

    //     return array;
    // }

    // public void mostrarCampamentos() {

    //     Campamento aux = new Campamento();

    //     for (Campamento campamentosAux : campamentos) {

    //         System.out.println("ID: " + campamentosAux.getIdCampamento()
    //                 + ", Fecha de inicio: " + campamentosAux.getFechaInicio()
    //                 + ", Fecha de fin: " + campamentosAux.getFechaFin()
    //                 + ", Nivel: " + campamentosAux.getNivel()
    //                 + ", Participantes maximos: " + campamentosAux.getMax_participantes()
    //                 + ", Lista de actividades: " + campamentosAux.getActividades()
    //                 + ", Lista de monitores: " + campamentosAux.getMonitores());

    //     }

    // }

    // public void mostrarCampamento(int idCampamento) {

    //     Campamento aux = new Campamento();

    //     for (Campamento campamentosAux : campamentos) {

    //         if (campamentosAux.getIdCampamento() == idCampamento) {

    //             System.out.println("ID: " + campamentosAux.getIdCampamento()
    //                     + ", Fecha de inicio: " + campamentosAux.getFechaInicio()
    //                     + ", Fecha de fin: " + campamentosAux.getFechaFin()
    //                     + ", Nivel: " + campamentosAux.getNivel()
    //                     + ", Participantes maximos: " + campamentosAux.getMax_participantes()
    //                     + ", Lista de actividades: " + campamentosAux.getActividades()
    //                     + ", Lista de monitores: " + campamentosAux.getMonitores());

    //         }

    //     }

    // }

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

    //     Actividad actividadAux = new Actividad();
    //     Scanner teclado = new Scanner(System.in);

    //     System.out.println("Introduzca el nombre de la nueva actividad: ");

    // }

    // public void asociarMonitorCampameto() {

    // }

    // public void asociarMonitorEspecialCampamento() {

    // }

}