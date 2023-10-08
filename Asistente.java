package Practica1;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Asistente {

    //Atributos
    private int id;
    private String nombre;
    private String apellidos;
    private Date fechaNacimiento;
    private Boolean atencionEspecial;
    
    //Constructor vacío
    public Asistente() {
    
    }

    //Cosntructor parametrizado
    public Asistente(int id, String nombre, String apellidos, Date fechaNacimiento, Boolean requiereAtencionEspecial){
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.atencionEspecial = atencionEspecial;
    }

    //Métodos get/set
    public void setid(int id){
        this.id = id;
    }

    public void setnombre(String nombre){
        this.nombre = nombre;
    }

    public void setapellidos(String apellidos){
        this.apellidos = apellidos;
    }

    public void setFechaNacimiento(Date fechaNacimiento){
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setatencionEspecial(boolean atencionEspecial){
        this.atencionEspecial = atencionEspecial;
    }

    public int getid(){
        return id;
    }

    public String getnombre(){
        return nombre; 
    }

    public String setapellidos(){
        return apellidos;
    }

    public Date setfechaNacimiento(){
        return fechaNacimiento;
    }

    public boolean setatencionEspecial(){
        return atencionEspecial;
    }

    //Metodo toString
    @Override
    public String toString(){
         SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String fechaNacimientoStr = dateFormat.format(fechaNacimiento);

        return "Asistente{" +
                "id=" + id +", nombre='" + nombre + '\'' +", apellidos='" + apellidos + '\'' +", fechaNacimiento=" + fechaNacimientoStr +", atencionEspecial=" + atencionEspecial +'}';
    }
}
