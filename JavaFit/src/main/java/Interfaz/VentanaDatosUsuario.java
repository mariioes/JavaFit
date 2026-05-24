package Interfaz;
import Logica.Socio;
import Logica.Gestor;
import java.awt.Color;

/**
 * Ventana que muestra y permite editar los datos personales del socio en JavaFit.
 * Carga los datos actuales del socio en los campos al abrirse y permite modificarlos.
 * Valida todos los campos antes de guardar y actualiza el fichero de socios.
 */
public class VentanaDatosUsuario extends javax.swing.JFrame {
    
    /** Referencia al menú del socio para restaurarlo al cerrar esta ventana. */
    private javax.swing.JFrame VentanaMenuSocio;
    
    /** Socio cuyos datos se están mostrando y editando. */
    private Socio socioActual;
    
    /**
     * Constructor de VentanaDatosUsuario.
     * Carga los datos del socio en los campos del formulario y aplica una máscara de 9 dígitos al campo de teléfono.
     * @param menu Ventana del menú del socio desde la que se abre.
     * @param socio Socio cuyos datos se van a mostrar y editar.
     */
    public VentanaDatosUsuario(javax.swing.JFrame menu, Socio socio) {
        this.VentanaMenuSocio = menu;
        this.socioActual = socio;
        
        initComponents(); // Carga el diseño de la interfaz
        
        // 1. Configuramos el tamaño y centrado
        this.setSize(710, 415);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.white);
        Logica.HerramientasVisuales.ponerIconoJavaFit(this);
        
        // 2. Rellenamos los datos en pantalla
        registroNombre.setText(socioActual.getNombre());
        registroTelefono.setText(socioActual.getTelefono());
        registroDireccion.setText(socioActual.getDireccion());
        registroCorreo.setText(socioActual.getCorreo());
        registroTarjeta.setText(socioActual.getTarjeta());
        registroSocioVip.setSelected(socioActual.esVip());
        // Asumiendo que tu método se llama getContraseña() en la clase Usuario/Socio
        registroContraseña.setText(socioActual.getContraseña()); 
        
