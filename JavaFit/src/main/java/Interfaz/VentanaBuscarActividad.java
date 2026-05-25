package Interfaz;

import Logica.Socio;
import java.awt.Color;

/**
 * Ventana que permite al socio buscar actividades deportivas disponibles en JavaFit.
 * Ofrece filtros por tipo de actividad, día de la semana y monitor.
 * Al buscar, abre la VentanaResultadosActividades con las actividades filtradas.
 */
public class VentanaBuscarActividad extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VentanaBuscarActividad.class.getName());

    /** Referencia a la ventana anterior para restaurarla al cerrar. */
    private javax.swing.JFrame ventanaAnterior;
    
    /** Socio que está realizando la búsqueda. */
    private Socio socioActual;

    /**
     * Constructor de VentanaBuscarActividad.
     * @param menu Ventana del menú del socio desde la que se abre.
     * @param socio Socio que va a realizar la búsqueda.
     */
    public VentanaBuscarActividad(javax.swing.JFrame menu, Socio socio) {
        this.ventanaAnterior = menu;
        this.socioActual = socio;
        initComponents();
        
        
        this.setSize(710, 415);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        Logica.HerramientasVisuales.ponerIconoJavaFit(this);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                if (ventanaAnterior != null) {
                    ventanaAnterior.setVisible(true);
                }
            }
        });
        // Fondo degradado
    javax.swing.JPanel panelFondo = new javax.swing.JPanel() {
        @Override
        protected void paintComponent(java.awt.Graphics g) {
            super.paintComponent(g);
            java.awt.Graphics2D g2d = (java.awt.Graphics2D) g;
            g2d.setPaint(new java.awt.GradientPaint(
                0, 0, new java.awt.Color(15, 20, 40),
                0, getHeight(), new java.awt.Color(25, 40, 70)
            ));
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    };
    panelFondo.setLayout(null);
    setContentPane(panelFondo);
    
    
    // Título
    javax.swing.JLabel lblTitulo = new javax.swing.JLabel("BÚSQUEDA DE ACTIVIDADES", javax.swing.SwingConstants.CENTER);
    lblTitulo.setFont(new java.awt.Font("Segoe UI Black", java.awt.Font.BOLD, 26));
    lblTitulo.setForeground(new java.awt.Color(0, 230, 180));
    lblTitulo.setBounds(0, 15, 710, 45);
    panelFondo.add(lblTitulo);
    
    // Labels de los filtros
    jLabel1.setForeground(java.awt.Color.WHITE);
    jLabel1.setBounds(63, 90, 167, 20);
    panelFondo.add(jLabel1);

    jLabel2.setForeground(java.awt.Color.WHITE);
    jLabel2.setBounds(280, 90, 133, 20);
    panelFondo.add(jLabel2);

    jLabel3.setForeground(java.awt.Color.WHITE);
    jLabel3.setBounds(500, 90, 82, 20);
    panelFondo.add(jLabel3);
    
    // ComboBoxes
    estilizarCombo(buscaTipoActividad);
    buscaTipoActividad.setBounds(63, 115, 160, 34);
    panelFondo.add(buscaTipoActividad);

    estilizarCombo(buscarDiaSemana);
    buscarDiaSemana.setBounds(270, 115, 160, 34);
    panelFondo.add(buscarDiaSemana);

    estilizarCombo(buscarMonitor);
    buscarMonitor.setBounds(477, 115, 160, 34);
    panelFondo.add(buscarMonitor);
    
    // Botón Buscar
    estilizarBoton(botonBuscar, "Buscar Actividad", new java.awt.Color(0, 200, 160));
    botonBuscar.setBounds(255, 185, 200, 55);
    panelFondo.add(botonBuscar);
    
    // Botón Volver
    botonVolver.setText("← Volver atrás");
    botonVolver.setBounds(25, 330, 130, 35);
    botonVolver.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
    botonVolver.setForeground(java.awt.Color.WHITE);
    botonVolver.setBackground(new java.awt.Color(180, 40, 40));
    botonVolver.setFocusPainted(false);
    botonVolver.setBorderPainted(false);
    botonVolver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    botonVolver.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent e) {
            botonVolver.setBackground(new java.awt.Color(220, 60, 60));
        }
        public void mouseExited(java.awt.event.MouseEvent e) {
            botonVolver.setBackground(new java.awt.Color(180, 40, 40));
        }
    });
    panelFondo.add(botonVolver);
}
    // Estilo de los botones
    private void estilizarBoton(javax.swing.JButton btn, String texto, java.awt.Color color) {
    btn.setText(texto);
    btn.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16));
    btn.setForeground(java.awt.Color.WHITE);
    btn.setBackground(color);
    btn.setFocusPainted(false);
    btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
}
    
