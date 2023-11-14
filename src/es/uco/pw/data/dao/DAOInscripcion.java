package es.uco.pw.data.dao;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Esta interfaz define las operaciones básicas de un objeto de acceso a datos (DAO) para la entidad Inscripción.
 *
 * @param <T> Tipo de objeto DTO (Data Transfer Object) que manejará el DAO.
 * @param <K> Tipo de clave utilizada para identificar los objetos, compuesta por dos partes (id1, id2).
 */
public interface DAOInscripcion<T, K> {
    /**
     * Inserta un nuevo objeto Inscripción en la base de datos.
     *
     * @param a El objeto Inscripción a insertar.
     * @return True si la inserción fue exitosa, false en caso contrario.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */
    boolean insert(T a) throws SQLException;

    /**
     * Actualiza un objeto Inscripción existente en la base de datos.
     *
     * @param a El objeto Inscripción con los nuevos datos.
     * @return True si la actualización fue exitosa, false en caso contrario.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */
    boolean update(T a) throws SQLException;

    /**
     * Elimina un objeto Inscripción de la base de datos por su clave primaria compuesta (id1, id2).
     *
     * @param id1 La primera parte de la clave primaria del objeto a eliminar.
     * @param id2 La segunda parte de la clave primaria del objeto a eliminar.
     * @return True si la eliminación fue exitosa, false en caso contrario.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */
    boolean delete(K id1, K id2) throws SQLException;

    /**
     * Obtiene todos los objetos Inscripción almacenados en la base de datos.
     *
     * @return Una lista de todos los objetos Inscripción.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */
    ArrayList<T> getAll() throws SQLException;

    /**
     * Obtiene un objeto Inscripción por su clave primaria compuesta (id1, id2).
     *
     * @param id1 La primera parte de la clave primaria del objeto a buscar.
     * @param id2 La segunda parte de la clave primaria del objeto a buscar.
     * @return El objeto Inscripción encontrado o null si no se encuentra.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */
    T get(K id1, K id2) throws SQLException;
}