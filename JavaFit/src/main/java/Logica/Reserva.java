package Logica;

import Logica.Actividad_Deportiva;
import Logica.Socio;
import java.time.LocalDate;

public class Reserva {
    private Socio socio;
    private Actividad_Deportiva actividad;
    private LocalDate fecha; // Ya veremos como hacerla
    private double precio_total;
    private double precio_pagado;

    public Reserva(Socio socio, Actividad_Deportiva actividad) {
        this.socio = socio;
        this.actividad = actividad;
        this.fecha = fecha;
        this.precio_total = precio_total;
        this.precio_pagado = precio_pagado;
    }

    public Socio getSocio() {
        return socio;
    }
    public Actividad_Deportiva getActividad() {
        return actividad;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public double getPrecio_total() {
        return precio_total;
    }
    public double getPrecio_pagado() {
        return precio_pagado;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    public void setActividad(Actividad_Deportiva actividad) {
        this.actividad = actividad;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setPrecio_total(double precio_total) {
        this.precio_total = precio_total;
    }

    public void setPrecio_pagado(double precio_pagado) {
        this.precio_pagado = precio_pagado;
    }
    
    
    public boolean esReservaValida(Reserva reserva) {
        return true;
    }
}
