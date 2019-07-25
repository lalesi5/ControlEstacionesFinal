/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funcionalidad.estacion;

import funcionalidad.commons.EntidadEstacion;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

/**
 *
 * @author user
 */
public class EstacionServidor {
    
    public static void main(String[] args) throws RemoteException,NotBoundException
    {
        String direccion = "localhost";
        String direccionEC = "localhost";
        int puerto = 1090;
        int puertoEC = 1092;
        try
        {
            Registry r = java.rmi.registry.LocateRegistry.createRegistry(puerto);
            r.rebind("Estacion", new Estacion(new EntidadEstacion(direccion, puerto),direccionEC,puertoEC));
            System.out.println("El servidor de la estación está corriendo");
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
}
