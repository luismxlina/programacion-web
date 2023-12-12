package display.userbean;

public class userBean implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	//Todos los atributos que nos interesan almacenar de un usuario al loguearse
	private String nombre ="";
	private String email ="";
	private String rol ="";
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
