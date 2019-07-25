/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funcionalidad.estacionCentral;

import funcionalidad.commons.EntidadEstacion;
import funcionalidad.commons.Estado;
import funcionalidad.controlador.IControlador;
import funcionalidad.estacion.IEstacion;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author user
 */
public class EstacionCentralCliente {
    
    EntidadEstacion[] parametrosEstaciones;
    IControlador controlador;
    IEstacion estacion;
    String direccionControlador;
    int puertoControlador;

    public EstacionCentralCliente(EntidadEstacion[] parametrosEstaciones, String direccionControlador, int puertoControlador) {
        this.parametrosEstaciones = parametrosEstaciones;
        this.direccionControlador = direccionControlador;
        this.puertoControlador = puertoControlador;
    }

    public EntidadEstacion[] getParametrosEstaciones() {
        return parametrosEstaciones;
    }

    public void setParametrosEstaciones(EntidadEstacion[] parametrosEstaciones) {
        this.parametrosEstaciones = parametrosEstaciones;
    }
    
    public String iniciar() throws NotBoundException, MalformedURLException, RemoteException {
        System.out.println("Se ha accedido al método iniciar");
        controlador = (IControlador) Naming.lookup(generarConexion(direccionControlador, puertoControlador));
        try {
            controlador.iniciar(parametrosEstaciones);
        } catch (Exception e) {
            System.out.println("No se pudo iniciar el controlador");
            return "No se pudo iniciar el controlador";
        }
        return "Se ha iniciado el sistema";
    }
    
    public String parar() throws NotBoundException, MalformedURLException, RemoteException {
        controlador = (IControlador) Naming.lookup(generarConexion(direccionControlador, puertoControlador));
        try {
            controlador.parar(parametrosEstaciones);
        } catch (Exception e) {
            System.out.println("No se pudo parar el controlador");
            return "No se pudo parar el controlador";
        }
        return "Se ha parado el sistema";
    }
    
    public String notificar(Estado estado, EntidadEstacion estacion) throws NotBoundException, MalformedURLException, RemoteException {
        controlador = (IControlador) Naming.lookup(generarConexion(direccionControlador, puertoControlador));
        try {
            controlador.recibirNotificaciones(estado, estacion);
        } catch (Exception e) {
            System.out.println("No se pudo notificar al controlador");
            return "No se pudo notificar al controlador";
        }
        return "Se notificó que la estación con dirección: " + estacion.getDireccion() +
                "tiene estado: " + estado.estado;
    }
    
    public String solicitarEstado() throws NotBoundException, MalformedURLException, RemoteException {
        String reporte = "";
        for (EntidadEstacion parametroEstacion : parametrosEstaciones) {
            estacion = (IEstacion) Naming.lookup(generarConexion(parametroEstacion));
            try {
                Estado estado = estacion.reportarEstado();
                reporte += "\nLa estacion: " + parametroEstacion.getDireccion() + 
                        "tiene estado: " + estado.estado;
            } catch (Exception e) {
                reporte += "\nLa estacion: " + parametroEstacion.getDireccion() +
                        "no puedo reportar su estado";
            }
        }
        return reporte;
    }
    
    
    
    private String generarConexion(String direccion, int puerto) {
        return "//" + direccion + ":" + puerto + "/Controlador";
    }
    
    private String generarConexion(EntidadEstacion estacion) {
        return "//" + estacion.getDireccion() + ":" + Integer.toString(estacion.getPuerto()) + "/Estacion";
    }
}
