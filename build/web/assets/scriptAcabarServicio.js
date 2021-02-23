window.addEventListener("scroll", function () {
var header = document.querySelector("header");
        header.classList.toggle("sticky", window.scrollY > 0);
});

var buscarVehiculo = document.getElementById("buscarVehiculo");
var f_fycsalida = document.getElementById("f_fycsalida");
var q_valorapagar = document.getElementById("q_valorapagar");

var buttonCrearServicio = document.getElementById("buttonCrearServicio");

buttonCrearServicio.addEventListener("click",function(){
    if((buscarVehiculo.value=== ""  & (f_fycsalida.value=== "" || q_valorapagar.value==="")) || (buscarVehiculo.value!== ""  & (f_fycsalida.value!== "" || q_valorapagar.value!=="" )) ){
        event.preventDefault();
        alert("por favor ingrese todos los datos");
    }
});
