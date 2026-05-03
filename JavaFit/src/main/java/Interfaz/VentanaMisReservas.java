/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaz;
import Logica.Gestor;
import Logica.Reserva;
import Logica.Socio;
import java.util.ArrayList;
import Logica.Actividad_Deportiva;
import Logica.Actividad_Especial;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
/**
 *
 * @author gdsergio1307
 */
public class VentanaMisReservas extends javax.swing.JFrame {
    private Socio socioLogueado;
    private javax.swing.JFrame ventanaMenu;
    private ArrayList<Reserva> misReservasActuales; // Para tener a mano los objetos reales

    public VentanaMisReservas(javax.swing.JFrame menu, Socio socio) {
        this.ventanaMenu = menu;
        this.socioLogueado = socio;
        initComponents();
        
        this.setLocationRelativeTo(null); 
        this.setResizable(false);
        this.setTitle("JavaFit - Mis Reservas");
        
        // Al abrir la ventana, cargamos los datos en la tabla inmediatamente
        actualizarTabla();
        
        // Configurar qué pasa al darle a la 'X' de la ventana
        this.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                if (ventanaMenu != null) {
                    ventanaMenu.setVisible(true);
                }
            }
        });
    }

    /**
     * Este método pide al Gestor las reservas de este socio y las pone en la tabla.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaMisReservas = new javax.swing.JTable();
        botonVolver = new javax.swing.JButton();
        botonCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tablaMisReservas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tablaMisReservas);

        botonVolver.setText("Volver atrás");
        botonVolver.addActionListener(this::botonVolverActionPerformed);

        botonCancelar.setText("Cancelar reserva");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 539, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(botonVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(211, 211, 211)
                        .addComponent(botonCancelar)))
                .addContainerGap(165, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botonCancelarReserva)
                .addGap(285, 285, 285))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botonCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(botonVolver)
                .addGap(48, 48, 48))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonVolverActionPerformed
        this.dispose();
    }//GEN-LAST:event_botonVolverActionPerformed

    /**
     * @param args the command line arguments
     */
    
    public void actualizarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) tablaMisReservas.getModel();
        modelo.setRowCount(0); // Vaciamos la tabla para no duplicar datos
        
        // 1. Pedimos al Gestor la lista filtrada
        misReservasActuales = Gestor.obtenerReservasPorSocio(socioLogueado);
        
        // 2. Rellenamos la tabla
        for (Reserva r : misReservasActuales) {
            Object[] fila = {
                r.getActividad().getTitulo(),
                r.getActividad().getHorario().getDia(),
                r.getActividad().getHorario().getHora_inicio(),
                r.getActividad().getSala().getNombre(),
                r.getActividad().getMonitor_asignado()
            };
            modelo.addRow(fila);
        }
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonCancelar;
    private javax.swing.JButton botonVolver;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaMisReservas;
    // End of variables declaration//GEN-END:variables
}
