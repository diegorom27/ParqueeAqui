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
    
    //Atributos
    
    private Servicio servicio;
    private ArrayList<Servicio> servicios;
    
    //Constructor
    
    public ServicioDAO() {
        servicio = new Servicio();
        servicios = new ArrayList();
    }

    //Getter & Setter

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }
    
    //Metodos publicos
    
    public void incluirServicio() throws CaException {
        try {
            String strSQL = "INSERT INTO servicio (k_idservicio, f_fycentrada, "
                            + "f_fycsalida, q_valorapagar k_idvehiculo) VALUES(?,?,?,?,?)";
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
        this.asignarCupo(String.valueOf(servicio.getK_idVehiculo()), String.valueOf(servicio.getK_idServicio()));
    }
    
    public void salida() throws CaException {
        try {
            String strSQL = "UPDATE servicio SET f_fycsalida=? AND q_valorapagar=? "
                            + "WHERE k_idservicio =?";
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
            String strSQL = "SELECT k_idservicio, f_fycentrada, f_fycsalida, "
                            + "q_valorapagar k_idvehiculo FROM vehiculo";
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
    
    public boolean verificarCupoVehiculoRegistrado(String k_idvehiculo) throws CaException{
        String i_tipo;
        boolean cupo = false;
        i_tipo=this.hallarTipo(k_idvehiculo);
        
        try {
            String strSQL = "SELECT q_cuposdisponibles from area WHERE i_tipo = " + i_tipo;
            Connection conexion = ServiceLocator.getInstance().tomarConexion();
            PreparedStatement prepStmt = conexion.prepareStatement(strSQL);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
                int cupos;
                cupos = rs.getInt(1);
                if(cupos > 0){
                   cupo=true;
                   break;
                }
            }
        } catch (SQLException e) {
            throw new CaException("ServicioDAO", "No pudo recuperar el servicio" + e.getMessage());
        }finally {
            ServiceLocator.getInstance().liberarConexion();
        }
        
        return cupo; 
    }
    
    public boolean verificarCupoVehiculoNuevo(String i_tipo) throws CaException{
        boolean cupo = false;
        try {
            String strSQL = "SELECT q_cuposdisponibles from area WHERE i_tipo = " + i_tipo;
            Connection conexion = ServiceLocator.getInstance().tomarConexion();
            PreparedStatement prepStmt = conexion.prepareStatement(strSQL);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
                int cupos;
                cupos = rs.getInt(1);
                if(cupos > 0){
                   cupo=true;
                   break;
                }
            }
        } catch (SQLException e) {
            throw new CaException("ServicioDAO", "No pudo recuperar el servicio" + e.getMessage());
        }finally {
            ServiceLocator.getInstance().liberarConexion();
        }
        
        return cupo;
    }

    //Metodos privados
    
    private String hallarTipo(String k_idvehiculo) throws CaException{
        String i_tipo;
        try {
            String strSQL = "SELECT i_tipo from vehiculo WHERE k_idvehiculo = " + k_idvehiculo;
            Connection conexion = ServiceLocator.getInstance().tomarConexion();
            PreparedStatement prepStmt = conexion.prepareStatement(strSQL);
            ResultSet rs = prepStmt.executeQuery();
            i_tipo = rs.getString(1);
        } catch (SQLException e) {
            throw new CaException("ServicioDAO", "No pudo recuperar el servicio" + e.getMessage());
        }finally {
            ServiceLocator.getInstance().liberarConexion();
        }
        
        return i_tipo;
    }   
     
    private String hallarArea(String i_tipo) throws CaException{
        String k_idarea = null;
        
        try {
            String strSQL = "SELECT k_idarea from area WHERE i_tipo = " + i_tipo
                            + "ORDER BY q_cuposdisponibles DESC;";
            Connection conexion = ServiceLocator.getInstance().tomarConexion();
            PreparedStatement prepStmt = conexion.prepareStatement(strSQL);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
                k_idarea = rs.getString(1);
                break;
            }
        } catch (SQLException e) {
            throw new CaException("ServicioDAO", "No pudo recuperar el servicio" + e.getMessage());
        }finally {
            ServiceLocator.getInstance().liberarConexion();
        }
        
        return k_idarea;
    }
    
    private String hallarParqueadero(String k_idarea) throws CaException{
        String k_idparqueadero = null;
        
        try {
            String strSQL = "SELECT k_idparqueadero from area WHERE k_idarea =  " + k_idarea + ";";
            Connection conexion = ServiceLocator.getInstance().tomarConexion();
            PreparedStatement prepStmt = conexion.prepareStatement(strSQL);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
                k_idparqueadero = String.valueOf(rs.getInt(1));
                break;
            }
        } catch (SQLException e) {
            throw new CaException("ServicioDAO", "No pudo recuperar el servicio" + e.getMessage());
        }finally {
            ServiceLocator.getInstance().liberarConexion();
        }
        
        return k_idparqueadero;
    }
    
    private String hallarCupoDisponible(String k_idarea) throws CaException{
        String k_idcupo = null;
        
        try {
            String strSQL = "SELECT k_idcupo from cupo WHERE k_idarea = " + k_idarea + " AND i_estado = ?;";
            Connection conexion = ServiceLocator.getInstance().tomarConexion();
            PreparedStatement prepStmt = conexion.prepareStatement(strSQL);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
                k_idcupo = String.valueOf(rs.getInt(1));
                break;
            }
        } catch (SQLException e) {
            throw new CaException("ServicioDAO", "No pudo recuperar el servicio" + e.getMessage());
        }finally {
            ServiceLocator.getInstance().liberarConexion();
        }
        
        return k_idcupo;
    }
    
    private void actualizarDatosCupo(String k_idarea, String k_idparqueadero, String k_idcupo) throws CaException{
        try {
            String strSQL = "UPDATE cupo SET i_estado = ? WHERE k_idarea = " + 
                            k_idarea + " AND k_idparqueadero = " + k_idparqueadero 
                            + " AND k_idcupo = " + k_idcupo + ";";
            Connection conexion = ServiceLocator.getInstance().tomarConexion();
            PreparedStatement prepStmt = conexion.prepareStatement(strSQL);
            prepStmt.executeUpdate();
            prepStmt.close();
            ServiceLocator.getInstance().commit();
        } catch (SQLException e) {
            throw new CaException("ServicioDAO", "No pudo actualizar el servicio" + e.getMessage());
        } finally {
            ServiceLocator.getInstance().liberarConexion();
        }
    }
    
    private void asignarDatosCupo_servicio(String k_idservicio, String k_idcupo) throws CaException{
        try {
            String strSQL = "INSERT INTO cupo_servicio (k_idservicio, k_idcupo) "
                            + "VALUES(" + k_idservicio + "," + k_idcupo + ")";
            Connection conexion = ServiceLocator.getInstance().tomarConexion();
            PreparedStatement prepStmt = conexion.prepareStatement(strSQL);
            prepStmt.executeUpdate();
            prepStmt.close();
            ServiceLocator.getInstance().commit();
        } catch (SQLException e) {
            throw new CaException("ServicioDAO", "No pudo crear el servicio" + e.getMessage());
        } finally {
            ServiceLocator.getInstance().liberarConexion();
        }
    }
    private void asignarCupo(String k_idvehiculo, String k_idservicio) throws CaException{
        String i_tipo = this.hallarTipo(k_idvehiculo);
        String k_idarea = this.hallarArea(i_tipo);
        String k_idcupo = this.hallarCupoDisponible(k_idarea);
        String k_idparqueadero = this.hallarParqueadero(k_idarea);
        this.actualizarDatosCupo(k_idarea, k_idparqueadero, k_idcupo);
        this.asignarDatosCupo_servicio(k_idservicio, k_idcupo);
    }
    
    /*
    private void rellenarTablas(int k_idServicio, int k_idVehiculo) throws CaException {
        try {
            String strSQL = "INSERT INTO cupo_sevicio (k_idservicio, k_idcupo)"
                            + " VALUES(" + k_idServicio + ",?)";
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
    */
    
    /*
    public void actualizarCupo(String servicio) throws CaException {
        if(servicio.equals("entrada")){
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
    }
    */
    
}
