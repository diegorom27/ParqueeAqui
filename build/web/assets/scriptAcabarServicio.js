window.addEventListener("scroll", function () {
var header = document.querySelector("header");
        header.classList.toggle("sticky", window.scrollY > 0);
});

var buscarServicio = document.getElementById("buscarServicio");

var buttonCrearServicio = document.getElementById("buttonCrearServicio");

buttonCrearServicio.addEventListener("click",function(){
    if(buscarServicio.value=== ""){
        event.preventDefault();
        alert("por favor ingrese todos los datos");
    }
});
