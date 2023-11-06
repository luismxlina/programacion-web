package es.uco.pw.view;

import java.util.Scanner;

import es.uco.pw.business.campamento.handler.GestorCampamentos;
import es.uco.pw.business.campamento.models.campamento.Campamento;

import java.util.HashSet;
import java.util.Random;

/**
 * La clase MainCampamentos proporciona un menú de opciones relacionadas con la gestión de campamentos.
 * Se utiliza para interactuar con el usuario a través de la consola y realizar operaciones en el sistema.
 */

public class MainCampamentos {

    private static HashSet<Integer> idSet = new HashSet<>();

    /**
     * Muestra un menú de opciones relacionadas con la gestión de campamentos.
     *
     * @param teclado               Scanner para la entrada del usuario.
     * @param gestor_campamentos    Objeto GestorCampamentos para gestionar las operaciones con campamentos.
     */

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
                    System.out.println("Introduzca id del campamento a editar");
                    teclado.nextLine();
                    idCampamento = teclado.nextInt();
                    MainEditarCampamento.mostrarMenuEditarCampamento(teclado, gestor_campamentos,idCampamento);
                    break;
                default:
                    System.out.println("Opción no válida");
            }

        } while (opcion != 0);
    }

    /**
     * Genera un ID único para un campamento utilizando un conjunto HashSet para evitar duplicados.
     *
     * @return Un ID único generado aleatoriamente.
     */

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
