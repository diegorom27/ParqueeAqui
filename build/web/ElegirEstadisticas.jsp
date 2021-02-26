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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
    </head>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"
    integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
    <body>
        <header>
            <a href="index.html">
                <img class="logo" src="assets/img/logo-removebg-preview.png" alt="">
            </a>
            <ul>
                <li><a href="crearParqueadero.jsp">Registro de parqueadero</a></li>
                <li><a href="editarParqueadero.jsp">Gestion de parqueadero</a></li>
                <li><a href="crearServicio.jsp">Ingreso</a></li>
                <li><a href="acabarServicio.jsp">Salida</a></li>
                <li><a href="crearContrato.jsp">Vinculación contrato</a></li>
               <li><a href="elegirEstadisticas.jsp">Estadisticas</a></li>
            </ul>
        </header>
        <h1 class="titulo" id="titulo1">Estadisticas</h1>
        <div class="container">
            <div class="row position-absolute top-50" >
                <div class="col-sm">
                    <form action="estadisticaFecha">
                        <div class="mb-3">
                            <label class="form-label">Fecha de incio</label>
                            <input class="form-control" name="f_fycentrada" placeholder="yyyy-MM-dd" autofocus>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Fecha final</label>
                            <input class="form-control" name="f_fycsalida" placeholder="yyyy-MM-dd" autofocus>
                        </div>
                        <button class="btn btn-primary" type="submit">Busqueda</button>   
                    </form>
                </div>
                <div class="col-sm">
                    <form action="estadisticaId">
                        <div class="mb-3">
                            <label class="form-label">Placa del vehiculo</label>
                            <input class="form-control" name="k_idvehiculo" list="vehiculos" autofocus>
                        </div>
                        <button class="btn btn-primary" type="submit">Busqueda</button> 
                    </form>
                    <datalist id="vehiculos">
                        <% while (w < v.size()) {%>
                        <option><%= v.get(w).getK_idVehiculo()%></option>
                        <%w++;
                        }%>
                    </datalist> 
                </div>
            </div>    
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>        
    </body>
    <script type="text/javascript" src="assets/index.js"></script>
</html>
