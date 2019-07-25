/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funcionalidad.estacion;

import funcionalidad.commons.EntidadEstacion;
import java.rmi.RemoteException;

/**
 *
 * @author user
 */
public class pruebaEstacion {
        
    static EntidadEstacion estacion1 = new EntidadEstacion("localhost", 1090);
    
    public static void main(String[] args) throws RemoteException {
        Estacion estacion = new Estacion(estacion1, "localhost", 1092);
        estacion.iniciar();
    }
}
