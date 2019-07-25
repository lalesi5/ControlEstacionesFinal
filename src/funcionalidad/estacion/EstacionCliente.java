/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funcionalidad.estacion;

import funcionalidad.commons.EntidadEstacion;
import funcionalidad.commons.Estado;
import funcionalidad.estacionCentral.IEstacionCentral;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 *
 * @author user
 */
public class EstacionCliente {
    
    EntidadEstacion estacion;
    IEstacionCentral estacionCentral;
    String conexion;

    public EstacionCliente(EntidadEstacion estacion,
            String direccionEC, int puertoEC) {
        this.estacion = estacion;
        conexion = "//" + direccionEC + ":" + Integer.toString(puertoEC) + "/EstacionCentral";
    }
    
    private void abrirConexion() throws NotBoundException, MalformedURLException, RemoteException {
        estacionCentral = (IEstacionCentral) Naming.lookup(conexion);
    }
    
    public void reportarEstado(Estado estado, EntidadEstacion estacion) throws RemoteException {
        try {
            abrirConexion();
            estacionCentral.recibirEstado(estado, estacion);
        } catch (Exception e) {
            System.out.println("No se ha podido conectar a la estación central");
        }
    }
    
}
