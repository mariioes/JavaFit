package Logica;


import Logica.Horario;
import Logica.Sala;
import Logica.Actividad_Deportiva;
import java.io.Serializable;

/**
 * Clase que hereda de Actividad_Deportiva que representa una Actividad Especial.
 * Implementa los atributos de la clase madre y los atributos de precio y descripción.
 * Incluye métodos específicos, getters y setters.
 */
public class Actividad_Especial extends Actividad_Deportiva implements Serializable {
    private double precio;
    private String descripcion;

    /**
     * Incluye los parámetros de la clase madre implementados mediante super().
     * Añade los parámetros propios de precio y descripción.
     * Fija el valor de aforo actual en 0 al instanciarse.
     */
    public Actividad_Especial(double precio, String descripcion, String titulo, Tipo_Actividad tipo, Sala sala, Horario horario, String monitor_asignado, int aforo_actual, String ruta) {
        super(titulo, tipo, sala, horario, monitor_asignado, aforo_actual, ruta);
        this.precio = precio;
        this.descripcion = descripcion;
        this.aforo_actual = 0;
    }
    
    /**
     * Calcula el precio para cada socio, aplicando un 10% de descuento si es socio VIP.
     * @param socio
     * @param precio
     * @return precio Precio con descuento si es socio VIP.
     */
    public double calcularPrecioParaSocio(Socio socio, double precio) {
        if (socio.esVip()) {
            return precio * 0.90;
        } else {
            return precio;
        }
    }

    /**
     * Devuelve el precio de la actividad especial.
     * @return precio.
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Devuelve la descripción de la actividad especial.
     * @return descripcion Descripcion específica de la actividad especial.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Devuelve el aforo actual de la actividad / sala.
     * @return Aforo actual de la actividad.
     */
    public int getAforo_actual() {
        return aforo_actual;
    }

    /**
     * Establece un nuevo precio para la actividad especial.
     * @param precio Nuevo precio de la actividad.
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Establece una nueva descripción para la actividad especial.
     * @param descripcion Nueva descripcion de la actividad.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Permite modificar el aforo actual.
     * @param aforo_actual Nuevo aforo de la actividad / sala.
     */
    public void setAforo_actual(int aforo_actual) {
        this.aforo_actual = aforo_actual;
    }
}