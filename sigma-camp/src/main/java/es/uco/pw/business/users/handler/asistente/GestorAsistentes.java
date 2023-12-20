package es.uco.pw.business.users.handler.asistente;

import java.util.ArrayList;

import es.uco.pw.business.users.dto.asistente.AsistenteDTO;
import es.uco.pw.business.users.handler.user.GestorUsuarios;
import es.uco.pw.business.users.models.asistente.Asistente;
import es.uco.pw.data.dao.AsistenteDAO;

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

	public Integer altaAsistente(Asistente nuevoAsistente, String email) {
		if (GestorUsuarios.getInstance().existEmail(email)) {
			return -1;
		}
		Integer value = asistenteDAO.insert(new AsistenteDTO(nuevoAsistente));
		return value;
	}

	public boolean eliminarAsistente(int identificador) {

		if (buscarAsistente(identificador)) {
			asistenteDAO.delete(identificador);
			return true;
		}
		return false;
	}
}
