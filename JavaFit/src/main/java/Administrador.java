import java.util.ArrayList;
public class Administrador {
    private String correo;
    private String clave;

    public Administrador(String correo, String clave) {
        this.correo = correo;
        this.clave = clave;
    }
    
    public void crea_actividad(Actividad_Deportiva actividad) {
        GestorActividades.agregar(actividad);
    }
    
    public void elimina_actividad(Actividad_Deportiva actividad) {
        GestorActividades.eliminar(actividad);
    }
    
    public static void set_actividades(ArrayList<Actividad_Deportiva> actividades) {
        GestorActividades.setActividades(actividades);
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
