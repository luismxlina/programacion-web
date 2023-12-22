<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" href="/sigma-camp/css/sidebar.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"/>
  </head>
  <body><div class="area"></div><nav class="main-menu">
            <ul>
                <li>
                    <a href="${pageContext.request.contextPath}/searchCampamentosFecha">
                        <i class="fa-solid fa-magnifying-glass"></i>
                        <span class="nav-text">
                           Consultar campamento por fecha
                        </span>
                    </a>
                  
                </li>
                <li class="has-subnav">
                    <a href="${pageContext.request.contextPath}/searchCampamentos">
                        <i class="fa-brands fa-searchengin"></i>
                        <span class="nav-text">
                            Consultar campamentos por nivel educativo o plazas disponibles
                        </span>
                    </a>
                    
                </li>
                <li class="has-subnav">
                    <a href="${pageContext.request.contextPath}/inscribeCampamentosView">
                        <i class="fa-solid fa-person-circle-plus"></i>
                        <span class="nav-text">
                            Inscribirse a un campamento
                        </span>
                    </a>
                </li>
                <li class="has-subnav">
                    <a href="${pageContext.request.contextPath}/viewReserve">
                        <i class="fa-solid fa-ban"></i>
                        <span class="nav-text">
                            cancelar inscripci&oacute;n
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