package es.uco.pw.data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import es.uco.pw.business.users.models.asistente.Asistente;

/**
 * Esta clase proporciona métodos para guardar y cargar datos de asistentes en
 * un fichero.
 * Implementa la interfaz InterfazRepositorio para trabajar con una lista de
 * objetos Asistente.
 */

public class RepositorioAsistentes implements InterfazRepositorio<ArrayList<Asistente>> {

	/**
	 * Guarda una lista de asistentes en un fichero serializado.
	 *
	 * @param obj     La lista de asistentes a guardar.
	 * @param archivo El nombre del archivo donde se almacenarán los datos.
	 */

	public void guardarEnFichero(ArrayList<Asistente> obj, String archivo) {
		try (FileOutputStream fileOut = new FileOutputStream(archivo);
				ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
			out.writeObject(obj);

		} catch (IOException e) {
			// Manejo de excepciones al guardar en el fichero
			e.printStackTrace();

		}
	}

	/**
	 * Carga una lista de asistentes desde un fichero serializado.
	 *
	 * @param archivo El nombre del archivo desde el cual se cargarán los datos.
	 * @return La lista de asistentes cargada desde el fichero o una lista vacía en
	 *         caso de error.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Asistente> cargarDatosFichero(String archivo) {
		ArrayList<Asistente> obj;
		try (FileInputStream fileIn = new FileInputStream(archivo);
				ObjectInputStream in = new ObjectInputStream(fileIn)) {
			obj = (ArrayList<Asistente>) in.readObject();
		} catch (IOException | ClassNotFoundException e) {
			// Manejo de excepciones al cargar datos desde el fichero
			obj = new ArrayList<Asistente>();
		}
		return obj;
	}

}
