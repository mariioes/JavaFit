package Logica;


import Logica.Horario;
import Logica.Sala;
import Logica.Actividad_Deportiva;
import java.io.Serializable;


public class Actividad_Especial extends Actividad_Deportiva implements Serializable {
    private double precio;
    private String descripcion;

    public Actividad_Especial(double precio, String descripcion, String titulo, Tipo_Actividad tipo, Sala sala, Horario horario, String monitor_asignado, int aforo_actual, String ruta) {
        super(titulo, tipo, sala, horario, monitor_asignado, aforo_actual, ruta);
        this.precio = precio;
        this.descripcion = descripcion;
        this.aforo_actual = 0;
    }

    public double getPrecio() {
        return precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getAforo_actual() {
        return aforo_actual;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setAforo_actual(int aforo_actual) {
        this.aforo_actual = aforo_actual;
    }
}