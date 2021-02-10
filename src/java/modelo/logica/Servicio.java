/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.logica;

/**
 *
 * @author lenovo
 */
public class Servicio {
    
    protected int k_idServicio;
    protected String f_fycEntrada;
    protected String f_fycSalida;
    protected int q_valorAPagar;
    protected int k_idVehiculo;

    public int getK_idServicio() {
        return k_idServicio;
    }

    public void setK_idServicio(int k_idServicio) {
        this.k_idServicio = k_idServicio;
    }

    public String getF_fycEntrada() {
        return f_fycEntrada;
    }

    public void setF_fycEntrada(String f_fycEntrada) {
        this.f_fycEntrada = f_fycEntrada;
    }

    public String getF_fycSalida() {
        return f_fycSalida;
    }

    public void setF_fycSalida(String f_fycSalida) {
        this.f_fycSalida = f_fycSalida;
    }

    public int getQ_valorAPagar() {
        return q_valorAPagar;
    }

    public void setQ_valorAPagar(int q_valorAPagar) {
        this.q_valorAPagar = q_valorAPagar;
    }

    public int getK_idVehiculo() {
        return k_idVehiculo;
    }

    public void setK_idVehiculo(int k_idVehiculo) {
        this.k_idVehiculo = k_idVehiculo;
    }
    
}
