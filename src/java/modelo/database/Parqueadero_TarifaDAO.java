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
import java.sql.SQLException;
import modelo.logica.Parqueadero;
import modelo.logica.Tarifa;

/**
 *
 * @author diego
 */
public class Parqueadero_TarifaDAO {
    
    Parqueadero parqueadero;
    Tarifa tarifa; 

    public Parqueadero_TarifaDAO() {
        parqueadero = new Parqueadero();
        tarifa = new Tarifa();
    }

    public Parqueadero getParqueadero() {
        return parqueadero;
    }

    public void setParqueadero(Parqueadero parqueadero) {
        this.parqueadero = parqueadero;
    }

    public Tarifa getTarifa() {
        return tarifa;
    }

    public void setTarifa(Tarifa tarifa) {
        this.tarifa = tarifa;
    }
    public void incluirParqueadero_Tarifa() throws CaException {
        try {
            String strSQL = "INSERT INTO Parqueadero_Tarifa (k_idParqueadero, k_codigoTarifa) VALUES(?,?)";
            Connection conexion = ServiceLocator.getInstance().tomarConexion();
            PreparedStatement prepStmt = conexion.prepareStatement(strSQL);
            prepStmt.setInt(1, parqueadero.getK_idParqueadero());
            prepStmt.setInt(2, tarifa.getK_codigoTarifa());;
            prepStmt.executeUpdate();
            prepStmt.close();
            ServiceLocator.getInstance().commit();
        } catch (SQLException e) {
            throw new CaException("Parqueadero_TarifaDAO", "No pudo añadir instancia a Parqueadero_Tarifa" + e.getMessage());
        } finally {
            ServiceLocator.getInstance().liberarConexion();
        }
    }
    
    
    
}
