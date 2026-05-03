package Logica;
import javax.swing.ImageIcon;
import java.io.Serializable;
public class Actividad_Deportiva implements Serializable {
    private String titulo;
    private Tipo_Actividad tipo_Actividad;
    private Sala sala;
    private Horario horario;
    private String monitor_asignado;
    private ImageIcon imagen;
    int aforo_actual;
    
    
    public Actividad_Deportiva(String titulo, Tipo_Actividad tipo_Actividad, Sala sala, Horario horario, String monitor_asignado, int aforo_actual, String ruta) {
        this.titulo = titulo;
        this.tipo_Actividad = tipo_Actividad;
        this.sala = sala;
        this.horario = horario;
        this.monitor_asignado = monitor_asignado;
        this.aforo_actual = 0;
        java.net.URL url = getClass().getResource(ruta);
        if (url != null) {
            this.imagen = new ImageIcon(url);
        } else {
            System.out.println("DEBUG - Imagen no encontrada: " + ruta);
            this.imagen = null;
    }
    }

    public String getTitulo() {
        return titulo;
    }

    public Tipo_Actividad getTipo_Actividad() {
        return tipo_Actividad;
    }

    public Sala getSala() {
        return sala;
    }

    public Horario getHorario() {
        return horario;
    }

    public String getMonitor_asignado() {
        return monitor_asignado;
    }

    public ImageIcon getImagen() {
        return imagen;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setTipo_Actividad(Tipo_Actividad tipo_Actividad) {
        this.tipo_Actividad = tipo_Actividad;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public void setMonitor_asignado(String monitor_asignado) {
        this.monitor_asignado = monitor_asignado;
    }

    public void setImagen(String ruta) {
        this.imagen = new ImageIcon(ruta);
    } 
}
