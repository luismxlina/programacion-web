package es.uco.pw.display;

<<<<<<< HEAD
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import es.uco.pw.classes.campamento.Campamento;
import es.uco.pw.gestores.GestorCampamentos;

public class Campamentos {
	
	public static Campamento pedirDatosTeclado(Scanner teclado)
	{		
		System.out.println("Introduzca los datos del nuevo asistente:");
		int idCamp = teclado.nextInt();
		
		return new Campamento(idCampa);
	}

	public static void mostrarMenuCampamentos(Scanner teclado, GestorCampamentos gestor_campamentos) {


		int opcion;

		do {

			System.out.println("*************************************");
			System.out.println("*******   MENÚ de Campamentos  *******");
			System.out.println("*************************************");
			System.out.println("1) Mostrar campamentos");
			System.out.println("2) Campamentos disponibles");
			System.out.println("3) Buscar Monitor");
			System.out.println("4) Buscar Actividad");
			System.out.println("5) Borrar campamento");
			System.out.println("0) Volver al menú principal");

			opcion = teclado.nextInt();

			switch (opcion) {
				case 0:
					System.out.println("Volviendo al menú principal...");
					break;
				case 1:
					Campamento nuevoCampamento = pedirDatosTeclado(teclado);
					gestor_campamentos.mostrarCampamentos(nuevoCampamento);
					break;
				case 2:
					gestor_campamentos.campamentosDisponibles();
					break;
				case 3:
					Monitor nuevoMonitor.
					gestor_campamentos.
				case 4: 
					
				default:
					System.out.println("Opción no válida");
			}

		} while (opcion != 0);

		// teclado.close();
	}

=======
public class MainCampamentos {
    
>>>>>>> ce7f67d83ad9934ee1ea4e034aab9b13f43e52ee
}
