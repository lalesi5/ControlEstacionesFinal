/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funcionalidad.commons;

import java.io.Serializable;

/**
 *
 * @author user
 */
public class Estado implements Serializable{
    public enum Estados {
        Correcto,
        Incorrecto,
        Fuego
    }
    
    public Estados estado;
}
