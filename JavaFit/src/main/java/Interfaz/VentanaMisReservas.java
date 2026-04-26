/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaz;
import Logica.Gestor;
import Logica.Reserva;
import Logica.Socio;
import java.util.ArrayList;
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 539, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(165, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(137, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaMisReservas;
    // End of variables declaration//GEN-END:variables
}
