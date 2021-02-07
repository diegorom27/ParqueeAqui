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
public class Cupo {
    protected int k_idCupo;
    protected String i_estado;
    protected String k_idArea;
    protected int k_idParqueadero;

    public Cupo() {
    }

    public int getK_idCupo() {
        return k_idCupo;
    }

    public void setK_idCupo(int k_idCupo) {
        this.k_idCupo = k_idCupo;
    }

    public String getI_estado() {
        return i_estado;
    }

    public void setI_estado(String i_estado) {
        this.i_estado = i_estado;
    }

    public String getK_idArea() {
        return k_idArea;
    }

    public void setK_idArea(String k_idArea) {
        this.k_idArea = k_idArea;
    }

    public int getK_idParqueadero() {
        return k_idParqueadero;
    }

    public void setK_idParqueadero(int k_idParqueadero) {
        this.k_idParqueadero = k_idParqueadero;
    }
    
    
}
