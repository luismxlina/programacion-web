package es.uco.pw.data.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Statement;
import es.uco.pw.business.campamento.dto.monitor.MonitorDTO;
import es.uco.pw.data.common.Conexion;

public class MonitorDAO implements DAOIncremental<MonitorDTO, Integer> {

    @Override
    public Integer insert(MonitorDTO monitor) {
        Conexion conexController = Conexion.getInstance();
        Connection conex = conexController.getConnection();
        String query = conexController.getSql().getProperty("INSERT_MONITOR");
        Integer idGenerado = -1;
        try {
            PreparedStatement st = conex.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, monitor.getNombre());
            st.setString(2, monitor.getApellidos());
            st.setBoolean(3, monitor.getEsEducador());
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
            if (monitors.isEmpty()) {
                return null;
            } else {
                return monitors;
            }
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

    public ArrayList<MonitorDTO> getMonitoresEspecialesCampamento(Integer idCampamento) {

        Conexion conexController = Conexion.getInstance();
        Connection conex = conexController.getConnection();
        String query = conexController.getSql().getProperty("SELECT_ALL_MONITOR_ESPECIAL_CAMPAMENTO");
        ArrayList<MonitorDTO> monitoresEspeciales = new ArrayList<>();

        try {

            PreparedStatement st = conex.prepareStatement(query);
            st.setInt(1, idCampamento);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                monitoresEspeciales.add(new MonitorDTO(rs.getInt("Identificador"), rs.getString("Nombre"),
                        rs.getString("Apellidos"), rs.getBoolean("EducadorEspecial")));
            }

            if (monitoresEspeciales.isEmpty()) {
                return null;
            } else {
                return monitoresEspeciales;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public MonitorDTO getMonitorActividad(String nombreActividad, Integer idMonitor) {
        Conexion conexController = Conexion.getInstance();
        Connection conex = conexController.getConnection();
        String query = conexController.getSql().getProperty("SELECT_MONITOR_ACTIVIDAD");
        try {
            PreparedStatement st = conex.prepareStatement(query);
            st.setString(1, nombreActividad);
            st.setInt(2, idMonitor);
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

    public ArrayList<MonitorDTO> getMonitoresEspeciales() {
        Conexion conexController = Conexion.getInstance();
        Connection conex = conexController.getConnection();
        String query = conexController.getSql().getProperty("SELECT_ALL_MONITOR_ESPECIAL");
        ArrayList<MonitorDTO> monitoresEspeciales = new ArrayList<>();

        try {

            PreparedStatement st = conex.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                monitoresEspeciales.add(new MonitorDTO(rs.getInt("Identificador"), rs.getString("Nombre"),
                        rs.getString("Apellidos"), rs.getBoolean("EducadorEspecial")));
            }

            if (monitoresEspeciales.isEmpty()) {
                return null;
            } else {
                return monitoresEspeciales;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Integer> getAllIdsMonitores() {
        ArrayList<Integer> ids = new ArrayList<Integer>();
        Conexion conexController = Conexion.getInstance();
        Connection conex = conexController.getConnection();
        String query = conexController.getSql().getProperty("SELECT_ALL_IDS_MONITOR");
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

    public ArrayList<MonitorDTO> getAllMonitoresCampamento(Integer idCampamento) {

        Conexion conexController = Conexion.getInstance();
        Connection conex = conexController.getConnection();
        String query = conexController.getSql().getProperty("SELECT_MONITORES_CAMPAMENTO");
        ArrayList<MonitorDTO> monitores = new ArrayList<>();

        try {

            PreparedStatement st = conex.prepareStatement(query);
            st.setInt(1, idCampamento);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                monitores.add(new MonitorDTO(rs.getInt("Identificador"), rs.getString("Nombre"),
                        rs.getString("Apellidos"), rs.getBoolean("EducadorEspecial")));
            }

            if (monitores.isEmpty()) {
                return null;
            } else {
                return monitores;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
