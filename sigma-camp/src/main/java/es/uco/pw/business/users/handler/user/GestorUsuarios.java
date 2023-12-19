package es.uco.pw.business.users.handler.user;

import java.util.ArrayList;
import es.uco.pw.business.users.dto.user.UserDTO;
import es.uco.pw.business.users.models.usuario.*;
import es.uco.pw.data.dao.UserDAO;

public class GestorUsuarios {
    private static GestorUsuarios instance = null;
    private static UserDAO dao;

    public static GestorUsuarios getInstance() {
        if (GestorUsuarios.instance == null) {
            dao = new UserDAO();
            GestorUsuarios.instance = new GestorUsuarios();
        }
        return GestorUsuarios.instance;
    }

    /**
     * Dar de alta a un usuario, comprobando que no está registrado previamente.
     * 
     * @param Usuario u
     * @return boolean
     */
    public boolean addUser(Usuario u) {
        if (existEmail(u.getEmail()))
            return false;
        return dao.insert(new UserDTO(u));
    }

    /**
     * Busca si el correo existe en la lista actual de usuarios
     * 
     * @param correo
     * @return boolean
     */
    public boolean existEmail(String email) {
        return (dao.get(email) != null);
    }

    /**
     * Busca si existe el usuario por id y lo devuelve
     * 
     * @param id
     * @return Usuario
     */
    public Usuario getUserByEmail(String email) {
        return new Usuario(dao.get(email));
    }

    public boolean checkout(String email, String password) {
        if (!existEmail(email)) {
            return false;
        }
        return (dao.get(email).getPassword().equals(password));
    }

    /**
     * Devuelve la lista de todos los usuarios
     * 
     * @return
     */
    public ArrayList<Usuario> getAllUsers() {
        ArrayList<Usuario> usersList = new ArrayList<Usuario>();
        for (UserDTO it : dao.getAll()) {
            usersList.add(new Usuario(it));
        }
        return usersList;
    }

    /**
     * Elimina el usuario de la lista si existe
     * 
     * @param id
     * @return boolean
     */
    public boolean removeUser(String email) {
        return dao.delete(email);
    }

    /**
     * Edita el usuario de la lista si existe
     * 
     * @param u
     * @return boolean
     */
    public boolean editUser(Usuario user) {
        return dao.update(new UserDTO(user));
    }

    /**
     * Imprimir todos los id registrados
     * 
     * @param
     * @return void
     */

    public void printNameUsers() {

        int count = 0;
        for (Usuario us : getAllUsers()) {
            System.out.println(count + ") " + us.getFullName());
            count++;
        }
    }

}
