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
	
	public static GestorAsistentes getInstance(ArrayList<Asistente> arrayNuevo)
	{
		if(instance == null)
		{
			instance = new GestorAsistentes(arrayNuevo);
		}
		return instance;
	}
	
	public ArrayList<Asistente> getAsistentes()
	{
		return this.asistentes;
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
