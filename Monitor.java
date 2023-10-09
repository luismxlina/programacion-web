package Practica1;

public class Monitor extends Asistente {
	 // Atributos
    private int idMonitor;
    private String nombreMonitor;
    private String apellidosMonitor;
    private boolean esEducadorEspecial;

   

	// Constructor vacío
    public Monitor() {
    }

    // Constructor parametrizado
    public Monitor(int idMonitor, String nombreMonitor, String apellidosMonitor, boolean esEducadorEspecial) {
        this.idMonitor = idMonitor;
        this.nombreMonitor = nombreMonitor;
        this.apellidosMonitor = apellidosMonitor;
        this.esEducadorEspecial = esEducadorEspecial;
    }

    // Métodos get/set
    /**
   	 * @return the idMonitor
   	 */
   	public int getIdMonitor() {
   		return idMonitor;
   	}

   	/**
   	 * @param idMonitor the idMonitor to set
   	 */
   	public void setIdMonitor(int idMonitor) {
   		this.idMonitor = idMonitor;
   	}

   	/**
   	 * @return the nombreMonitor
   	 */
   	public String getNombreMonitor() {
   		return nombreMonitor;
   	}

   	/**
   	 * @param nombreMonitor the nombreMonitor to set
   	 */
   	public void setNombreMonitor(String nombreMonitor) {
   		this.nombreMonitor = nombreMonitor;
   	}

   	/**
   	 * @return the apellidosMonitor
   	 */
   	public String getApellidosMonitor() {
   		return apellidosMonitor;
   	}

   	/**
   	 * @param apellidosMonitor the apellidosMonitor to set
   	 */
   	public void setApellidosMonitor(String apellidosMonitor) {
   		this.apellidosMonitor = apellidosMonitor;
   	}

   	/**
   	 * @return the esEducadorEspecial
   	 */
   	public boolean isEsEducadorEspecial() {
   		return esEducadorEspecial;
   	}

   	/**
   	 * @param esEducadorEspecial the esEducadorEspecial to set
   	 */
   	public void setEsEducadorEspecial(boolean esEducadorEspecial) {
   		this.esEducadorEspecial = esEducadorEspecial;
   	}

    // Método toString
    @Override
    public String toString() {
        return "Monitor{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", esEducadorEspecial=" + esEducadorEspecial +
                '}';
    }
}

