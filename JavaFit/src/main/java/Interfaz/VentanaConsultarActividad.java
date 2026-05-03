/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaz;
import javax.swing.JOptionPane;
import Logica.Gestor;
/**
 *
 * @author gdsergio1307
 */
public class VentanaConsultarActividad extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VentanaConsultarActividad.class.getName());
    private javax.swing.JFrame ventanaAdmin;
    private java.util.ArrayList<Logica.Actividad_Deportiva> listaActividadesActual;
    private Logica.Administrador adminActual;

    /**
     * Creates new form VentanaConsultarActividad
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

    jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            int fila = jTable1.getSelectedRow();
            if (fila != -1 && listaActividadesActual != null) {
                actualizarPanelDetalles(listaActividadesActual.get(fila));
            }
        }
    });
    }
    
    public void cargarActividades() {
        javax.swing.table.DefaultTableModel modelo = (javax.swing.table.DefaultTableModel) jTable1.getModel();
        modelo.setRowCount(0);

        listaActividadesActual = Logica.Gestor.getActividades();
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
            act.getMonitor_asignado(),horario,
            restante + "/" + act.getSala().getAforo_maximo(),
            esEspecial ? "Sí" : "No",
            precio});
    }
}

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
    
    private void eliminarActividad() {
        int fila = jTable1.getSelectedRow();
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
            cargarActividades(); // Refresca la tabla
        }
        }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Volver atrás");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jScrollPane2.setViewportView(jEditorPane1);

        jButton2.setText("Eliminar Actividad");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        jButton3.setText("Crear Actividad");
        jButton3.addActionListener(this::jButton3ActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(201, 201, 201)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(192, 192, 192)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
        if (ventanaAdmin!=null) {
            ventanaAdmin.setVisible(true);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        eliminarActividad();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (this.adminActual != null) {
            VentanaCrearActividad vcca = new VentanaCrearActividad(this, this.adminActual);
            vcca.setVisible(true);
            this.setVisible(false);
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Error: No hay un admin logueado.");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