        // 3. Máscara del teléfono
        try {
            javax.swing.text.MaskFormatter mf = new javax.swing.text.MaskFormatter("#########");
            mf.setPlaceholderCharacter('_');
            registroTelefono.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(mf));
        } catch (java.text.ParseException ex) {
            System.err.println("Error en máscara: " + ex.getMessage());
        }
        
        // 4. Configurar el evento de volver al menú al cerrar
        this.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                if (VentanaMenuSocio != null) {
                    VentanaMenuSocio.setVisible(true);
                }
            }
        });
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        registroNombre = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        registroDireccion = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        registroTarjeta = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        registroSocioVip = new javax.swing.JCheckBox();
        botonVolverLogin = new javax.swing.JButton();
        botonGuardarCambios = new javax.swing.JButton();
        registroCorreo = new javax.swing.JFormattedTextField();
        registroContraseña = new javax.swing.JPasswordField();
        registroTelefono = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Nombre Completo");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 70, -1, -1));

        registroNombre.setBackground(new java.awt.Color(204, 255, 255));
        registroNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        registroNombre.setFormatterFactory(null);
        registroNombre.addActionListener(this::registroNombreActionPerformed);
        getContentPane().add(registroNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 90, 250, 40));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Teléfono");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, -1, -1));

        registroDireccion.setBackground(new java.awt.Color(204, 255, 255));
        registroDireccion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        registroDireccion.addActionListener(this::registroDireccionActionPerformed);
        getContentPane().add(registroDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, 250, 40));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Dirección");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, -1, -1));

        registroTarjeta.setBackground(new java.awt.Color(204, 255, 255));
        registroTarjeta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        registroTarjeta.addActionListener(this::registroTarjetaActionPerformed);
        getContentPane().add(registroTarjeta, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 260, 250, 40));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Tarjeta de crédito");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 240, -1, -1));

        registroSocioVip.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        registroSocioVip.setText("Socio VIP");
        registroSocioVip.addActionListener(this::registroSocioVipActionPerformed);
        getContentPane().add(registroSocioVip, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 310, -1, -1));

        botonVolverLogin.setBackground(new java.awt.Color(255, 204, 153));
        botonVolverLogin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        botonVolverLogin.setLabel("Volver atrás");
        botonVolverLogin.addActionListener(this::botonVolverLoginActionPerformed);
        getContentPane().add(botonVolverLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 80, 30));

        botonGuardarCambios.setBackground(new java.awt.Color(0, 204, 255));
        botonGuardarCambios.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        botonGuardarCambios.setText("Guardar cambios");
        botonGuardarCambios.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        botonGuardarCambios.addActionListener(this::botonGuardarCambiosActionPerformed);
        getContentPane().add(botonGuardarCambios, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 340, 140, 30));

        registroCorreo.setBackground(new java.awt.Color(204, 255, 255));
        registroCorreo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        registroCorreo.addActionListener(this::registroCorreoActionPerformed);
        getContentPane().add(registroCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 180, 250, 40));

        registroContraseña.setBackground(new java.awt.Color(204, 255, 255));
        registroContraseña.setText("jPasswordField1");
        registroContraseña.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(registroContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 260, 250, 40));

        registroTelefono.setBackground(new java.awt.Color(204, 255, 255));
        registroTelefono.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        registroTelefono.addActionListener(this::registroTelefonoActionPerformed);
        getContentPane().add(registroTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, 250, 40));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Correo");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 160, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Contraseña");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 240, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void registroNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registroNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_registroNombreActionPerformed

    private void registroTarjetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registroTarjetaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_registroTarjetaActionPerformed

    private void registroSocioVipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registroSocioVipActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_registroSocioVipActionPerformed

    /**
     * Cierra la ventana y restaura el menú del socio.
     * @param evt Evento de acción del botón.
     */
    private void botonVolverLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonVolverLoginActionPerformed
        this.dispose();
        
    }//GEN-LAST:event_botonVolverLoginActionPerformed

    /**
     * Valida todos los campos del formulario y si son correctos actualiza los datos del socio y los guarda en el fichero mediante el Gestor.
     * Valida nombre, teléfono, correo, dirección, tarjeta y contraseña.
     * @param evt Evento de acción del botón.
     */
    private void botonGuardarCambiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardarCambiosActionPerformed
        String nombre = registroNombre.getText().trim();
        String telefono = registroTelefono.getText().replace("_", "").trim();
        String direccion = registroDireccion.getText().trim();
        String correo = registroCorreo.getText().trim();
        String contraseña = new String(registroContraseña.getPassword());
        String tarjeta = registroTarjeta.getText().replace("_", "").replace(" ", "").trim();
        boolean esVip = registroSocioVip.isSelected();

        // Validaciones
        if (!nombre.matches("^[a-zA-ZñÑáéíóúÁÉÍÓÚ\\s]+$")) {
            mostrarError("El nombre solo puede contener letras.");
        } else if (telefono.length() != 9) {
            mostrarError("El teléfono debe tener 9 números.");
        } else if (!correo.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            mostrarError("El formato del correo no es válido.");
        } else if (direccion.isEmpty()) {
            mostrarError("La dirección no puede estar vacía.");
        } else if (!tarjeta.matches("^\\d{16}$")) {
            mostrarError("La tarjeta debe tener 16 números.");
        } else if (contraseña.length() < 6) {
            mostrarError("La contraseña debe tener al menos 6 caracteres.");
        } else { 
            // SI TODO ES CORRECTO: Actualizamos el socio actual usando Setters
            socioActual.setNombre(nombre);
            socioActual.setTelefono(telefono);
            socioActual.setDireccion(direccion);
            socioActual.setTarjeta(tarjeta);
            socioActual.setEsVip(esVip);
            // Asegúrate de que tienes estos Setters creados en tu clase Socio/Usuario
            socioActual.setCorreo(correo); 
            socioActual.setContraseña(contraseña); 

            // ¡CRÍTICO! Guardamos la lista completa en el fichero
            Logica.Gestor.guardarSocios();

            javax.swing.JOptionPane.showMessageDialog(this, "¡Datos actualizados correctamente!");
            this.dispose(); // Volvemos al menú
        }
    }

    /**
     * Muestra un mensaje de error de validación en un cuadro de diálogo.
     * @param mensaje Texto del error a mostrar.
     */
    private void mostrarError(String mensaje) {
        javax.swing.JOptionPane.showMessageDialog(this, mensaje, "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
    
    }//GEN-LAST:event_botonGuardarCambiosActionPerformed

    private void registroCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registroCorreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_registroCorreoActionPerformed

    private void registroTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registroTelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_registroTelefonoActionPerformed

    private void registroDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registroDireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_registroDireccionActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonGuardarCambios;
    private javax.swing.JButton botonVolverLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPasswordField registroContraseña;
    private javax.swing.JFormattedTextField registroCorreo;
    private javax.swing.JFormattedTextField registroDireccion;
    private javax.swing.JFormattedTextField registroNombre;
    private javax.swing.JCheckBox registroSocioVip;
    private javax.swing.JFormattedTextField registroTarjeta;
    private javax.swing.JFormattedTextField registroTelefono;
    // End of variables declaration//GEN-END:variables
}
