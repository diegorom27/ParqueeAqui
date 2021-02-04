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
public class Parqueadero {
    protected int k_idParqueadero;
    protected float v_nfs;
    protected int q_areas;
    protected String n_direccion;
    protected String n_localidad;

    public Parqueadero() {
    }
    
    

    public int getK_idParqueadero() {
        return k_idParqueadero;
    }

    public void setK_idParqueadero(int k_idParqueadero) {
        this.k_idParqueadero = k_idParqueadero;
    }

    public float getV_nfs() {
        return v_nfs;
    }

    public void setV_nfs(float v_nfs) {
        this.v_nfs = v_nfs;
    }

    public int getQ_areas() {
        return q_areas;
    }

    public void setQ_areas(int q_areas) {
        this.q_areas = q_areas;
    }

    public String getN_direccion() {
        return n_direccion;
    }

    public void setN_direccion(String n_direccion) {
        this.n_direccion = n_direccion;
    }

    public String getN_localidad() {
        return n_localidad;
    }

    public void setN_localidad(String n_localidad) {
        this.n_localidad = n_localidad;
    }
}
