/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.logica;

import controlador.util.CaException;
import java.util.ArrayList;
import modelo.database.AreaDAO;
import modelo.database.ClienteDAO;
import modelo.database.ContratoDAO;
import modelo.database.CupoDAO;
import modelo.database.ParqueaderoDAO;
import modelo.database.Parqueadero_TarifaDAO;
import modelo.database.ServicioDAO;
import modelo.database.TarifaDAO;
import modelo.database.VehiculoDAO;
import modelo.logica.Tarifa;

/**
 *
 * @author diego
 */
public class GestorParqueadero {
    
    private ParqueaderoDAO parqueaderoDAO= new ParqueaderoDAO();
    private AreaDAO areaDAO= new AreaDAO();
    private CupoDAO cupoDAO= new CupoDAO();
    private ClienteDAO clienteDAO = new ClienteDAO();
    private VehiculoDAO vehiculoDAO = new VehiculoDAO();
    private ContratoDAO contratoDAO = new ContratoDAO();
    private TarifaDAO tarifaDAO = new TarifaDAO();
    private Parqueadero_TarifaDAO parqueadero_TarifaDAO = new Parqueadero_TarifaDAO();
    private ServicioDAO servicioDAO = new ServicioDAO();
            
    public GestorParqueadero() {
        parqueaderoDAO = new ParqueaderoDAO();
        areaDAO = new AreaDAO();
        cupoDAO = new CupoDAO();
        clienteDAO = new ClienteDAO();
        vehiculoDAO = new VehiculoDAO();
        contratoDAO = new ContratoDAO();
        tarifaDAO = new TarifaDAO();
        parqueadero_TarifaDAO = new Parqueadero_TarifaDAO();
        servicioDAO = new ServicioDAO();
    }
    //Incluir
    
    public void incluirParqueadero() throws CaException {
      parqueaderoDAO.incluirParqueadero();
    }
    public void incluirArea() throws CaException {
      areaDAO.incluirArea();
    }
    public void incluirCupo() throws CaException {
      cupoDAO.incluirCupo();
    }
    public void incluirCliente() throws CaException {
      clienteDAO.incluirCliente();
    }
    public void incluirVehiculo() throws CaException {
      vehiculoDAO.incluirVehiculo();
    }
    public void incluirContrato() throws CaException {
      contratoDAO.incluirContrato();
    }
    public void incluirParqueadero_Tarifa() throws CaException {
      parqueadero_TarifaDAO.incluirParqueadero_Tarifa();
    }
    
    public void incluirServicio() throws CaException {
        servicioDAO.incluirServicio();
    }

    //get
    
    public Parqueadero getParqueadero() {
        return parqueaderoDAO.getParqueadero();
    }
    public Area getArea() {
        return areaDAO.getArea();
    }
    public Cupo getCupo() {
        return cupoDAO.getCupo();
    }
    public Cliente getCliente() {
        return clienteDAO.getCliente();
    }
    public Vehiculo getVehiculo() {
        return vehiculoDAO.getVehiculo();
    }
    public Contrato getContrato() {
        return contratoDAO.getContrato();
    }
    public Tarifa getTarifaDAO() {
        return tarifaDAO.getTarifa();
    }
    public Tarifa getParqueadero_TarifaDAO1() {
        return parqueadero_TarifaDAO.getTarifa();
    }
    public Parqueadero getParqueadero_TarifaDAO2() {
        return parqueadero_TarifaDAO.getParqueadero();
    }
    public Servicio getServicio() {
        return servicioDAO.getServicio();
    }
    
    //cargar
    
    public ArrayList<Parqueadero> cargarParqueaderos() throws CaException{
        return parqueaderoDAO.cargarParqueaderos();
    }
    public ArrayList<Cliente> cargarClientes() throws CaException{
        return clienteDAO.cargarClientes();
    }
    public ArrayList<Vehiculo> cargarVehiculos() throws CaException{
        return vehiculoDAO.cargarVehiculos();
    }
    public ArrayList<Contrato> cargarContratos() throws CaException{
        return contratoDAO.cargarContratos();
    }
    public ArrayList<Servicio> cargarServicios() throws CaException{
        return servicioDAO.cargarServicios();
    }
    public ArrayList<Tarifa> cargarTarifas() throws CaException{
        return tarifaDAO.cargarTarifas();
    }
    
    //verificar datos tabla de rompimiento Parqueadero_tarifa 
    public boolean verificar() throws CaException{
        return parqueadero_TarifaDAO.verificar();
    }
    
    //verificar si existen cupos para el vehículo
    public boolean verificarCupos(String k_idvehiculo, String k_idparqueadero)throws CaException{
        return servicioDAO.verificarCupo(k_idvehiculo, k_idparqueadero);
    }
    
    //verificar si el vehículo tiene contrato
    public boolean verificarContrato(String k_idservicio)throws CaException{
        return servicioDAO.verificarContrato(k_idservicio);
    }
    
    //actualizar datos de servicio, cupo, area cuando un vehículo sale
    public void salida(String k_idparqueadero) throws CaException {
        servicioDAO.salida(k_idparqueadero);
    }
    
    public ArrayList<Servicio> buscarServiciPorVehiculo() throws CaException{
        return servicioDAO.buscarServiciPorVehiculo();
    }
    public int buscarServiciPorFecha() throws CaException{
        return servicioDAO.buscarServicioPorFecha();
    }
    
}
