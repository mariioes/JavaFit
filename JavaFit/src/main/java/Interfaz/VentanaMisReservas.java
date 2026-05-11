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
 * Ventana que muestra las reservas activas del socio en JavaFit.
 * Permite consultar todas las actividades reservadas y cancelar cualquiera de ellas.
 * @author gdsergio1307
 */
public class VentanaMisReservas extends javax.swing.JFrame {
    
    /** Socio cuyas reservas se están mostrando. */
    private Socio socioLogueado;
    
    /** Referencia al menú del socio para restaurarlo al cerrar esta ventana. */
    private javax.swing.JFrame ventanaMenu;
    
    /** Lista de reservas actuales del socio, sincronizada con la tabla. */
    private ArrayList<Reserva> misReservasActuales;

    /**
     * Constructor de VentanaMisReservas.
     * Carga las reservas del socio en la tabla al abrirse.
     * @param menu Ventana del menú del socio desde la que se abre.
     * @param socio Socio cuyas reservas se van a mostrar.
     */
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


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaMisReservas = new javax.swing.JTable();
        botonVolver = new javax.swing.JButton();
        botonCancelarReserva = new javax.swing.JButton();

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

        botonCancelarReserva.setText("Cancelar reserva");
        botonCancelarReserva.addActionListener(this::botonCancelarReservaActionPerformed);

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
                        .addComponent(botonCancelarReserva)))
                .addContainerGap(177, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botonCancelarReserva)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(botonVolver)
                .addGap(48, 48, 48))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Cierra la ventana y restaura el menú del socio.
     * @param evt Evento de acción del botón.
     */
    private void botonVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonVolverActionPerformed
        this.dispose();
    }//GEN-LAST:event_botonVolverActionPerformed

    /**
     * Cancela la reserva seleccionada en la tabla tras pedir confirmación al socio.
     * Llama al Gestor para eliminar la reserva y actualiza la tabla si tiene éxito.
     * @param evt Evento de acción del botón.
     */
    private void botonCancelarReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelarReservaActionPerformed
        int fila = tablaMisReservas.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona una actividad de la tabla.");
            return;
        }

        Reserva miReserva = misReservasActuales.get(fila);
        Actividad_Deportiva seleccionada = miReserva.getActividad();
        
        int confirmacion = javax.swing.JOptionPane.showConfirmDialog(this, 
        "¿Estás seguro de que deseas cancelar la reserva de " + seleccionada.getTitulo() + "?", 
        "Confirmar cancelación", 
        javax.swing.JOptionPane.YES_NO_OPTION);
        
        if (confirmacion == javax.swing.JOptionPane.YES_OPTION){
            // Llamada al método del Gestor
            String resultado_2 = Gestor.cancelarReserva(socioLogueado, seleccionada);
            
        switch (resultado_2) {
            case "EXITO":
                misReservasActuales.remove(fila);
                javax.swing.table.DefaultTableModel modelo = (javax.swing.table.DefaultTableModel) tablaMisReservas.getModel();
                modelo.removeRow(fila);
                
                javax.swing.JOptionPane.showMessageDialog(this, "Reserva cancelada con exito");
                break;
                
            case "ERROR":
                javax.swing.JOptionPane.showMessageDialog(this, "No se ha podido encontrar la reserva para cancelar", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                break;
            }
        }
    }//GEN-LAST:event_botonCancelarReservaActionPerformed

    /**
     * Obtiene las reservas del socio desde el Gestor y las muestra en la tabla.
     * Vacía la tabla antes de rellenarla para evitar duplicados.
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
    private javax.swing.JButton botonCancelarReserva;
    private javax.swing.JButton botonVolver;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaMisReservas;
    // End of variables declaration//GEN-END:variables
}
