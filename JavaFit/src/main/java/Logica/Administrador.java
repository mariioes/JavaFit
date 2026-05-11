package Logica;

import Logica.Reserva;
import Logica.Actividad_Deportiva;
import Logica.Gestor;
import java.util.ArrayList;
import java.io.Serializable;

/**
 * Clase que hereda de usuario representa un administrador del sistema JavaFit.
 * Contiene los atributos de la clase Usuario. 
 * Puede crear, eliminar o modificar actividades o reservas, teniendo su propio menu especial.
 */
public class Administrador extends Usuario implements Serializable {
    /**
     * Constructor de Administrador.
     * Añade los parámeros de la clase Usuario con super().
     */
    public Administrador(String correo, String contraseña) {
        super(correo, contraseña);
    }
    
    /**
     * Permite al administrador añadir/crear una nueva actividad y añadirla.
     * @param actividad Actividad a agregar.
     */
    public void crea_actividad(Actividad_Deportiva actividad) {
        Gestor.agregar(actividad);
    }    
    
    /**
     * Permite al administrador eliminar una actividad del sistema.
     * @param actividad Actividad a eliminar.
     */
    public void elimina_actividad(Actividad_Deportiva actividad) {
        Gestor.eliminar(actividad);
    }
    
    /**
     * Permite al administrador modificar los datos de una actividad del sistema.
     * @param actividades 
     */
    public static void set_actividades(ArrayList<Actividad_Deportiva> actividades) {
        Gestor.setActividades(actividades);
    }
    
    /**
     * Permite al administrador consultar las reservas actuales.
     * @return lista de reservas.
     */
    public static ArrayList<Reserva> get_reservas() {
        return Gestor.getReservas();
    }
}
