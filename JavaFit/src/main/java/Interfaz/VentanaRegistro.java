package Interfaz; // Asegúrate de que coincida con el nombre de tu paquete

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaRegistro extends JFrame {

    // Referencia a la ventana principal para poder volver a ella
    private JFrame ventanaPrincipal;

    public VentanaRegistro(JFrame principal) {
        this.ventanaPrincipal = principal;

        // --- Configuración básica de la ventana ---
        setTitle("Registro de Nuevo Usuario");
        setSize(350, 350); // Un poco más alta que el login para que quepan más campos
        setLocationRelativeTo(null); // Centrar en pantalla
        setLayout(null); // Layout nulo para usar coordenadas exactas

        // Cerrar solo esta ventana al darle a la 'X'
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Evento para volver a mostrar la ventana principal si el usuario cierra esta
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                ventanaPrincipal.setVisible(true);
            }
        });

        // --- Creación de Componentes ---
        
        JLabel lblNombre = new JLabel("Nombre completo:");
        lblNombre.setBounds(30, 30, 120, 25);
        JTextField txtNombre = new JTextField();
        txtNombre.setBounds(150, 30, 150, 25);

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setBounds(30, 70, 120, 25);
        JTextField txtUsuario = new JTextField();
        txtUsuario.setBounds(150, 70, 150, 25);

        JLabel lblPass = new JLabel("Contraseña:");
        lblPass.setBounds(30, 110, 120, 25);
        JPasswordField txtPass = new JPasswordField(); // Oculta los caracteres
        txtPass.setBounds(150, 110, 150, 25);

        JLabel lblEmail = new JLabel("Correo (Email):");
        lblEmail.setBounds(30, 150, 120, 25);
        JTextField txtEmail = new JTextField();
        txtEmail.setBounds(150, 150, 150, 25);

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(60, 220, 100, 30);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(180, 220, 100, 30);

        // --- Eventos de los Botones ---

        // Evento del botón Volver
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra esta ventana y activa el windowClosed
            }
        });

        // Evento del botón Registrar
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí iría tu lógica interna para guardar el usuario (Base de datos, listas, etc.)
                
                // Mostramos un mensaje de confirmación al usuario
                JOptionPane.showMessageDialog(VentanaRegistro.this, "¡Usuario registrado con éxito!", "Registro Completado", JOptionPane.INFORMATION_MESSAGE);
                
                // Tras registrarse, cerramos la ventana y volvemos al menú principal
                dispose();
            }
        });

        // --- Añadir componentes a la ventana ---
        add(lblNombre);
        add(txtNombre);
        add(lblUsuario);
        add(txtUsuario);
        add(lblPass);
        add(txtPass);
        add(lblEmail);
        add(txtEmail);
        add(btnRegistrar);
        add(btnVolver);
    }
}
