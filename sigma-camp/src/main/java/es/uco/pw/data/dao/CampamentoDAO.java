package es.uco.pw.data.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Statement;
import es.uco.pw.business.campamento.dto.campamento.CampamentoDTO;
import es.uco.pw.business.campamento.models.actividad.NivelEducativo;
import es.uco.pw.data.common.Conexion;

public class CampamentoDAO implements DAOIncremental<CampamentoDTO, Integer> {

    @Override
    public Integer insert(CampamentoDTO campamento) {
        Conexion conexController = Conexion.getInstance();
        Connection conex = conexController.getConnection();
        String query = conexController.getSql().getProperty("INSERT_CAMPAMENTO");
        Integer idGenerado = -1;
        try {
            PreparedStatement st = conex.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            st.setDate(1, new java.sql.Date(campamento.getFechaInicio().getTime()));
            st.setDate(2, new java.sql.Date(campamento.getFechaFin().getTime()));
            st.setString(3, campamento.getNivel());
            st.setInt(4, campamento.getMaxAsistentes());
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                idGenerado = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idGenerado;
    }

    @Override
    public boolean update(CampamentoDTO campamento) {
        Conexion conexController = Conexion.getInstance();
        Connection conex = conexController.getConnection();
        String query = conexController.getSql().getProperty("UPDATE_CAMPAMENTO");
        try {
            PreparedStatement st = conex.prepareStatement(query);
            st.setInt(1, campamento.getIdentificador());
            st.setDate(2, new java.sql.Date(campamento.getFechaInicio().getTime()));
            st.setDate(3, new java.sql.Date(campamento.getFechaFin().getTime()));
            st.setString(4, campamento.getNivel());
            st.setInt(5, campamento.getMaxAsistentes());
            st.setInt(6, campamento.getIdentificador()); // Agregar un método getId() a la clase CampamentoDTO
            return st.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        Conexion conexController = Conexion.getInstance();
        Connection conex = conexController.getConnection();
        String query = conexController.getSql().getProperty("DELETE_CAMPAMENTO");
        try {
            PreparedStatement st = conex.prepareStatement(query);
            st.setInt(1, id);
            return st.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<CampamentoDTO> getAll() {
        Conexion conexController = Conexion.getInstance();
        Connection conex = conexController.getConnection();
        String query = conexController.getSql().getProperty("SELECT_ALL_CAMPAMENTO");
        try {
            PreparedStatement st = conex.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            ArrayList<CampamentoDTO> campamentos = new ArrayList<CampamentoDTO>();
            while (rs.next()) {
                // Debes crear un constructor en CampamentoDTO para inicializar los atributos
                // desde un ResultSet
                // Ejemplo: return new CampamentoDTO(rs.getInt("identificador"),
                // rs.getDate("fechaInicio"), rs.getDate("fechaFin"), rs.getString("nivel"),
                // rs.getInt("maxAsistentes"));
                campamentos.add(
                        new CampamentoDTO(rs.getInt("Identificador"), rs.getDate("FechaInicio"), rs.getDate("FechaFin"),
                                rs.getString("NivelEducativo".toUpperCase()), rs.getInt("NumeroMaximoAsistentes")));
            }
            return campamentos;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public CampamentoDTO get(Integer id) {
        Conexion conexController = Conexion.getInstance();
        Connection conex = conexController.getConnection();
        String query = conexController.getSql().getProperty("SELECT_ID_CAMPAMENTO");
        try {
            PreparedStatement st = conex.prepareStatement(query);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {

                return new CampamentoDTO(rs.getInt("Identificador"), rs.getDate("FechaInicio"), rs.getDate("FechaFin"),
                        rs.getString("NivelEducativo".toUpperCase()), rs.getInt("NumeroMaximoAsistentes"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean insertCampamentoMonitor(Integer campamentoId, Integer monitorId) {
        Conexion conexController = Conexion.getInstance();
        Connection conex = conexController.getConnection();
        String query = conexController.getSql().getProperty("INSERT_CAMPAMENTO_MONITOR");
        try {
            PreparedStatement st = conex.prepareStatement(query);
            st.setInt(1, campamentoId);
            st.setInt(2, monitorId);
            return st.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean insertCampamentoActividad(Integer campamentoId, String nombreActividad) {
        Conexion conexController = Conexion.getInstance();
        Connection conex = conexController.getConnection();
        String query = conexController.getSql().getProperty("INSERT_CAMPAMENTO_ACTIVIDAD");
        try {
            PreparedStatement st = conex.prepareStatement(query);
            st.setInt(1, campamentoId);
            st.setString(2, nombreActividad);
            return st.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Integer> getAllIdsCampamentos() {
        ArrayList<Integer> ids = new ArrayList<Integer>();
        Conexion conexController = Conexion.getInstance();
        Connection conex = conexController.getConnection();
        String query = conexController.getSql().getProperty("SELECT_ALL_IDS_CAMPAMENTO");
        try {
            PreparedStatement st = conex.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ids.add(rs.getInt("Identificador"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ids;
    }

    public ArrayList<CampamentoDTO> getCampamentosByNivelEducativo(NivelEducativo nivel) {
        ArrayList<CampamentoDTO> campamentos = new ArrayList<CampamentoDTO>();
        Conexion conexController = Conexion.getInstance();
        Connection conex = conexController.getConnection();
        String query = conexController.getSql().getProperty("SELECT_CAMPAMENTOS_BY_NIVEL");

        try {
            PreparedStatement st = conex.prepareStatement(query);
            st.setString(1, nivel.toString());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                campamentos.add(
                        new CampamentoDTO(rs.getInt("Identificador"), rs.getDate("FechaInicio"), rs.getDate("FechaFin"),
                                rs.getString("NivelEducativo"), rs.getInt("NumeroMaximoAsistentes")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return campamentos;
    }

    public ArrayList<CampamentoDTO> getCampamentosByFechas(Date fechaInicio, Date fechaFin) {
        ArrayList<CampamentoDTO> campamentos = new ArrayList<CampamentoDTO>();
        Conexion conexController = Conexion.getInstance();
        Connection conex = conexController.getConnection();
        String query = conexController.getSql().getProperty("SELECT_CAMPAMENTOS_BY_FECHAS");

        try {
            PreparedStatement st = conex.prepareStatement(query);
            st.setDate(1, new java.sql.Date(fechaInicio.getTime()));
            st.setDate(2, new java.sql.Date(fechaFin.getTime()));
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                campamentos.add(
                        new CampamentoDTO(rs.getInt("Identificador"), rs.getDate("FechaInicio"), rs.getDate("FechaFin"),
                                rs.getString("NivelEducativo"), rs.getInt("NumeroMaximoAsistentes")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return campamentos;
    }
}
