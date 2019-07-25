/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funcionalidad.controlador;

import funcionalidad.commons.EntidadEstacion;
import funcionalidad.commons.Estado;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author user
 */
public class Controlador extends UnicastRemoteObject implements IControlador{
    
    ControladorCliente cliente;
    EntidadEstacion[] parametrosEstaciones;
    
    public Controlador() throws RemoteException {
        cliente = new ControladorCliente(parametrosEstaciones);
    }

    @Override
    public boolean iniciar(EntidadEstacion[] estaciones) throws RemoteException {
        cliente.setParametrosEstaciones(estaciones);
        System.out.println("Se ha accedido al metodo iniciar (Controlador)");
        try {
            cliente.iniciarTodas();
        } catch (NotBoundException | MalformedURLException ex) {
            System.out.println("No se han podido iniciar las estaciones: " + ex);
            return false;
        }
        return true;
    }

    @Override
    public boolean parar(EntidadEstacion[] estaciones) throws RemoteException {
        cliente.setParametrosEstaciones(estaciones);
        System.out.println("Se ha accedido al metodo parar (Controlador)");
        try {
            cliente.pararTodas();
        } catch (NotBoundException | MalformedURLException ex) {
            System.out.println("No se han podido parar las estaciones: " + ex);
            return false;
        }
        return true;
    }

    @Override
    public void recibirNotificaciones(Estado estado, EntidadEstacion estacion) throws RemoteException {
        System.out.println("Se ha accedido al metodo recibir notificaciones (Controlador)");
        try {
            if(estado.estado == Estado.Estados.Incorrecto) {
                cliente.parar(estacion);
            }else if (estado.estado == Estado.Estados.Fuego) {
                cliente.activarSA(estacion);
            }
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            System.out.println("No se ha podido establecer contacto con la estación: " + e);
        }
    }
    
}
