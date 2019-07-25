/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funcionalidad.estacion;

import funcionalidad.commons.EntidadEstacion;
import funcionalidad.commons.Estado;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

/**
 *
 * @author user
 */
public class Estacion extends UnicastRemoteObject implements IEstacion{
    
    EntidadEstacion parametros;
    Estado estado;
    EstacionCliente cliente;
    Random random;
    
    boolean funcionamiento;
    boolean fuego;

    public Estacion(EntidadEstacion parametros, String direccionEC,
            int puertoEC) throws RemoteException {
        this.parametros = parametros;
        estado = new Estado();
        estado.estado = Estado.Estados.Correcto;
        cliente = new EstacionCliente(parametros, direccionEC, puertoEC);
        random = new Random();
        fuego = false;
    }
    
    @Override
    public boolean iniciar() throws RemoteException {
        System.out.println("Se ha accedido al metodo iniciar (Estacion)");
        try {
            funcionar();
        } catch (InterruptedException ex) {
            System.out.println("No se ha podido iniciar la estación: " + ex);
            return false;
        }
        return true;
    }

    @Override
    public boolean parar() throws RemoteException {
        System.out.println("Se ha accedido al metodo parar (Estacion)");
        funcionamiento = false;
        return true;
    }

    @Override
    public boolean activarSA() throws RemoteException {
        System.out.println("Se ha accedido al metodo ActivarSA (Estacion)");
        fuego = false;
        try {
            funcionar();
        } catch (InterruptedException ex) {
            System.out.println("No se ha podido encender el sistema anti incendios: " + ex);
            return false;
        }
        return true;
    }

    @Override
    public Estado reportarEstado() throws RemoteException {
        System.out.println("Se ha accedido al metodo reportarEstado (Estacion)");
        return estado;
    }
    
    
    private void generarEstado() {
        int valorAleatorio = random.nextInt(3);
        if(valorAleatorio%3 == 0)
            estado.estado = Estado.Estados.Correcto;
        else if(valorAleatorio%3 == 1)
            estado.estado = Estado.Estados.Incorrecto;
        else{
            estado.estado = Estado.Estados.Fuego;
            fuego = true;
        }
    }
    
    private void funcionar() throws InterruptedException, RemoteException {
        System.out.println("Se ha accedido al metodo funcionar (Estacion)");
        funcionamiento = true;
        while(funcionamiento && !fuego) {
            Thread.sleep(random.nextInt(10));
            generarEstado();
            if(estado.estado == Estado.Estados.Fuego || estado.estado == Estado.Estados.Incorrecto) {
                cliente.reportarEstado(estado, parametros);
                break;
            }
        }
    }
    
}
