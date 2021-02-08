window.addEventListener("scroll", function () {
var header = document.querySelector("header");
        header.classList.toggle("sticky", window.scrollY > 0);
});

var flecha = document.getElementsByClassName("flecha");
var opcion = document.getElementsByClassName("opcion"); 
var negrita = document.getElementsByClassName("negrita")


for (let index = 0; index < opcion.length; index++) {
    opcion[index].addEventListener("click",function(){
        var seleccionado = opcion[index].nextElementSibling;
        if(seleccionado.style.display=="block"){
            seleccionado.style.display="none";
            seleccionado.style.borderBottom="none";
            opcion[index].style.borderBottom="solid hsl(240, 6%, 50%)";
            negrita[index].style.fontWeight="normal";
            if(seleccionado.style.display=="none"){
                flecha[index].style.transform="rotate(0)";
            }
        }else{
            seleccionado.style.display="block";
            opcion[index].style.fontWeight="600";
            opcion[index].style.borderBottom="none";
            seleccionado.style.borderBottom="solid hsl(240, 6%, 50%)";
            negrita[index].style.fontWeight="bolder";
            if(seleccionado.style.display=="block"){
                flecha[index].style.transform="rotate(-180deg)";
            }
        }
    });    
}
for (let index = 0; index < opcion.length; index++) {
    negrita[index].addEventListener("mouseover",function(){
            ImgAutos.style.left="2px";  
    });
}
for (let index = 0; index < opcion.length; index++) {
    negrita[index].addEventListener("mouseleave",function(){
            ImgAutos.style.left="19px";  
    });
}
