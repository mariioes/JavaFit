package Interfaz;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaLogin extends JFrame {
    
    // Guardamos la referencia de la ventana principal para poder volver a ella
    private JFrame inicio;

    // Modificamos el constructor para recibir la ventana principal
    public VentanaLogin(JFrame principal) {
        this.inicio = principal;
        
        // Configuración básica de esta nueva ventana
        setTitle("Iniciar Sesión");
        setSize(300, 250);
        setLocationRelativeTo(null); // Centrar en pantalla
        setLayout(null); // Layout nulo para posicionar libremente

        // --- IMPORTANTE: Comportamiento al cerrar ---
        // Ponemos DISPOSE_ON_CLOSE para que solo se cierre esta ventana y no toda la app
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Evento: ¿Qué pasa si el usuario le da a la 'X' de la ventana para cerrarla?
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                // Volvemos a mostrar la ventana principal [cite: 958]
                inicio.setVisible(true);
            }
        });

        // --- Crear componentes del Login ---
        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setBounds(30, 30, 80, 25);
        
        JTextField txtUsuario = new JTextField();
        txtUsuario.setBounds(100, 30, 150, 25);
        
        JLabel lblPass = new JLabel("Contraseña:");
        lblPass.setBounds(30, 70, 80, 25);
        
        // Usamos JPasswordField para que salgan asteriscos en la contraseña
        JPasswordField txtPass = new JPasswordField();
        txtPass.setBounds(100, 70, 150, 25);
        
        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setBounds(100, 120, 100, 30);
        
        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(100, 160, 100, 30);

        // Evento para el botón Volver
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra esta ventana, lo que disparará el evento windowClosed de arriba
            }
        });

        // Añadir componentes a la ventana
        add(lblUsuario);
        add(txtUsuario);
        add(lblPass);
        add(txtPass);
        add(btnEntrar);
        add(btnVolver);
    }
}