<%-- 
    Document   : CrearServicio
    Created on : 20/02/2021, 01:53:20 PM
    Author     : diego
--%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="modelo.logica.Vehiculo"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.logica.Parqueadero"%>
<%@page import="modelo.logica.GestorParqueadero"%>
<%
    Date date = new Date();
    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
    GestorParqueadero gestor1 = new GestorParqueadero();
    GestorParqueadero gestor2 = new GestorParqueadero();
    
    ArrayList<Parqueadero> p = gestor1.cargarParqueaderos();
    ArrayList<Vehiculo> v = gestor2.cargarVehiculos();
    int i = 0;
    int j = 0;
    int w = 0;

%>
<html>

    <head>
        <meta charset='utf-8'>
        <title> Ingreso</title>
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
                <li ><a href="crearParqueadero.jsp">Registro de parqueadero</a></li>
                <li><a href="editarParqueadero.jsp">Gestion de parqueadero</a></li>
                <li><a href="crearServicio.jsp">Ingreso</a></li>
                <li><a href="acabarServicio.jsp">Salida</a></li>
                <li><a href="crearContrato.jsp">Vinculación contrato</a></li>
                <li><a href="elegirEstadisticas.jsp">Estadisticas</a></li>
            </ul>
        </header>
        <div id="contenedorContrato">
        <form id="creacionSevicio" action="crearServicio" method="post">
            <div class="crearContrato" id="encontrarParqueadero">
                <label>Parqueadero:</label>
                <input name="k_idParqueadero" id="buscarParqueadero" list="parqueaderos" placeholder="buscar" required>
            </div> 
            <div class="crearContrato" id="creaVehiculo">
                <h3>Vehiculo</h3>
                <label>Buscar:</label>
                <input name="k_idVehiculoSearch" id="buscarVehiculo" list="vehiculos" placeholder="buscar">
                <label>ID:</label>
                <input name="k_idVehiculo" id="identificadorVehiculo" autofocus>
                <label>Marca:</label>
                <input name="n_marca" id="marca" autofocus>
                <label>Color:</label>
                <input name="n_color" id="color" autofocus>
                <label>Tipo:</label>
                <input name="i_tipo" id="tipo" type="number" step="1" min="1" max="6" autofocus>
            </div >
            <div class="crearContrato" id="creaContrato">
                <h3>Servicio</h3>
                <label>ID:</label>
                <input name="k_idServicio" id="identificadorServicio" autofocus>
                <%--  <h3>Servicio</h3>
                <label>Fecha de entrada:</label>--%>
                <input name="f_fycentrada" id="f_fycentrada" value="<%=formato.format(date)%>" placeholder="fecha entrada" hidden required>
            </div>
            <button type="submit" id="buttonCrearServicio">submit</button>
        </form>
        
        <datalist id="parqueaderos">
            <% while (j < p.size()) {%>
            <option><%= p.get(j).getK_idParqueadero()%></option>
            <%j++;
                }%>
        </datalist>
        <datalist id="vehiculos">
            <% while (w < v.size()) {%>
            <option><%= v.get(w).getK_idVehiculo()%></option>
            <%w++;
                }%>
        </datalist> 
    </div>
    </body>
    <script type="text/javascript" src="assets/scriptCrearServicio.js"></script>

</html>
