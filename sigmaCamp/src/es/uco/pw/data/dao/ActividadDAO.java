package es.uco.pw.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.uco.pw.business.campamento.dto.actividad.ActividadDTO;
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
            st.setTime(3, java.sql.Time.valueOf(actividad.getHora()));
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
            st.setTime(3, java.sql.Time.valueOf(actividad.getHora()));
            st.setInt(4, actividad.getMaxParticipantes());
            st.setInt(5, actividad.getNumMonitores());
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
                // Debes crear un constructor en ActividadDTO para inicializar los atributos
                // desde un ResultSet
                // Ejemplo: new ActividadDTO(rs.getString("nombreActividad"),
                // NivelEducativo.valueOf(rs.getString("nivel")),
                // rs.getTime("hora").toLocalTime(), rs.getInt("maxParticipantes"),
                // rs.getInt("numMonitores"));

                // Nombre VARCHAR(255) PRIMARY KEY,
                // NivelEducativo ENUM('Infantil', 'Juvenil', 'Adolescente'),
                // Horario TIME,
                // NumeroMaximoParticipantes INT,
                // NumeroMonitoresNecesarios INT
                actividades.add(new ActividadDTO(rs.getString("Nombre"),
                        NivelEducativo.valueOf(rs.getString("NivelEducativo").toUpperCase()), rs.getTime("Horario").toLocalTime(),
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
                // Debes crear un constructor en ActividadDTO para inicializar los atributos
                // desde un ResultSet
                // Ejemplo: return new ActividadDTO(rs.getString("nombreActividad"),
                // NivelEducativo.valueOf(rs.getString("nivel")),
                // rs.getTime("hora").toLocalTime(), rs.getInt("maxParticipantes"),
                // rs.getInt("numMonitores"));
                return new ActividadDTO(rs.getString("nombreActividad"), NivelEducativo.valueOf(rs.getString("nivel")),
                        rs.getTime("hora").toLocalTime(), rs.getInt("maxParticipantes"), rs.getInt("numMonitores"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
