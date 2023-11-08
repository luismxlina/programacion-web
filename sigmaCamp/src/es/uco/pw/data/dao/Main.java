package es.uco.pw.data.dao;
import es.uco.pw.data.dao.ActividadDAO;
import es.uco.pw.data.dao.CampamentoDAO;
import es.uco.pw.business.campamento.dto.actividad.ActividadDTO;
import es.uco.pw.business.campamento.dto.campamento.CampamentoDTO;
import es.uco.pw.business.campamento.models.actividad.NivelEducativo;

import java.util.Date;
import java.time.LocalTime;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Crear una instancia de ActividadDAO y CampamentoDAO
        ActividadDAO actividadDAO = new ActividadDAO();
        // CampamentoDAO campamentoDAO = new CampamentoDAO();

        // Crear objetos de prueba para ActividadDTO y CampamentoDTO
        // ActividadDTO actividad2 = new ActividadDTO("Actividad6", NivelEducativo.JUVENIL, LocalTime.of(14, 30), 30, 3);
        // CampamentoDTO campamento1 = new CampamentoDTO(1, new Date(), new Date(), "Nivel3", 50);
        // CampamentoDTO campamento2 = new CampamentoDTO(2, new Date(), new Date(), "Nivel4", 40);
        
        // Insertar actividades y campamentos en la base de datos
        // ActividadDTO Actividad5 = new ActividadDTO("Actividad5", NivelEducativo.INFANTIL, LocalTime.of(10, 0), 20, 7000);
        // ActividadDTO test = new ActividadDTO();
        // boolean updateActividad = actividadDAO.update(test);       
        boolean updateActividad = actividadDAO.delete("Actividad5");
        // boolean insertActividad2 = actividadDAO.insert(actividad2);
        // boolean insertCampamento1 = campamentoDAO.insert(campamento1);
        // boolean insertCampamento2 = campamentoDAO.insert(campamento2);

        if (updateActividad) {
            System.out.println("éxito. updateaado tu puta madre");
        } else {
            System.out.println("Error ");
        }

        // if (insertCampamento1 && insertCampamento2) {
        //     System.out.println("Campamentos insertados con éxito.");
        // } else {
        //     System.out.println("Error al insertar campamentos.");
        // }

        // Actualizar una actividad y un campamento
        //actividad1.setNombreActividad("NuevaActividad");
        //campamento1.setNivel("NuevoNivel");
        //boolean updateActividad = actividadDAO.update(actividad1);
        //boolean updateCampamento = campamentoDAO.update(campamento1);

        // if (updateActividad) {
            // System.out.println("Actividad actualizada con éxito.");
        // } else {
            // System.out.println("Error al actualizar actividad.");
        // }

        // if (updateCampamento) {
        //     System.out.println("Campamento actualizado con éxito.");
        // } else {
        //     System.out.println("Error al actualizar campamento.");
        // }

        // Eliminar una actividad y un campamento
        // boolean deleteActividad = actividadDAO.delete(actividad1.getNombreActividad());
        // boolean deleteCampamento = campamentoDAO.delete(campamento1.getIdentificador());

        // if (deleteActividad) {
        //     System.out.println("Actividad eliminada con éxito.");
        // } else {
        //     System.out.println("Error al eliminar actividad.");
        // }

        // if (deleteCampamento) {
        //     System.out.println("Campamento eliminado con éxito.");
        // } else {
        //     System.out.println("Error al eliminar campamento.");
        // }
        


        // Obtener todas las actividades y campamentos
        ArrayList<ActividadDTO> actividades = actividadDAO.getAll();
        // ArrayList<CampamentoDTO> campamentos = campamentoDAO.getAll();

        System.out.println("Actividades en la base de datos:");
        for (ActividadDTO actividad : actividades) {
            System.out.println(actividad.getNombreActividad());
            System.out.println(actividad.getNumMonitores());
            System.out.println("\n");
        }

        // System.out.println("Campamentos en la base de datos:");
        // for (CampamentoDTO campamento : campamentos) {
        //     System.out.println(campamento.getIdentificador());
        // }
    }
}
