/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.logica;

import java.sql.Timestamp;

/**
 *
 * @author lenovo
 */
public class Servicio {
    
    protected int k_idservicio;
    protected Timestamp f_fycentrada;
    protected Timestamp f_fycsalida;
    protected int q_valorapagar;
    protected int k_idvehiculo;

    public int getK_idservicio() {
        return k_idservicio;
    }

    public void setK_idservicio(int k_idservicio) {
        this.k_idservicio = k_idservicio;
    }

    public Timestamp getF_fycentrada() {
        return f_fycentrada;
    }

    public void setF_fycentrada(Timestamp f_fycentrada) {
        this.f_fycentrada = f_fycentrada;
    }

    public Timestamp getF_fycsalida() {
        return f_fycsalida;
    }

    public void setF_fycsalida(Timestamp f_fycsalida) {
        this.f_fycsalida = f_fycsalida;
    }

    public int getQ_valorapagar() {
        return q_valorapagar;
    }

    public void setQ_valorapagar(int q_valorapagar) {
        this.q_valorapagar = q_valorapagar;
    }

    public int getK_idvehiculo() {
        return k_idvehiculo;
    }

    public void setK_idvehiculo(int k_idvehiculo) {
        this.k_idvehiculo = k_idvehiculo;
    }
}
    