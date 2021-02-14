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

var identificadorVehiculo = document.getElementById("identificadorVehiculo");
var marca = document.getElementById("marca");
var color = document.getElementById("color");
var tipo = document.getElementById("tipo");

var buttonCrearContrato = document.getElementById("buttonCrearContrato");

buttonCrearContrato.addEventListener("click",function(){
    if((buscarCliente.value=== "" & (cedula.value==="" || nombre.value==="" || Papellido.value==="" || sApellido.value==="" || direccion.value==="" || telefono.value==="")) ||(buscarCliente.value!== "" & (cedula.value!=="" || nombre.value!=="" || Papellido.value!=="" || sApellido.value!=="" || direccion.value!=="" || telefono.value!=="")) ){
        event.preventDefault();
        alert("por favor no marque buscar si desea ingrear un nuevo cliente");
    }
    if((identificadorVehiculo.value=== "" & (marca.value==="" || color.value==="" || tipo.value==="")) || (identificadorVehiculo.value!== "" & (marca.value!=="" || color.value!=="" || tipo.value!=="" )) ){
        event.preventDefault();
        alert("por favor no marque buscar si desea ingrear un nuevo vehiculo");
    }
});



