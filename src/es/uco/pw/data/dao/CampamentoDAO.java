package es.uco.pw.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.uco.pw.business.campamento.dto.campamento.CampamentoDTO;
import es.uco.pw.business.campamento.dto.actividad.ActividadDTO;
import es.uco.pw.business.campamento.models.actividad.NivelEducativo;
import es.uco.pw.business.users.dto.monitor.MonitorDTO;
import es.uco.pw.data.common.Conexion;

/**
 * Esta clase representa un objeto de acceso a datos (DAO) para la entidad Campamento.
 */
public class CampamentoDAO implements DAO<CampamentoDTO, Integer> {

    /**
     * Inserta un nuevo campamento en la base de datos.
     *
     * @param campamento El campamento a insertar.
     * @return True si la inserción fue exitosa, false en caso contrario.
     */
    @Override
    public boolean insert(CampamentoDTO campamento) {
        Conexion conexController = Conexion.getInstance();
        Connection conex = conexController.getConnection();
        String query = conexController.getSql().getProperty("INSERT_CAMPAMENTO");
        System.out.println(campamento.getNivel());
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

    /**
     * Actualiza un campamento existente en la base de datos.
     *
     * @param campamento El campamento con los nuevos datos.
     * @return True si la actualización fue exitosa, false en caso contrario.
     */
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

    /**
     * Elimina un campamento de la base de datos por su identificador.
     *
     * @param id El identificador del campamento a eliminar.
     * @return True si la eliminación fue exitosa, false en caso contrario.
     */
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

    /**
     * Obtiene todos los campamentos almacenados en la base de datos.
     *
     * @return Una lista de todos los campamentos.
     */
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
                campamentos.add(new CampamentoDTO(rs.getInt("Identificador"), rs.getDate("FechaInicio"), rs.getDate("FechaFin"),
                        rs.getString("NivelEducativo".toUpperCase()), rs.getInt("NumeroMaximoAsistentes")));
            }
            return campamentos;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Obtiene un campamento por su identificador.
     *
     * @param id El identificador del campamento a buscar.
     * @return El campamento encontrado o null si no se encuentra.
     */
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
                // Debes crear un constructor en CampamentoDTO para inicializar los atributos
                // desde un ResultSet
                // Ejemplo: return new CampamentoDTO(rs.getInt("identificador"),
                // rs.getDate("fechaInicio"), rs.getDate("fechaFin"), rs.getString("nivel"),
                // rs.getInt("maxAsistentes"));
                return new CampamentoDTO(rs.getInt("Identificador"), rs.getDate("FechaInicio"), rs.getDate("FechaFin"),
                        rs.getString("NivelEducativo".toUpperCase()), rs.getInt("NumeroMaximoAsistentes"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Inserta un monitor en un campamento específico.
     *
     * @param campamentoId El identificador del campamento.
     * @param monitorId El identificador del monitor a insertar.
     * @return True si la inserción fue exitosa, false en caso contrario.
     */
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

    /**
     * Inserta una actividad en un campamento específico.
     *
     * @param campamentoId El identificador del campamento.
     * @param actividad El nombre de la actividad a insertar.
     * @return True si la inserción fue exitosa, false en caso contrario.
     */
    public boolean insertCampamentoActividad(Integer campamentoId, String actividad) {
        Conexion conexController = Conexion.getInstance();
        Connection conex = conexController.getConnection();
        String query = conexController.getSql().getProperty("INSERT_CAMPAMENTO_ACTIVIDAD");
        try {
            PreparedStatement st = conex.prepareStatement(query);
            st.setInt(1, campamentoId);
            st.setString(2, actividad);
            return st.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Obtiene los monitores asociados a un campamento específico.
     *
     * @param campamentoId El identificador del campamento.
     * @param monitorId El identificador del monitor a buscar.
     * @return Una lista de monitores asociados al campamento.
     */
    public ArrayList<MonitorDTO> getCampamentoMonitor(Integer campamentoId, Integer monitorId){
    Conexion conexController = Conexion.getInstance();
    Connection conex = conexController.getConnection();
    String query = conexController.getSql().getProperty("SELECT_CAMPAMENTO_MONITOR");
    ArrayList<MonitorDTO> monitores = new ArrayList<MonitorDTO>();
    try {
        PreparedStatement st = conex.prepareStatement(query);
        ArrayList<MonitorDTO> monitors = new ArrayList<>();
        st.setInt(1, campamentoId);
         st.setInt(2, monitorId);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            // Debes crear un constructor en MonitorDTO para inicializar los atributos
            // desde un ResultSet
            monitors.add(new MonitorDTO(rs.getInt("Identificador"), rs.getString("Nombre"),
                        rs.getString("Apellidos"), rs.getBoolean("EducadorEspecial")));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return monitores;
}

    /**
     * Obtiene las actividades asociadas a un campamento específico.
     *
     * @param campamentoId El identificador del campamento.
     * @return Una lista de actividades asociadas al campamento.
     */
    public ArrayList<ActividadDTO> getActividadesCampamento(Integer campamentoId) {
    Conexion conexController = Conexion.getInstance();
    Connection conex = conexController.getConnection();
    String query = conexController.getSql().getProperty("SELECT_CAMPAMENTO_ACTIVIDAD");
    ArrayList<ActividadDTO> actividades = new ArrayList<ActividadDTO>();
    try {
        PreparedStatement st = conex.prepareStatement(query);
        st.setInt(1, campamentoId);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            // Assuming you have a constructor in ActividadDTO to initialize the attributes from ResultSet
            // Example: return new ActividadDTO(rs.getString("nombreActividad"), NivelEducativo.valueOf(rs.getString("nivel")),
            //                                  rs.getTime("hora").toLocalTime(), rs.getInt("maxParticipantes"), rs.getInt("numMonitores"));
            actividades.add(new ActividadDTO(
                    rs.getString("Nombre"),
                    NivelEducativo.valueOf(rs.getString("NivelEducativo")),
                    rs.getTime("Horario").toLocalTime(),
                    rs.getInt("NumeroMaximoParticipantes"),
                    rs.getInt("NumeroMonitoresNecesarios")
            ));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return actividades;
}

    /**
     * Inserta una actividad en la base de datos.
     *
     * @param actividad La actividad a insertar.
     * @return True si la inserción fue exitosa, false en caso contrario.
     */
    public boolean insertActividad(ActividadDTO actividad)
    {
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

    /**
     * Obtiene los monitores asociados a un campamento específico.
     *
     * @param idCampamento El identificador del campamento.
     * @return Una lista de monitores asociados al campamento.
     */
    public ArrayList<MonitorDTO> getMonitoresCampamento(Integer idCampamento) {
        Conexion conexController = Conexion.getInstance();
        Connection conex = conexController.getConnection();
        String query = conexController.getSql().getProperty("SELECT_MONITORES_CAMPAMENTO");
        ArrayList<MonitorDTO> monitores = new ArrayList<MonitorDTO>();
        try {
            PreparedStatement st = conex.prepareStatement(query);
            st.setInt(1, idCampamento);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                monitores.add(new MonitorDTO(rs.getInt("Identificador"), rs.getString("Nombre"),
                            rs.getString("Apellidos"), rs.getBoolean("EducadorEspecial")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return monitores;
    }
}
