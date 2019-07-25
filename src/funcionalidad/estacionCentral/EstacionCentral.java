/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funcionalidad.estacionCentral;

import funcionalidad.commons.EntidadEstacion;
import funcionalidad.commons.Estado;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class EstacionCentral extends UnicastRemoteObject implements IEstacionCentral{
    
    EstacionCentralCliente cliente;
    boolean funcionamiento;
    EntidadEstacion[] estaciones;
    String direccionControlador;
    int puertoControlador;

    public EntidadEstacion[] getEstaciones() {
        return estaciones;
    }

    public void setEstaciones(EntidadEstacion[] estaciones) {
        this.estaciones = estaciones;
    }

    public EstacionCentral(EntidadEstacion[] estaciones, String direccionControlador, int puertoControlador) throws RemoteException {
        this.estaciones = estaciones;
        this.direccionControlador = direccionControlador;
        this.puertoControlador = puertoControlador;
    }

    public EstacionCentral() throws RemoteException {
    }

    @Override
    public void recibirEstado(Estado estado, EntidadEstacion estacion) throws RemoteException{
        System.out.println("Se ha accedido al notificar estado (Estacion Central)");
        cliente = new EstacionCentralCliente(estaciones, direccionControlador, puertoControlador);
        try {
            cliente.notificar(estado, estacion);
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            System.out.println("No se pudo notificar el estado: " + e);
        }
    }
    
    public String arracarSistema() throws NotBoundException, MalformedURLException, RemoteException, InterruptedException {
        String reporte = "";
        cliente = new EstacionCentralCliente(estaciones, direccionControlador, puertoControlador);
        reporte += cliente.iniciar();
        while (funcionamiento) {
            reporte += cliente.solicitarEstado();
            Thread.sleep(10000);
        }
        return reporte;
    }
    
    public String pararSistema() throws NotBoundException, MalformedURLException, RemoteException {
        cliente = new EstacionCentralCliente(estaciones, direccionControlador, puertoControlador);
        try {
            cliente.parar();
        } catch (Exception e) {
            return "No se ha podido detener el sistema";
        }
        return "Se ha detenido el sistema";
        
    }
    
}
