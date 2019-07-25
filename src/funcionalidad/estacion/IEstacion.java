
package funcionalidad.estacion;

import funcionalidad.commons.Estado;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IEstacion extends Remote{
    public boolean iniciar() throws RemoteException;
    public boolean parar() throws RemoteException;
    public boolean activarSA() throws RemoteException;
    public Estado reportarEstado() throws RemoteException;
}
