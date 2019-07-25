/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funcionalidad.controlador;

import funcionalidad.commons.EntidadEstacion;
import funcionalidad.commons.Estado;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author user
 */
public interface IControlador extends Remote{
    public boolean iniciar(EntidadEstacion[] estaciones) throws RemoteException;
    public boolean parar(EntidadEstacion[] estacions) throws RemoteException;
    public void recibirNotificaciones(Estado estado, EntidadEstacion estacion) throws RemoteException;
}
