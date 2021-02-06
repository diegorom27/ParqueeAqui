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
import javax.servlet.http.HttpSession;
import modelo.database.ParqueaderoDAO;
import modelo.logica.GestorParqueadero;
import modelo.logica.Parqueadero;

/**
 *
 * @author diego
 */
public class crearParqueadero extends HttpServlet {

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
        
            HttpSession lol = request.getSession();
            
            GestorParqueadero gestor = new GestorParqueadero();
            Parqueadero p = gestor.getParqueadero();
            
            int k_idParqueadero = Integer.valueOf(request.getParameter("k_idParqueadero"));
            int q_areas = Integer.valueOf(request.getParameter("q_areas"));
            String n_direccion = request.getParameter("n_direccion");
            String n_localidad = request.getParameter("n_localidad");
            float v_nfs = Float.valueOf(request.getParameter("v_nfs"));
            p.setK_idParqueadero(k_idParqueadero);
            p.setV_nfs(v_nfs);
            p.setQ_areas(q_areas);
            p.setN_direccion(n_direccion);
            p.setN_localidad(n_localidad);

        try {
            gestor.incluirParqueadero();
            
        } catch (CaException ex) {
            Logger.getLogger(crearParqueadero.class.getName()).log(Level.SEVERE, null, ex);
        }
        Parqueadero a = new Parqueadero();
        a=p;
        lol.setAttribute("parqueadero", p);
        response.sendRedirect("crearAreas.jsp");
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
