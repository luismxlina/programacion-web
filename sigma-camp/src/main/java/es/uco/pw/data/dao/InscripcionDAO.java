package es.uco.pw.data.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.uco.pw.business.inscripcion.dto.inscripcion.InscripcionDTO;
import es.uco.pw.business.inscripcion.models.inscripcion.TipoInscripcion;
import es.uco.pw.data.common.Conexion;

/**
 * Esta clase implementa la interfaz DAOInscripcion y proporciona métodos para
 * realizar operaciones CRUD
 * (Create, Read, Update, Delete) en la entidad Inscripción en la base de datos.
 */
public class InscripcionDAO implements DAOInscripcion<InscripcionDTO, Integer> {

    @Override
    public boolean insert(InscripcionDTO inscripcion) {
        Conexion conexController = Conexion.getInstance();
        Connection conex = conexController.getConnection();
        String query = conexController.getSql().getProperty("INSERT_INSCRIPCION");
        try {
            PreparedStatement st = conex.prepareStatement(query);
            st.setInt(1, inscripcion.getAsistenteId());
            st.setInt(2, inscripcion.getCampamentoId());
            java.util.Date fechaInscripcionUtil = inscripcion.getFechaInscripcion();
            java.sql.Date fechaInscripcionSql = new java.sql.Date(fechaInscripcionUtil.getTime());
            st.setDate(3, fechaInscripcionSql);
            st.setDouble(4, inscripcion.getPrecio());
            st.setString(5, inscripcion.getTipoInscripcion().name());
            return st.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(InscripcionDTO inscripcion) {
        Conexion conexController = Conexion.getInstance();
        Connection conex = conexController.getConnection();
        String query = conexController.getSql().getProperty("UPDATE_INSCRIPCION");
        try {
            PreparedStatement st = conex.prepareStatement(query);
            java.util.Date fechaInscripcionUtil = inscripcion.getFechaInscripcion();
            java.sql.Date fechaInscripcionSql = new java.sql.Date(fechaInscripcionUtil.getTime());
            st.setDate(1, fechaInscripcionSql);
            st.setDouble(2, inscripcion.getPrecio());
            st.setString(3, inscripcion.getTipoInscripcion().name());
            st.setInt(4, inscripcion.getAsistenteId());
            st.setInt(5, inscripcion.getCampamentoId());
            return st.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Integer asistenteId, Integer campamentoId) {
        Conexion conexController = Conexion.getInstance();
        Connection conex = conexController.getConnection();
        String query = conexController.getSql().getProperty("DELETE_INSCRIPCION");
        try {
            PreparedStatement st = conex.prepareStatement(query);
            st.setInt(1, asistenteId);
            st.setInt(2, campamentoId);
            return st.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<InscripcionDTO> getAll() {
        Conexion conexController = Conexion.getInstance();
        Connection conex = conexController.getConnection();
        String query = conexController.getSql().getProperty("SELECT_ALL_INSCRIPCION");
        try {
            PreparedStatement st = conex.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            ArrayList<InscripcionDTO> inscripciones = new ArrayList<InscripcionDTO>();
            while (rs.next()) {
                inscripciones.add(new InscripcionDTO(rs.getInt("AsistenteId"), rs.getInt("CampamentoId"),
                        rs.getDate("FechaInscripcion"), rs.getDouble("Precio"),
                        TipoInscripcion.valueOf(rs.getString("TipoInscripcion").toUpperCase())));
            }
            return inscripciones;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public InscripcionDTO get(Integer asistenteId, Integer campamentoId) {
        Conexion conexController = Conexion.getInstance();
        Connection conex = conexController.getConnection();
        String query = conexController.getSql().getProperty("SELECT_ID_INSCRIPCION");
        try {
            PreparedStatement st = conex.prepareStatement(query);
            st.setInt(1, asistenteId);
            st.setInt(2, campamentoId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new InscripcionDTO(rs.getInt("AsistenteId"), rs.getInt("CampamentoId"),
                        rs.getDate("FechaInscripcion"), rs.getDouble("Precio"),
                        TipoInscripcion.valueOf(rs.getString("TipoInscripcion").toUpperCase()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Obtiene todas las inscripciones completas almacenadas en la base de datos.
     *
     * @return Una lista de objetos InscripcionDTO que representan inscripciones
     *         completas.
     */
    public ArrayList<InscripcionDTO> getAllInscripcionesCompletas() {
        Conexion conexController = Conexion.getInstance();
        Connection conex = conexController.getConnection();
        String query = conexController.getSql().getProperty("SELECT_ALL_INSCRIPCION_COMPLETA");
        try {
            PreparedStatement st = conex.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            ArrayList<InscripcionDTO> inscripciones = new ArrayList<InscripcionDTO>();
            while (rs.next()) {
                inscripciones.add(new InscripcionDTO(rs.getInt("AsistenteId"), rs.getInt("CampamentoId"),
                        rs.getDate("FechaInscripcion"), rs.getDouble("Precio"),
                        TipoInscripcion.valueOf(rs.getString("TipoInscripcion").toUpperCase())));
            }
            return inscripciones;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Obtiene todas las inscripciones parciales almacenadas en la base de datos.
     *
     * @return Una lista de objetos InscripcionDTO que representan inscripciones
     *         parciales.
     */
    public ArrayList<InscripcionDTO> getAllInscripcionesParciales() {
        Conexion conexController = Conexion.getInstance();
        Connection conex = conexController.getConnection();
        String query = conexController.getSql().getProperty("SELECT_ALL_INSCRIPCION_PARCIAL");
        try {
            PreparedStatement st = conex.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            ArrayList<InscripcionDTO> inscripciones = new ArrayList<InscripcionDTO>();
            while (rs.next()) {
                inscripciones.add(new InscripcionDTO(rs.getInt("AsistenteId"), rs.getInt("CampamentoId"),
                        rs.getDate("FechaInscripcion"), rs.getDouble("Precio"),
                        TipoInscripcion.valueOf(rs.getString("TipoInscripcion").toUpperCase())));
            }
            return inscripciones;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Cuenta el número de inscripciones asociadas a un campamento.
     *
     * @param campamentoId El identificador del campamento.
     * @return El número de inscripciones asociadas al campamento.
     */
    public int count(Integer campamentoId) {
        Conexion conexController = Conexion.getInstance();
        Connection conex = conexController.getConnection();
        String query = conexController.getSql().getProperty("COUNT_INSCRIPCION_BY_CAMPAMENTO");
        try {
            PreparedStatement st = conex.prepareStatement(query);
            st.setInt(1, campamentoId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Obtiene todas las inscripciones asociadas a un campamento.
     *
     * @param campamentoId El identificador del campamento.
     * @return Una lista de objetos InscripcionDTO que representan las inscripciones
     *         asociadas al campamento.
     */
    public ArrayList<InscripcionDTO> getAllInscripcionesCampamento(Integer campamentoId) {
        Conexion conexController = Conexion.getInstance();
        Connection conex = conexController.getConnection();
        String query = conexController.getSql().getProperty("SELECT_ALL_INSCRIPCION_BY_CAMPAMENTO");

        try (PreparedStatement st = conex.prepareStatement(query)) {
            st.setInt(1, campamentoId);
            ResultSet rs = st.executeQuery();

            ArrayList<InscripcionDTO> inscripciones = new ArrayList<>();

            while (rs.next()) {
                inscripciones.add(new InscripcionDTO(
                        rs.getInt("AsistenteId"),
                        rs.getInt("CampamentoId"),
                        rs.getDate("FechaInscripcion"),
                        rs.getDouble("Precio"),
                        TipoInscripcion.valueOf(rs.getString("TipoInscripcion").toUpperCase())));
            }

            return inscripciones;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // or return an empty list if no results are found or an exception occurs
    }
	
}
