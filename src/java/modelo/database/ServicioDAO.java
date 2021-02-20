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
    
    //METODOS PUBLICOS
    
    /* Incluye un servicio (entrada de vehículo a la base de datos), solo añade
       solo añade los valores id, fech entrada, id vehículo*/
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
    
    // Se actualiza la tabla de servicios con los valores que faltaban
    public void salida(String q_valorapagar) throws CaException {
        try {
            String strSQL = "UPDATE servicio SET f_fycsalida=? AND " + q_valorapagar 
                            + " WHERE k_idservicio =?";
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
    
    // crea un array con todos los servicios en bd
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
    
    // verifica si existe cupo para un vehículo ya registrado en el sistema
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
    
    // verifica si existen cupos para un vehículo que no esta registrado en bd
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
    
    // Retorna un bool dependiendo si el vehículo tiene contrato o no
    public boolean verificarContrato(String k_idvehiculo) throws CaException{
        boolean contrato = false;
        try {
            String strSQL = "SELECT i_estado from contrato WHERE k_idvehiculo = " + k_idvehiculo;
            Connection conexion = ServiceLocator.getInstance().tomarConexion();
            PreparedStatement prepStmt = conexion.prepareStatement(strSQL);
            ResultSet rs = prepStmt.executeQuery();
            String estado = rs.getString(1);
            if(estado == "v"){
                contrato = true;
            }
        } catch (SQLException e) {
            throw new CaException("ServicioDAO", "No pudo recuperar el servicio" + e.getMessage());
        }finally {
            ServiceLocator.getInstance().liberarConexion();
        }
        
        return contrato;
    }

    //METODOS PRIVADOS
    
    // Metodos de entrada de vehículo (*)
    
    //* Retorna el tipo de vehículo según la id del vehículo
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
     
    //* Retorna el área seguún el tipo de vehículo 
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
    
    //* Retorna el parqueadero según el id de el id área
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
    
    // Retorna el cupo según la id servicio
    private String hallarCupo(String k_idservicio) throws CaException{
        String k_idcupo;
        
        try {
            String strSQL = "SELECT k_idcupo from cupo_servicio WHERE k_idservicio =  " 
                            + k_idservicio + ";";
            Connection conexion = ServiceLocator.getInstance().tomarConexion();
            PreparedStatement prepStmt = conexion.prepareStatement(strSQL);
            ResultSet rs = prepStmt.executeQuery();
            k_idcupo = String.valueOf(rs.getInt(1));
        } catch (SQLException e) {
            throw new CaException("ServicioDAO", "No pudo recuperar el servicio" + e.getMessage());
        }finally {
            ServiceLocator.getInstance().liberarConexion();
        }
        
        return k_idcupo;
    }
    
    //* retorna un cupo disponible en un área
    private String hallarCupoDisponible(String k_idarea) throws CaException{
        String k_idcupo = null;
        
        try {
            String strSQL = "SELECT k_idcupo from cupo WHERE k_idarea = " + k_idarea + " AND i_estado = v;";
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
    
    //* Actualiza los datos de la tabla cupo en bd cuando una vehículo ingresa
    private void actualizarDatosCupo(String k_idarea, String k_idparqueadero, String k_idcupo) throws CaException{
        try {
            String strSQL = "UPDATE cupo SET i_estado = o WHERE k_idarea = " + 
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
    
    //* Inserta los datos necesarios a la tabla cupo_servicio cuando un vehículo ingresa
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
    
    //* Asigna un cupo a un vehículo que esta por ingresar
    private void asignarCupo(String k_idvehiculo, String k_idservicio) throws CaException{
        String i_tipo = this.hallarTipo(k_idvehiculo);
        String k_idarea = this.hallarArea(i_tipo);
        String k_idcupo = this.hallarCupoDisponible(k_idarea);
        String k_idparqueadero = this.hallarParqueadero(k_idarea);
        
        this.actualizarDatosCupo(k_idarea, k_idparqueadero, k_idcupo);
        this.asignarDatosCupo_servicio(k_idservicio, k_idcupo);
    }
    
    // Metodos salida de vehículo (-)
    
    //actualiza los datos de las tablas cuando sale un vehículo
    private void actualizarDatosSalida(String k_idvehiculo, String k_idservicio) throws CaException{
        this.actualizaCupoSalida(k_idservicio);
    }
    
    //Actualiza la tabla cupo cuando un vehículo sale
    private void actualizaCupoSalida(String k_idservicio) throws CaException{
        String k_idcupo = this.hallarCupo(k_idservicio);
        
        try {
            String strSQL = "UPDATE cupo SET i_estado = v WHERE k_idcupo = " 
                            + k_idcupo + ";";
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
