<%-- 
    Document   : acabarServicio
    Created on : 23/02/2021, 12:55:28 AM
    Author     : lenovo
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="modelo.logica.Vehiculo"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.logica.Parqueadero"%>
<%@page import="modelo.logica.GestorParqueadero"%>
<%
    Date date = new Date();
    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS");
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
        <title> Salida</title>
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
                <li><a href="#">Registro de parqueadero</a></li>
                <li><a href="#">Gestion de parqueadero</a></li>
                <li><a href="#">Ingreso </a></li>
                <li><a id="elegida" href="#">Salida</a></li>
                <li><a href="#">Historial Ingresos</a></li>
                <li><a href="#">Vinculación contrato</a></li>
                <li><a href="#">Gestión contratos</a></li>
                <li><a href="#">Estadisticas</a></li>
            </ul>
        </header>
        <div id="contenedorContrato">
        <form id="creacionContrato" action="acabarServicio" method="post">
            <div class="crearContrato" id="encontrarParqueadero">
                <label>Parqueadero:</label>
                <input name="k_idParqueadero" id="buscarParqueadero" list="parqueaderos" placeholder="buscar" required>
            </div> 
            <div class="crearContrato" id="creaVehiculo">
                <h3>Servicio</h3>
                <label>Buscar:</label>
                <input name="k_idServicioSearch" id="buscarServicio" list="servicios" placeholder="buscar">
                <%-- <label>Fecha de salida:</label>--%>
                <input name="f_fycsalida" id="f_fycsalida" value="<%=formato.format(date)%>" placeholder="fecha salida" hidden required>
            </div >
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
    <script type="text/javascript" src="assets/scriptAcabarServicio.js"></script>

</html>

