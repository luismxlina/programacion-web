package es.uco.pw.gestores;

public interface Interfaz_Repositorio<T> {
	
	void guardarEnFichero(T obj,String nombreFichero);
	
	T cargarDatosFichero(String nombreFichero);

}
