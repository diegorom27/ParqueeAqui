<%-- 
Document   : CrearParqueadero
Created on : 1/02/2021, 11:42:44 AM
Author     : diego
--%>
<%@page import="modelo.logica.Vehiculo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.logica.GestorParqueadero"%>
<!DOCTYPE html>
<%
    GestorParqueadero gestor2 = new GestorParqueadero();
    ArrayList<Vehiculo> v = gestor2.cargarVehiculos();
    int w = 0;

%>
<html>

    <head>
        <meta charset='utf-8'>
        <title>Elegir estadisticas</title>
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
                <li ><a href="crearParqueadero.jsp">Registro de parqueadero</a></li>
                <li><a href="editarParqueadero.jsp">Gestion de parqueadero</a></li>
                <li><a href="crearServicio.jsp">Ingreso</a></li>
                <li><a href="acabarServicio.jsp">Salida</a></li>
                <li><a href="#">Historial Ingresos</a></li>
                <li><a href="crearContrato.jsp">Vinculación contrato</a></li>
                <li><a href="#">Estadisticas</a></li>
            </ul>
        </header>
        <div>
            <div>
                <form>
                    <label>Fecha de incio</label>
                    <input placeholder="yyyy-MM-dd" autofocus>
                    <label>Fecha final</label>
                    <input placeholder="yyyy-MM-dd" autofocus>
                    <button type="submit">Busqueda</button>   
                </form>
            </div>
            <div>
                <form>
                    placa del vehiculo
                    <input list="vehiculos" autofocus>
                    <button type="submit">Busqueda</button> 
                </form>
                <datalist id="vehiculos">
                    <% while (w < v.size()) {%>
                    <option><%= v.get(w).getK_idVehiculo()%></option>
                    <%w++;
                }%>
                </datalist> 
            </div>
        </div>
    </body>
    <script type="text/javascript" src="assets/index.js"></script>
</html>
