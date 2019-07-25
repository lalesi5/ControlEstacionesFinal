/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funcionalidad.estacionCentral;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

/**
 *
 * @author user
 */
public class EstacionCentralServidor {
    public static void main(String[] args) throws RemoteException,NotBoundException
    {
        int puerto = 1092;
        try
        {
            Registry r = java.rmi.registry.LocateRegistry.createRegistry(puerto);
            r.rebind("EstacionCentral", new EstacionCentral());
            System.out.println("El servidor de la estación central está corriendo");
        }
        catch(Exception e)
        {
                    System.out.println(e);
        }
    }
}
