package es.uco.pw.data;

/**
 * Interfaz que define operaciones comunes para un repositorio de datos genérico.
 *
 * @param <T> Tipo de objeto que se va a almacenar en el repositorio.
 */
public interface InterfazRepositorio<T> {
	
	/**
     * Guarda un objeto en un fichero.
     *
     * @param obj Objeto a guardar.
     * @param nombreFichero Nombre del fichero en el que se va a guardar el objeto.
     */
	void guardarEnFichero(T obj,String nombreFichero);
	
	/**
     * Carga datos desde un fichero y devuelve un objeto del tipo T.
     *
     * @param nombreFichero Nombre del fichero del que se van a cargar los datos.
     * @return Objeto del tipo T cargado desde el fichero.
     */
	T cargarDatosFichero(String nombreFichero);

}
