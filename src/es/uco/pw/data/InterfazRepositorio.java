package es.uco.pw.data;

public interface InterfazRepositorio<T> {
	
	void guardarEnFichero(T obj,String nombreFichero);
	
	T cargarDatosFichero(String nombreFichero);

}
