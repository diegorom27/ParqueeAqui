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

/**
 *
 * @author diego
 */
public class ParqueaderoDAO {

    private Parqueadero parqueadero;

    public ParqueaderoDAO() {
        parqueadero = new Parqueadero();
    }

    public Parqueadero getParqueadero() {
        return parqueadero;
    }

    public void setParqueadero(Parqueadero parqueadero) {
        this.parqueadero = parqueadero;
    }
    
    public void incluirParqueadero() throws CaException {
        try {
            String strSQL = "INSERT INTO Parqueadero (k_idParqueadero, v_nfs, q_areas, n_direccion, n_localidad) VALUES(?,?,?,?,?)";
        Connection conexion = ServiceLocator.getInstance().tomarConexion();
        PreparedStatement prepStmt = conexion.prepareStatement(strSQL);
        prepStmt.setInt(1,parqueadero.getK_idParqueadero()); 
        prepStmt.setFloat(2, parqueadero.getV_nfs()); 
        prepStmt.setInt(3, parqueadero.getQ_areas()); 
        prepStmt.setString(4, parqueadero.getN_direccion()); 
        prepStmt.setString(5, parqueadero.getN_localidad()); 
        prepStmt.executeUpdate();
        prepStmt.close();
        ServiceLocator.getInstance().commit();
        } catch(SQLException e) {
            throw new CaException("ParqueaderoDAO", "No pudo crear el parqueadero" + e.getMessage());
        } finally {
            ServiceLocator.getInstance().liberarConexion();
        }
    }
}
