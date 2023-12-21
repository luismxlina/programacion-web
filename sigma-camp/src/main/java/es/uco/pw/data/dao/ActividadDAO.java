package es.uco.pw.data.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.uco.pw.business.campamento.dto.actividad.ActividadDTO;
import es.uco.pw.business.campamento.models.actividad.Horario;
import es.uco.pw.business.campamento.models.actividad.NivelEducativo;
import es.uco.pw.data.common.Conexion;

public class ActividadDAO implements DAO<ActividadDTO, String> {

    @Override
    public boolean insert(ActividadDTO actividad) {
        Conexion conexController = Conexion.getInstance();
        Connection conex = conexController.getConnection();
        String query = conexController.getSql().getProperty("INSERT_ACTIVIDAD");
        try {
            PreparedStatement st = conex.prepareStatement(query);
            st.setString(1, actividad.getNombreActividad());
            st.setString(2, actividad.getNivel().name());
            st.setString(3, actividad.getHora().name());
            st.setInt(4, actividad.getMaxParticipantes());
            st.setInt(5, actividad.getNumMonitores());
            return st.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(ActividadDTO actividad) {
        Conexion conexController = Conexion.getInstance();
        Connection conex = conexController.getConnection();
        String query = conexController.getSql().getProperty("UPDATE_ACTIVIDAD");
        try {
            PreparedStatement st = conex.prepareStatement(query);
            st.setString(1, actividad.getNombreActividad());
            st.setString(2, actividad.getNivel().name());
            st.setString(3, actividad.getHora().name());
            st.setInt(4, actividad.getMaxParticipantes());
            st.setInt(5, actividad.getNumMonitores());
            st.setString(6, actividad.getNombreActividad());
            return st.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

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
                        Horario.valueOf(rs.getString("Horario").toUpperCase()),
                        rs.getInt("NumeroMaximoParticipantes"), rs.getInt("NumeroMonitoresNecesarios")));
            }
            return actividades;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

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
                        Horario.valueOf(rs.getString("Horario").toUpperCase()),
                        rs.getInt("NumeroMaximoParticipantes"),
                        rs.getInt("NumeroMonitoresNecesarios"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean insertActividadMonitor(String nombreActividad, Integer monitorId) {
        Conexion conexController = Conexion.getInstance();
        Connection conex = conexController.getConnection();
        String query = conexController.getSql().getProperty("INSERT_ACTIVIDAD_MONITOR");
        try {
            PreparedStatement st = conex.prepareStatement(query);
            st.setString(1, nombreActividad);
            st.setInt(2, monitorId);
            return st.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ActividadDTO getActividadCampamento(String nombreActividad, Integer idCampamento) {
        Conexion conexController = Conexion.getInstance();
        Connection conex = conexController.getConnection();
        String query = conexController.getSql().getProperty("SELECT_ACTIVIDAD_CAMPAMENTO");
        try {
            PreparedStatement st = conex.prepareStatement(query);
            st.setInt(1, idCampamento);
            st.setString(2, nombreActividad);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new ActividadDTO(rs.getString("Nombre"),
                        NivelEducativo.valueOf(rs.getString("NivelEducativo").toUpperCase()),
                        Horario.valueOf(rs.getString("Horario").toUpperCase()), rs.getInt("NumeroMaximoParticipantes"),
                        rs.getInt("NumeroMonitoresNecesarios"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<ActividadDTO> getActividadesCampamento(Integer idCampamento) {
        Conexion conexController = Conexion.getInstance();
        Connection conex = conexController.getConnection();
        String query = conexController.getSql().getProperty("SELECT_ACTIVIDADES_CAMPAMENTO");
        try {
            PreparedStatement st = conex.prepareStatement(query);
            st.setInt(1, idCampamento);
            ResultSet rs = st.executeQuery();
            ArrayList<ActividadDTO> actividades = new ArrayList<ActividadDTO>();
            while (rs.next()) {
                actividades.add(new ActividadDTO(rs.getString("Nombre"),
                        NivelEducativo.valueOf(rs.getString("NivelEducativo").toUpperCase()),
                        Horario.valueOf(rs.getString("Horario").toUpperCase()),
                        rs.getInt("NumeroMaximoParticipantes"), rs.getInt("NumeroMonitoresNecesarios")));
            }
            return actividades;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Boolean deleteActividadCampamento(String nombreActividad, Integer idCampamento) {
        Conexion conexController = Conexion.getInstance();
        Connection conex = conexController.getConnection();
        String query = conexController.getSql().getProperty("DELETE_ACTIVIDAD_CAMPAMENTO");
        try {
            PreparedStatement st = conex.prepareStatement(query);
            st.setInt(1, idCampamento);
            st.setString(2, nombreActividad);
            return st.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<ActividadDTO> getActividadesByNivelEducativo(NivelEducativo nivel) {
        ArrayList<ActividadDTO> actividades = new ArrayList<ActividadDTO>();
        Conexion conexController = Conexion.getInstance();
        Connection conex = conexController.getConnection();
        String query = conexController.getSql().getProperty("SELECT_ACTIVIDADES_BY_NIVEL");

        try {
            PreparedStatement st = conex.prepareStatement(query);
            st.setString(1, nivel.toString());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                actividades.add(new ActividadDTO(rs.getString("Nombre"),
                        NivelEducativo.valueOf(rs.getString("NivelEducativo").toUpperCase()),
                        Horario.valueOf(rs.getString("Horario").toUpperCase()),
                        rs.getInt("NumeroMaximoParticipantes"), rs.getInt("NumeroMonitoresNecesarios")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actividades;
    }
}
