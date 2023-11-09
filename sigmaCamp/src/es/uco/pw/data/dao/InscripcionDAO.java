package es.uco.pw.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.uco.pw.business.inscripcion.dto.inscripcion.InscripcionDTO;
import es.uco.pw.data.common.Conexion;

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
            st.setDate(3, java.sql.Date.valueOf(inscripcion.getFechaInscripcion()));
            st.setDouble(4, inscripcion.getPrecio());
            st.setString(5, inscripcion.getTipoInscripcion());
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
            st.setDate(1, java.sql.Date.valueOf(inscripcion.getFechaInscripcion()));
            st.setDouble(2, inscripcion.getPrecio());
            st.setString(3, inscripcion.getTipoInscripcion());
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
                        rs.getDate("FechaInscripcion").toString(), rs.getDouble("Precio"),
                        rs.getString("TipoInscripcion")));
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
                        rs.getDate("FechaInscripcion").toString(), rs.getDouble("Precio"),
                        rs.getString("TipoInscripcion"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

