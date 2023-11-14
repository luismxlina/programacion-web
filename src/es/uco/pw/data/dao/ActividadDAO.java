package es.uco.pw.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.uco.pw.business.campamento.dto.actividad.ActividadDTO;
import es.uco.pw.business.campamento.models.actividad.NivelEducativo;
import es.uco.pw.data.common.Conexion;

/**
 * Esta clase representa un objeto de acceso a datos (DAO) para la entidad Actividad.
 */
public class ActividadDAO implements DAO<ActividadDTO, String> {

     /**
     * Inserta una nueva actividad en la base de datos.
     *
     * @param actividad La actividad a insertar.
     * @return True si la inserción fue exitosa, false en caso contrario.
     */
    @Override
    public boolean insert(ActividadDTO actividad) {
        Conexion conexController = Conexion.getInstance();
        Connection conex = conexController.getConnection();
        String query = conexController.getSql().getProperty("INSERT_ACTIVIDAD");
        try {
            PreparedStatement st = conex.prepareStatement(query);
            st.setString(1, actividad.getNombreActividad());
            st.setString(2, actividad.getNivel().name());
            st.setTime(3, java.sql.Time.valueOf(actividad.getHora()));
            st.setInt(4, actividad.getMaxParticipantes());
            st.setInt(5, actividad.getNumMonitores());
            return st.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Actualiza una actividad existente en la base de datos.
     *
     * @param actividad La actividad con los nuevos datos.
     * @return True si la actualización fue exitosa, false en caso contrario.
     */
    @Override
    public boolean update(ActividadDTO actividad) {
        Conexion conexController = Conexion.getInstance();
        Connection conex = conexController.getConnection();
        String query = conexController.getSql().getProperty("UPDATE_ACTIVIDAD");
        try {
            PreparedStatement st = conex.prepareStatement(query);
            st.setString(1, actividad.getNombreActividad());
            st.setString(2, actividad.getNivel().name());
            st.setTime(3, java.sql.Time.valueOf(actividad.getHora()));
            st.setInt(4, actividad.getMaxParticipantes());
            st.setInt(5, actividad.getNumMonitores());
            st.setString(6, actividad.getNombreActividad());
            return st.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Elimina una actividad de la base de datos por su nombre.
     *
     * @param nombreActividad El nombre de la actividad a eliminar.
     * @return True si la eliminación fue exitosa, false en caso contrario.
     */
    public boolean delete(String nombreActividad) {
        Conexion conexController = Conexion.getInstance();
        Connection conex = conexController.getConnection();
        String query = conexController.getSql().getProperty("DELETE_ACTIVIDAD");

        try {
            PreparedStatement st = conex.prepareStatement(query);
            st.setString(1, nombreActividad);
            return st.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

     /**
     * Obtiene todas las actividades almacenadas en la base de datos.
     *
     * @return Una lista de todas las actividades.
     */
    @Override
    public ArrayList<ActividadDTO> getAll() {
        Conexion conexController = Conexion.getInstance();
        Connection conex = conexController.getConnection();
        String query = conexController.getSql().getProperty("SELECT_ALL_ACTIVIDAD");
        try {
            PreparedStatement st = conex.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            ArrayList<ActividadDTO> actividades = new ArrayList<ActividadDTO>();
            while (rs.next()) {
                actividades.add(new ActividadDTO(rs.getString("Nombre"),
                        NivelEducativo.valueOf(rs.getString("NivelEducativo").toUpperCase()),
                        rs.getTime("Horario").toLocalTime(),
                        rs.getInt("NumeroMaximoParticipantes"), rs.getInt("NumeroMonitoresNecesarios")));
            }
            return actividades;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

     /**
     * Obtiene una actividad por su nombre.
     *
     * @param nombre El nombre de la actividad a buscar.
     * @return La actividad encontrada o null si no se encuentra.
     */
    @Override
    public ActividadDTO get(String nombre) {
        Conexion conexController = Conexion.getInstance();
        Connection conex = conexController.getConnection();
        String query = conexController.getSql().getProperty("SELECT_NOMBRE_ACTIVIDAD");
        try {
            PreparedStatement st = conex.prepareStatement(query);
            st.setString(1, nombre);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new ActividadDTO(rs.getString("Nombre"),
                        NivelEducativo.valueOf(rs.getString("NivelEducativo").toUpperCase()),
                        rs.getTime("Horario").toLocalTime(), rs.getInt("NumeroMaximoParticipantes"),
                        rs.getInt("NumeroMonitoresNecesarios"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Inserta un monitor en una actividad específica.
     *
     * @param actividadNombre El nombre de la actividad.
     * @param monitorId El ID del monitor a insertar.
     * @return True si la inserción fue exitosa, false en caso contrario.
     */
    public boolean insertActividadMonitor(String actividadNombre, Integer monitorId) {
        Conexion conexController = Conexion.getInstance();
        Connection conex = conexController.getConnection();
        String query = conexController.getSql().getProperty("INSERT_ACTIVIDADMONITOR");
        
        try {
            PreparedStatement st = conex.prepareStatement(query);
            st.setString(1, actividadNombre);
            st.setInt(2, monitorId);
            return st.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Verifica si una actividad con un nombre específico existe en un campamento.
     *
     * @param nombreActividad El nombre de la actividad a verificar.
     * @param idCampamento El ID del campamento en el que se verifica la existencia.
     * @return True si la actividad existe en el campamento, false en caso contrario.
     */
     public boolean exists(String nombreActividad, int idCampamento) {
        Conexion conexController = Conexion.getInstance();
        Connection conex = conexController.getConnection();
        String query = conexController.getSql().getProperty("SELECT_NOMBRE_ACTIVIDAD");
        try {
            PreparedStatement st = conex.prepareStatement(query);
            st.setString(1, nombreActividad);
            st.setInt(2, idCampamento);
            ResultSet rs = st.executeQuery();
            return rs.next(); // Devuelve true si hay al menos una fila (actividad encontrada), false en caso contrario.
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteActividadCampamento(String nombreActividad, Integer idCampamento) {
       Conexion conexController = Conexion.getInstance();
        Connection conex = conexController.getConnection();
        String query = conexController.getSql().getProperty("DELETE_ACTIVIDAD_CAMPAMENTO");

        try {
            PreparedStatement st = conex.prepareStatement(query);
            st.setString(1, nombreActividad);
            st.setInt(2, idCampamento);
            return st.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
