package es.uco.pw.gestores;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import es.uco.pw.classes.asistente.Asistente;

public class GestorAsistentes {
	
	private ArrayList<Asistente> asistentes;
	
	private static GestorAsistentes instance = null;
	
	private GestorAsistentes(ArrayList<Asistente> arrayNuevo)
	{
		this.asistentes = arrayNuevo;
	}
	
	public static GestorAsistentes getIntance(ArrayList<Asistente> arrayNuevo)
	{
		if(instance == null)
		{
			instance = new GestorAsistentes(arrayNuevo);
		}
		return instance;
	}
	
	public Boolean altaAsistente(Asistente nuevoAsistente)
	{
		if(buscarAsistente(nuevoAsistente.getIdentificador())==false)
		{
			this.asistentes.add(nuevoAsistente);
			return true;
		}
		return false;
	}
	
	public Boolean buscarAsistente(int id)
	{
		if(asistentes.size()==0)
			return false;
	
		for(int i=0; i<asistentes.size(); i++)
		{
			if(this.asistentes.get(i).getIdentificador() == id)
			{
				return true;
			}
		}
		
		return false;
	}
	
	public static ArrayList<Asistente> cargarFichero(String nombreFichero)
	{
		ArrayList<Asistente> array = new ArrayList<Asistente>();
		
		File fichero = new File(nombreFichero); 
		
		try {
			
			Scanner scanner = new Scanner(fichero);
			SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
			
			while(scanner.hasNextLine())
			{
				String linea = scanner.nextLine();
				String [] campos = linea.split(";");
				
				int id = Integer.parseInt(campos[0]);
				String nombre = campos[1];
				String apellidos = campos[2];
				Date fecha = new Date();
				try {
					fecha = formatoFecha.parse(campos[3]);
				} catch(ParseException e)
				{
					System.out.println("Error al convertir la fecha");
				}
				int atencion = Integer.parseInt(campos[4]);
				Boolean requiereAtencion = (atencion!=0);
				
				Asistente nuevoAsistente = new Asistente(id,nombre,apellidos,fecha,requiereAtencion);
				
				array.add(nuevoAsistente);
			}
			
			scanner.close();
			
		} catch(FileNotFoundException e){
			e.printStackTrace();
		}
		
		return array;
	}
	
	public void mostrarAsistentes()
	{
		if(instance != null)
		{
			for(int i = 0; i< this.asistentes.size(); i++)
			{
				System.out.println("ID: " + this.asistentes.get(i).getIdentificador() 
						+ ", Nombre: " + this.asistentes.get(i).getNombre()
						+ ", Apellidos: " + this.asistentes.get(i).getApellidos()
						+ ", Fecha nacimiento: " + this.asistentes.get(i).getFechaNacimiento()
						+ ", Necesita atención: " + this.asistentes.get(i).getRequiereAtencion());
			}
		}
	}

}
