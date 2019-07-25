/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funcionalidad.controlador;

import funcionalidad.commons.EntidadEstacion;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class PruebaControlador {
    static Controlador c;
    static EntidadEstacion[] estaciones;
    
    public static void main(String[] args) throws RemoteException {
        instanciar();
        iniciar();
    }
    
    public static void instanciar(){
        try {
            c  = new Controlador();
        } catch (RemoteException ex) {
            Logger.getLogger(PruebaControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        estaciones = new EntidadEstacion[1];
        estaciones[0] = new EntidadEstacion("localhost", 1090);
    }
    
    public static void iniciar (){
        try {
            c.iniciar(estaciones);
        } catch (RemoteException ex) {
            Logger.getLogger(PruebaControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
