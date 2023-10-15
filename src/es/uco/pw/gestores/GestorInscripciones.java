package es.uco.pw.gestores;

import es.uco.pw.classes.asistente.Asistente;
import es.uco.pw.classes.inscripcion.InscripcionCampamento;
import es.uco.pw.gestores.GestorAsistentes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;

public class GestorInscripciones {

    private ArrayList<InscripcionCampamento> inscripciones;


    private static GestorInscripciones instance = null;

    private GestorInscripciones(ArrayList<InscripcionCampamento> inscripciones) {
        this.inscripciones = inscripciones;
    }

    public static GestorInscripciones getInstance(ArrayList<InscripcionCampamento> inscripciones) {
        if (instance == null) {
            instance = new GestorInscripciones(inscripciones);
        }
        return instance;
    }

    // Create: Añade una inscripción
    public void addInscripcion(InscripcionCampamento inscripcion) {
        inscripciones.add(inscripcion);
    }

    // Read: Obtiene una inscripción por ID
    public InscripcionCampamento getInscripcion(int id) {
        for (InscripcionCampamento inscripcion : inscripciones) {
            if (inscripcion.getIdentificador() == id) {
                return inscripcion;
            }
        }
        return null;
    }

    // Update: Modifica una inscripción
    public void updateInscripcion(InscripcionCampamento inscripcionActualizada) {
        for (int i = 0; i < inscripciones.size(); i++) {
            if (inscripciones.get(i).getIdentificador() == inscripcionActualizada.getIdentificador()) {
                inscripciones.set(i, inscripcionActualizada);
                break;
            }
        }
    }

    // Delete: Elimina una inscripción por ID
    public void deleteInscripcion(int id) {
        inscripciones.removeIf(inscripcion -> inscripcion.getIdentificador() == id);
    }

    public void mostrarInscripciones() {
        for (InscripcionCampamento inscripcion : inscripciones) {
            System.out.println(inscripcion.toString());
        }
    }

    public void crearInscripcionDesdeTerminal(Scanner teclado) {

        System.out.println("Creación de nueva inscripción:");

        // Solicitar datos al usuario
        System.out.print("ID del participante: ");
        int idParticipante = teclado.nextInt();
        System.out.print("ID del campamento a inscribirse: ");
        int idCampamento = teclado.nextInt();

        Asistente asistente = GestorAsistentes.getInstance(null).getAsistente(idParticipante);

        // Crear una inscripción
        InscripcionCampamento nuevaInscripcion = new InscripcionCampamento(asistente, idCampamento);

        // Agregar la inscripción a la lista de inscripciones
        addInscripcion(nuevaInscripcion);

        System.out.println("Inscripción creada con éxito.");
    }

    public void mostrarInscripcion(int id) {
        for (InscripcionCampamento inscripcion : inscripciones) {
            if (inscripcion.getIdentificador() == id) {
                System.out.println(inscripcion.toString());
            }
        }
    }
}
