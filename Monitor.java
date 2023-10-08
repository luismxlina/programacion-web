package Practica1;

public class Monitor {
	 // Atributos
    private int id;
    private String nombre;
    private String apellidos;
    private boolean esEducadorEspecial;

    // Constructor vacío
    public Monitor() {
    }

    // Constructor parametrizado
    public Monitor(int id, String nombre, String apellidos, boolean esEducadorEspecial) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.esEducadorEspecial = esEducadorEspecial;
    }

    // Métodos get/set
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public boolean isEsEducadorEspecial() {
        return esEducadorEspecial;
    }

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

