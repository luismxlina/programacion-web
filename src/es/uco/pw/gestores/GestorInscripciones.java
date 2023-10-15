package es.uco.pw.gestores;

import es.uco.pw.classes.inscripcion.InscripcionCampamento;
import java.util.ArrayList;

public class GestorInscripciones {

    private ArrayList<InscripcionCampamento> inscripciones;

    public GestorInscripciones() {
        this.inscripciones = new ArrayList<>();
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
}
