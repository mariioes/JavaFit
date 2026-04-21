package Logica;

import Logica.Reserva;
import Logica.Actividad_Deportiva;
import Logica.Gestor;
import java.util.ArrayList;
public class Administrador {
    private String correo;
    private String clave;

    public Administrador(String correo, String clave) {
        this.correo = correo;
        this.clave = clave;
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

    public String getCorreo() {
        return correo;
    }

    public String getClave() {
        return clave;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
