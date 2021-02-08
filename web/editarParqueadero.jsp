<%-- 
    Document   : editarParqueadero
    Created on : 7/02/2021, 07:33:22 PM
    Author     : diego
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.logica.Parqueadero"%>
<%@page import="modelo.logica.GestorParqueadero"%>
<%
    GestorParqueadero gestor = new GestorParqueadero();
    ArrayList<Parqueadero> cl = gestor.cargarParqueaderos();
    int i = 0;

%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            <li><a href="crearParqueadero.jsp">Registro de parqueadero</a></li>
            <li><a id="elegida" href="#">Gestion de parqueadero</a></li>
            <li><a href="#">Ingreso </a></li>
            <li><a href="#">Historial Ingresos</a></li>
            <li><a href="crearContrato.jsp">Vinculación contrato</a></li>
            <li><a href="#">Gestión contratos</a></li>
            <li><a href="#">Estadisticas</a></li>
        </ul>
    </header>
    <div class="contenedor">
        <h1 class="titulo">Editar parqueadero</h1>
        <div id="contTabla">
            <table>
                <tr>
                    <th>ID parqueadero</th>
                    <th>Nro de areas</th>
                    <th>NFS</th>
                    <th>Localidad</th>
                    <th>Dirección</th>
                    <th>DELETE</th>
                </tr>
                <% while (i < cl.size()) {%>
                <tr>
                    <td><%= cl.get(i).getK_idParqueadero()%></td>
                    <td><%= cl.get(i).getQ_areas()%></td>
                    <td><%= cl.get(i).getV_nfs()%></td>
                    <td><%= cl.get(i).getN_localidad()%></td>
                    <td><%= cl.get(i).getN_direccion()%></td>
                    <td>DELETE</td>
                </tr>
                <%i++;
                    }%>

            </table>
        </div>
    </div>
</body>
<script type="text/javascript" src="assets/scriptCrearParq.js"></script>

</html>
