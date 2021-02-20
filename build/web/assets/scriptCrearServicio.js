window.addEventListener("scroll", function () {
var header = document.querySelector("header");
        header.classList.toggle("sticky", window.scrollY > 0);
});

var buscarVehiculo = document.getElementById("buscarVehiculo");
var identificadorVehiculo = document.getElementById("identificadorVehiculo");
var marca = document.getElementById("marca");
var color = document.getElementById("color");
var tipo = document.getElementById("tipo");

var buttonCrearServicio = document.getElementById("buttonCrearServicio");

buttonCrearServicio.addEventListener("click",function(){
    if((buscarVehiculo.value=== ""  & (identificadorVehiculo.value=== "" || marca.value==="" || color.value==="" || tipo.value==="")) || (buscarVehiculo.value!== ""  & (identificadorVehiculo.value!== "" || marca.value!=="" || color.value!=="" || tipo.value!=="" )) ){
        event.preventDefault();
        alert("por favor no marque buscar si desea ingrear un nuevo vehiculo");
    }
});



