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
import modelo.logica.Cliente;
import modelo.logica.Parqueadero;

/**
 *
 * @author diego
 */
public class ClienteDAO {
    protected Cliente cliente;
    protected ArrayList<Cliente> clientes;

    public ClienteDAO() {
        cliente = new Cliente();
        clientes = new ArrayList();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public void incluirCliente() throws CaException {
        try {
            String strSQL = "INSERT INTO cliente (k_cedula, n_primerNombre, n_primerApellido, n_segundoApellido, n_direccion, q_telefono) VALUES(?,?,?,?,?,?)";
            Connection conexion = ServiceLocator.getInstance().tomarConexion();
            PreparedStatement prepStmt = conexion.prepareStatement(strSQL);
            prepStmt.setInt(1, cliente.getK_cedula());
            prepStmt.setString(2, cliente.getN_primerNombre());
            prepStmt.setString(3, cliente.getN_primerApellido());
            prepStmt.setString(4, cliente.getN_segundoApellido());
            prepStmt.setString(5, cliente.getN_direccion());
            prepStmt.setInt(6, cliente.getQ_telefono());
            prepStmt.executeUpdate();
            prepStmt.close();
            ServiceLocator.getInstance().commit();
        } catch (SQLException e) {
            throw new CaException("ClienteDAO", "No pudo crear el cliente" + e.getMessage());
        } finally {
            ServiceLocator.getInstance().liberarConexion();
        }
    }

    public ArrayList<Cliente> cargarClientes() throws CaException {
        try {
            String strSQL = "SELECT k_cedula, n_primerNombre, n_primerApellido, n_segundoApellido, n_direccion, q_telefono FROM cliente";
            Connection conexion = ServiceLocator.getInstance().tomarConexion();
            PreparedStatement prepStmt = conexion.prepareStatement(strSQL);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
                Cliente cliente1 = new Cliente();
                cliente1.setK_cedula(rs.getInt(1));
                cliente1.setN_primerNombre(rs.getString(2));
                cliente1.setN_primerApellido(rs.getString(3));
                cliente1.setN_segundoApellido(rs.getString(4));
                cliente1.setN_direccion(rs.getString(5));
                cliente1.setQ_telefono(rs.getInt(6));

                clientes.add(cliente1);
            }
        } catch (SQLException e) {
            throw new CaException("ClienteDAO", "No pudo recuperar el cliente" + e.getMessage());
        }finally {
            ServiceLocator.getInstance().liberarConexion();
        }
        return clientes;
    }
}
