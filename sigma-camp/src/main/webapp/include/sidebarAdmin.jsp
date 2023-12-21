<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" href="/sigma-camp/css/sidebarAdmin.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"/>
  </head>
  <body><div class="area"></div><nav class="main-menu">
            <ul>
                <li>
                    <a href="${pageContext.request.contextPath}/addActividad">
                        <i class="fa-regular fa-star"></i>
                        <span class="nav-text">
                           A&ntilde;adir Actividades
                        </span>
                    </a>
                  
                </li>
                <li class="has-subnav">
                    <a href="${pageContext.request.contextPath}/addCampamento">
                        <i class="fa-solid fa-campground"></i>
                        <span class="nav-text">
                            A&ntilde;adir Campamentos
                        </span>
                    </a>
                    
                </li>
                <li class="has-subnav">
                    <a href="${pageContext.request.contextPath}/addMonitor">
                        <i class="fa-solid fa-user-plus"></i>
                        <span class="nav-text">
                            Dar de alta Monitor
                        </span>
                    </a>
                </li>
                <li class="has-subnav">
                    <a href="${pageContext.request.contextPath}/asociarActividad">
                        <i class="fa-regular fa-clipboard"></i>
                        <span class="nav-text">
                            Asociar Actividades a Campamentos
                        </span>
                    </a>
                   
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/AsociarMonitorActividadCampamento">
                        <i class="fa-regular fa-address-card"></i>
                        <span class="nav-text">
                            Asociar Monitores a Actividades de Campamentos
                        </span>
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/">
                        <i class="fa fa-pencil-square"></i>
                        <span class="nav-text">
                           Modificar Pista
                        </span>
                    </a>
                </li>
                <li>
                   <a href="${pageContext.request.contextPath}/">
                       <i class="fa fa-cog"></i>
                        <span class="nav-text">
                            Modificar estado de Pista
                        </span>
                    </a>
                </li>
                <li>
                <li>
                   <a href="${pageContext.request.contextPath}/">
                        <i class="fa fa-trash-o"></i>
                        <span class="nav-text">
                            Borrar Reserva
                        </span>
                    </a>
                </li>
            </ul>

            <ul class="logout">
                <li>
                   <a href="${pageContext.request.contextPath}<%=application.getInitParameter("logoutController")%>">
                         <i class="fa fa-power-off fa-2x"></i>
                        <span class="nav-text">
                            Cerrar Sesi&otilde;n
                        </span>
                    </a>
                </li>  
            </ul>
        </nav>
  </body>
 </html>