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
import modelo.logica.Servicio;


/**
 *
 * @author lenovo
 */
public class ServicioDAO {
    
    private Servicio servicio;
    private ArrayList<Servicio> servicios;

    public ServicioDAO() {
        servicio = new Servicio();
        servicios = new ArrayList();
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }
    
    public void entrada() throws CaException {
        try {
            String strSQL = "INSERT INTO servicio (k_idServicio, f_fycEntrada, "
                            + "f_fycSalida, q_valorAPagar k_idVehiculo) VALUES(?,?,?,?,?)";
            Connection conexion = ServiceLocator.getInstance().tomarConexion();
            PreparedStatement prepStmt = conexion.prepareStatement(strSQL);
            prepStmt.setInt(1, servicio.getK_idServicio());
            prepStmt.setString(2, servicio.getF_fycEntrada());
            prepStmt.setString(3, "01/01/0001");
            prepStmt.setInt(4, 0);
            prepStmt.setInt(5, servicio.getK_idVehiculo());
            prepStmt.executeUpdate();
            prepStmt.close();
            ServiceLocator.getInstance().commit();
        } catch (SQLException e) {
            throw new CaException("ServicioDAO", "No pudo crear el servicio" + e.getMessage());
        } finally {
            ServiceLocator.getInstance().liberarConexion();
        }
    }
    
    public void salida() throws CaException {
        try {
            String strSQL = "UPDATE servicio SET f_fycSalida=? AND q_valorAPagar=? "
                            + "WHERE k_idServicio =?";
            Connection conexion = ServiceLocator.getInstance().tomarConexion();
            PreparedStatement prepStmt = conexion.prepareStatement(strSQL);
            prepStmt.setString(1, servicio.getF_fycSalida());
            prepStmt.setInt(2, servicio.getQ_valorAPagar());
            prepStmt.setInt(3, servicio.getK_idServicio());
            prepStmt.executeUpdate();
            prepStmt.close();
            ServiceLocator.getInstance().commit();
        } catch (SQLException e) {
            throw new CaException("ServicioDAO", "No pudo actualizar el servicio" + e.getMessage());
        } finally {
            ServiceLocator.getInstance().liberarConexion();
        }
    }
    
    public ArrayList<Servicio> cargarServicios() throws CaException {
        try {
            String strSQL = "SELECT k_idServicio, f_fycEntrada, f_fycSalida, "
                            + "q_valorAPagar k_idVehiculo FROM vehiculo";
            Connection conexion = ServiceLocator.getInstance().tomarConexion();
            PreparedStatement prepStmt = conexion.prepareStatement(strSQL);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
                Servicio servicio1 = new Servicio();
                servicio1.setK_idServicio(rs.getInt(1));
                servicio1.setF_fycEntrada(rs.getString(2));
                servicio1.setF_fycSalida(rs.getString(3));
                servicio1.setQ_valorAPagar(rs.getInt(4));
                servicio1.setK_idVehiculo(rs.getInt(5));

                servicios.add(servicio1);
            }
        } catch (SQLException e) {
            throw new CaException("ServicioDAO", "No pudo recuperar el servicio" + e.getMessage());
        }finally {
            ServiceLocator.getInstance().liberarConexion();
        }
        return servicios;
    }
    
}
