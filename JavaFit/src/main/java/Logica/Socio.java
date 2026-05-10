package Logica;
import java.io.Serializable;

/**
 * Clase que hereda de usuario y representa a un socio de JavaFit.
 * Añade los atributos nombre, teléfono, dirección, tarjeta de crédito y si es socio VIP.
 * Añade nuevos métodos getters y setters.
 */

public class Socio extends Usuario implements Serializable{
    private static final long serialVersionUID = 1L;
    private String nombre;
    private String telefono;
    private String direccion;
    private String tarjeta_credito;
    private boolean es_vip;

    /**
     * Constructor de Socio.
     * Añade los parámetros de la clase Usuario mediante super().
     * @param nombre Nombre del usuario, usado para facilitar la consulta de reservas.
     * @param telefono Número de teléfono del usuario.
     * @param direccion Dirección del usuario.
     * @param tarjeta_credito Número de tarjeta de crédito (16 dígitos) para realizar los cobros.
     * @param es_vip Indica si el usuario es socio VIP o no.
     */
    public Socio(String nombre, String telefono, String direccion, String tarjeta_credito, boolean es_vip, String correo, String contraseña) {
        super(correo, contraseña);
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.tarjeta_credito = tarjeta_credito;
        this.es_vip = es_vip;
    }

    /**
     * Devuelve el nombre del usuario.
     * @return nombre.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve el número de teléfono del usuario.
     * @return telefono.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Devuelve la dirección del usuario.
     * @return direccion.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Devuelve el número de tarjeta del usuario.
     * @return número de tarjeta.
     */
    public String getTarjeta() {
        return tarjeta_credito;
    }

    /**
     * Devuelve si el usuario es o no es VIP.
     * @return es VIP.
     */
    public boolean esVip() {
        return es_vip;
    }

    /**
     * Establece un nuevo nombre para el usuario.
     * @param nombre Nuevo nombre.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Establece un nuevo número de teléfono para el usuario.
     * @param telefono Nuevo número de teléfono.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Establece una nueva dirección para el usuario.
     * @param direccion Nueva dirección.
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Establece un nuevo número de tarjeta para el usuario.
     * @param tarjeta_credito Nueva tarjeta de crédito.
     */
    public void setTarjeta(String tarjeta_credito) {
        this.tarjeta_credito = tarjeta_credito;
    }

    /**
     * Establece si el usuario es o no es vip.
     * @param es_vip Nuevo estado de es_vip.
     */
    public void setEsVip(boolean es_vip) {
        this.es_vip = es_vip;
    }
    
}
