package es.uco.pw.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.uco.pw.business.campamento.dto.monitor.MonitorDTO;
import es.uco.pw.data.common.Conexion;

public class MonitorDAO implements DAO<MonitorDTO, Integer> {

    @Override
    public boolean insert(MonitorDTO monitor) {
        Conexion conexController = Conexion.getInstance();
        Connection conex = conexController.getConnection();
        String query = conexController.getSql().getProperty("INSERT_MONITOR");
        try {
            PreparedStatement st = conex.prepareStatement(query);
            st.setInt(1, monitor.getIdentificador());
            st.setString(2, monitor.getNombre());
            st.setString(3, monitor.getApellidos());
            st.setBoolean(4, monitor.getEsEducador());
            return st.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(MonitorDTO monitor) {
        Conexion conexController = Conexion.getInstance();
        Connection conex = conexController.getConnection();
        String query = conexController.getSql().getProperty("UPDATE_MONITOR");
        try {
            PreparedStatement st = conex.prepareStatement(query);
            st.setInt(1, monitor.getIdentificador());
            st.setString(2, monitor.getNombre());
            st.setString(3, monitor.getApellidos());
            st.setBoolean(4, monitor.getEsEducador());
            st.setInt(5, monitor.getIdentificador());
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
        String query = conexController.getSql().getProperty("DELETE_MONITOR");
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
    public ArrayList<MonitorDTO> getAll() {
        Conexion conexController = Conexion.getInstance();
        Connection conex = conexController.getConnection();
        String query = conexController.getSql().getProperty("SELECT_ALL_MONITOR");
        try {
            PreparedStatement st = conex.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            ArrayList<MonitorDTO> monitors = new ArrayList<>();
            while (rs.next()) {
                monitors.add(new MonitorDTO(rs.getInt("Identificador"), rs.getString("Nombre"),
                        rs.getString("Apellidos"), rs.getBoolean("EducadorEspecial")));
            }
            return monitors;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public MonitorDTO get(Integer id) {
        Conexion conexController = Conexion.getInstance();
        Connection conex = conexController.getConnection();
        String query = conexController.getSql().getProperty("SELECT_ID_MONITOR");
        try {
            PreparedStatement st = conex.prepareStatement(query);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new MonitorDTO(rs.getInt("Identificador"), rs.getString("Nombre"), rs.getString("Apellidos"),
                        rs.getBoolean("EducadorEspecial"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public MonitorDTO getMonitorCampamento(Integer idMonitor, Integer idCampamento) {
        Conexion conexController = Conexion.getInstance();
        Connection conex = conexController.getConnection();
        String query = conexController.getSql().getProperty("SELECT_MONITOR_CAMPAMENTO");
        try {
            PreparedStatement st = conex.prepareStatement(query);
            st.setInt(1, idMonitor);
            st.setInt(2, idCampamento);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new MonitorDTO(rs.getInt("Identificador"), rs.getString("Nombre"), rs.getString("Apellidos"),
                        rs.getBoolean("EducadorEspecial"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public MonitorDTO getMonitorEspecialCampamento(Integer idMonitor, Inte) {

    }
}
