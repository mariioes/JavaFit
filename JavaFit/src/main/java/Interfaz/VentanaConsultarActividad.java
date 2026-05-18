package Interfaz;
import javax.swing.JOptionPane;
import Logica.Gestor;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Ventana que permite al administrador gestionar las actividades deportivas de JavaFit.
 * Muestra una tabla con todas las actividades y un panel lateral con el detalle de la seleccionada.
 * Permite crear nuevas actividades, modificar las existentes y eliminarlas.
 */
public class VentanaConsultarActividad extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VentanaConsultarActividad.class.getName());
    
    /** Referencia al menú del administrador para restaurarlo al cerrar. */
    private javax.swing.JFrame ventanaAdmin;
    /** Lista de actividades mostradas en la tabla, sincronizada con las filas. */
    private java.util.ArrayList<Logica.Actividad_Deportiva> listaActividadesActual;
    /** Administrador que está gestionando las actividades. */
    private Logica.Administrador adminActual;

    /**
     * Constructor de VentanaConsultarActividad.
     * Carga las actividades en la tabla y añade el listener de clic para mostrar detalles.
     * @param ventanaAdmin Ventana del menú del administrador desde la que se abre.
     * @param admin Administrador que está realizando la gestión.
     */
    public VentanaConsultarActividad(javax.swing.JFrame ventanaAdmin, Logica.Administrador admin) {
        this.ventanaAdmin = ventanaAdmin;
        this.adminActual = admin;
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("JAVAFIT - Consultar Actividades");
        this.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosed(java.awt.event.WindowEvent e) {
            if (ventanaAdmin != null) {
                ventanaAdmin.setVisible(true);
                ((VentanaConsultarActividad) ventanaAdmin).cargarActividades();
            }
        }
    });
        jEditorPane1.setEditable(false);
        cargarActividades();

        tablaActividades.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            int fila = tablaActividades.getSelectedRow();
            if (fila != -1 && listaActividadesActual != null) {
                actualizarPanelDetalles(listaActividadesActual.get(fila));
            }
        }
    });
    }
    
    /**
     * Carga todas las actividades sin ningún filtro (comportamiento original).
     */
    /**
     * Carga todas las actividades del sistema en la tabla sin filtros.
     */
    public void cargarActividades() {
        cargarActividades(Logica.Gestor.getActividades()); 
    }

    public void cargarActividades(java.util.ArrayList<Logica.Actividad_Deportiva> actividadesAMostrar) {
        javax.swing.table.DefaultTableModel modelo = (javax.swing.table.DefaultTableModel) tablaActividades.getModel();
        modelo.setRowCount(0); // Vaciar la tabla

        this.listaActividadesActual = actividadesAMostrar;

        // Si la lista está vacía, no hacemos nada más
        if (actividadesAMostrar == null || actividadesAMostrar.isEmpty()) {
            return;
        }

        // Rellenamos la tabla recorriendo la lista que nos han pasado
        for (Logica.Actividad_Deportiva act : listaActividadesActual) {
            
            // Calculamos aforo restante
            long inscritos = Logica.Gestor.getReservas().stream()
                .filter(r -> r.getActividad().getTitulo().equals(act.getTitulo()))
                .count();
            int restante = act.getSala().getAforo_maximo() - (int) inscritos;

            // Comprobamos si es especial para el precio
            boolean esEspecial = act instanceof Logica.Actividad_Especial;
            String precio = esEspecial ? ((Logica.Actividad_Especial) act).getPrecio() + "€" : "Incluido";
            
            // Formateamos el horario
            String horario = act.getHorario().getDia() + " " + act.getHorario().getHora_inicio() + "-" + act.getHorario().getHora_final();

            // Añadimos la fila
            modelo.addRow(new Object[]{
                act.getTitulo(),
                act.getTipo_Actividad().toString(),
                act.getSala().getNombre(),
                act.getMonitor_asignado(),
                horario,
                restante + "/" + act.getSala().getAforo_maximo(),
                esEspecial ? "Sí" : "No",
                precio
            });
        }
    }

    /**
     * Muestra los detalles completos de la actividad seleccionada en el panel lateral,
     * incluyendo el aforo restante calculado a partir de las reservas activas.
     * @param act Actividad seleccionada en la tabla.
     */
    private void actualizarPanelDetalles(Logica.Actividad_Deportiva act) {
        long inscritos = Logica.Gestor.getReservas().stream()
            .filter(r -> r.getActividad().getTitulo().equals(act.getTitulo()))
            .count();
        int restante = act.getSala().getAforo_maximo() - (int) inscritos;

        boolean esEspecial = act instanceof Logica.Actividad_Especial;
        String precio = esEspecial ? ((Logica.Actividad_Especial) act).getPrecio() + "€" : "Incluido";

        String texto = "Título: " + act.getTitulo() + "\n"
            + "Tipo: " + act.getTipo_Actividad().toString() + "\n"
            + "Sala: " + act.getSala().getNombre() + "\n"
            + "Monitor: " + act.getMonitor_asignado() + "\n"
            + "Día: " + act.getHorario().getDia() + "\n"
            + "Horario: " + act.getHorario().getHora_inicio() + " - " + act.getHorario().getHora_final() + "\n"
            + "Aforo: " + restante + " plazas libres de " + act.getSala().getAforo_maximo() + "\n"
            + "¿Especial?: " + (esEspecial ? "Sí" : "No") + "\n"
            + "Precio: " + precio;

        jEditorPane1.setText(texto);
    }
    
    /**
     * Elimina la actividad seleccionada en la tabla tras pedir confirmación al administrador.
     * Refresca la tabla tras la eliminación.
     */
    private void eliminarActividad() {
        int fila = tablaActividades.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una actividad para eliminar.");
            return;
        }
        int confirmar = JOptionPane.showConfirmDialog(this,
            "¿Seguro que quieres eliminar esta actividad?",
            "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirmar == JOptionPane.YES_OPTION) {
            Gestor.eliminar(listaActividadesActual.get(fila));
            JOptionPane.showMessageDialog(this, "Actividad eliminada correctamente.");
            cargarActividades();
        }
        }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        botonEliminarActividad = new javax.swing.JButton();
        botonCrearActividad = new javax.swing.JButton();
        botonModificarActividad = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaActividades = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        botonNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        botonTipoActividad = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        botonDia = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        botonMonitor = new javax.swing.JComboBox<>();
        botonBuscar = new javax.swing.JButton();
        checkActividadEspecial = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Volver atrás");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jScrollPane2.setViewportView(jEditorPane1);

        botonEliminarActividad.setText("Eliminar Actividad");
        botonEliminarActividad.addActionListener(this::botonEliminarActividadActionPerformed);

        botonCrearActividad.setText("Crear Actividad");
        botonCrearActividad.addActionListener(this::botonCrearActividadActionPerformed);

        botonModificarActividad.setText("Modificar Actividad");
        botonModificarActividad.addActionListener(this::botonModificarActividadActionPerformed);

        tablaActividades.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Título", "Tipo", "Sala", "Monitor", "Horario", "Aforo actual", "¿Especial?", "Precio"
            }
        ));
        jScrollPane1.setViewportView(tablaActividades);

        jScrollPane3.setViewportView(jScrollPane1);

        jLabel2.setText("Nombre:");

        botonNombre.addActionListener(this::botonNombreActionPerformed);

        jLabel3.setText("Tipo de Actividad:");

        botonTipoActividad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cualquiera", "YOGA", "MUSCULACION", "NATACION", "CARDIO" }));

        jLabel4.setText("Día:");

        botonDia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cualquiera", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sábado", "Domingo" }));

        jLabel5.setText("Monitor:");

        botonMonitor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cualquiera", "Monitor 1", "Monitor 2", "Monitor 3", "Monitor 4" }));

        botonBuscar.setText("Buscar");
        botonBuscar.addActionListener(this::botonBuscarActionPerformed);

        checkActividadEspecial.setText("Solo Actividades Especiales");
        checkActividadEspecial.addActionListener(this::checkActividadEspecialActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(botonEliminarActividad)
                        .addGap(43, 43, 43)
                        .addComponent(botonCrearActividad, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(botonModificarActividad))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(botonNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(botonTipoActividad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(87, 87, 87)
                                        .addComponent(jLabel5))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(botonDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(botonMonitor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(botonBuscar)
                                                .addGap(28, 28, 28))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(checkActividadEspecial)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(160, 160, 160))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botonNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonTipoActividad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonMonitor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonBuscar))
                        .addGap(10, 10, 10)
                        .addComponent(checkActividadEspecial)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonCrearActividad)
                    .addComponent(botonModificarActividad)
                    .addComponent(botonEliminarActividad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Cierra la ventana y restaura el menú del administrador.
     * @param evt Evento de acción del botón.
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
        if (ventanaAdmin!=null) {
            ventanaAdmin.setVisible(true);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * Llama al método de eliminar actividad.
     * @param evt Evento de acción del botón.
     */
    private void botonEliminarActividadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarActividadActionPerformed
        eliminarActividad();
    }//GEN-LAST:event_botonEliminarActividadActionPerformed

    /**
     * Abre la ventana de creación de actividades para añadir una nueva.
     * @param evt Evento de acción del botón.
     */
    private void botonCrearActividadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCrearActividadActionPerformed
        if (this.adminActual != null) {
            VentanaCrearActividad vcca = new VentanaCrearActividad(this, this.adminActual);
            vcca.setVisible(true);
            this.setVisible(false);
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Error: No hay un admin logueado.");
        }
    }//GEN-LAST:event_botonCrearActividadActionPerformed

    /**
     * Abre la ventana de creación de actividades con los datos de la actividad seleccionada para permitir su modificación.
     * @param evt Evento de acción del botón.
     */
    private void botonModificarActividadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonModificarActividadActionPerformed
        int fila = tablaActividades.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona una actividad para modificarla");
            return;
        }
        
        // Obtenemos la actividad que queremos modificar
        Logica.Actividad_Deportiva actividadAModificar = listaActividadesActual.get(fila);
        
        // Abrimos la ventana de crear actividad pero con los datos de la actividad a modificar
        if (this.adminActual != null) {
            VentanaCrearActividad vca = new VentanaCrearActividad(this, this.adminActual, actividadAModificar);
            vca.setVisible(true);
            this.setVisible(false);
            
        } else {
            JOptionPane.showMessageDialog(this, "Error.");
        }
    }//GEN-LAST:event_botonModificarActividadActionPerformed

    private void botonNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonNombreActionPerformed

    private void checkActividadEspecialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkActividadEspecialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkActividadEspecialActionPerformed

    private void botonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBuscarActionPerformed
        String nombre = botonNombre.getText().trim().toLowerCase();
        String tipo = botonTipoActividad.getSelectedItem().toString();
        String dia = botonDia.getSelectedItem().toString();
        String monitor = botonMonitor.getSelectedItem().toString();
        
        final boolean soloEspecial = checkActividadEspecial.isSelected();
        
        ArrayList<Logica.Actividad_Deportiva> actividadesFiltradas = Logica.Gestor.getActividades().stream()
            // Comprobamos directamente sobre la actividad 'a'
            .filter(a -> nombre.isEmpty() || a.getTitulo().toLowerCase().contains(nombre))
            .filter(a -> tipo.equals("Cualquiera") || a.getTipo_Actividad().toString().equals(tipo))
            .filter(a -> dia.equals("Cualquiera") || a.getHorario().getDia().equals(dia))
            .filter(a -> monitor.equals("Cualquiera") || a.getMonitor_asignado().equals(monitor))
            // Filtro adaptado usando Polimorfismo (Tema 2-5)
            .filter(a -> !soloEspecial || a instanceof Logica.Actividad_Especial) 
            .collect(Collectors.toCollection(ArrayList::new));
        
        cargarActividades(actividadesFiltradas);
        
        if (actividadesFiltradas.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "No se han encontrado actividades con estos criterios");
        }
    }//GEN-LAST:event_botonBuscarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonBuscar;
    private javax.swing.JButton botonCrearActividad;
    private javax.swing.JComboBox<String> botonDia;
    private javax.swing.JButton botonEliminarActividad;
    private javax.swing.JButton botonModificarActividad;
    private javax.swing.JComboBox<String> botonMonitor;
    private javax.swing.JTextField botonNombre;
    private javax.swing.JComboBox<String> botonTipoActividad;
    private javax.swing.JCheckBox checkActividadEspecial;
    private javax.swing.JButton jButton1;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tablaActividades;
    // End of variables declaration//GEN-END:variables
}
