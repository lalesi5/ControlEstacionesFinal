/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funcionalidad.controlador;

import funcionalidad.commons.EntidadEstacion;
import funcionalidad.estacion.IEstacion;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 *
 * @author user
 */
public class ControladorCliente {
    
    EntidadEstacion[] parametrosEstaciones;

    public ControladorCliente(EntidadEstacion[] estaciones) {
        this.parametrosEstaciones = estaciones;
    }

    public EntidadEstacion[] getParametrosEstaciones() {
        return parametrosEstaciones;
    }

    public void setParametrosEstaciones(EntidadEstacion[] parametrosEstaciones) {
        this.parametrosEstaciones = parametrosEstaciones;
    }
    
    private IEstacion abrirConexion(EntidadEstacion parametroEstacion) throws NotBoundException, MalformedURLException, RemoteException {        
        return (IEstacion) Naming.lookup(definirConexion(parametroEstacion));
    }
    
    private String definirConexion(EntidadEstacion estacion) {
        return "//" + estacion.getDireccion() + ":" + Integer.toString(estacion.getPuerto()) + "/Estacion";
    }
    
    public void iniciarTodas() throws NotBoundException, MalformedURLException, RemoteException {
        for (EntidadEstacion parametroEstacion : parametrosEstaciones) {
            IEstacion estacion = abrirConexion(parametroEstacion);
            estacion.iniciar();
        }
    }
    
    public void pararTodas() throws NotBoundException, MalformedURLException, RemoteException {
        for (EntidadEstacion parametroEstacion : parametrosEstaciones) {
            IEstacion estacion = abrirConexion(parametroEstacion);
            estacion.parar();
        }
    }
    
    public void activarSA(EntidadEstacion parametroEstacion) throws NotBoundException, MalformedURLException, RemoteException {
        IEstacion estacion = abrirConexion(parametroEstacion);
        estacion.activarSA();
    }
    
    public void parar(EntidadEstacion parametroEstacion) throws NotBoundException, MalformedURLException, RemoteException {
        IEstacion estacion = abrirConexion(parametroEstacion);
        estacion.parar();
    }
}
