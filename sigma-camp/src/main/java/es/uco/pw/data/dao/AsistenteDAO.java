package es.uco.pw.data.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Statement;

import es.uco.pw.business.users.dto.asistente.AsistenteDTO;
import es.uco.pw.data.common.Conexion;

public class AsistenteDAO implements DAOAsistente<AsistenteDTO, Integer> {

    @Override
    public Integer insert(AsistenteDTO asistente) {
        Conexion conexController = Conexion.getInstance();
        Connection conex = conexController.getConnection();
        String query = conexController.getSql().getProperty("INSERT_ASISTENTE");
        Integer idGenerado = -1;
        try {
            PreparedStatement st = conex.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, asistente.getNombre());
            st.setString(2, asistente.getApellidos());
            st.setDate(3, new java.sql.Date(asistente.getFechaNacimiento().getTime()));
            st.setBoolean(4, asistente.getRequiereAtencion());
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
    public boolean update(AsistenteDTO asistente) {
        Conexion conexController = Conexion.getInstance();
        Connection conex = conexController.getConnection();
        String query = conexController.getSql().getProperty("UPDATE_ASISTENTE");
        try {
            PreparedStatement st = conex.prepareStatement(query);
            st.setInt(1, asistente.getIdentificador());
            st.setString(2, asistente.getNombre());
            st.setString(3, asistente.getApellidos());
            st.setDate(4, new java.sql.Date(asistente.getFechaNacimiento().getTime()));
            st.setBoolean(5, asistente.getRequiereAtencion());
            st.setInt(6, asistente.getIdentificador());
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
        String query = conexController.getSql().getProperty("DELETE_ASISTENTE");
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
    public ArrayList<AsistenteDTO> getAll() {
        Conexion conexController = Conexion.getInstance();
        Connection conex = conexController.getConnection();
        String query = conexController.getSql().getProperty("SELECT_ALL_ASISTENTE");
        try {
            PreparedStatement st = conex.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            ArrayList<AsistenteDTO> asistentes = new ArrayList<AsistenteDTO>();
            while (rs.next()) {
                // Debes crear un constructor en AsistenteDTO para inicializar los atributos
                // desde un ResultSet
                // Ejemplo: return new AsistenteDTO(rs.getInt("identificador"),
                // rs.getDate("fechaInicio"), rs.getDate("fechaFin"), rs.getString("nivel"),
                // rs.getInt("maxAsistentes"));
                asistentes.add(new AsistenteDTO(rs.getInt("identificador"), rs.getString("nombre"),
                        rs.getString("apellidos"), rs.getDate("fechaNacimiento"),
                        rs.getBoolean("RequiereAtencionEspecial")));
            }
            return asistentes;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public AsistenteDTO get(Integer id) {
        Conexion conexController = Conexion.getInstance();
        Connection conex = conexController.getConnection();
        String query = conexController.getSql().getProperty("SELECT_ID_ASISTENTE");
        try {
            PreparedStatement st = conex.prepareStatement(query);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                // Debes crear un constructor en AsistenteDTO para inicializar los atributos
                // desde un ResultSet
                // Ejemplo: return new AsistenteDTO(rs.getInt("identificador"),
                // rs.getDate("fechaInicio"), rs.getDate("fechaFin"), rs.getString("nivel"),
                // rs.getInt("maxAsistentes"));
                return new AsistenteDTO(rs.getInt("Identificador"), rs.getString("Nombre"), rs.getString("Apellidos"),
                        rs.getDate("FechaNacimiento"), rs.getBoolean("RequiereAtencionEspecial"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}