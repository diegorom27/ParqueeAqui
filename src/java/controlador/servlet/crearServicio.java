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
import modelo.logica.GestorParqueadero;
import modelo.logica.Servicio;
import modelo.logica.Vehiculo;

/**
 *
 * @author diego
 */
public class crearServicio extends HttpServlet {

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
        
        GestorParqueadero gestorV = new GestorParqueadero();
        GestorParqueadero gestorS = new GestorParqueadero();
        
        Vehiculo vehiculo = gestorV.getVehiculo();
        Servicio servicio = gestorS.getServicio();
        
        int k_idParqueadero = Integer.valueOf(request.getParameter("k_idParqueadero"));
        
        String k_idVehiculoSearch = (request.getParameter("k_idVehiculoSearch"));
        int k_idVehiculo;
        
        if (k_idVehiculoSearch.equals("")) {

            k_idVehiculo = Integer.valueOf(request.getParameter("k_idVehiculo"));
            String n_marca = request.getParameter("n_marca");
            String n_color = request.getParameter("n_color");
            String i_tipo = request.getParameter("i_tipo");

            vehiculo.setK_idVehiculo(k_idVehiculo);
            vehiculo.setN_marca(n_marca);
            vehiculo.setN_color(n_color);
            vehiculo.setI_tipo(i_tipo);

            try {
                gestorV.incluirVehiculo();
            } catch (CaException ex) {
                Logger.getLogger(crearContrato.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            k_idVehiculo = Integer.valueOf(k_idVehiculoSearch);
        }
        
        boolean cupos = false;
        try {
            cupos = gestorS.verificarCupos(String.valueOf(k_idVehiculo), String.valueOf(k_idParqueadero));
        } catch (CaException ex) {
            Logger.getLogger(crearServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        servicio.setF_fycEntrada(request.getParameter("f_fycentrada"));
        servicio.setK_idVehiculo(Integer.valueOf(request.getParameter("k_idVehiculo")));
        
        if(cupos == false){
            System.out.println ("1");
        }
        else{
            try {
                gestorS.incluirServicio();    
            } catch (CaException ex) {
                Logger.getLogger(crearParqueadero.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println ("2");
        }
        
        response.sendRedirect("index.html");
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
