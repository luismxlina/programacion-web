package Practica1;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Asistente {

    //Atributos
    private int idAsistente;
    private String nombreAsistente;
    private String apellidosAsistente;
    private Date fechaNacimientoAsistente;
    private Boolean atencionEspecial;
	
    
    //Constructor vacío
    public Asistente() {
		this.idAsistente = 0;
        this.nombreAsistente = "";
        this.apellidosAsistente = "";
        this.fechaNacimientoAsistente = new Date();
        this.atencionEspecial = false;
    }

    //Cosntructor parametrizado
    public Asistente(int idAsistente, String nombreAsistente, String apellidosAsistente, Date fechaNacimientoAsistente, Boolean requiereAtencionEspecial){
        this.idAsistente = idAsistente;
        this.nombreAsistente = nombreAsistente;
        this.apellidosAsistente = apellidosAsistente;
        this.fechaNacimientoAsistente = fechaNacimientoAsistente;
        this.atencionEspecial = atencionEspecial;
    }

    //Métodos get/set
    /**
	 * @return the idAsistente
	 */
	public int getIdAsistente() {
		return idAsistente;
	}

	/**
	 * @param idAsistente the idAsistente to set
	 */
	public void setIdAsistente(int idAsistente) {
		this.idAsistente = idAsistente;
	}

	/**
	 * @return the nombreAsistente
	 */
	public String getNombreAsistente() {
		return nombreAsistente;
	}

	/**
	 * @param nombreAsistente the nombreAsistente to set
	 */
	public void setNombreAsistente(String nombreAsistente) {
		this.nombreAsistente = nombreAsistente;
	}

	/**
	 * @return the apellidosAsistente
	 */
	public String getApellidosAsistente() {
		return apellidosAsistente;
	}

	/**
	 * @param apellidosAsistente the apellidosAsistente to set
	 */
	public void setApellidosAsistente(String apellidosAsistente) {
		this.apellidosAsistente = apellidosAsistente;
	}

	/**
	 * @return the fechaNacimientoAsistente
	 */
	public Date getFechaNacimientoAsistente() {
		return fechaNacimientoAsistente;
	}

	/**
	 * @param fechaNacimientoAsistente the fechaNacimientoAsistente to set
	 */
	public void setFechaNacimientoAsistente(Date fechaNacimientoAsistente) {
		this.fechaNacimientoAsistente = fechaNacimientoAsistente;
	}

	/**
	 * @return the atencionEspecial
	 */
	public Boolean getAtencionEspecial() {
		return atencionEspecial;
	}

	/**
	 * @param atencionEspecial the atencionEspecial to set
	 */
	public void setAtencionEspecial(Boolean atencionEspecial) {
		this.atencionEspecial = atencionEspecial;
	}

    //Metodo toString
    @Override
    public String toString(){
         SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String fechaNacimientoStr = dateFormat.format(fechaNacimientoAsistente);

        return "Asistente{" +
                "id=" + idAsistente +", nombre='" + nombreAsistente + '\'' +", apellidos='" + apellidosAsistente + '\'' +", fechaNacimiento=" + fechaNacimientoStr +", atencionEspecial=" + atencionEspecial +'}';
    }

	
}
