package es.uco.pw.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import es.uco.pw.classes.actividad.Actividad;
import es.uco.pw.classes.campamento.Campamento;
import es.uco.pw.classes.monitor.Monitor;
import es.uco.pw.gestores.GestorCampamentos;

public class Repositorio_Campamento implements Interfaz_Repositorio<ArrayList<Campamento>> {

	public void guardarEnFichero(ArrayList<Campamento> obj, String nombreFichero) {

	}

	public ArrayList<Campamento> cargarDatosFichero(String nombreFichero) {
		ArrayList<Campamento> array = new ArrayList<Campamento>();
		File fichero = new File(nombreFichero);
		try {
			Scanner scanner = new Scanner(fichero);
			SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
			while (scanner.hasNextLine()) {
				String linea = scanner.nextLine();
				String[] campos = linea.split(";");
				int idCampa = Integer.parseInt(campos[0]);
				int max = Integer.parseInt(campos[4]);
				EnumActividad nivel = EnumActividad.valueOf(campos[3]);
				Date fechaInicio = new Date();
				Date fechaFin = new Date();
				try {
					fechaInicio = formatoFecha.parse(campos[1]);
				} catch (ParseException e) {
					System.out.println("Error al convertir la fecha");
				}
				try {
					fechaFin = formatoFecha.parse(campos[2]);
				} catch (ParseException e) {
					System.out.println("Error al convertir la fecha");
				}
				// Aquí deberías crear un nuevo objeto de tipo Campamento y agregarlo a la lista
				Campamento nuevoCampamento = new Campamento(idCampa, fechaInicio, fechaFin, nivel, max);

				// Cargo en un array de monitores todos los monitores del fichero
				ArrayList<Monitor> monitores = cargarMonitoresFichero("BD_monitores.txt");
				ArrayList<Actividad> actividades = cargarActividadesFichero("BD_actividades.txt", monitores);

				String[] campos2 = campos[5].split(",");
				for (int i = 0; i < campos2.length; i++) {
					String nombreActividad = campos2[i];
					Actividad actividad = obj.buscarActividad(nombreActividad, actividades);
					nuevoCampamento.asociarActividad(actividad);
				}

				array.add(nuevoCampamento);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return array;
	}

}
