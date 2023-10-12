package es.uco.pw.gestores;

import es.uco.pw.classes.actividad.Actividad;
import es.uco.pw.classes.campamento.Campamento;
import es.uco.pw.classes.monitor.Monitor;

import java.util.ArrayList;

public class GestorCampamentos {
    private ArrayList<Campamento> campamentos;

    private static GestorCampamentos instance = null;

    private GestorCampamentos(ArrayList<Campamento> arrayNuevo) {
        this.campamentos = arrayNuevo;
    }

    public static GestorCampamentos getInstance(ArrayList<Campamento> arrayNuevo) {
        if (instance == null) {
            instance = new GestorCampamentos(arrayNuevo);
        }
        return instance;
    }

    public boolean altaCampamento(Campamento nuevoCampamento) {
        if (!buscarCampamento(nuevoCampamento.getIdentificador())) {
            campamentos.add(nuevoCampamento);
            return true;
        }
        return false;
    }

    public boolean buscarCampamento(int id) {
        if (campamentos.size() == 0)
            return false;

        for (int i = 0; i < campamentos.size(); i++) {
            if (this.campamentos.get(i).getIdentificador() == id) {
                return true;
            }
        }

        return false;
    }

    public Campamento getCampamento(int id) {
        if (campamentos.size() == 0)
            return null;

        for (int i = 0; i < campamentos.size(); i++) {
            if (this.campamentos.get(i).getIdentificador() == id) {
                return this.campamentos.get(i);
            }
        }

        return null;
    }

    public boolean asociarActividadCampamento(Actividad nuevaActividad, int idCampamento) {
        if (buscarCampamento(idCampamento)) {
            ArrayList<Campamento> campamentos = obtenerCampamentos();
            for (Campamento campamento : campamentos) {
                if ((campamento.getIdentificador() == idCampamento)
                        && (campamento.getNivel() == nuevaActividad.getNivel())) {
                    campamento.asociarActividad(nuevaActividad);
                    return true;
                }
            }
        }
        return false;
    }

    public ArrayList<Campamento> obtenerCampamentos() {
        return campamentos;
    }

    public void mostrarCampamentos() {
        if (instance != null) {
            for (Campamento campamento : campamentos) {
                System.out.println("ID: " + campamento.getIdentificador() +
                        ", Fecha de inicio: " + campamento.getFechaInicio() +
                        ", Fecha de fin: " + campamento.getFechaFin() +
                        ", Nivel educativo: " + campamento.getNivel() +
                        ", Máximo de asistentes: " + campamento.getMax_asistentes());
            }
        }
    }

    public boolean asociarMonitorActividad(int idCampamento, Monitor monitor, String nombreActividad) {
        return false;
    }

    public boolean asociarMonitorCampamento(Campamento campamento, Monitor monitor) {
        if (campamento == null)
            return false;
        for (Monitor m : campamento.getMonitoresResponsables()) {
            if (m.getIdentificador() == monitor.getIdentificador()) {
                campamento.asociarMonitor(monitor);
                return true;
            }
        }
        return false;
    }

    public boolean asociarMonitorEspecialCampamento(int idCampamento, int idMonitor) {
        return false;
    }
}
