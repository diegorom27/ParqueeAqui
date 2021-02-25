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

/**
 *
 * @author lenovo
 */
public class acabarServicio extends HttpServlet {

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
        
        GestorParqueadero gestorS = new GestorParqueadero();
        Servicio servicio = gestorS.getServicio();
        
        int k_idParqueadero = Integer.valueOf(request.getParameter("k_idParqueadero"));
        
        String k_idServicioSearch = (request.getParameter("k_idServicioSearch"));
        
        String f_fycsalida=request.getParameter("f_fycsalida");
        int q_valorapagar=0;
        int k_idservicio = Integer.valueOf(k_idServicioSearch);
        
        boolean contrato = false;
        
        try {
            contrato=gestorS.verificarContrato(String.valueOf(k_idservicio));
        } catch (CaException ex) {
            Logger.getLogger(acabarServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(contrato==false){
            q_valorapagar=2000;
        }
        else{
            q_valorapagar=0;
        }
        
        servicio.setF_fycsalida(f_fycsalida);
        servicio.setQ_valorapagar(q_valorapagar);
        servicio.setK_idservicio(k_idservicio);
        
        try {
            gestorS.salida(String.valueOf(k_idParqueadero));
        } catch (CaException ex) {
            Logger.getLogger(acabarServicio.class.getName()).log(Level.SEVERE, null, ex);
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
