package es.uco.pw.gestores;

import es.uco.pw.classes.actividad.Actividad;
import es.uco.pw.classes.campamento.Campamento;

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

    /**
     * Asocia una actividad a un campamento existente en el gestor de campamentos.
     * 
     * @param idCampamento   el identificador del campamento al que se desea asociar
     *                       la actividad
     * @param nuevaActividad la actividad que se desea asociar al campamento
     * @return true si la actividad se asoció correctamente al campamento, false en
     *         caso contrario
     * @throws NullPointerException si la actividad es null
     */
    public boolean asociarActividad(int idCampamento, Actividad nuevaActividad) {
        if (buscarCampamento(idCampamento) == false) {
            // Error puesto que no existe el campamento
            return false;
        } else {
            for (Campamento campamento : campamentos) {
                if (campamento.getIdentificador() == idCampamento) {
                    if (nuevaActividad.getNivel() == campamento.getNivel()) {
                        campamento.getActividades().add(nuevaActividad);
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
