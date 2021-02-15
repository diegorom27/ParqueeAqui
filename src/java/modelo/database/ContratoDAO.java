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
import modelo.logica.Contrato;
import modelo.logica.Parqueadero;

/**
 *
 * @author diego
 */
public class ContratoDAO {
    
    Contrato contrato;
    ArrayList<Contrato> contratos;

    public ContratoDAO() {
        contrato = new Contrato();
        contratos= new ArrayList();
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }
    public void incluirContrato() throws CaException {
        try {
            String strSQL = "INSERT INTO Contrato (k_idContrato, q_costoTotal, f_fechaDeInicio, f_fechaDeFinalizacion, i_estado, k_idParqueadero, k_cedula, k_idVehiculo) VALUES(?,?,?,?,?,?,?,?)";
            Connection conexion = ServiceLocator.getInstance().tomarConexion();
            PreparedStatement prepStmt = conexion.prepareStatement(strSQL);
            prepStmt.setInt(1, contrato.getK_idContrato());
            prepStmt.setInt(2, contrato.getQ_costoTotal());
            prepStmt.setString(3, contrato.getF_fechaDeInicio());
            prepStmt.setString(4, contrato.getF_fechaDeFinalizacion());
            prepStmt.setString(5, contrato.getI_estado());
            prepStmt.setInt(6, contrato.getK_idParqueadero());
            prepStmt.setInt(7, contrato.getK_cedula());
            prepStmt.setInt(8, contrato.getK_idVehiculo());   
            prepStmt.executeUpdate();
            prepStmt.close();
            ServiceLocator.getInstance().commit();
        } catch (SQLException e) {
            throw new CaException("ContratoDAO", "No pudo crear el contrato" + e.getMessage());
        } finally {
            ServiceLocator.getInstance().liberarConexion();
        }
    }

    public ArrayList<Contrato> cargarContratos() throws CaException {
        try {
            String strSQL = "SELECT k_idContrato, q_costoTotal, f_fechaDeInicio, f_fechaDeFinalizacion, i_estado, k_idParqueadero, k_cedula, k_idVehiculo FROM contrato";
            Connection conexion = ServiceLocator.getInstance().tomarConexion();
            PreparedStatement prepStmt = conexion.prepareStatement(strSQL);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
                Contrato contrato1 = new Contrato();
                contrato1.setK_idContrato(rs.getInt(1));
                contrato1.setQ_costoTotal(rs.getInt(2));
                contrato1.setF_fechaDeInicio(rs.getString(3));
                contrato1.setF_fechaDeFinalizacion(rs.getString(4));
                contrato1.setI_estado(rs.getString(5));
                contrato1.setK_idParqueadero(rs.getInt(6));
                contrato1.setK_cedula(rs.getInt(7));
                contrato1.setK_idVehiculo(rs.getInt(8));
                contratos.add(contrato1);
            }
        } catch (SQLException e) {
            throw new CaException("ContratoDAO", "No pudo recuperar el contrato" + e.getMessage());
        }finally {
            ServiceLocator.getInstance().liberarConexion();
        }
        return contratos;
    }
    
}
