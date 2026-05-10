package Logica;
import java.io.Serializable; //Importamos serializable para los ficheros de texto.

/**
 * Clase abstracta que representa un usuario del sistema JavaFit.
 * Contiene los atributos comunes a todos los tipos de usuario (correo y contraseña).
 * Es la clase base de la que heredan Socio y Administrador.
 */
public abstract class Usuario implements Serializable{ //Implementamos la clase Serializable para poder guardar datos.
    private String correo;
    private String contraseña;

    /**
     * Constructor de Usuario.
     * @param correo Dirección de correo electrónico del usuario, usada como identificador único.
     * @param contraseña Contraseña de acceso del usuario.
     */
    public Usuario(String correo, String contraseña) {
        this.correo = correo;
        this.contraseña = contraseña;
    }

    /**
     * Devuelve el correo electrónico del usuario.
     * @return Correo electrónico.
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Devuelve la contraseña del usuario.
     * @return Contraseña.
     */
    public String getContraseña() {
        return contraseña;
    }

    /**
     * Establece un nuevo correo electrónico para el usuario.
     * @param correo Nuevo correo electrónico.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Establece una nueva contraseña para el usuario.
     * @param contraseña Nueva contraseña.
     */
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
    
}
