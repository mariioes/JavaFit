package Interfaz;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Inicio extends JFrame {

    public Inicio() {
        super("JAVAFIT");
        this.setSize(500, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); // Centrar ventana

        PanelFondo fondo = new PanelFondo("/imagenes/imagen_inicio.jpg");
        fondo.setLayout(null);

        JButton botonRegistrar = new JButton("Registrarse");
        JButton botonLogin = new JButton("Iniciar Sesión");
        
        botonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 1. Instanciamos la nueva ventana
                VentanaLogin login = new VentanaLogin(Inicio.this); 
                // 2. Ocultamos la ventana principal actual
                setVisible(false); 
                // 3. Mostramos la nueva ventana
                login.setVisible(true);
            }
        });

        // Evento para el botón de Registrarse
        botonRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaRegistro registro = new VentanaRegistro(Inicio.this);
                setVisible(false);
                registro.setVisible(true);
            }
        });
        // Posicionar botones (x, y, ancho, alto) [cite: 578, 579]
        botonRegistrar.setBounds(150, 150, 200, 40);
        botonLogin.setBounds(150, 210, 200, 40);

        // Añadir componentes
        fondo.add(botonRegistrar);
        fondo.add(botonLogin);
        this.add(fondo);

        this.setVisible(true);
    }
 
class PanelFondo extends JPanel {
    private Image imagen;

    public PanelFondo(String rutaRecurso) {
        // El método getResource es la forma estándar en Swing para acceder a archivos internos 
        java.net.URL url = getClass().getResource(rutaRecurso);
        
        if (url != null) {
            this.imagen = new ImageIcon(url).getImage(); // Cargamos la imagen mediante ImageIcon 
        } else {
            // Si ves este mensaje en la consola de NetBeans, la ruta sigue estando mal
            System.err.println("La ruta no es válida: " + rutaRecurso);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagen != null) {
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
        } else {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    }

    public static void main(String[] args) {
        Inicio inicio = new Inicio();
    }
}