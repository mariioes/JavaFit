package Logica;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class HerramientasVisuales {
    
    public static void ponerIconoJavaFit(JFrame ventana) {
        try {
            java.net.URL url = ventana.getClass().getResource("/imagenes/LogoFavicon.png");
            if (url != null) {
                Image icono = new ImageIcon(url).getImage();
                ventana.setIconImage(icono);
            }
        } catch (Exception e) {
            System.err.println("No se pudo cargar el icono: " + e.getMessage());
        }
    }
}