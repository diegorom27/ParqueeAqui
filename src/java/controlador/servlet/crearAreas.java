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
import modelo.logica.Area;
import modelo.logica.GestorParqueadero;

/**
 *
 * @author diego
 */
public class crearAreas extends HttpServlet {

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

        int numeroDeAreas = Integer.valueOf(request.getParameter("numeroDeAreas"));
        int i = 0;
        GestorParqueadero gestor = new GestorParqueadero();
        Area a = gestor.getArea();

        while (i < numeroDeAreas) {
            i++;
            int k_idParqueadero = Integer.valueOf(request.getParameter("k_idParqueadero" + i));
            String k_idArea = request.getParameter("k_idArea" + i);
            int q_cuposTotales = Integer.valueOf(request.getParameter("k_idArea" + i));
            int i_tipo = Integer.valueOf(request.getParameter("i_tipo"+i));
            a.setK_idParqueadero(k_idParqueadero);
            a.setK_idArea(k_idArea);
            a.setQ_cuposTotales(q_cuposTotales);
            a.setQ_cuposDisponibles(q_cuposTotales);
            a.setI_tipo(i_tipo);

            try {
                gestor.incluirArea();

            } catch (CaException ex) {
                Logger.getLogger(crearAreas.class.getName()).log(Level.SEVERE, null, ex);
            }
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
