Descripción del Proyecto
Este proyecto Java se centra en la gestión de asistentes, proporcionando un menú de opciones para mostrar, añadir y eliminar asistentes. El código se organiza en la clase MainAsistentes y hace uso de la clase GestorAsistentes para realizar operaciones con los asistentes.

Problemas con la Carga de Ficheros de Propiedades
Se ha experimentado dificultades en la carga de ficheros de propiedades. Se ha intentado utilizar la línea comentada:


// InputStream pathConfig = getClass().getClassLoader().getResourceAsStream("/config.properties");
Sin embargo, esta línea no ha funcionado como se esperaba. En su lugar, se ha utilizado la línea descomentada:

InputStream pathConfig = ClassLoader.getSystemResourceAsStream("config.properties");
Esta última ha sido la solución para cargar el archivo de propiedades como recursos del sistema.
