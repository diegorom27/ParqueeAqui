/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.logica;

import controlador.util.CaException;
import modelo.database.AreaDAO;
import modelo.database.ParqueaderoDAO;

/**
 *
 * @author diego
 */
public class GestorParqueadero {
    
    private ParqueaderoDAO parqueaderoDAO= new ParqueaderoDAO();
    private AreaDAO areaDAO= new AreaDAO();
    
    public GestorParqueadero() {
        parqueaderoDAO = new ParqueaderoDAO();
        areaDAO= new AreaDAO();
        
    }
    public void incluirParqueadero() throws CaException {
      parqueaderoDAO.incluirParqueadero();
    }
    public void incluirArea() throws CaException {
      areaDAO.incluirArea();
    }

    public Parqueadero getParqueadero() {
        return parqueaderoDAO.getParqueadero();
    }

    public Area getArea() {
        return areaDAO.getArea();
    }
    
}
