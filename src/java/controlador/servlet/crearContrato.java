/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.servlet;

import controlador.util.CaException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.logica.Cliente;
import modelo.logica.Contrato;
import modelo.logica.GestorParqueadero;
import modelo.logica.Vehiculo;

/**
 *
 * @author diego
 */
public class crearContrato extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        GestorParqueadero gestorC = new GestorParqueadero();
        GestorParqueadero gestorV = new GestorParqueadero();
        GestorParqueadero gestorCon = new GestorParqueadero();

        Cliente cl = gestorC.getCliente();
        Vehiculo v = gestorV.getVehiculo();
        Contrato con = gestorCon.getContrato();
        
        int k_idParqueadero = Integer.valueOf(request.getParameter("k_idParqueadero"));
        
        int k_cedulaSearch = Integer.valueOf(request.getParameter("k_cedulaSearch"));
        int k_cedula;
        
        int k_idVehiculoSearch = Integer.valueOf(request.getParameter("k_idVehiculo"));
        int k_idVehiculo;
        

        if (k_cedulaSearch == 0) {

            k_cedula = Integer.valueOf(request.getParameter("k_cedula"));
            String n_primerNombre = request.getParameter("n_primerNombre");
            String n_primerApellido = request.getParameter("n_primerApellido");
            String n_segundoApellido = request.getParameter("n_segundoApellido");
            String n_direccion = request.getParameter("n_direccion");
            int q_telefono = Integer.valueOf(request.getParameter("q_telefono"));

            cl.setK_cedula(k_cedula);
            cl.setN_primerNombre(n_primerNombre);
            cl.setN_primerApellido(n_primerApellido);
            cl.setN_segundoApellido(n_segundoApellido);
            cl.setN_direccion(n_direccion);
            cl.setQ_telefono(q_telefono);

            try {
                gestorC.incluirCliente();
            } catch (CaException ex) {
                Logger.getLogger(crearContrato.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            k_cedula = k_cedulaSearch;
        }
        
        if (k_idVehiculoSearch == 0) {

            k_idVehiculo = Integer.valueOf(request.getParameter("k_idVehiculo"));
            String n_marca = request.getParameter("n_marca");
            String n_color = request.getParameter("n_color");
            String i_tipo = request.getParameter("i_tipo");

            v.setK_idVehiculo(k_idVehiculo);
            v.setN_marca(n_marca);
            v.setN_color(n_color);
            v.setI_tipo(i_tipo);

            try {
                gestorV.incluirVehiculo();
            } catch (CaException ex) {
                Logger.getLogger(crearContrato.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            k_idVehiculo = k_idVehiculoSearch;
        }
        int k_idContrato = Integer.valueOf(request.getParameter("k_idContrato"));
        int q_costoTotal = Integer.valueOf(request.getParameter("q_costoTotal"));
        String f_fechaDeInicio = request.getParameter("f_fechaDeInicio");
        String f_fechaDeFinalizacion = request.getParameter("f_fechaDeFinalizacion");
        String i_estado = "v";
        
        con.setK_idContrato(k_idContrato);
        con.setQ_costoTotal(q_costoTotal);
        con.setF_fechaDeInicio(f_fechaDeInicio);
        con.setF_fechaDeFinalizacion(f_fechaDeFinalizacion);
        con.setI_estado(i_estado);
        
        con.setK_idParqueadero(k_idContrato);
        con.setK_idVehiculo(k_idVehiculo);
        con.setK_cedula(k_idParqueadero);
        
        try {
                gestorCon.incluirContrato();
            } catch (CaException ex) {
                Logger.getLogger(crearContrato.class.getName()).log(Level.SEVERE, null, ex);
            } 
        
        

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
