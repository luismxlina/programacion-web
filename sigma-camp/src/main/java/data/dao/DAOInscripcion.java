package data.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DAOInscripcion<T, K> {
    boolean insert(T a) throws SQLException;

    boolean update(T a) throws SQLException;

    boolean delete(K id1, K id2) throws SQLException;

    ArrayList<T> getAll() throws SQLException;

    T get(K id1, K id2) throws SQLException;
}