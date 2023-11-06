import java.util.ArrayList;
import es.uco.pw.bussines.users.models.monitor.Monitor;

public class ActividadDTO 
{
    private String nombreActividad;
    private NivelEducativo nivel;
    private String hora;
    private int max_participantes;
    private int num_monitores;

    public ActividadDTO() {}

    public ActividadDTO(String nombreActividad, String nivel, String hora, int maxParticipantes, int numMonitores, ArrayList<MonitorDTO> monitores) 
    {
        this.nombreActividad = nombreActividad;
        this.nivel = nivel;
        this.hora = hora;
        this.maxParticipantes = maxParticipantes;
        this.numMonitores = numMonitores;
    }

    // métodos getter y setter para nombreActividad
    public String getNombreActividad() 
    {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) 
    {
        this.nombreActividad = nombreActividad;
    }

    // métodos getter y setter para nivel
    public NivelEducativo getNivel() 
    {
        return nivel;
    }

    public void setNivel(NivelEducativo nivel) 
    {
        this.nivel = nivel;
    }

    // métodos getter y setter para hora
    public String getHora() 
    {
        return hora;
    }

    public void setHora(String hora) 
    {
        this.hora = hora;
    }

    // métodos getter y setter para max_participantes
    public int getMaxParticipantes()
    {
        return max_participantes;
    }

    public void setMaxParticipantes(int max_participantes) 
    {
        this.max_participantes = max_participantes;
    }

    // métodos getter y setter para num_monitores
    public int getNumMonitores() 
    {
        return num_monitores;
    }

    public void setNumMonitores(int num_monitores) 
    {
        this.num_monitores = num_monitores;
    }
}