package Interfaz;
import Logica.Socio;
import Logica.Gestor;
/**
 *
 * @author gdsergio1307
 */
public class VentanaRegistro extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VentanaRegistro.class.getName());
    private javax.swing.JFrame ventanaPrincipal;

    public VentanaRegistro(javax.swing.JFrame principal) {
        this.ventanaPrincipal = principal;
        initComponents();
        
        this.getContentPane().setBackground(java.awt.Color.cyan);
        this.setSize(710,415);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        try {
            javax.swing.text.MaskFormatter mf = new javax.swing.text.MaskFormatter("#########");
            mf.setPlaceholderCharacter('_');
            registroTelefono.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(mf));
        } catch (java.text.ParseException ex) {
            System.err.println("Error en máscara: " + ex.getMessage());
        }
        
        this.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                if (ventanaPrincipal != null) {
                    ventanaPrincipal.setVisible(true);
                }
            }
        });
        
        this.setLocationRelativeTo(null);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        registroTelefono = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        registroDireccion = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        registroTarjeta = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        registroSocioVip = new javax.swing.JCheckBox();
        botonVolverLogin = new javax.swing.JButton();
        botonConfirmarRegistro = new javax.swing.JButton();
        registroCorreo = new javax.swing.JFormattedTextField();
        registroContraseña = new javax.swing.JPasswordField();
        registroNombre = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Nombre Completo");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, -1, -1));

        registroTelefono.addActionListener(this::registroTelefonoActionPerformed);
        getContentPane().add(registroTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 120, 230, 40));

        jLabel2.setText("Teléfono");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 100, -1, -1));

        registroDireccion.addActionListener(this::registroDireccionActionPerformed);
        getContentPane().add(registroDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 190, 230, 40));

        jLabel3.setText("Dirección");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 170, -1, -1));

        registroTarjeta.addActionListener(this::registroTarjetaActionPerformed);
        getContentPane().add(registroTarjeta, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 260, 230, 40));

        jLabel4.setText("Tarjeta de crédito");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 240, -1, -1));

        registroSocioVip.setLabel("Registrarse como Socio VIP");
        registroSocioVip.addActionListener(this::registroSocioVipActionPerformed);
        getContentPane().add(registroSocioVip, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 120, -1, -1));

        botonVolverLogin.setLabel("Volver atrás");
        botonVolverLogin.addActionListener(this::botonVolverLoginActionPerformed);
        getContentPane().add(botonVolverLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        botonConfirmarRegistro.setText("Registrarse");
        botonConfirmarRegistro.addActionListener(this::botonConfirmarRegistroActionPerformed);
        getContentPane().add(botonConfirmarRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 320, -1, -1));

        registroCorreo.addActionListener(this::registroCorreoActionPerformed);
        getContentPane().add(registroCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 180, 130, 30));

        registroContraseña.setText("jPasswordField1");
        getContentPane().add(registroContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 260, -1, -1));

        registroNombre.addActionListener(this::registroNombreActionPerformed);
        getContentPane().add(registroNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 250, 40));

        jLabel5.setText("Correo");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 160, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void registroTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registroTelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_registroTelefonoActionPerformed

    private void registroTarjetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registroTarjetaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_registroTarjetaActionPerformed

    private void registroSocioVipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registroSocioVipActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_registroSocioVipActionPerformed

    private void botonVolverLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonVolverLoginActionPerformed
        this.dispose();
    }//GEN-LAST:event_botonVolverLoginActionPerformed

    private void botonConfirmarRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonConfirmarRegistroActionPerformed
        String nombre = registroNombre.getText().trim();
        String telefono = registroTelefono.getText().replace("_", "").trim();
        String direccion = registroDireccion.getText().trim();
        String correo = registroCorreo.getText().trim();
        String contraseña = new String(registroContraseña.getPassword());
        String tarjeta = registroTarjeta.getText().replace("_", "").replace(" ", "").trim();
        boolean esVip = registroSocioVip.isSelected();

    // 2. Validar Nombre (Solo letras y espacios)
    
        System.out.println("DEBUG - Tarjeta: '" + tarjeta + "' Longitud: " + tarjeta.length());
        if (!nombre.matches("^[a-zA-ZñÑáéíóúÁÉÍÓÚ\\s]+$")) {
            mostrarError("El nombre solo puede contener letras.");
        }

    // 3. Validar Teléfono (9 dígitos)
        else if (telefono.length() != 9) {
            mostrarError("El teléfono debe tener 9 números.");
        }

        else if (!correo.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            mostrarError("El formato del correo no es válido.");
        }

    // 5. Validar Dirección
        else if (direccion.isEmpty()) {
            mostrarError("La dirección no puede estar vacía.");
        }
        
        else if (!tarjeta.matches("^\\d{16}$")) {
            mostrarError("La tarjeta debe tener 16 números");
        }

    // 6. Validar Contraseña (Longitud)
        else if (contraseña.length() < 6) {
            mostrarError("La contraseña debe tener al menos 6 caracteres.");
        }

        else { // Si pasó todos los filtros if
        

            Socio nuevoSocio = new Socio(nombre, telefono, direccion, tarjeta, esVip, correo, contraseña);
            Logica.Gestor.agregarSocio(nuevoSocio);
            System.out.println("Objeto Socio creado: " + nuevoSocio.getNombre());
            javax.swing.JOptionPane.showMessageDialog(this, "Usuario " + nuevoSocio.getNombre() + " vinculado correctamente.");
        
            this.dispose(); 
        }
    }

// Método auxiliar para los mensajes
    private void mostrarError(String mensaje) {
        javax.swing.JOptionPane.showMessageDialog(this, mensaje, "Error de validación", javax.swing.JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_botonConfirmarRegistroActionPerformed

    private void registroCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registroCorreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_registroCorreoActionPerformed

    private void registroNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registroNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_registroNombreActionPerformed

    private void registroDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registroDireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_registroDireccionActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonConfirmarRegistro;
    private javax.swing.JButton botonVolverLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPasswordField registroContraseña;
    private javax.swing.JFormattedTextField registroCorreo;
    private javax.swing.JFormattedTextField registroDireccion;
    private javax.swing.JFormattedTextField registroNombre;
    private javax.swing.JCheckBox registroSocioVip;
    private javax.swing.JFormattedTextField registroTarjeta;
    private javax.swing.JFormattedTextField registroTelefono;
    // End of variables declaration//GEN-END:variables
}
