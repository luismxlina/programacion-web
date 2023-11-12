package es.uco.pw.data.dao;

import java.util.Scanner;

import es.uco.pw.business.users.handler.GestorAsistentes;
import es.uco.pw.view.MainAsistentes;

public class Main {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        GestorAsistentes gestor_asistentes = new GestorAsistentes();
        MainAsistentes.mostrarMenuAsistentes(teclado, gestor_asistentes);
        teclado.close();
    }
}