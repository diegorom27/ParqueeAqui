/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.logica;

/**
 *
 * @author diego
 */
public class Vehiculo {
   protected int k_idVehiculo; 
   protected String n_marca;
   protected String n_color;
   protected String i_tipo;

    public Vehiculo() {
    }

    public int getK_idVehiculo() {
        return k_idVehiculo;
    }

    public void setK_idVehiculo(int k_idVehiculo) {
        this.k_idVehiculo = k_idVehiculo;
    }

    public String getN_marca() {
        return n_marca;
    }

    public void setN_marca(String n_marca) {
        this.n_marca = n_marca;
    }

    public String getN_color() {
        return n_color;
    }

    public void setN_color(String n_color) {
        this.n_color = n_color;
    }

    public String getI_tipo() {
        return i_tipo;
    }

    public void setI_tipo(String i_tipo) {
        this.i_tipo = i_tipo;
    }
   
   
}
