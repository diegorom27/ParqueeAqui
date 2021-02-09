window.addEventListener("scroll", function () {
var header = document.querySelector("header");
        header.classList.toggle("sticky", window.scrollY > 0);
});
var buscarCliente = document.getElementById("buscarCliente");
var cedula = document.getElementById("cedula");
var nombre = document.getElementById("nombre");
var Papellido = document.getElementById("Papellido");
var sApellido = document.getElementById("sApellido");
var direccion = document.getElementById("direccion");
var telefono = document.getElementById("telefono");

var buttonCrearContrato = document.getElementById("buttonCrearContrato");

buttonCrearContrato.addEventListener("click",function(){
    if(buscarCliente.value=== "" & (cedula.value==="" || nombre.value==="" || Papellido.value==="" || sApellido.value==="" || direccion.value==="" || telefono.value==="")){
        event.preventDefault();
    }
});



/*
        var creacionParqueadero = document.getElementById("creacionParqueadero");
        var buttonCrearParqueadero = document.getElementById("buttonCrearParqueadero");
        var contenedor = document.getElementById("contenedor");
        var estiloForm = "estiloForm";
        var idEnvio = "envioArea";
        var idParArea = "idParArea";
        var idArea = "idArea";
        var idTipo = "idTipo";
        var idCupos = "idcupos";
        var contTabla = document.getElementById("contTabla");
        buttonCrearParqueadero.addEventListener("click", function () {
        var numeroDeAreas = document.getElementById("numeroDeAreas").value;
                var idParqueadero = document.getElementById("idParqueadero").value;
                contTabla.innerHTML = "";
                alert(numeroDeAreas);
                for (let index = 0; index < numeroDeAreas; index++) {
        var form = document.createElement("form");
                form.className = estiloForm;
                contTabla.appendChild(form);
                var labelIdParq = document.createElement("label");
                var inputIdParq = document.createElement("input");
                labelIdParq.innerHTML = "ID parqueadero:";
                labelIdParq.name = labelIdParq
                inputIdParq.value = idParqueadero;
                inputIdParq.id = idParArea;
                form.appendChild(labelIdParq);
                form.appendChild(inputIdParq);
                var labelIdArea = document.createElement("label");
                var inputIdArea = document.createElement("input");
                inputIdArea.value = index + 1;
                inputIdArea.id = idArea;
                labelIdArea.innerHTML = "ID area:";
                form.appendChild(labelIdArea);
                form.appendChild(inputIdArea);
                var labelTipo = document.createElement("label");
                var inputTipo = document.createElement("input");
                labelTipo.innerHTML = "Tipo:";
                inputTipo.id = idTipo;
                form.appendChild(labelTipo);
                form.appendChild(inputTipo);
                var labelCupos = document.createElement("label");
                var inputCupos = document.createElement("input");
                labelCupos.innerHTML = "Cupos:";
                inputCupos.id = idCupos;
                form.appendChild(labelCupos);
                form.appendChild(inputCupos);
                var button = document.createElement("button");
                button.id = idEnvio;
                form.appendChild(button);
                $(document).ready(function() {
        $('#envioArea').click(function(event) {
        var idParqArea1 = $('#idParArea').val();
                var idArea1 = $('#idArea').val();
                var tipo = $('#idTipo').val();
                var cupos = $('#idcupos').val();
                // Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get
                $.post('crearArea', {
                idParqArea1 : idParqArea1,
                        idArea1: idArea1,
                        tipo: tipo,
                        cupos: cupos,
                }, alert("guardado con exito")
                        );
        });
        });
        }
        });
*/

//aun no funciona correctamente el envio
/*
        $(document).ready(function() {
$("#buttonCrearParqueadero").click(function(){
     event.preventDefault();
var idParqueadero = $("#idParqueadero").val();
        var numeroDeAreas = $("#numeroDeAreas").val();
        var direccion = $("#direccion").val();
        var localidad = $("#localidad").val();
        var FNS = $("#FNS").val();
        // Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get
        $.post("crearParqueadero", {
                idParqueadero : idParqueadero,
                numeroDeAreas: numeroDeAreas,
                direccion: direccion,
                localidad: localidad,
                FNS: FNS
        }, alert("guardado con exito")
            );
        });
});

$(document).ready(function(){
   $("#creacionParqueadero").bind("submit",function(){
    $.ajax({
        type: $(this).attr("method"),
        url: $(this).attr("action"),
        data: $(this).serialize(),
        success: function(){
            alert("envio correcto");
        },
        error: function(){
            alert("se fue a la verga");
            
        }
    });   
   }); 
   return false;
});
*/
