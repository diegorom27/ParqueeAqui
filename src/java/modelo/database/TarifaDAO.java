/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.database;

import controlador.util.CaException;
import controlador.util.ServiceLocator;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.logica.Parqueadero;
import modelo.logica.Tarifa;

/**
 *
 * @author diego
 */
public class TarifaDAO {
  
    Tarifa tarifa;
    ArrayList<Tarifa> tarifas;

    public TarifaDAO() {
        tarifa = new Tarifa();
        tarifas = new ArrayList();
    }

    public Tarifa getTarifa() {
        return tarifa;
    }

    public void setTarifa(Tarifa tarifa) {
        this.tarifa = tarifa;
    }

    public ArrayList<Tarifa> getTarifas() {
        return tarifas;
    }

    public void setTarifas(ArrayList<Tarifa> tarifas) {
        this.tarifas = tarifas;
    }
    
    public ArrayList<Tarifa> cargarTarifas() throws CaException {
        try {
            String strSQL = "SELECT k_codigoTarifa, q_precioPorMinuto, i_tipo FROM tarifa";
            Connection conexion = ServiceLocator.getInstance().tomarConexion();
            PreparedStatement prepStmt = conexion.prepareStatement(strSQL);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
                Tarifa tarifa1 = new Tarifa();
                tarifa1.setK_codigoTarifa(rs.getInt(1));
                tarifa1.setQ_precioPorMinuto(rs.getInt(2));
                tarifa1.setI_tipo(rs.getString(3));
                
                tarifas.add(tarifa1);
            }
        } catch (SQLException e) {
            throw new CaException("TarifasDAO", "No pudo recuperar la tarifa" + e.getMessage());
        }finally {
            ServiceLocator.getInstance().liberarConexion();
        }
        return tarifas;
    }
        
}
