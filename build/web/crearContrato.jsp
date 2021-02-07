<%-- 
    Document   : crearContrato
    Created on : 3/02/2021, 05:57:59 PM
    Author     : diego
--%>

<%@page import="modelo.logica.Vehiculo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.logica.Cliente"%>
<%@page import="modelo.logica.GestorParqueadero"%>

<%
    GestorParqueadero gestor = new GestorParqueadero();
    ArrayList<Cliente> cl = gestor.cargarClientes();
    int i = 0;
    

%>
<!DOCTYPE html>
<head>
    <meta charset='utf-8'>
    <title>Regsitro Parqueadero</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
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
            <li><a href="#">Historial Ingresos</a></li>
            <li><a href="#">Vinculación contrato</a></li>
            <li><a id="elegida" href="#">Gestión contratos</a></li>
            <li><a href="#">Estadisticas</a></li>
        </ul>
    </header>
    <div class="contenedor">
        <h1 class="titulo">Registro contrato</h1>
        <form id="creacionContrato" action="crearContrato" method="post">
            <div class="crearContrato" id="creaCliente">
                <input type="list" id="buscarCliente">
                <datalist id="buscarCliente">
                    <% while (i < cl.size()) {%>
                    <option value="<%= cl.get(i).getK_cedula()%>">
                        <%i++;
                        } %>
                </datalist>
            </div>
            <div class="crearContrato" id="creaVehiculo">
                <input type="list" id="buscarVehiculo">
                <datalist id="buscarVehiculo">
                    
                </datalist>
            </div >
            <div class="crearContrato" id="creaContrato">
            </div >
            <button type="submit" id="buttonCrearParqueadero">submit</button>
        </form>
    </div>
</body>
<script type="text/javascript" src="assets/scriptCrearParq.js"></script>

</html>

