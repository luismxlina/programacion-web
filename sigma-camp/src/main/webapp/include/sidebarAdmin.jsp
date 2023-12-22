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
                        <i class="fa-regular fa-star fa"></i>
                        <span class="nav-text">
                           A&ntilde;adir Actividades
                        </span>
                    </a>
                  
                </li>
                <li class="has-subnav">
                    <a href="${pageContext.request.contextPath}/addCampamento">
                        <i class="fa-solid fa-campground fa"></i>
                        <span class="nav-text">
                            A&ntilde;adir Campamentos
                        </span>
                    </a>
                    
                </li>
                <li class="has-subnav">
                    <a href="${pageContext.request.contextPath}/addMonitor">
                        <i class="fa-solid fa-user-plus fa"></i>
                        <span class="nav-text">
                            Dar de alta Monitor
                        </span>
                    </a>
                </li>
                <li class="has-subnav">
                    <a href="${pageContext.request.contextPath}/asociarActividad">
                        <i class="fa-regular fa-clipboard fa"></i>
                        <span class="nav-text">
                            Asociar Actividades a Campamentos
                        </span>
                    </a>
                   
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/asociarMonitorActividad">
                        <i class="fa-regular fa-address-card fa"></i>
                        <span class="nav-text">
                            Asociar Monitores a Actividades de Campamentos
                        </span>
                    </a>
                </li>
            </ul>

            <ul class="logout">
                <li>
                   <a href="${pageContext.request.contextPath}<%=application.getInitParameter("logoutController")%>">
                         <i class="fa fa-power-off fa-2x"></i>
                        <span class="nav-text">
                            Cerrar Sesi&oacute;n
                        </span>
                    </a>
                </li>  
            </ul>
        </nav>
  </body>
 </html>