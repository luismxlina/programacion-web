package es.uco.pw.data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import es.uco.pw.classes.asistente.Asistente;

public class RepositorioAsistentes implements InterfazRepositorio<ArrayList<Asistente>> {

	public void guardarEnFichero(ArrayList<Asistente> obj, String archivo) {
		try (FileOutputStream fileOut = new FileOutputStream(archivo);
				ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
			out.writeObject(obj);
			System.out.println("Asistentes serializados y guardados en " + archivo);

		} catch (IOException e) {

			e.printStackTrace();

		}
	}

	public ArrayList<Asistente> cargarDatosFichero(String archivo) {
		ArrayList<Asistente> obj;
		try (FileInputStream fileIn = new FileInputStream(archivo);
			 ObjectInputStream in = new ObjectInputStream(fileIn)) {
			obj = (ArrayList<Asistente>) in.readObject();
			System.out.println("Actividades deserializadas desde " + archivo);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			obj = new ArrayList<>();
		}
		return obj;
	}
	
}
