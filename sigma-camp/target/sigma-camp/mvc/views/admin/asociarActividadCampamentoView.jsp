<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Asociar Actividad</title>
</head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/marco.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
<body>
    <!-- ACL -->
    <% String aclAdmin = application.getInitParameter("aclAdmin"); %>
    <jsp:include page="<%=aclAdmin%>"></jsp:include>
    <!-- ACL -->
    <jsp:include page="/include/headerAdmin.jsp"></jsp:include>
    <div class="form-style-6">
        <h1>Asociar Actividad</h1>
        <form id="formulario" action="/sigma-camp/asociarActividadData" method="POST">
            <div class="formulario__grupo" id="grupoAsociacion">
                <label for="nivelEducativo">Nivel Educativo:</label><br>
                <select id="nivelEducativo" name="nivelEducativo" class="cajaBlanca">
                    <option value="INFANTIL">Infantil</option>
                    <option value="JUVENIL">Juvenil</option>
                    <option value="ADOLESCENTE">Adolescente</option>
                </select><br>
                <label for="campamento">Campamento:</label><br>
                <select id="campamento" name="campamento" class="cajaBlanca" disabled>
                    <!-- Options will be populated based on selected 'nivelEducativo' -->
                </select><br>
                <label for="actividad">Actividad:</label><br>
                <select id="actividad" name="actividad" class="cajaBlanca" disabled>
                    <!-- Options will be populated based on selected 'nivelEducativo' -->
                </select><br>
                <input type="submit" id="submit" value="Asociar Actividad"><br><br>
                <input type="reset" id="reset">
            </div>
        </form>
        <div id="mensajeRespuesta"></div>
    </div>

    <script src="${pageContext.request.contextPath}/js/script.js"></script> 
    <script>
    document.getElementById('nivelEducativo').addEventListener('change', function() {
        var nivelSeleccionado = this.value;
        var campamentoSelect = document.getElementById('campamento');
        var actividadSelect = document.getElementById('actividad');

        fetch('/sigma-camp/asociarActividadData')
            .then(response => response.json())
            .then(data => {
                // Filtrar los datos según el nivel educativo seleccionado
                var campamentos = data[nivelSeleccionado + 'Campamentos'];
                var actividades = data[nivelSeleccionado + 'Actividades'];

                // Función para llenar los selectores
                function llenarSelect(select, options) {
                    select.innerHTML = '';
                    options.forEach(option => {
                        var optionElement = document.createElement('option');
                        optionElement.value = option.identificador || option.nombreActividad;
                        optionElement.text = option.identificador || option.nombreActividad;
                        select.appendChild(optionElement);
                    });
                }

                // Llenar los selectores con los datos filtrados
                llenarSelect(campamentoSelect, campamentos);
                llenarSelect(actividadSelect, actividades);

                // Habilitar los selectores
                campamentoSelect.disabled = false;
                actividadSelect.disabled = false;
            })
            .catch(error => {
                console.error('Error fetching data:', error);
            });
    });
</script>

</body>
</html>
