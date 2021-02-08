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

/**
 *
 * @author diego
 */
public class ParqueaderoDAO {

    private Parqueadero parqueadero;

    private ArrayList<Parqueadero> parqueaderos;

    public ParqueaderoDAO() {
        parqueadero = new Parqueadero();

        parqueaderos = new ArrayList();
    }

    public Parqueadero getParqueadero() {
        return parqueadero;
    }

    public void setParqueaderos(ArrayList<Parqueadero> parqueaderos) {
        this.parqueaderos = parqueaderos;
    }
    
    public void incluirParqueadero() throws CaException {
        try {
            String strSQL = "INSERT INTO Parqueadero (k_idParqueadero, v_nfs, q_areas, n_direccion, n_localidad) VALUES(?,?,?,?,?)";
            Connection conexion = ServiceLocator.getInstance().tomarConexion();
            PreparedStatement prepStmt = conexion.prepareStatement(strSQL);
            prepStmt.setInt(1, parqueadero.getK_idParqueadero());
            prepStmt.setFloat(2, parqueadero.getV_nfs());
            prepStmt.setInt(3, parqueadero.getQ_areas());
            prepStmt.setString(4, parqueadero.getN_direccion());
            prepStmt.setString(5, parqueadero.getN_localidad());
            prepStmt.executeUpdate();
            prepStmt.close();
            ServiceLocator.getInstance().commit();
        } catch (SQLException e) {
            throw new CaException("ParqueaderoDAO", "No pudo crear el parqueadero" + e.getMessage());
        } finally {
            ServiceLocator.getInstance().liberarConexion();
        }
    }

    public ArrayList<Parqueadero> cargarParqueaderos() throws CaException {
        try {
            String strSQL = "SELECT k_idParqueadero, v_nfs, q_areas, n_direccion, n_localidad FROM Parqueadero";
            Connection conexion = ServiceLocator.getInstance().tomarConexion();
            PreparedStatement prepStmt = conexion.prepareStatement(strSQL);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
                Parqueadero parqueadero1 = new Parqueadero();
                parqueadero1.setK_idParqueadero(rs.getInt(1));
                parqueadero1.setV_nfs(rs.getInt(2));
                parqueadero1.setQ_areas(rs.getInt(3));
                parqueadero1.setN_direccion(rs.getString(4));
                parqueadero1.setN_localidad(rs.getString(5));

                parqueaderos.add(parqueadero1);
            }
        } catch (SQLException e) {
            throw new CaException("ParqueaderoDAO", "No pudo recuperar el parqueadero" + e.getMessage());
        }finally {
            ServiceLocator.getInstance().liberarConexion();
        }
        return parqueaderos;
    }

}
