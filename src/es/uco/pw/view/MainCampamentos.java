package es.uco.pw.view;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;

import es.uco.pw.business.campamento.handler.GestorCampamentos;
import es.uco.pw.business.campamento.models.campamento.Campamento;
import es.uco.pw.business.campamento.models.actividad.NivelEducativo;


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
                    mostrarCampamentos(gestor_campamentos.getCampamentos());
                    break;
                case 2:
                    System.out.println("Introduzca id del campamento a buscar");
                    teclado.nextLine();
                    int idCampamento = teclado.nextInt();
                    // gestor_campamentos.mostrarCampamento(idCampamento);
                    mostrarCampamento(gestor_campamentos.getCampamentos(),idCampamento);
                    break;
                case 3:
                    // Campamento nuevoCampamento = new Campamento(generarIDUnico());
                    // if (!GestorCampamentos.pedirDatosTeclado(teclado, nuevoCampamento)) {
                    //     break; 
                    // }
                    // gestor_campamentos.altaCampamento(nuevoCampamento);
                    Campamento nuevoCampamento = pedirDatosTeclado(teclado, new Campamento(generarIDUnico()));
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

    /**
     * Muestra los detalles de varios campamentos en la consola.
     *
     * @param campamentos Lista de objetos Campamento que se mostrarán.
     */
    public static void mostrarCampamentos(ArrayList<Campamento> campamentos) {
                for (int i = 0; i < campamentos.size(); i++) {
                    System.out.println("ID: " + campamentos.get(i).getIdentificador()
                            + ", Fecha de inicio: " + campamentos.get(i).getFechaInicio()
                            + ", Fecha de fin: " + campamentos.get(i).getFechaFin()
                            + ", Nivel: " + campamentos.get(i).getNivel()
                            + ", Participantes maximos: " + campamentos.get(i).getMax_asistentes()
                            //+ ", Lista de actividades: " + campamentos.get(i).getActividades()
                            /*+ ", Lista de monitores: " + campamentos.get(i).getMonitoresResponsables()*/);
                    System.out.println("");
        
                }
            }

    /**
     * Solicita datos al usuario a través del teclado para crear un nuevo Campamento.
     *
     * @param teclado           Scanner para la entrada de datos desde el teclado.
     * @param nuevoCampamento   Objeto Campamento que se actualizará con los datos ingresados.
     * @return El objeto Campamento actualizado con los datos ingresados.
     */
    public static Campamento pedirDatosTeclado(Scanner teclado, Campamento nuevoCampamento) {
                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                String fechaInicioTexto;
                String fechaFinTexto;
                NivelEducativo nivel = NivelEducativo.INFANTIL;
                int max_asistentes = 0;
        
                System.out.println("Introduzca los datos del nuevo campamento:");
        
                System.out.print("Fecha inicio (yyyy-mm-dd): ");
                teclado.nextLine();
                fechaInicioTexto = teclado.nextLine();
        
                System.out.print("Fecha fin (yyyy-mm-dd): ");
                fechaFinTexto = teclado.nextLine();
        
                Date fechaInicioDate = new Date();
                Date fechaFinDate = new Date();
                Date fechaActual = new Date();
                try {
        
                    fechaInicioDate = formatoFecha.parse(fechaInicioTexto);
                    fechaFinDate = formatoFecha.parse(fechaFinTexto);
                    if (fechaFinDate.compareTo(fechaInicioDate) <= 0 || fechaInicioDate.before(fechaActual)
                            || fechaFinDate.before(fechaActual)) {
                        System.out.println("La fecha de fin debe ser posterior a la fecha de inicio.");
                        return null;
                    }
        
                } catch (ParseException e) {
                    System.out.println("Error al convertir la fecha...");
                    return null;
                }
        
                System.out.print("Indique el nivel educativo del campamento:\n");
                System.out.println("(1) INFANTIL");
                System.out.println("(2) JUVENIL");
                System.out.println("(3) ADOLESCENTE");
                System.out.print("");
                int opcion;
                do {
                    opcion = teclado.nextInt();
        
                    switch (opcion) {
                        case 1:
                            nivel = NivelEducativo.INFANTIL;
                            break;
                        case 2:
                            nivel = NivelEducativo.JUVENIL;
                            break;
                        case 3:
                            nivel = NivelEducativo.ADOLESCENTE;
                            break;
                        default:
                            System.out.println("Opción no válida. Escriba otro número válido:");
                            break;
                    }
                } while (opcion < 1 || opcion > 3);
        
                System.out.print("Máximo número de asistentes: ");
                max_asistentes = teclado.nextInt();
                while (max_asistentes <= 0) {
                    System.out.print("Error al indicar el número máximo de asistentes...");
                    return null;
                }
        
                nuevoCampamento.setFechaInicio(fechaInicioDate);
                nuevoCampamento.setFechaFin(fechaFinDate);
                nuevoCampamento.setNivel(nivel);
                nuevoCampamento.setMax_asistentes(max_asistentes);
                return nuevoCampamento;
            }

     /**
     * Muestra los detalles de un campamento específico en la consola.
     *
     * @param campamentos Lista de objetos Campamento.
     * @param idCampamento Identificador del campamento a mostrar.
     */
    public static void mostrarCampamento(ArrayList<Campamento> campamentos, int idCampamento) {
               
                for (Campamento campamento : campamentos) {
        
                    if (campamento.getIdentificador() == idCampamento) {
        
                        System.out.println("ID: " + campamento.getIdentificador()
                                + ", Fecha de inicio: " + campamento.getFechaInicio()
                                + ", Fecha de fin: " + campamento.getFechaFin()
                                + ", Nivel: " + campamento.getNivel()
                                + ", Participantes maximos: " + campamento.getMax_asistentes()
                                /*+ ", Lista de actividades: " + campamento.getActividades()
                                /*+ ", Lista de monitores: " + campamento.getMonitoresResponsables()*/);
        
                    }
        
                }

               
        
            }


}
