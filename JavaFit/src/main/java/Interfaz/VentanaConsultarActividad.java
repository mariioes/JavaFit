package Interfaz;
import javax.swing.JOptionPane;
import Logica.Gestor;

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
    public void cargarActividades() {
        cargarActividadesFiltradas(""); // Cadena vacía = no filtra nada
    }
/**
     * Obtiene la lista de actividades, aplica un filtro por palabra y rellena la tabla.
     */
    public void cargarActividadesFiltradas(String palabra) {
        javax.swing.table.DefaultTableModel modelo = (javax.swing.table.DefaultTableModel) tablaActividades.getModel();
        modelo.setRowCount(0);

        // Pasamos la palabra a minúsculas para que dé igual cómo escriba el usuario
        String textoBusqueda = palabra.toLowerCase().trim();

        // AQUÍ ESTÁ EL FILTER: 
        // Cogemos todas las actividades, abrimos un stream, filtramos, y lo volvemos a convertir en ArrayList
        listaActividadesActual = (java.util.ArrayList<Logica.Actividad_Deportiva>) Logica.Gestor.getActividades().stream()
            .filter(act -> textoBusqueda.isEmpty() || // Si no hay texto, pasan todas
                           act.getTitulo().toLowerCase().contains(textoBusqueda) ||
                           act.getMonitor_asignado().toLowerCase().contains(textoBusqueda) ||
                           act.getSala().getNombre().toLowerCase().contains(textoBusqueda) ||
                           act.getTipo_Actividad().toString().toLowerCase().contains(textoBusqueda))
            .collect(java.util.stream.Collectors.toList());

        // Rellenamos la tabla solo con las que han superado el filtro
        for (Logica.Actividad_Deportiva act : listaActividadesActual) {
            // Calculamos aforo restante
            long inscritos = Logica.Gestor.getReservas().stream()
                .filter(r -> r.getActividad().getTitulo().equals(act.getTitulo()))
                .count();
            int restante = act.getSala().getAforo_maximo() - (int) inscritos;

            boolean esEspecial = act instanceof Logica.Actividad_Especial;
            String precio = esEspecial ? ((Logica.Actividad_Especial) act).getPrecio() + "€" : "Incluido";
            String horario = act.getHorario().getDia() + " " + act.getHorario().getHora_inicio() + "-" + act.getHorario().getHora_final();

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
        txtBuscador = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        botonBuscar = new javax.swing.JButton();

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

        jLabel1.setText("Buscar:");

        botonBuscar.setText("Filtrar");
        botonBuscar.addActionListener(this::botonBuscarActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(txtBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addComponent(botonEliminarActividad)
                        .addGap(51, 51, 51)
                        .addComponent(botonCrearActividad, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(botonModificarActividad)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(botonBuscar)
                .addGap(131, 131, 131))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botonEliminarActividad)
                            .addComponent(botonCrearActividad)
                            .addComponent(botonModificarActividad))
                        .addGap(53, 53, 53)))
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

    private void botonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBuscarActionPerformed
        cargarActividadesFiltradas(txtBuscador.getText());        // TODO add your handling code here:
    }//GEN-LAST:event_botonBuscarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonBuscar;
    private javax.swing.JButton botonCrearActividad;
    private javax.swing.JButton botonEliminarActividad;
    private javax.swing.JButton botonModificarActividad;
    private javax.swing.JButton jButton1;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tablaActividades;
    private javax.swing.JTextField txtBuscador;
    // End of variables declaration//GEN-END:variables
}
