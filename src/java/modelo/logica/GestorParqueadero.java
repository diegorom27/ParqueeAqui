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
import modelo.database.VehiculoDAO;

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
    
    public GestorParqueadero() {
        parqueaderoDAO = new ParqueaderoDAO();
        areaDAO = new AreaDAO();
        cupoDAO = new CupoDAO();
        clienteDAO = new ClienteDAO();
        vehiculoDAO = new VehiculoDAO();
        contratoDAO = new ContratoDAO();
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
    
    
}
