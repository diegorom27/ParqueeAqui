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
public class Cliente {
    protected int k_cedula;
    protected String n_primerNombre;
    protected String n_primerApellido;
    protected String n_segundoApellido;
    protected String n_direccion;
    protected int q_telefono;

    public Cliente() {
    }

    public int getK_cedula() {
        return k_cedula;
    }

    public void setK_cedula(int k_cedula) {
        this.k_cedula = k_cedula;
    }

    public String getN_primerNombre() {
        return n_primerNombre;
    }

    public void setN_primerNombre(String n_primerNombre) {
        this.n_primerNombre = n_primerNombre;
    }

    public String getN_primerApellido() {
        return n_primerApellido;
    }

    public void setN_primerApellido(String n_primerApellido) {
        this.n_primerApellido = n_primerApellido;
    }

    public String getN_segundoApellido() {
        return n_segundoApellido;
    }

    public void setN_segundoApellido(String n_segundoApellido) {
        this.n_segundoApellido = n_segundoApellido;
    }

    public String getN_direccion() {
        return n_direccion;
    }

    public void setN_direccion(String n_direccion) {
        this.n_direccion = n_direccion;
    }

    public int getQ_telefono() {
        return q_telefono;
    }

    public void setQ_telefono(int q_telefono) {
        this.q_telefono = q_telefono;
    }
    
    
}
