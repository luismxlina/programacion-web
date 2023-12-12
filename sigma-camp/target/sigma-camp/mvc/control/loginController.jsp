<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="display.userbean.userBean" %>

<jsp:useBean  id="userBean" scope="session" class="display.userbean.userBean"></jsp:useBean>

<%

String nextPage = "";
String textNextPage = "";

//Controlo que no llegue a este controlador, estando ya logueado
if(userBean != null || !userBean.getEmail().equals(""))
{
	//Redirijo a la página principal
	nextPage = "../../index.jsp";
	textNextPage = "Ya se encuentra logueado";
}
//sino estoy logueado, debo comprobar si el login es correcto
else
{
	//Primero obtengo los parámetros del formulario
	String email = request.getParameter("correo");
	String pass = request.getParameter("passWord");
	
	//Compruebo el login
	//Crear un DAO que contenga el método al que yo le paso un usuario y una contraseña y me retorna true o false dependiendo de si es correcto o no 
	//Doy por hecho que es correcto el login
	//UsuarioDTO usuario = usuarioDAO.comprobar_login(email,pass);
	//if(usuario==null)
	userBean.setEmail("angel@uco.es");
	userBean.setNombre("Angel");
	nextPage = "../../index.jsp";
	textNextPage = "Datos correctos, login realizado";
}

%>
<jsp:forward page="<%=nextPage%>">
<jsp:param value="<%=textNextPage%>" name="mensaje"/>
</jsp:forward>
