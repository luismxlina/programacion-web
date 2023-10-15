package es.uco.pw.data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import es.uco.pw.classes.campamento.Campamento;
import es.uco.pw.classes.inscripcion.Inscripcion;
import es.uco.pw.classes.inscripcion.InscripcionCampamento;

public class RepositoryInscripciones {
    public void guardarEnFichero(ArrayList<InscripcionCampamento> obj, String archivo) {
        try (FileOutputStream fileOut = new FileOutputStream(archivo);
                ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(obj);

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    public ArrayList<InscripcionCampamento> cargarDatosFichero(String archivo) {
        ArrayList<InscripcionCampamento> obj;
        try (FileInputStream fileIn = new FileInputStream(archivo);
                ObjectInputStream in = new ObjectInputStream(fileIn)) {
            obj = (ArrayList<InscripcionCampamento>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            obj = new ArrayList<>();
        }
        return obj;
    }
}
