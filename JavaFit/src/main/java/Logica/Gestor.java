import java.util.ArrayList;
public class Gestor {
    private static ArrayList<Actividad_Deportiva> actividades = new ArrayList<>();
    private static ArrayList<Socio> socios = new ArrayList<>();
    private static ArrayList<Reserva> reservas = new ArrayList<>();
 
    public static ArrayList<Actividad_Deportiva> getActividades() {
        return actividades;
    }

    public static void setActividades(ArrayList<Actividad_Deportiva> nuevasActividades) {
        actividades = nuevasActividades;
    }
    
    public static void agregar(Actividad_Deportiva actividad) {
        actividades.add(actividad);
    }
    
    public static void eliminar(Actividad_Deportiva actividad) {
        actividades.remove(actividad);
    }
    
    public static ArrayList<Socio> getSocios() {
        return socios;
    }

    public static ArrayList<Reserva> getReservas() {
        return reservas;
    }
    
}

