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
import modelo.logica.Area;

/**
 *
 * @author diego
 */
public class AreaDAO {
    private Area area;

    public AreaDAO() {
        area = new Area();
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
   
    public void incluirArea() throws CaException {
        try {
            String strSQL = "INSERT INTO area (k_idArea, k_idParqueadero, q_cuposTotales, i_tipo, q_cuposDisponibles) VALUES(?,?,?,?,?)";
        Connection conexion = ServiceLocator.getInstance().tomarConexion();
        PreparedStatement prepStmt = conexion.prepareStatement(strSQL);
        prepStmt.setString(1,area.getK_idArea()); 
        prepStmt.setFloat(2, area.getK_idParqueadero()); 
        prepStmt.setInt(3, area.getQ_cuposTotales()); 
        prepStmt.setInt(4, area.getI_tipo()); 
        prepStmt.setInt(5, area.getQ_cuposDisponibles()); 
        prepStmt.executeUpdate();
        prepStmt.close();
        ServiceLocator.getInstance().commit();
        } catch(SQLException e) {
            throw new CaException("AreaDAO", "No pudo crear el Area" + e.getMessage());
        } finally {
            ServiceLocator.getInstance().liberarConexion();
        }
    }
   
    
}
