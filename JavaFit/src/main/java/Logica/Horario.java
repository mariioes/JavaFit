package Logica;
import java.io.Serializable;

/**
 * Clase usada para crear un horario para una actividad del sistema JavaFit.
 * Contiene los atributos dia, hora_inicio y hora_final pero como Strings.
 * Contiene getters y setters para cada atributo.
 */
public class Horario implements Serializable {
    private String dia;
    private String hora_inicio;
    private String hora_final;

    /**
     * Constructor de Horario.
     * @param dia Dia de la semana en el que se realizará la actividad.
     * @param hora_inicio Hora a la que empezará la actividad.
     * @param hora_final Hora a la que finalizará la actividad.
     */
    public Horario(String dia, String hora_inicio, String hora_final) {
        this.dia = dia;
        this.hora_inicio = hora_inicio;
        this.hora_final = hora_final;
    }

    /**
     * Devuelve el dia de la actividad.
     * @return dia.
     */
    public String getDia() {
        return dia;
    }

    /**
     * Devuelve la hora de inicio de la actividad
     * @return hora de inicio.
     */
    public String getHora_inicio() {
        return hora_inicio;
    }

    /**
     * Devuelve la hora final de la actividad.
     * @return hora final.
     */
    public String getHora_final() {
        return hora_final;
    }

    /**
     * Establece un nuevo día para la actividad.
     * @param dia Nuevo dia.
     */
    public void setDia(String dia) {
        this.dia = dia;
    }

    /**
     * Establece una nueva hora de inicio.
     * @param hora_inicio Nueva hora de inicio.
     */
    public void setHora_inicio(String hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    /**
     * Establece una nueva hora de finalización.
     * @param hora_final Nueva hora final.
     */
    public void setHora_final(String hora_final) {
        this.hora_final = hora_final;
    }
    
}
