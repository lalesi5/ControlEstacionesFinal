
package funcionalidad.commons;

import java.io.Serializable;
import java.rmi.Remote;

public class EntidadEstacion implements Serializable{
    
    String direccion;
    int puerto;

    public EntidadEstacion(String direccion, int puerto) {
        this.direccion = direccion;
        this.puerto = puerto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }
    
    
}
