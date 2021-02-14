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
public class Tarifa {
    
    protected int k_codigoTarifa;
    protected int q_precioPorMinuto;
    protected String i_tipo;

    public Tarifa() {
    }
    
    
    public int getK_codigoTarifa() {
        return k_codigoTarifa;
    }

    public void setK_codigoTarifa(int k_codigoTarifa) {
        this.k_codigoTarifa = k_codigoTarifa;
    }

    public int getQ_precioPorMinuto() {
        return q_precioPorMinuto;
    }

    public void setQ_precioPorMinuto(int q_precioPorMinuto) {
        this.q_precioPorMinuto = q_precioPorMinuto;
    }

    public String getI_tipo() {
        return i_tipo;
    }

    public void setI_tipo(String i_tipo) {
        this.i_tipo = i_tipo;
    }
    
    
    
}
