/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funcionalidad.estacionCentral;

import funcionalidad.commons.EntidadEstacion;
import funcionalidad.commons.Estado;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author user
 */
public interface IEstacionCentral extends Remote{
    
    public void recibirEstado(Estado estado, EntidadEstacion estacion) throws RemoteException;
    
}
