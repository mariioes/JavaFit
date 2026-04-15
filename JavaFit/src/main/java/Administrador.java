
public class Administrador {
    private String correo;
    private String clave;

    public Administrador(String correo, String clave) {
        this.correo = correo;
        this.clave = clave;
    }
    
    public void crear_actividad(Actividad_Deportiva actividad) {
        
    }

    public String getCorreo() {
        return correo;
    }

    public String getClave() {
        return clave;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
