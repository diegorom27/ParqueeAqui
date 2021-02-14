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
import modelo.logica.Cupo;
import modelo.logica.GestorParqueadero;
import modelo.logica.Parqueadero;
import modelo.logica.Tarifa;

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
        int j = 0;
        GestorParqueadero gestor = new GestorParqueadero();
        GestorParqueadero gestorC = new GestorParqueadero();
        GestorParqueadero gestorT = new GestorParqueadero();
        Area a = gestor.getArea();
        Cupo c = gestorC.getCupo();
        
        Parqueadero p = gestorT.getParqueadero_TarifaDAO2();
        Tarifa t = gestorT.getParqueadero_TarifaDAO1();
        
        while (i < numeroDeAreas) {
            i++;
            int k_idParqueadero = Integer.valueOf(request.getParameter("k_idParqueadero" + i));
            String k_idArea = request.getParameter("k_idArea" + i);
            int q_cuposTotales = Integer.valueOf(request.getParameter("q_cuposTotales" + i));
            int i_tipo = Integer.valueOf(request.getParameter("i_tipo" + i));
            int v_nfs = Integer.valueOf(request.getParameter("v_nfs" + i));
            
            a.setK_idParqueadero(k_idParqueadero);
            a.setK_idArea(k_idArea);
            a.setQ_cuposTotales(q_cuposTotales);
            a.setQ_cuposDisponibles(q_cuposTotales);
            a.setI_tipo(i_tipo);
            
            t.setK_codigoTarifa(v_nfs*100 + i_tipo );
            p.setK_idParqueadero(k_idParqueadero);

            try {
                gestor.incluirArea();
                gestorT.incluirParqueadero_Tarifa();

            } catch (CaException ex) {
                Logger.getLogger(crearAreas.class.getName()).log(Level.SEVERE, null, ex);
            }
            while (j < q_cuposTotales) {
                j++;
                c.setI_estado("v");
                c.setK_idArea(k_idArea);
                c.setK_idParqueadero(k_idParqueadero);
                try {
                    gestorC.incluirCupo();

                } catch (CaException ex) {
                    Logger.getLogger(crearAreas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            j = 0;

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
