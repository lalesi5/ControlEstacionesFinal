/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funcionalidad.controlador;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

/**
 *
 * @author user
 */
public class ControladorServidor {
    public static void main(String[] args) throws RemoteException,NotBoundException
    {
        int puerto = 1091;
        try
        {
            Registry r = java.rmi.registry.LocateRegistry.createRegistry(puerto);
            r.rebind("Controlador", new Controlador());
            System.out.println("El servidor del controlador está corriendo");
            
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
