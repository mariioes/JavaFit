package Logica;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Clase que representa una reserva dentro del sistema JavaFit.
 * Contiene los atributos socio (instancia de Socio), actividad (instancia de Actividad_Deportiva), precio_total y precio_pagado.
 * Contiene getters y setters para cada atributo.
 */
public class Reserva implements Serializable {
    private Socio socio;
    private Actividad_Deportiva actividad;
    private double precio_total;
    private double precio_pagado;
    private LocalDate fechaReserva;

    /**
     * Constructor de Reserva.
     * @param socio Instancia de Socio con todos sus atributos.
     * @param actividad Instancia de Actividad_Deportiva con todos sus atributos.
     */
    public Reserva(Socio socio, Actividad_Deportiva actividad, LocalDate fechaReserva) {    
        this.socio = socio;
        this.actividad = actividad;
        this.fechaReserva = fechaReserva;
    }

    /**
     * Devuelve el objeto socio.
     * @return socio.
     */
    public Socio getSocio() {
        return socio;
    }
    
    /**
     * Devuelve el objeto actividad.
     * @return actividad.
     */
    public Actividad_Deportiva getActividad() {
        return actividad;
    }

    /**
     * Devuelve el precio total de la reserva.
     * @return Precio total.
     */
    public double getPrecio_total() {
        return precio_total;
    }
    
        /**
     * Devuelve el correo electrónico del usuario.
     * @return Correo electrónico.
     */
    public double getPrecio_pagado() {
        return precio_pagado;
    }

    /**
     * Establece un nuevo socio para la reserva.
     * @param socio Nuevo socio.
     */
    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }
    

    /**
     * Establece una nueva actividad para la reserva.
     * @param actividad Nueva actividad.
     */
    public void setActividad(Actividad_Deportiva actividad) {
        this.actividad = actividad;
    }

    /**
     * Establece un nuevo precio total para la reserva.
     * @param precio_total Nuevo precio total.
     */
    public void setPrecio_total(double precio_total) {
        this.precio_total = precio_total;
    }

    /**
     * Establece un nuevo precio pagado del precio total.
     * @param precio_pagado Nuevo precio pagado.
     */
    public void setPrecio_pagado(double precio_pagado) {
        this.precio_pagado = precio_pagado;
    }
    
    /**
     * Establece si una reserva es válida.
     * @param reserva.
     */
    public boolean esReservaValida(Reserva reserva) {
        return true;
    }

    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }
}
