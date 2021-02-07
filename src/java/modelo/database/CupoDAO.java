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
import modelo.logica.Cupo;

/**
 *
 * @author diego
 */
public class CupoDAO {

    private Cupo cupo;

    public CupoDAO() {
        cupo = new Cupo();
    }

    public Cupo getCupo() {
        return cupo;
    }

    public void setCupo(Cupo cupo) {
        this.cupo = cupo;
    }

    public void incluirCupo() throws CaException {
        try {
            String strSQL = "INSERT INTO cupo (i_estado, k_idArea, k_idParqueadero) VALUES(?,?,?,?)";
            Connection conexion = ServiceLocator.getInstance().tomarConexion();
            PreparedStatement prepStmt = conexion.prepareStatement(strSQL);
            prepStmt.setString(1, cupo.getI_estado());
            prepStmt.setString(2, cupo.getK_idArea());
            prepStmt.setInt(3, cupo.getK_idParqueadero());
            prepStmt.executeUpdate();
            prepStmt.close();
            ServiceLocator.getInstance().commit();
        } catch (SQLException e) {
            throw new CaException("CupoDAO", "No pudo crear el cupo" + e.getMessage());
        } finally {
            ServiceLocator.getInstance().liberarConexion();
        }
    }

}
