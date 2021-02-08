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
import modelo.logica.Vehiculo;

/**
 *
 * @author diego
 */
public class VehiculoDAO {
    private Vehiculo vehiculo;
    private ArrayList<Vehiculo> vehiculos;

    public VehiculoDAO() {
        vehiculo = new Vehiculo();
        vehiculos = new ArrayList();
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
    public void incluirVehiculo() throws CaException {
        try {
            String strSQL = "INSERT INTO vehiculo (k_idVehiculo, n_marca, n_color, i_tipo) VALUES(?,?,?,?)";
            Connection conexion = ServiceLocator.getInstance().tomarConexion();
            PreparedStatement prepStmt = conexion.prepareStatement(strSQL);
            prepStmt.setInt(1, vehiculo.getK_idVehiculo());
            prepStmt.setString(2, vehiculo.getN_marca());
            prepStmt.setString(3, vehiculo.getN_color());
            prepStmt.setString(4, vehiculo.getI_tipo());
            prepStmt.executeUpdate();
            prepStmt.close();
            ServiceLocator.getInstance().commit();
        } catch (SQLException e) {
            throw new CaException("VehiculoDAO", "No pudo crear el vehiculo" + e.getMessage());
        } finally {
            ServiceLocator.getInstance().liberarConexion();
        }
    }

    public ArrayList<Vehiculo> cargarVehiculos() throws CaException {
        try {
            String strSQL = "SELECT k_idVehiculo, n_marca, n_color, i_tipo FROM vehiculo";
            Connection conexion = ServiceLocator.getInstance().tomarConexion();
            PreparedStatement prepStmt = conexion.prepareStatement(strSQL);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
                Vehiculo vehiculo1 = new Vehiculo();
                vehiculo1.setK_idVehiculo(rs.getInt(1));
                vehiculo1.setN_marca(rs.getString(2));
                vehiculo1.setN_color(rs.getString(3));
                vehiculo1.setI_tipo(rs.getString(4));

                vehiculos.add(vehiculo1);
            }
        } catch (SQLException e) {
            throw new CaException("VehiculoDAO", "No pudo recuperar el vehiculo" + e.getMessage());
        }finally {
            ServiceLocator.getInstance().liberarConexion();
        }
        return vehiculos;
    }
    
    
}
