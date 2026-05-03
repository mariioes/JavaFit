package Logica;
import java.io.Serializable;


public class Sala implements Serializable {
    private String nombre;
    private int aforo_maximo;

    public Sala(String nombre, int aforo_maximo) {
        this.nombre = nombre;
        this.aforo_maximo = aforo_maximo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getAforo_maximo() {
        return aforo_maximo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAforo_maximo(int aforo_maximo) {
        this.aforo_maximo = aforo_maximo;
    }
}
