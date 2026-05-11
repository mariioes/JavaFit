package Logica;
import javax.swing.ImageIcon;
import java.io.Serializable;

/**
 * Clase que representa una actividad deportiva en el sistema de JavaFit.
 * Incluye los atributos de titulo de la actividad, tipo, sala donde se realiza, monitor que imparte la actividad, horario (de la clase Horario) e imagen de la actividad.
 * Contiene getters y setters para cada atributo.
 */
public class Actividad_Deportiva implements Serializable {
    private String titulo;
    private Tipo_Actividad tipo_Actividad;
    private Sala sala;
    private Horario horario;
    private String monitor_asignado;
    private ImageIcon imagen;
    int aforo_actual;
    
    /**
     * Constructor de Actividad Deportiva.
     * @param titulo Titulo/Nombre de la actividad deportiva.
     * @param tipo_Actividad Tipo de actividad (String de la lista enumerada de tipos).
     * @param sala Sala donde se realiza.
     * @param horario Horario en el que se imparte.
     * @param monitor_asignado Monitor asignado para impartir la actividad.
     * @param aforo_actual Aforo actual de la activida.
     * @param ruta Ruta de la imagen de la actividad.
     */
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

    /**
     * Devuelve el título de la actividad.
     * @return Título de la actividad.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Devuelve el tipo de actividad.
     * @return Tipo de actividad.
     */
    public Tipo_Actividad getTipo_Actividad() {
        return tipo_Actividad;
    }

    /**
     * Devuelve la sala donde se realiza.
     * @return Sala.
     */
    public Sala getSala() {
        return sala;
    }

    /**
     * Devuelve el horario de la actividad.
     * @return Horario de la actividad.
     */
    public Horario getHorario() {
        return horario;
    }

    /**
     * Devuelve el monitor asignado para la actividad.
     * @return Monitor asignado.
     */
    public String getMonitor_asignado() {
        return monitor_asignado;
    }

    /**
     * Devuelve la imagen de la actividad.
     * @return imagen de la actividad.
     */
    public ImageIcon getImagen() {
        return imagen;
    }

    /**
     * Establece un nuevo título para la actividad.
     * @param titulo Nuevo título.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Establece un nuevo tipo de actividad para la actividad.
     * @param tipo_Actividad nuevo Tipo de Actividad.
     */
    public void setTipo_Actividad(Tipo_Actividad tipo_Actividad) {
        this.tipo_Actividad = tipo_Actividad;
    }

    /**
     * Modifica la sala donde se realiza la actividad.
     * @param sala Nueva sala.
     */
    public void setSala(Sala sala) {
        this.sala = sala;
    }

    /**
     * Establece un nuevo horario para la actividad.
     * @param  horario Nueva actividad.
     */
    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    /**
     * Establece un nuevo monitor para la actividad.
     * @param monitor_asignado Nuevo monitor.
     */
    public void setMonitor_asignado(String monitor_asignado) {
        this.monitor_asignado = monitor_asignado;
    }

    /**
     * Establece una nueva imagen de la actividad.
     * @param ruta Nueva imagen.
     */
    public void setImagen(String ruta) {
        this.imagen = new ImageIcon(ruta);
    } 
}
