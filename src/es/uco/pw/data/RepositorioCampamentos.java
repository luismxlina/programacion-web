package es.uco.pw.data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import es.uco.pw.classes.campamento.Campamento;

/**
 * Esta clase proporciona métodos para guardar y cargar datos de un archivo
 * para la gestión de campamentos.
 */
public class RepositorioCampamentos {

    /**
     * Guarda un ArrayList de objetos Campamento en un archivo.
     *
     * @param obj     ArrayList de objetos Campamento a guardar.
     * @param archivo Nombre del archivo en el que se guardarán los datos.
     */
    public void guardarEnFichero(ArrayList<Campamento> obj, String archivo) {
        try (FileOutputStream fileOut = new FileOutputStream(archivo);
                ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(obj);

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

     /**
     * Carga un ArrayList de objetos Campamento desde un archivo.
     *
     * @param archivo Nombre del archivo del que se cargarán los datos.
     * @return ArrayList de objetos Campamento cargado desde el archivo.
     */
    @SuppressWarnings("unchecked")
    public ArrayList<Campamento> cargarDatosFichero(String archivo) {
        ArrayList<Campamento> obj;
        try (FileInputStream fileIn = new FileInputStream(archivo);
                ObjectInputStream in = new ObjectInputStream(fileIn)) {
            obj = (ArrayList<Campamento>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {

            obj = new ArrayList<Campamento>();
        }
        return obj;
    }
}
