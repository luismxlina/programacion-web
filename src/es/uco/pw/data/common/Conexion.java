package es.uco.pw.data.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;



/**
 * Clase para gestionar la conexión MySQL (métodos generales y configuración).
 * 
 * Esta clase proporciona métodos para conectar y desconectar a la base de datos MySQL,
 * así como para obtener la configuración y las propiedades SQL necesarias.
 * 
 * @author Aurora Ramirez
 * @author Jose Raul Romero
 */
public class Conexion {

    protected Connection connection = null;
    private static Conexion instance = null;
    private String url;
    private String user;
    private String passwd;
    private Properties config;
    private Properties sql;

    private Conexion(){

        config = new Properties();
        sql=new Properties();
        try {
        	Class.forName("com.mysql.jdbc.Driver");
 
        	// InputStream pathConfig = getClass().getClassLoader().getResourceAsStream("/config.properties");
        	// InputStream pathSQL = getClass().getClassLoader().getResourceAsStream("/sql.properties");

            InputStream pathConfig = ClassLoader.getSystemResourceAsStream("config.properties");
            InputStream pathSQL = ClassLoader.getSystemResourceAsStream("sql.properties");

        	BufferedReader readerConfig = new BufferedReader(new InputStreamReader(pathConfig));
        	BufferedReader readerSQL = new BufferedReader(new InputStreamReader(pathSQL));

            config.load( readerConfig );
            sql.load( readerSQL );
           
            this.url=config.getProperty("URL");
            this.user=config.getProperty("USER");
            this.passwd=config.getProperty("PASSWORD");
        
        } catch (IOException e ) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
    }
    
    /**
     * Obtiene una instancia única de la clase Conexion.
     * 
     * @return Instancia única de la clase Conexion.
     */
    public static Conexion getInstance() {
        if ( instance == null ) {
            return new Conexion();
        }
        return instance;
    }

    private Connection connect() {
        try {
            connection = (Connection) DriverManager.getConnection(this.url, this.user, this.passwd);
        } catch (SQLException e) {
            System.out.println("Connection with the database has failed!");
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Obtiene la conexión a la base de datos. Si no hay una conexión existente, se crea una nueva.
     * 
     * @return Conexión a la base de datos.
     */
    public Connection getConnection() {
        if( connection == null ){
            connection = connect();
        }
        return connection;
    }

    private void disconnect() {
        try{
            if ( connection != null && !connection.isClosed() ){
                this.connection.close();
                System.out.println("Connection with database closed");
            }
        }catch ( SQLException e ) {
            System.out.println("Failed to close connection");
            e.printStackTrace();
        }
    }

    /**
     * Desconecta de la base de datos. Si hay una conexión existente, se cierra.
     */
    public void getDisconnected() {
        if( connection == null ) return;
        disconnect();
    }

    /**
     * Obtiene la configuración de la conexión.
     * 
     * @return Configuración de la conexión.
     */
    public Properties getConfig() {
        return config;
    }
    
    /**
     * Obtiene las propiedades SQL necesarias.
     * 
     * @return Propiedades SQL.
     */
    public Properties getSql() {
        return sql;
    }
}