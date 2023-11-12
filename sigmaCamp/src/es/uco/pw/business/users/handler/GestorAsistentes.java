package es.uco.pw.business.users.handler;

import java.util.ArrayList;

import es.uco.pw.business.users.dto.asistente.AsistenteDTO;
import es.uco.pw.business.users.models.asistente.Asistente;
import es.uco.pw.data.dao.AsistenteDAO;

/**
 * Clase que gestiona los asistentes.
 */
public class GestorAsistentes {

	private static GestorAsistentes instance = null;
	private static AsistenteDAO asistenteDAO;

	public static GestorAsistentes getInstance() {
		if (instance == null) {
			instance = new GestorAsistentes();
			asistenteDAO = new AsistenteDAO();
		}
		return instance;
	}

	public Boolean buscarAsistente(int id) {
		return asistenteDAO.get(id) != null;
	}

	public ArrayList<Asistente> getAsistentes() {
		ArrayList<Asistente> asistentes = new ArrayList<Asistente>();
		for (AsistenteDTO asistente : asistenteDAO.getAll()) {
			asistentes.add(new Asistente(asistente));
		}
		return asistentes;
	}

	public ArrayList<Integer> getAllIds() {
		ArrayList<Integer> ids = new ArrayList<Integer>();
		for (AsistenteDTO asistente : asistenteDAO.getAll()) {
			ids.add(asistente.getIdentificador());
		}
		return ids;
	}

	public Asistente getAsistente(int id) {
		return new Asistente(asistenteDAO.get(id));
	}

	public Boolean altaAsistente(Asistente nuevoAsistente) {
		if (buscarAsistente(nuevoAsistente.getIdentificador())) {
			return false;
		}
		asistenteDAO.insert(new AsistenteDTO(nuevoAsistente));
		return true;
	}

	// public Asistente modificarAsistenteTeclado(int identificador) {

	// Scanner teclado = new Scanner(System.in);
	// // int identificador;

	// // System.out.print("Escriba el identificador del asistente a modificar: ");
	// // identificador = teclado.nextInt();

	// if (buscarAsistente(identificador)) {

	// SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

	// String newNombre;
	// String newApellidos;
	// String newFechaTexto;
	// Date newFecha = new Date();
	// int newAtencionTexto;
	// Boolean newAtencion = false;

	// System.out.println("Introduzca los nuevos datos del asistente: ");
	// // String saltoDeLinea = teclado.nextLine();

	// // Leer los nuevos datos del asistente
	// System.out.print("Nuevo nombre: ");
	// newNombre = teclado.nextLine();
	// System.out.print("Nuevos apellidos: ");
	// newApellidos = teclado.nextLine();
	// System.out.print("Nueva fecha nacimiento (yyyy-mm-dd): ");
	// newFechaTexto = teclado.nextLine();
	// System.out.print("Requiere atencion especial (Si - 1 / No - 0): ");
	// newAtencionTexto = teclado.nextInt();

	// if (newAtencionTexto == 1) {
	// newAtencion = true;
	// }

	// try {

	// newFecha = formatoFecha.parse(newFechaTexto);

	// } catch (ParseException e) {

	// System.out.println("Error al convertir la fecha");

	// }

	// asistente.setNombre(newNombre);
	// asistente.setApellidos(newApellidos);
	// asistente.setFechaNacimiento(newFecha);
	// asistente.setRequiereAtencion(newAtencion);

	// teclado.close();

	// return true;

	// } else {

	// teclado.close();

	// return false;

	// }

	// }

	public boolean eliminarAsistente(int identificador) {

		if (buscarAsistente(identificador)) {
			asistenteDAO.delete(identificador);
			return true;
		}
		return false;
	}
}
