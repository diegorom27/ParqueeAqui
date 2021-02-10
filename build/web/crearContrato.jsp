<%-- 
    Document   : crearContrato
    Created on : 3/02/2021, 05:57:59 PM
    Author     : diego
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="modelo.logica.Parqueadero"%>
<%@page import="modelo.logica.Vehiculo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.logica.Cliente"%>
<%@page import="modelo.logica.GestorParqueadero"%>

<%
    Date date = new Date();
    SimpleDateFormat formato = new SimpleDateFormat("dd-mm-yyyy"); 
    GestorParqueadero gestor = new GestorParqueadero();
    GestorParqueadero gestor1 = new GestorParqueadero();
    GestorParqueadero gestor2 = new GestorParqueadero();
    
    ArrayList<Cliente> cl = gestor.cargarClientes();
    ArrayList<Parqueadero> p = gestor1.cargarParqueaderos();
    ArrayList<Vehiculo> v = gestor2.cargarVehiculos();
    int i = 0;
    int j = 0;
    int w = 0;

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
            <li><a id="elegida" href="#">Vinculación contrato</a></li>
            <li><a href="#">Gestión contratos</a></li>
            <li><a href="#">Estadisticas</a></li>
        </ul>
    </header>
    <div id="contenedorContrato">
        <form id="creacionContrato" action="crearContrato" method="post">
            
            <div class="crearContrato" id="encontrarParqueadero">
                <label>Parqueadero:</label>
                <input name="k_idParqueadero" id="buscarParqueadero" list="parqueaderos" placeholder="buscar">
            </div>
            
            <div class="crearContrato" id="creaCliente">
                <h3>Cliente</h3>
                <input name="k_cedulaSearch" id="buscarCliente"list="clientes" placeholder="buscar">
                <label>Cedula:</label>
                <input name="k_cedula"  id="cedula" autofocus >
                <label>Primer nombre:</label>
                <input name="n_primerNombre" id="nombre" autofocus >
                <label>Primer apellido:</label>
                <input name="n_primerApellido" id="Papellido" autofocus >
                <label>Segundo apellido:</label>
                <input name="n_segundoApellido" id="sApellido" autofocus >
                <label>Direccion:</label>
                <input name="n_direccion" id="direccion" autofocus >
                <label>telefono:</label>
                <input name="q_telefono" id="telefono" autofocus >
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
                <input name="i_tipo" id="tipo" autofocus>
            </div >
            
            <div class="crearContrato" id="creaContrato">
                <h3>Contrato</h3>
                <label>ID contrato:</label>
                <input name="k_idContrato" id="identificadorContrato" required>
                <label>Costo:</label>
                <input name="q_costoTotal" id="costo" required>
                <label>Fecha de inicio:</label>
                <input name="f_fechaDeInicio" id="fechaIni" value="<%=formato.format(date)%>" required>
                <label>Fecha de finalización:</label>
                <input name="f_fechaDeFinalizacion" id="fechaFin" required>
            </div >
            <button type="submit" id="buttonCrearContrato">submit</button>
        </form>
        
        <datalist id="parqueaderos">
            <% while (j < p.size()) {%>
            <option><%= p.get(j).getK_idParqueadero()%></option>
            <%j++;
                }%>
        </datalist>
        <datalist id="clientes">
            <% while (i < cl.size()) {%>
            <option><%= cl.get(i).getK_cedula()%></option>
            <%i++;
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
<script type="text/javascript" src="assets/scriptCrearContrato.js"></script>

</html>

