
public class Socio {
    
    private String nombre;
    private String telefono;
    private String direccion;
    private Long tarjeta_credito;
    private boolean es_vip;

    public Socio(String nombre, String telefono, String direccion, Long tarjeta_credito, boolean es_vip, String correo, String clave) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.tarjeta_credito = tarjeta_credito;
        this.es_vip = es_vip;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public Long getTarjeta_credito() {
        return tarjeta_credito;
    }

    public boolean isEs_vip() {
        return es_vip;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTarjeta_credito(Long tarjeta_credito) {
        this.tarjeta_credito = tarjeta_credito;
    }

    public void setEs_vip(boolean es_vip) {
        this.es_vip = es_vip;
    }
}