// Estilo de los ComboBox
private void estilizarCombo(javax.swing.JComboBox<String> combo) {
    combo.setBackground(new java.awt.Color(30, 40, 65));
    combo.setForeground(java.awt.Color.WHITE);
    combo.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13));
    combo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 230, 180), 1));
    combo.setRenderer(new javax.swing.DefaultListCellRenderer() {
        @Override
        public java.awt.Component getListCellRendererComponent(javax.swing.JList<?> list,
                Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            setBackground(isSelected ? new java.awt.Color(0, 180, 140) : new java.awt.Color(30, 40, 65));
            setForeground(java.awt.Color.WHITE);
            return this;
        }
    });
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botonBuscar = new javax.swing.JButton();
        buscaTipoActividad = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        buscarDiaSemana = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        buscarMonitor = new javax.swing.JComboBox<>();
        botonVolver = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Búsqueda de Actividades - JavaFit");

        botonBuscar.setBackground(new java.awt.Color(51, 102, 255));
        botonBuscar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        botonBuscar.setForeground(new java.awt.Color(255, 255, 255));
        botonBuscar.setText("Buscar Actividad");
        botonBuscar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        botonBuscar.addActionListener(this::botonBuscarActionPerformed);

        buscaTipoActividad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cualquiera", "Yoga", "Musculación", "Natación", "Cardio" }));
        buscaTipoActividad.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buscaTipoActividad.addActionListener(this::buscaTipoActividadActionPerformed);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Tipo de Actividad");

        buscarDiaSemana.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cualquiera", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sábado", "Domingo" }));
        buscarDiaSemana.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Día de la semana");

        buscarMonitor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cualquiera", "Iago Palazón", "Lorena Menéndez", "Paco Díaz", "Natalia Romareda" }));
        buscarMonitor.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buscarMonitor.addActionListener(this::buscarMonitorActionPerformed);

        botonVolver.setBackground(new java.awt.Color(255, 204, 153));
        botonVolver.setText("Volver atrás");
        botonVolver.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        botonVolver.addActionListener(this::botonVolverActionPerformed);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Monitor");

        lblTitulo.setFont(new java.awt.Font("Segoe UI Black", 0, 36)); // NOI18N
        lblTitulo.setText("BÚSQUEDA DE ACTIVIDADES");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(buscarDiaSemana, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(60, 60, 60)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(buscaTipoActividad, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(121, 121, 121)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                                .addComponent(buscarMonitor, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(85, 85, 85)
                                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(263, 263, 263)
                                .addComponent(botonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(botonVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(85, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buscaTipoActividad, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscarDiaSemana, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscarMonitor, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addComponent(botonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(botonVolver)
                .addGap(37, 37, 37))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buscaTipoActividadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscaTipoActividadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buscaTipoActividadActionPerformed

    private void buscarMonitorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarMonitorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buscarMonitorActionPerformed

    /**
     * Cierra la ventana de búsqueda y restaura el menú del socio.
     * @param evt Evento de acción del botón.
     */
    private void botonVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonVolverActionPerformed
        this.dispose(); // Cierra esta ventana
        if (ventanaAnterior != null) {
            ventanaAnterior.setVisible(true); // Muestra el menú anterior
        }
    }//GEN-LAST:event_botonVolverActionPerformed

    /**
     * Recoge los filtros seleccionados, consulta al Gestor las actividades
     * que los cumplen y abre la VentanaResultadosActividades con los resultados.
     * @param evt Evento de acción del botón.
     */
    private void botonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBuscarActionPerformed
                                             
    
    String tipo = buscaTipoActividad.getSelectedItem().toString();
    String dia = buscarDiaSemana.getSelectedItem().toString();
    String monitor = buscarMonitor.getSelectedItem().toString();

    
    java.util.ArrayList<Logica.Actividad_Deportiva> resultados = Logica.Gestor.filtrarActividades(tipo, dia, monitor);

    VentanaResultadosActividades vResultados = new VentanaResultadosActividades(ventanaAnterior, socioActual);

    vResultados.setLocationRelativeTo(null);
    vResultados.setResizable(false);
    vResultados.cargarDatosEnTabla(resultados);
    vResultados.setVisible(true);
    this.setVisible(false);     
    }//GEN-LAST:event_botonBuscarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonBuscar;
    private javax.swing.JButton botonVolver;
    private javax.swing.JComboBox<String> buscaTipoActividad;
    private javax.swing.JComboBox<String> buscarDiaSemana;
    private javax.swing.JComboBox<String> buscarMonitor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblTitulo;
    // End of variables declaration//GEN-END:variables
}