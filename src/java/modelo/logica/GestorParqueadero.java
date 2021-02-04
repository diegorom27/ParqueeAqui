/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.logica;

import controlador.util.CaException;
import modelo.database.ParqueaderoDAO;

/**
 *
 * @author diego
 */
public class GestorParqueadero {
    
    private ParqueaderoDAO parqueaderoDAO= new ParqueaderoDAO();
    
    public GestorParqueadero() {
        parqueaderoDAO = new ParqueaderoDAO();
    }
    public void incluirParqueadero() throws CaException {
      parqueaderoDAO.incluirParqueadero();
    }


    public Parqueadero getParqueadero() {
        return parqueaderoDAO.getParqueadero();
    }
}
