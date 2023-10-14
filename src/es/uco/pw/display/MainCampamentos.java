package es.uco.pw.display;

import java.util.Scanner;
import java.util.HashSet;
import java.util.Random;

import es.uco.pw.classes.campamento.Campamento;
import es.uco.pw.gestores.GestorCampamentos;

public class MainCampamentos {

    private static HashSet<Integer> idSet = new HashSet<>();

    public static void mostrarMenuCampamentos(Scanner teclado, GestorCampamentos gestor_campamentos) {

        int opcion;

        System.out.println("·-----------------------------------·");
        System.out.println("|         MENÚ de Campamentos       |");
        System.out.println("·-----------------------------------·");

        do {
            System.out.println("");
            System.out.println("(1) Mostrar campamentos");
            System.out.println("(2) Mostrar campamento");
            System.out.println("(3) Añadir campamento");
            System.out.println("(4) Editar campamento");
            System.out.println("(0) Volver al menú principal");
            System.out.println("");

            opcion = teclado.nextInt();

            switch (opcion) {
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                case 1:
                    gestor_campamentos.mostrarCampamentos();
                    break;
                case 2:
                    System.out.println("Introduzca id del campamento a buscar");
                    teclado.nextLine();
                    int idCampamento = teclado.nextInt();
                    gestor_campamentos.mostrarCampamento(idCampamento);
                    break;
                case 3:
                    Campamento nuevoCampamento = new Campamento(generarIDUnico());
                    if (!GestorCampamentos.pedirDatosTeclado(teclado, nuevoCampamento)) {
                        break;
                    }
                    gestor_campamentos.altaCampamento(nuevoCampamento);
                    break;
                case 4:
                    MainEditarCampamento.mostrarMenuEditarCampamento(teclado, gestor_campamentos);
                    break;
                default:
                    System.out.println("Opción no válida");
            }

        } while (opcion != 0);
    }

    public static int generarIDUnico() {
        Random random = new Random();
        int id;
        do {
            id = random.nextInt(100000);
        } while (idSet.contains(id));
        idSet.add(id);
        return id;
    }
}
