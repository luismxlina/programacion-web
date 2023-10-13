package es.uco.pw.display;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import es.uco.pw.classes.asistente.Asistente;
import es.uco.pw.gestores.GestorAsistentes;

public class MainAsistentes {
	
	public static Asistente pedirDatosTeclado(Scanner teclado)
	{
		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
		
		System.out.println("Introduzca los datos del nuevo asistente:");
		System.out.print("ID del asistente: ");
		int idAsis = teclado.nextInt();
		teclado.nextLine();
		System.out.print("Nombre: ");
		String nom = teclado.nextLine();
		System.out.print("Apellidos: ");
		String ape = teclado.nextLine();
		System.out.print("Fecha nacimiento (yyyy-mm-dd): ");
		String fechaTexto = teclado.nextLine();
		System.out.print("Requiere atencion especial (Si - 1 / No - 0): ");
		int atencionInt = teclado.nextInt();
		
		Date fecha = new Date();
		try {
			
			fecha = formatoFecha.parse(fechaTexto);
		
		} catch(ParseException e) {
			System.out.println("Error al convertir la fecha...");
		}
		
		Boolean atencion = false;
		if(atencionInt == 1) {
			atencion = true;
		}
		
		return new Asistente(idAsis, nom, ape, fecha, atencion);
	}

	public static void mostrarMenuAsistentes(Scanner teclado, GestorAsistentes gestor_asistentes) {


		int opcion;

		do {

			System.out.println("*************************************");
			System.out.println("*******   MENÚ de Asistentes  *******");
			System.out.println("*************************************");
			System.out.println("1) Mostrar asistentes");
			System.out.println("2) Añadir asistente");
			System.out.println("3) Borrar asistente");
			System.out.println("0) Volver al menú principal");

			opcion = teclado.nextInt();

			switch (opcion) {
				case 0:
					System.out.println("Volviendo al menú principal...");
					break;
				case 1:
					gestor_asistentes.mostrarAsistentes();
					break;
				case 2:
					Asistente nuevoAsistente = pedirDatosTeclado(teclado);
					gestor_asistentes.altaAsistente(nuevoAsistente);
					break;
				default:
					System.out.println("Opción no válida");
			}

		} while (opcion != 0);

		// teclado.close();
	}

}
