<%@page import="modelo.logica.Parqueadero"%>

<% Parqueadero a = new Parqueadero();
    a = (Parqueadero) session.getAttribute("parqueadero");
    int i = 0;
%>

<html>

    <head>
        <meta charset='utf-8'>
        <title>Regsitro Parqueadero</title>
        <meta name='viewport' content='width=device-width, initial-scale=1' >
        <link href="https://fonts.googleapis.com/css2?family=Spartan:wght@300&display=swap" rel="stylesheet">
        <LINK REL=StyleSheet HREF="assets/style.css" TYPE="text/css" MEDIA=screen>
    </head>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"
    integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
    <body>
        <header>
            <a href="index.html">
                <img class="logo" src="assets/img/logo-removebg-preview.png" alt="">
            </a>
            <ul>
                <li><a id="elegida" href="#">Registro de parqueadero</a></li>
                <li><a href="#">Gestion de parqueadero</a></li>
                <li><a href="#">Ingreso </a></li>
                <li><a href="#">Historial Ingresos</a></li>
                <li><a href="#">Vinculación contrato</a></li>
                <li><a href="#">Gestión contratos</a></li>
                <li><a href="#">Estadisticas</a></li>
            </ul>
        </header>
        <div class="contenedor" id="contenedorArea">
            <form id="creacionAreas" action="crearAreas" method="get">
                <input name="numeroDeAreas" value="<%=a.getQ_areas()%>" type="hidden">
                <% while (i < a.getQ_areas()) {%>
                <div class="contenedorA">
                    <label ><b id="idParq">ID:   <%=a.getK_idParqueadero()%></b></label><br>
                    <input type="hidden" name="k_idParqueadero<%=(i+1)%>" value="<%=a.getK_idParqueadero()%>"  placeholder="ID" autofocus required>
                    <label><b>k_idArea:</b></label>
                    <input type="number" name="k_idArea<%=(i+1)%>" value="<%=(i+1)%>" placeholder="area" autofocus required>
                    <label><b>q_cuposTotales:</b></label>
                    <input type="number" name="q_cuposTotales<%=(i+1)%>"  placeholder="cupos totales" autofocus required>
                    <label><b>i_tipo</b></label>
                    <input type="number" name="i_tipo<%=(i+1)%>"placeholder="tipo" autofocus required>
                </div>
                <% i++;
                    }%>
                <button type="submit" id="buttonCrearAreas">submit</button>
            </form>
            <div id="parqueaderoImagen">    
                
            </div>    
        </div>
    </body>
    <script type="text/javascript" src="assets/scriptCrearParq.js"></script>

</html>