package es.uco.pw.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.uco.pw.business.campamento.dto.campamento.CampamentoDTO;
import es.uco.pw.data.common.Conexion;

public class CampamentoDAO implements DAO<CampamentoDTO, Integer> {

    @Override
    public boolean insert(CampamentoDTO campamento) {
        Conexion conexController = Conexion.getInstance();
        Connection conex = conexController.getConnection();
        String query = conexController.getSql().getProperty("INSERT_CAMPAMENTO");
        try {
            PreparedStatement st = conex.prepareStatement(query);
            st.setInt(1, campamento.getIdentificador());
            st.setDate(2, new java.sql.Date(campamento.getFechaInicio().getTime()));
            st.setDate(3, new java.sql.Date(campamento.getFechaFin().getTime()));
            st.setString(4, campamento.getNivel());
            st.setInt(5, campamento.getMaxAsistentes());
            return st.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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
                // Debes crear un constructor en CampamentoDTO para inicializar los atributos desde un ResultSet
                // Ejemplo: return new CampamentoDTO(rs.getInt("identificador"), rs.getDate("fechaInicio"), rs.getDate("fechaFin"), rs.getString("nivel"), rs.getInt("maxAsistentes"));
                campamentos.add(new CampamentoDTO(rs.getInt("identificador"), rs.getDate("fechaInicio"), rs.getDate("fechaFin"), rs.getString("nivel"), rs.getInt("maxAsistentes")));
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
                // Debes crear un constructor en CampamentoDTO para inicializar los atributos desde un ResultSet
                // Ejemplo: return new CampamentoDTO(rs.getInt("identificador"), rs.getDate("fechaInicio"), rs.getDate("fechaFin"), rs.getString("nivel"), rs.getInt("maxAsistentes"));
                return new CampamentoDTO(rs.getInt("identificador"), rs.getDate("fechaInicio"), rs.getDate("fechaFin"), rs.getString("nivel"), rs.getInt("maxAsistentes"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
