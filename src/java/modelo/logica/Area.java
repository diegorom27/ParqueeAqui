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
public class Area {
    protected String k_idArea;
    protected int k_idParqueadero;
    protected int q_cuposTotales;
    protected int i_tipo;
    protected int q_cuposDisponibles;

    public Area() {
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

    public int getQ_cuposTotales() {
        return q_cuposTotales;
    }

    public void setQ_cuposTotales(int q_cuposTotales) {
        this.q_cuposTotales = q_cuposTotales;
    }

    public int getI_tipo() {
        return i_tipo;
    }

    public void setI_tipo(int i_tipo) {
        this.i_tipo = i_tipo;
    }

    public int getQ_cuposDisponibles() {
        return q_cuposDisponibles;
    }

    public void setQ_cuposDisponibles(int q_cuposDisponibles) {
        this.q_cuposDisponibles = q_cuposDisponibles;
    }
    
}
