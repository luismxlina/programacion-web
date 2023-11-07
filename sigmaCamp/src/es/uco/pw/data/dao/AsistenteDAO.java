package es.uco.pw.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.uco.pw.business.users.dto.asistente.AsistenteDTO;
import es.uco.pw.data.common.Conexion;

public class AsistenteDAO implements DAO<AsistenteDTO, Integer> {

    @Override
    public boolean insert(AsistenteDTO asistente)
    {
        Conexion conexController = Conexion.getInstance();
        Connection conex = conexController.getConnection();
        String query = conexController.getSql().getProperty("INSERT_ASISTENTE");
        try {
            PreparedStatement st = conex.prepareStatement(query);
            st.setInt(1, asistente.getIdentificador());
            st.setString(2, asistente.getNombre());
            st.setString(3, asistente.getApellidos());
            st.setDate(4, new java.sql.Date(asistente.getFechaNacimiento().getTime()));
            st.setBoolean(5, asistente.getRequiereAtencion());
            return st.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(AsistenteDTO asistente)
    {
        Conexion conexController = Conexion.getInstance();
        Connection conex = conexController.getConnection();
        String query = conexController.getSql().getProperty("INSERT_ASISTENTE");
        try {
            PreparedStatement st = conex.prepareStatement(query);
            st.setInt(1, asistente.getIdentificador());
            st.setString(2, asistente.getNombre());
            st.setString(3, asistente.getApellidos());
            st.setDate(4, new java.sql.Date(asistente.getFechaNacimiento().getTime()));
            st.setBoolean(5, asistente.getRequiereAtencion());
            return st.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Integer id)
    {
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
    public ArrayList<AsistenteDTO> getAll()
    {
         Conexion conexController = Conexion.getInstance();
        Connection conex = conexController.getConnection();
        String query = conexController.getSql().getProperty("SELECT_ALL_CAMPAMENTO");
        try {
            PreparedStatement st = conex.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            ArrayList<AsistenteDTO> asistente = new ArrayList<AsistenteDTO>();
            while (rs.next()) {
                // Debes crear un constructor en AsistenteDTO para inicializar los atributos desde un ResultSet
                // Ejemplo: return new AsistenteDTO(rs.getInt("identificador"), rs.getDate("fechaInicio"), rs.getDate("fechaFin"), rs.getString("nivel"), rs.getInt("maxAsistentes"));
                asistente.add(new AsistenteDTO(rs.getInt("identificador"), rs.getString("nombre"), rs.getString("apellidos"), rs.getDate("fechaNacimiento"), rs.getBoolean("RequiereAtencion")));
            }
            return asistente;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public AsistenteDTO get(Integer id) 
    {
         Conexion conexController = Conexion.getInstance();
        Connection conex = conexController.getConnection();
        String query = conexController.getSql().getProperty("SELECT_ID_CAMPAMENTO");
        try {
            PreparedStatement st = conex.prepareStatement(query);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                // Debes crear un constructor en AsistenteDTO para inicializar los atributos desde un ResultSet
                // Ejemplo: return new AsistenteDTO(rs.getInt("identificador"), rs.getDate("fechaInicio"), rs.getDate("fechaFin"), rs.getString("nivel"), rs.getInt("maxAsistentes"));
              return new AsistenteDTO(rs.getInt("identificador"), rs.getString("nombre"), rs.getString("apellidos"), rs.getDate("fechaNacimiento"), rs.getBoolean("RequiereAtencion"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}