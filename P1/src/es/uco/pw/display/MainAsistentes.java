package es.uco.pw.display;

import java.util.Scanner;

import es.uco.pw.gestores.GestorAsistentes;

public class MainAsistentes {

	public static void mostrarMenuAsistentes(Scanner teclado,GestorAsistentes gestor_asistentes) {
		
		//Scanner teclado = new Scanner(System.in);
		int opcion;
		
		do {
			
			System.out.println("*************************************");
			System.out.println("********    MENÚ de Asistentes  *********");
			System.out.println("*************************************");
			System.out.println("1) Mostrar asistentes");
			System.out.println("2) Añadir asistente");
			System.out.println("3) Borrar asistente");
			System.out.println("0) Volver al menú principal");
			
			opcion = teclado.nextInt();
			
			switch(opcion)
			{
			case 0:
				System.out.println("Volviendo al menú principal...");
				break;
			case 1:
				gestor_asistentes.mostrarAsistentes();
				break;
			case 2:
				break;
			default:
				System.out.println("Opción no valida inútil");
			}
			
		}while(opcion!=0);

		//teclado.close();
	}

}


