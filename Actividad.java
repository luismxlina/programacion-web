package Practica1;

import java.util.List;
import java.util.ArrayList;

public class Actividad extends Monitor{



    //Nivel educativo
    public enum NivelEducativo {
        INFANTIL, JUVENIL, ADOLESCENTE
    }

    //Atributos
    private String nombreActividad;
    private NivelEducativo NivelEducativo;
    private String horario;
    private int numMaxParticipantes;
    private int numMonitoresNecesarios;
    private ArrayList<Monitor> monitoresAsignados;


    //Constructor vacío
    public Actividad(){

    }

    //Constructor parametrizado
    public Actividad(String nombreActividad, NivelEducativo NivelEducativo, String horario, int numMaxParticipantes, int numMonitoresNecesarios){
        this.nombreActividad = nombreActividad;
        this.NivelEducativo = NivelEducativo;
        this.horario = horario;
        this.numMaxParticipantes = numMaxParticipantes;
        this.numMonitoresNecesarios = numMonitoresNecesarios;
        this.monitoresAsignados = new ArrayList<Monitor>();
    }

    //Metodos get/set
    public String getnombreActividad(){
        return nombreActividad;
    }

    public NivelEducativo getNivelEducativo(){
        return NivelEducativo;
    }

    public String gethorario(){
        return horario;
    }

    public int getNumMaxParticipantes(){
        return numMaxParticipantes;
    }

    public int getNumMonitoresNecesarios(){
        return numMonitoresNecesarios;
    }

    public void setnombreActividad(){
        this.nombreActividad = nombreActividad;
    }

    public void setNivelEducativo(){
        this.NivelEducativo = NivelEducativo;
    }

    public void sethorario(){
        this.horario = horario;
    }

    public void setNumMaxParticipantes(){
        this.numMaxParticipantes = numMaxParticipantes;
    }

    public void setNumMonitoresNecesarios(){
        this.numMonitoresNecesarios = numMonitoresNecesarios;
    }

    //Método toString
    @Override 
    public String toString(){
        return "Actividad {" + "nombreActividad='" + nombreActividad + '\'' +
                ", nivelEducativo=" + NivelEducativo +
                ", horario='" + horario + '\'' +
                ", numMaxParticipantes=" + numMaxParticipantes +
                ", numMonitoresNecesarios=" + numMonitoresNecesarios +
                ", monitoresAsignados=" + monitoresAsignados +
                '}';
    }
    
    //asociarMonitor
    public boolean asociarMonitor(Monitor monitor){

        if(monitoresAsignados.size() < numMonitoresNecesarios){
            monitoresAsignados.add(monitor);
            System.out.printf("El monitor " + monitor.getNombre() + " ha sido asignado a la actividad " + nombreActividad + ".");

        }
        else
        {
            System.out.printf("La actividad" + nombreActividad + " ya cuenta con los monitores necesarios");
        }
		return false;
    }

	
	public Monitor getmonitoresAsignados(Monitor monitor) {
		return monitor;
	}
}

