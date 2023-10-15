package es.uco.pw.data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import es.uco.pw.classes.inscripcion.Inscripcion;

/**
 * La clase RepositoryInscripciones proporciona métodos para guardar y cargar datos de inscripciones en un archivo.
 */

public class RepositoryInscripciones {

     /**
     * Guarda una lista de inscripciones en un archivo.
     *
     * @param obj      La lista de inscripciones a guardar.
     * @param archivo  La ruta del archivo en el que se guardará la información.
     */

    public void guardarEnFichero(ArrayList<Inscripcion> obj, String archivo) {
        try (FileOutputStream fileOut = new FileOutputStream(archivo);
                ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(obj);

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

     /**
     * Carga una lista de inscripciones desde un archivo.
     *
     * @param archivo  La ruta del archivo del que se cargarán las inscripciones.
     * @return         La lista de inscripciones cargada desde el archivo.
     */

    public ArrayList<Inscripcion> cargarDatosFichero(String archivo) {
        ArrayList<Inscripcion> obj;
        try (FileInputStream fileIn = new FileInputStream(archivo);
                ObjectInputStream in = new ObjectInputStream(fileIn)) {
            obj = (ArrayList<Inscripcion>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            obj = new ArrayList<>();
        }
        return obj;
    }
}
