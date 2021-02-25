/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.database;

import controlador.util.CaException;
import controlador.util.ServiceLocator;
import java.sql.Connection;
import java.sql.Date;
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
    private ArrayList<Servicio> servicios2 = new ArrayList();
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
            //String strSQL = "INSERT INTO Parqueadero (k_idParqueadero, v_nfs, q_areas, n_direccion, n_localidad) VALUES(?,?,?,?,?)";
            //String strSQL = "INSERT INTO servicio (f_fycentrada, f_fycsalida, q_valorapagar, k_idvehiculo, k_idservicio) VALUES(LOCALTIMESTAMP(2), LOCALTIMESTAMP(2), " + q_valorapagar + ", 123, 123456)";
            String strSQL = "INSERT INTO servicio (k_idservicio, f_fycentrada, f_fycsalida, q_valorapagar, k_idvehiculo) VALUES(?,?,?,?,?)";
            Connection conexion = ServiceLocator.getInstance().tomarConexion();
            PreparedStatement prepStmt = conexion.prepareStatement(strSQL);
            prepStmt.setInt(1, servicio.getK_idservicio());
            prepStmt.setDate(2,Date.valueOf(servicio.getF_fycentrada()));
            prepStmt.setDate(3, Date.valueOf(servicio.getF_fycsalida()));
            prepStmt.setInt(4, servicio.getQ_valorapagar());
            prepStmt.setInt(5, servicio.getK_idvehiculo());
            prepStmt.executeUpdate();
            prepStmt.close();
            ServiceLocator.getInstance().commit();
        } catch (SQLException e) {
            throw new CaException("ServicioDAO", "No pudo crear el servicio" + e.getMessage());
        } finally {
            ServiceLocator.getInstance().liberarConexion();
        }
        this.asignarCupo(String.valueOf(servicio.getK_idvehiculo()), String.valueOf(servicio.getK_idservicio()));
    }
    
    // Se actualiza la tabla de servicios con los valores que faltaban
    public void salida(String k_idparqueadero) throws CaException {
        try {
            String strSQL = "UPDATE servicio SET f_fycsalida=?, q_valorapagar=? WHERE k_idservicio =?";
            Connection conexion = ServiceLocator.getInstance().tomarConexion();
            PreparedStatement prepStmt = conexion.prepareStatement(strSQL);
            prepStmt.setDate(1, Date.valueOf(servicio.getF_fycsalida()));
            prepStmt.setInt(2, servicio.getQ_valorapagar());
            prepStmt.setInt(3, servicio.getK_idservicio());
            prepStmt.executeUpdate();
            prepStmt.close();
            ServiceLocator.getInstance().commit();
        } catch (SQLException e) {
            throw new CaException("ServicioDAO", "No pudo actualizar el servicio" + e.getMessage());
        } finally {
            ServiceLocator.getInstance().liberarConexion();
        }
        this.actualizarDatosSalida(String.valueOf(servicio.getK_idservicio()), k_idparqueadero);
    }
    
    // crea un array con todos los servicios en bd
    public ArrayList<Servicio> cargarServicios() throws CaException {
        try {
            String strSQL = "SELECT k_idservicio, f_fycentrada, f_fycsalida, q_valorapagar, k_idvehiculo FROM servicio";
            Connection conexion = ServiceLocator.getInstance().tomarConexion();
            PreparedStatement prepStmt = conexion.prepareStatement(strSQL);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
                Servicio servicio1 = new Servicio();
                servicio1.setK_idservicio(rs.getInt(1));
                servicio1.setF_fycentrada(rs.getString(2));
                servicio1.setF_fycsalida(rs.getString(3));
                servicio1.setQ_valorapagar(rs.getInt(4));
                servicio1.setK_idvehiculo(rs.getInt(5));

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
    public boolean verificarCupo(String k_idvehiculo, String k_idparqueadero) throws CaException{
        String i_tipo;
        boolean cupo = false;
        i_tipo=this.hallarTipo(k_idvehiculo);
        
        try {
            String strSQL = "SELECT q_cuposdisponibles from area WHERE i_tipo = " 
                            + i_tipo + " AND k_idparqueadero = " + k_idparqueadero;
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
    public boolean verificarContrato(String k_idservicio) throws CaException{
        boolean contrato = false;
        String estado = "";
        String k_idvehiculo=hallarVehiculo(k_idservicio);
        
        try {
            String strSQL = "SELECT i_estado from contrato WHERE k_idvehiculo =?";
            Connection conexion = ServiceLocator.getInstance().tomarConexion();
            PreparedStatement prepStmt = conexion.prepareStatement(strSQL);
            prepStmt.setInt(1, Integer.valueOf(k_idvehiculo));
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
                estado = rs.getString(1);
                break;
            }
            if(estado.equals("v")){
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
    
    // Retorna el vehículo segun el id servicio
    private String hallarVehiculo(String k_idservicio) throws CaException{
        String k_idvehiculo = null;
        
        try {
            //SELECT EXISTS(SELECT k_idParqueadero, k_codigoTarifa FROM Parqueadero_Tarifa WHERE k_idParqueadero = ? AND k_codigoTarifa = ?)
            String strSQL = "SELECT k_idvehiculo from servicio s, cupo_servicio c WHERE s.k_idservicio=c.k_idservicio AND s.k_idservicio=?";
            Connection conexion = ServiceLocator.getInstance().tomarConexion();
            PreparedStatement prepStmt = conexion.prepareStatement(strSQL);
            prepStmt.setInt(1, Integer.valueOf(k_idservicio));
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
                k_idvehiculo = String.valueOf(rs.getInt(1));
                break;
            }
        } catch (SQLException e) {
            throw new CaException("ServicioDAO", "No pudo recuperar el servicio" + e.getMessage());
        }finally {
            ServiceLocator.getInstance().liberarConexion();
        }
        
        return k_idvehiculo;
    }
    
    //* Retorna el tipo de vehículo según la id del vehículo
    private String hallarTipo(String k_idvehiculo) throws CaException{
        String i_tipo = null;
        try {
            String strSQL = "SELECT i_tipo from vehiculo WHERE k_idvehiculo = " + k_idvehiculo + ";";
            Connection conexion = ServiceLocator.getInstance().tomarConexion();
            PreparedStatement prepStmt = conexion.prepareStatement(strSQL);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
                i_tipo = rs.getString(1);
                break;
            }
        } catch (SQLException e) {
            throw new CaException("ServicioDAO", "No pudo recuperar el servicio" + e.getMessage());
        }finally {
            ServiceLocator.getInstance().liberarConexion();
        }
        
        return i_tipo;
    }
    
    // Retorna el tipo según el área i parqueadero
    private String hallarTipoSalida(String k_idarea, String k_idparqueadero) throws CaException{
        String i_tipo = null;
        
        try {
            String strSQL = "SELECT i_tipo from area WHERE k_idarea=? AND k_idparqueadero=?;";
            Connection conexion = ServiceLocator.getInstance().tomarConexion();
            PreparedStatement prepStmt = conexion.prepareStatement(strSQL);
            prepStmt.setString(1, k_idarea);
            prepStmt.setInt(2, Integer.valueOf(k_idparqueadero));
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
                i_tipo = rs.getString(1);
                break;
            }
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
    
    // Retorna el area según el id servicio
    private String hallarAreaSalida(String k_idservicio) throws CaException{
        String k_idarea = null;
        
        try {
            String strSQL = "SELECT k_idarea from cupo c, cupo_servicio cs WHERE c.k_idcupo=cs.k_idcupo AND k_idservicio=?";
            Connection conexion = ServiceLocator.getInstance().tomarConexion();
            PreparedStatement prepStmt = conexion.prepareStatement(strSQL);
            prepStmt.setInt(1, Integer.valueOf(k_idservicio));
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
            String strSQL = "SELECT k_idparqueadero from area WHERE k_idarea =?;";
            Connection conexion = ServiceLocator.getInstance().tomarConexion();
            PreparedStatement prepStmt = conexion.prepareStatement(strSQL);
            prepStmt.setString(1, k_idarea);
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
        String k_idcupo = null;
        
        try {
            String strSQL = "SELECT k_idcupo from cupo_servicio WHERE k_idservicio =?";
            Connection conexion = ServiceLocator.getInstance().tomarConexion();
            PreparedStatement prepStmt = conexion.prepareStatement(strSQL);
            prepStmt.setInt(1, Integer.valueOf(k_idservicio));
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
    
    //* retorna un cupo disponible en un área
    private String hallarCupoDisponible(String k_idarea) throws CaException{
        String k_idcupo = null;
                
        try {
            String strSQL = "SELECT k_idcupo from cupo WHERE k_idarea =? AND i_estado = 'v'";
            Connection conexion = ServiceLocator.getInstance().tomarConexion();
            PreparedStatement prepStmt = conexion.prepareStatement(strSQL);
            prepStmt.setString(1, k_idarea);
            //prepStmt.executeUpdate();
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
                k_idcupo = rs.getString(1);
                break;
            }
        } catch (SQLException e) {
            throw new CaException("ServicioDAO", "No pudo recuperar el servicio" + e.getMessage());
        }finally {
            ServiceLocator.getInstance().liberarConexion();
        }
        
        return k_idcupo;
    }
    
    // halla q_cuposdisponibles del area que se va a actualizar
    private int hallarCuposArea(String k_idparqueadero, String k_idarea) throws CaException{
        int cupos = 0;
                
        try {
            String strSQL = "SELECT q_cuposdisponibles from area WHERE k_idparqueadero=? AND k_idarea=?";
            Connection conexion = ServiceLocator.getInstance().tomarConexion();
            PreparedStatement prepStmt = conexion.prepareStatement(strSQL);
            prepStmt.setInt(1, Integer.valueOf(k_idparqueadero));
            prepStmt.setString(2, k_idarea);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
                cupos = rs.getInt(1);
                break;
            }
        } catch (SQLException e) {
            throw new CaException("ServicioDAO", "No pudo recuperar el servicio" + e.getMessage());
        }finally {
            ServiceLocator.getInstance().liberarConexion();
        }
        
        return cupos;
    }
    
    //* Actualiza los datos de la tabla cupo en bd cuando una vehículo ingresa
    private void actualizarDatosCupo(String k_idarea, String k_idparqueadero, String k_idcupo) throws CaException{
        try {
            String strSQL = "UPDATE cupo SET i_estado = 'o' WHERE k_idarea =? AND k_idparqueadero =? AND k_idcupo =?;";
            Connection conexion = ServiceLocator.getInstance().tomarConexion();
            PreparedStatement prepStmt = conexion.prepareStatement(strSQL);
            prepStmt.setString(1, k_idarea);
            prepStmt.setInt(2, Integer.valueOf(k_idparqueadero));
            prepStmt.setInt(3, Integer.valueOf(k_idcupo));
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
    
    // Actualiza los datos del area a la que ingreso un vehículo
    private void actualizarDatosArea(String k_idarea, String k_idparqueadero, String i_tipo) throws CaException{
        int cupos = this.hallarCuposArea(k_idparqueadero, k_idarea);
        int q_cuposdisponibles = cupos - 1;
        try {
            String strSQL = "UPDATE area SET q_cuposdisponibles = ? WHERE k_idarea =? AND k_idparqueadero =? AND i_tipo =?;";
            Connection conexion = ServiceLocator.getInstance().tomarConexion();
            PreparedStatement prepStmt = conexion.prepareStatement(strSQL);
            prepStmt.setInt(1, q_cuposdisponibles);
            prepStmt.setString(2, k_idarea);
            prepStmt.setInt(3, Integer.valueOf(k_idparqueadero));
            prepStmt.setInt(4, Integer.valueOf(i_tipo));
            prepStmt.executeUpdate();
            prepStmt.close();
            ServiceLocator.getInstance().commit();
        } catch (SQLException e) {
            throw new CaException("ServicioDAO", "No pudo actualizar el servicio" + e.getMessage());
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
        this.actualizarDatosArea(k_idarea, k_idparqueadero, i_tipo);
    }
    
    // Metodos salida de vehículo (-)
    
    //actualiza los datos de las tablas cuando sale un vehículo
    private void actualizarDatosSalida(String k_idservicio, String k_idparqueadero) throws CaException{
        String k_idarea = this.hallarAreaSalida(k_idservicio);
        String i_tipo = this.hallarTipoSalida(k_idarea, k_idparqueadero);
        this.actualizaAreaSalida(k_idarea, k_idparqueadero, i_tipo);
        this.actualizaCupoSalida(k_idservicio);
    }
    
    //Actualiza la tabla cupo cuando un vehículo sale
    private void actualizaCupoSalida(String k_idservicio) throws CaException{
        String k_idcupo = this.hallarCupo(k_idservicio);
        
        try {
            String strSQL = "UPDATE cupo SET i_estado = 'v' WHERE k_idcupo =?;";
            Connection conexion = ServiceLocator.getInstance().tomarConexion();
            PreparedStatement prepStmt = conexion.prepareStatement(strSQL);
            prepStmt.setInt(1, Integer.valueOf(k_idcupo));
            prepStmt.executeUpdate();
            prepStmt.close();
            ServiceLocator.getInstance().commit();
        } catch (SQLException e) {
            throw new CaException("ServicioDAO", "No pudo actualizar el servicio" + e.getMessage());
        } finally {
            ServiceLocator.getInstance().liberarConexion();
        }
    }
    
    // Actualiza los datos de la tabla area cuando sale un vehículo
    private void actualizaAreaSalida(String k_idarea, String k_idparqueadero, String i_tipo) throws CaException{
        int cupos = this.hallarCuposArea(k_idparqueadero, k_idarea);
        int q_cuposdisponibles = cupos + 1;
        try {
            String strSQL = "UPDATE area SET q_cuposdisponibles = ? WHERE k_idarea =? AND k_idparqueadero =? AND i_tipo =?;";
            Connection conexion = ServiceLocator.getInstance().tomarConexion();
            PreparedStatement prepStmt = conexion.prepareStatement(strSQL);
            prepStmt.setInt(1, q_cuposdisponibles);
            prepStmt.setString(2, k_idarea);
            prepStmt.setInt(3, Integer.valueOf(k_idparqueadero));
            prepStmt.setInt(4, Integer.valueOf(i_tipo));
            prepStmt.executeUpdate();
            prepStmt.close();
            ServiceLocator.getInstance().commit();
        } catch (SQLException e) {
            throw new CaException("ServicioDAO", "No pudo actualizar el servicio" + e.getMessage());
        } finally {
            ServiceLocator.getInstance().liberarConexion();
        }
    }
    public int buscarServicioPorFecha() throws CaException {
        int servicios = 0;

        try {
            String strSQL = "select count( k_idservicio) from servicio s WHERE f_fycentrada BETWEEN ? and ? GROUP BY f_fycentrada";
            Connection conexion = ServiceLocator.getInstance().tomarConexion();
            PreparedStatement prepStmt = conexion.prepareStatement(strSQL);
            prepStmt.setDate(1, Date.valueOf(servicio.getF_fycentrada()));
            prepStmt.setDate(2, Date.valueOf(servicio.getF_fycsalida()));
            ResultSet rs = prepStmt.executeQuery();
            while(rs.next()){
                servicios = servicios + rs.getInt(1);
            }    
        } catch (SQLException e) {
            throw new CaException("ServicioDAO", "No pudo recuperar el servicio" + e.getMessage());
        } finally {
            ServiceLocator.getInstance().liberarConexion();
        }
        return servicios;
    }
    public ArrayList<Servicio> buscarServiciPorVehiculo() throws CaException {
        int servicios = 0;
        try {
            String strSQL = "select v.k_idvehiculo, f_fycentrada, f_fycsalida from servicio s, vehiculo v WHERE s.k_idvehiculo= v.k_idvehiculo AND v.k_idvehiculo=?";
            Connection conexion = ServiceLocator.getInstance().tomarConexion();
            PreparedStatement prepStmt = conexion.prepareStatement(strSQL);
            prepStmt.setInt(1, Integer.valueOf(servicio.getK_idservicio()));
            ResultSet rs = prepStmt.executeQuery();
            
            
            while (rs.next()) {
                Servicio servicio1 = new Servicio();
                servicio1.setK_idservicio(rs.getInt(1));
                servicio1.setF_fycentrada(rs.getString(2));
                servicio1.setF_fycsalida(rs.getString(3));
                servicios2.add(servicio1);
            }

        } catch (SQLException e) {
            throw new CaException("ServicioDAO", "No pudo recuperar el servicio" + e.getMessage());
        } finally {
            ServiceLocator.getInstance().liberarConexion();
        }
        return servicios2;
    }
}
