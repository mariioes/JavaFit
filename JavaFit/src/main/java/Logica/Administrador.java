package Logica;

import Logica.Reserva;
import Logica.Actividad_Deportiva;
import Logica.Gestor;
import java.util.ArrayList;
import java.io.Serializable;

public class Administrador extends Usuario implements Serializable {
    public Administrador(String correo, String contraseña) {
        super(correo, contraseña);
    }
    
    public void crea_actividad(Actividad_Deportiva actividad) {
        Gestor.agregar(actividad);
    }
    
    public void elimina_actividad(Actividad_Deportiva actividad) {
        Gestor.eliminar(actividad);
    }
    
    public static void set_actividades(ArrayList<Actividad_Deportiva> actividades) {
        Gestor.setActividades(actividades);
    }
    
    public static ArrayList<Reserva> get_reservas() {
        return Gestor.getReservas();
    }
}
