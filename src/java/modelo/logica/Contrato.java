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
public class Contrato {
    protected int k_idContrato;
    protected int q_costoTotal;
    protected String f_fechaDeInicio;
    protected String f_fechaDeFinalizacion;
    protected String i_estado;
    protected int k_idParqueadero;
    protected int k_cedula; 
    protected int k_idVehiculo;

    public Contrato() {
    }

    public int getK_idContrato() {
        return k_idContrato;
    }

    public void setK_idContrato(int k_idContrato) {
        this.k_idContrato = k_idContrato;
    }

    public int getQ_costoTotal() {
        return q_costoTotal;
    }

    public void setQ_costoTotal(int q_costoTotal) {
        this.q_costoTotal = q_costoTotal;
    }

    public String getF_fechaDeInicio() {
        return f_fechaDeInicio;
    }

    public void setF_fechaDeInicio(String f_fechaDeInicio) {
        this.f_fechaDeInicio = f_fechaDeInicio;
    }

    public String getF_fechaDeFinalizacion() {
        return f_fechaDeFinalizacion;
    }

    public void setF_fechaDeFinalizacion(String f_fechaDeFinalizacion) {
        this.f_fechaDeFinalizacion = f_fechaDeFinalizacion;
    }

    public String getI_estado() {
        return i_estado;
    }

    public void setI_estado(String i_estado) {
        this.i_estado = i_estado;
    }

    public int getK_idParqueadero() {
        return k_idParqueadero;
    }

    public void setK_idParqueadero(int k_idParqueadero) {
        this.k_idParqueadero = k_idParqueadero;
    }

    public int getK_cedula() {
        return k_cedula;
    }

    public void setK_cedula(int k_cedula) {
        this.k_cedula = k_cedula;
    }

    public int getK_idVehiculo() {
        return k_idVehiculo;
    }

    public void setK_idVehiculo(int k_idVehiculo) {
        this.k_idVehiculo = k_idVehiculo;
    }   
}
