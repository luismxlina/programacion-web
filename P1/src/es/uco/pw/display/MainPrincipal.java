package es.uco.pw.display;

import java.util.ArrayList;
import java.util.Scanner;

import es.uco.pw.classes.asistente.Asistente;
import es.uco.pw.gestores.GestorAsistentes;

public class MainPrincipal {

	public static void main(String[] args) {
		
		System.out.println("Bienvenido UCOCampamentos");
		
		Scanner teclado = new Scanner(System.in);
		int opcion;
		
		//Crear los gestores
		ArrayList<Asistente> arrayAsistentesFichero = GestorAsistentes.cargarFichero("asistentes.txt");
		GestorAsistentes gestor_asistentes = GestorAsistentes.getInstance(arrayAsistentesFichero);
		
		do {
			
			System.out.println("*************************************");
			System.out.println("********    MENÚ PRINCIPAL  *********");
			System.out.println("*************************************");
			System.out.println("1) Gestor de asistentes");
			System.out.println("2) Gestor de campamentos");
			System.out.println("3) Gestor de inscripciones");
			System.out.println("0) SALIR");
			
			opcion = teclado.nextInt();
			
			switch(opcion)
			{
			case 0:
				System.out.println("Saliendo del programa...");
				break;
			case 1:
				MainAsistentes.mostrarMenuAsistentes(teclado,gestor_asistentes);
				break;
			case 2:
				
				break;
			default:
				System.out.println("Opción no valida inútil");
			}
			
			
			
		}while(opcion!=0);

		teclado.close();
	}

}
