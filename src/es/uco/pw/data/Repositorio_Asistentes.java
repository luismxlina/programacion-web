package es.uco.pw.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import es.uco.pw.classes.asistente.Asistente;

public class Repositorio_Asistentes implements Interfaz_Repositorio<ArrayList<Asistente>> {
	
	public void guardarEnFichero(ArrayList<Asistente> obj,String nombreFichero)
	{
		try {
			
			// Creamos una variable para meter el fichero en el buffer
			BufferedWriter fichero = new BufferedWriter(new FileWriter(nombreFichero));
			
			// Recorremos la lista de inscripciones
			for(Asistente asistente : obj) {
				
				// Hacemos parse a String de los datos 
				// Creamos un formato de fecha a nuestro gusto
				SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
				String fecha = formatoFecha.format(asistente.getFechaNacimiento());
				
				int requiereAtencion = 0;
				if(asistente.getRequiereAtencion()==true) {
					
					requiereAtencion=1;
				
				}
				
				// Escribimos en el fichero con el formato deseado
				fichero.write(asistente.getIdentificador()+";"+asistente.getNombre()+";"+
						asistente.getApellidos()+";"+fecha+";"+requiereAtencion+"\n");
				
			}
			
			fichero.close();
		
		}
		
		catch (IOException e) {
			
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        
		}
	}
	
	public ArrayList<Asistente> cargarDatosFichero(String nombreFichero)
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

}
