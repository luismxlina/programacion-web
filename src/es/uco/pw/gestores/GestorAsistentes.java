package es.uco.pw.gestores;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import es.uco.pw.classes.asistente.Asistente;

public class GestorAsistentes {

	private ArrayList<Asistente> asistentes;

	private static GestorAsistentes instance = null;

	private GestorAsistentes(ArrayList<Asistente> arrayNuevo) {
		this.asistentes = arrayNuevo;
	}

	public static GestorAsistentes getInstance(ArrayList<Asistente> arrayNuevo) {
		if (instance == null) {
			instance = new GestorAsistentes(arrayNuevo);
		}
		return instance;
	}

	public ArrayList<Asistente> getAsistentes() {
		return this.asistentes;
	}

	public Boolean altaAsistente(Asistente nuevoAsistente) {
		if (buscarAsistente(nuevoAsistente.getIdentificador()) == false) {
			this.asistentes.add(nuevoAsistente);
			return true;
		}
		return false;
	}

	public Boolean buscarAsistente(int id) {
		if (asistentes.size() == 0)
			return false;

		for (int i = 0; i < asistentes.size(); i++) {
			if (this.asistentes.get(i).getIdentificador() == id) {
				return true;
			}
		}

		return false;
	}

	public void mostrarAsistentes() {
		if (instance != null) {
			for (int i = 0; i < this.asistentes.size(); i++) {
				System.out.println("ID: " + this.asistentes.get(i).getIdentificador()
						+ ", Nombre: " + this.asistentes.get(i).getNombre()
						+ ", Apellidos: " + this.asistentes.get(i).getApellidos()
						+ ", Fecha nacimiento: " + this.asistentes.get(i).getFechaNacimiento()
						+ ", Necesita atención: " + this.asistentes.get(i).getRequiereAtencion());
			}
		}
	}

public void modificarAsistente() {
		
		Scanner teclado = new Scanner(System.in);
		int ID;
		
		System.out.print("Introduzca el ID del asistente a modificar: ");
		ID = teclado.nextInt();
		
		if(buscarAsistente(ID)) {
			
			SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

			String newNombre;
			String newApellidos;
			String newFechaTexto;
			Date newFecha = new Date();
			int newAtencionTexto;
			Boolean newAtencion = false;
			
			System.out.println("Introduzca los nuevos datos del asistente: ");
			String saltoDeLinea = teclado.nextLine();
			System.out.print("Nuevo nombre: ");
			newNombre = teclado.nextLine();
			System.out.print("Nuevos apellidos: ");
			newApellidos = teclado.nextLine();
			System.out.print("Nueva fecha nacimiento: ");
			newFechaTexto = teclado.nextLine();
			System.out.print("Requiere atencion especial (Si - 1 / No - 0): ");
			newAtencionTexto = teclado.nextInt();
			
			if(newAtencionTexto == 1) {
				
				newAtencion = true;
				
			}
			
			try {
				
				newFecha = formatoFecha.parse(newFechaTexto);
			
			} catch(ParseException e) {
				
				System.out.println("Error al convertir la fecha");
			
			}
			
			for(Asistente asistente : this.asistentes) {
				
				if(asistente.getIdAsistente() == ID) {
					
					asistente.setNombre(newNombre);
					asistente.setApellidos(newApellidos);
					asistente.setFecha_nacimiento(newFecha);
					asistente.setRequiere_atencion(newAtencion);
					
				}
				
			}
			
		}
		
		else {
			
			System.out.println("No existe ninun usuario con el ID indicado");
			
		}
			
	}
}
