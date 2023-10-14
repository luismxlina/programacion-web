package es.uco.pw.data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import es.uco.pw.classes.campamento.Campamento;

public class RepositorioCampamentos {
    public void guardarEnFichero(ArrayList<Campamento> obj, String archivo) {
        try (FileOutputStream fileOut = new FileOutputStream(archivo);
                ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(obj);

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    public ArrayList<Campamento> cargarDatosFichero(String archivo) {
        ArrayList<Campamento> obj;
        try (FileInputStream fileIn = new FileInputStream(archivo);
                ObjectInputStream in = new ObjectInputStream(fileIn)) {
            obj = (ArrayList<Campamento>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            obj = new ArrayList<>();
        }
        return obj;
    }
}
