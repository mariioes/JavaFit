package Logica;
import java.io.Serializable;

/**
 * Clase que representa una sala de las instalaciones de JavaFit donde se realizan las actividades.
 * Contiene los atributos de nombre de sala y aforo máximo.
 * Implementa getters y setters para cada atributo.
 */
public class Sala implements Serializable {
    private String nombre;
    private int aforo_maximo;

    /**
     * Constructor de Sala.
     * @param nombre Nombre de la sala, usado como identificador único.
     * @param aforo_maximo Aforo máximo, para llevar un control de la gente apuntada.
     */
    public Sala(String nombre, int aforo_maximo) {
        this.nombre = nombre;
        this.aforo_maximo = aforo_maximo;
    }

    /**
     * Devuelve el nombre de la sala.
     * @return Nombre de la sala.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve el aforo máximo de sala.
     * @return aforo máximo.
     */
    public int getAforo_maximo() {
        return aforo_maximo;
    }

    /**
     * Establece un nuevo nombre para la sala.
     * @param nombre Nuevo nombre de la sala.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Establece un nuevo aforo máximo para la sala.
     * @param aforo_maximo Nuevo aforo máximo para la sala.
     */
    public void setAforo_maximo(int aforo_maximo) {
        this.aforo_maximo = aforo_maximo;
    }
}
