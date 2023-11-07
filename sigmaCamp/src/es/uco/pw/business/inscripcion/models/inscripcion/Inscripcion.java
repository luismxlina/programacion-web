package es.uco.pw.business.inscripcion.models.inscripcion;

import java.io.Serializable;
import java.util.Date;

/**
 * Clase abstracta que representa una inscripción a un campamento.
 * Proporciona atributos y métodos comunes para todas las inscripciones.
 */

public abstract class Inscripcion implements Serializable {

     // Atributos comunes

     /**
      * Identificador del participante asociado a la inscripción.
      */

     private int id_Participante;
     /**
      * Identificador del campamento al que se realiza la inscripción.
      */
     private int id_Campamento;
     /**
      * Fecha en la que se realizó la inscripción.
      */
     private Date fechaInscripcion;

     /**
      * Indica si la inscripción es cancelable.
      */

     private Boolean cancelable;

     public Inscripcion() {
          this.id_Participante = 0;
          this.id_Campamento = 0;
          this.fechaInscripcion = new Date();
          this.cancelable = false;
     }

     public Inscripcion(int id_Participante, int id_Campamento, Date fechaInscripcion, 
               boolean cancelable) {
          this.id_Participante = id_Participante;
          this.id_Campamento = id_Campamento;
          this.fechaInscripcion = fechaInscripcion;
          this.cancelable = cancelable;
     }

     // Métodos abstractos

     /**
      * Devuelve una representación en cadena de la inscripción.
      *
      * @return Cadena que representa la inscripción.
      */
     public abstract String toString();

     /**
      * Obtiene el precio total de la inscripción.
      *
      * @return Precio de la inscripción.
      */

     public abstract Double getPrecio();

     /**
      * Obtiene el identificador del participante asociado a la inscripción.
      *
      * @return Identificador del participante.
      */
     public int getId_Participante() {
          return id_Participante;
     }

     /**
      * Establece el identificador del participante asociado a la inscripción.
      *
      * @param id_Participante Identificador del participante.
      */
     public void setId_Participante(int id_Participante) {
          this.id_Participante = id_Participante;
     }

     /**
      * Obtiene el identificador del campamento al que se realiza la inscripción.
      *
      * @return Identificador del campamento.
      */
     public int getId_Campamento() {
          return id_Campamento;
     }

     /**
      * Establece el identificador del campamento al que se realiza la inscripción.
      *
      * @param id_Campamento Identificador del campamento.
      */
     public void setId_Campamento(int id_Campamento) {
          this.id_Campamento = id_Campamento;
     }

     /**
      * Obtiene la fecha en la que se realizó la inscripción.
      *
      * @return Fecha de inscripción.
      */
     public Date getFechaInscripcion() {
          return fechaInscripcion;
     }

     /**
      * Establece la fecha en la que se realizó la inscripción.
      *
      * @param fechaInscripcion Fecha de inscripción.
      */
     public void setFechaInscripcion(Date fechaInscripcion) {
          this.fechaInscripcion = fechaInscripcion;
     }

     /**
      * Obtiene si la inscripción es cancelable.
      *
      * @return true si la inscripción es cancelable, false en caso contrario.
      */
     public Boolean getCancelable() {
          return cancelable;
     }

     /**
      * Establece si la inscripción es cancelable.
      *
      * @param cancelable true si la inscripción es cancelable, false en caso
      *                   contrario.
      */
     public void setCancelable(Boolean cancelable) {
          this.cancelable = cancelable;
     }

}
