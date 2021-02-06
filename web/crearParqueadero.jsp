<%-- 
    Document   : crearParqueadero
    Created on : 3/02/2021, 05:57:59 PM
    Author     : diego
--%>

<!DOCTYPE html>
<html>

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
            <li><a id="elegida" href="#">Registro de parqueadero</a></li>
            <li><a href="#">Gestion de parqueadero</a></li>
            <li><a href="#">Ingreso </a></li>
            <li><a href="#">Historial Ingresos</a></li>
            <li><a href="#">Vinculación contrato</a></li>
            <li><a href="#">Gestión contratos</a></li>
            <li><a href="#">Estadisticas</a></li>
        </ul>
    </header>
    <div class="contenedor">
        <h1 class="titulo">Registro parqueadero</h1>
        <div id="contTabla">
            <table summary="Factor por Nivel de Servicio: Factor asociado con las características fisicas del
        estacionamiento que puede fluctuar entre 0,5 y 1,0. ">
                <caption>Factor por Nivel de Servicio: Factor asociado con las características fisicas del
                    estacionamiento que puede fluctuar entre 0,5 y 1,0. </caption>
                <tr>
                    <th>Caracteristicas del estacionamiento</th>
                    <th>FNS</th>
                </tr>
                <tr>
                    <td>En altura o subterráneo con dos o más niveles </td>
                    <td>1.0</td>
                </tr>
                <tr>
                    <td>Subterráneo, un solo nivel y 50 cupos o más </td>
                    <td>0.9</td>
                </tr>
                <tr>
                    <td>Subterráneo, un solo nivel y con menos de 50 cupos </td>
                    <td>0.8</td>
                </tr>
                <tr>
                    <td>A nivel, piso en concreto, asfalto o gravilla lavada de río<br>
                        compactada, y con 50 cupos o más </td>
                    <td>0.7</td>
                </tr>
                <tr>
                    <td>A nivel, piso en concreto, asfalto o gravilla lavada de río<br>
                        compactada,_y_con menos de 50 cupos </td>
                    <td>0.6</td>
                </tr>
                <tr>
                    <td>A nivel, pisos en afirmado o césped </td>
                    <td>0.5</td>
                </tr>
            </table>
        </div>
        <form id="creacionParqueadero" action="crearParqueadero" method="post">
            <label><b>ID:</b></label>
            <input type="number" name="k_idParqueadero" id="idParqueadero" placeholder="ID" autofocus required>
            <label><b>Numero de areas:</b></label>
            <input type="number" name="q_areas" id="numeroDeAreas" placeholder="areas" autofocus required>
            <label><b>Direccion:</b></label>
            <input type="text" name="n_direccion" id="direccion" placeholder="direccion" autofocus required>
            <label><b>Localidad:</b></label>
            <input type="text" name="n_localidad" id="localidad" placeholder="localidad" autofocus required>
            <label><b>FNS:</b></label>
            <input type="text" name="v_nfs" id="FNS" placeholder="FNS" autofocus required>
            <button type="submit" id="buttonCrearParqueadero">submit</button>
        </form>
    </div>
</body>
<script type="text/javascript" src="assets/scriptCrearParq.js"></script>

</html>
