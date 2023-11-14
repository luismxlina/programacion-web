package es.uco.pw.data.dao;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Esta interfaz define las operaciones básicas de un objeto de acceso a datos (DAO).
 *
 * @param <T> Tipo de objeto DTO (Data Transfer Object) que manejará el DAO.
 * @param <K> Tipo de clave primaria utilizada para identificar los objetos.
 */
public interface DAO<T, K> {
    /**
     * Inserta un nuevo objeto en la base de datos.
     *
     * @param a El objeto a insertar.
     * @return True si la inserción fue exitosa, false en caso contrario.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */
    boolean insert(T a) throws SQLException;

    /**
     * Actualiza un objeto existente en la base de datos.
     *
     * @param a El objeto con los nuevos datos.
     * @return True si la actualización fue exitosa, false en caso contrario.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */
    boolean update(T a) throws SQLException;

    /**
     * Elimina un objeto de la base de datos por su clave primaria.
     *
     * @param id La clave primaria del objeto a eliminar.
     * @return True si la eliminación fue exitosa, false en caso contrario.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */
    boolean delete(K id) throws SQLException;

    /**
     * Obtiene todos los objetos almacenados en la base de datos.
     *
     * @return Una lista de todos los objetos.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */
    ArrayList<T> getAll() throws SQLException;

    /**
     * Obtiene un objeto por su clave primaria.
     *
     * @param id La clave primaria del objeto a buscar.
     * @return El objeto encontrado o null si no se encuentra.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */
    T get(K id) throws SQLException;
}