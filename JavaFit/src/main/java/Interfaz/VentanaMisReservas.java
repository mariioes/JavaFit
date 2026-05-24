/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaz;
import Logica.Actividad_Deportiva;
import Logica.Actividad_Especial;
import Logica.Gestor;
import Logica.Reserva;
import Logica.Socio;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.ImageIcon;
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
        this.getContentPane().setBackground(Color.white);
        Logica.HerramientasVisuales.ponerIconoJavaFit(this);
        
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
        
        // Evento para detectar el clic en la tabla de reservas
        tablaMisReservas.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int fila = tablaMisReservas.getSelectedRow();
                // Usamos misReservasActuales que es la lista que tienes declarada arriba
                if (fila != -1 && misReservasActuales != null) {
                    Reserva seleccionada = misReservasActuales.get(fila);
                    actualizarPanelDetalles(seleccionada);
                }
            // Margen texto
            txtDescripcion.setMargin(new java.awt.Insets(10, 10, 10, 10));
            
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
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        lblFotos = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tablaMisReservas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Actividad", "Tipo", "Sala (Aforo)", "Dia", "Horario", "Monitor", "Precio"
            }
        ));
        jScrollPane1.setViewportView(tablaMisReservas);

        botonVolver.setBackground(new java.awt.Color(255, 204, 153));
        botonVolver.setText("Volver atrás");
        botonVolver.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        botonVolver.addActionListener(this::botonVolverActionPerformed);

        botonCancelarReserva.setBackground(new java.awt.Color(255, 51, 51));
        botonCancelarReserva.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        botonCancelarReserva.setForeground(new java.awt.Color(255, 255, 255));
        botonCancelarReserva.setText("Cancelar reserva");
        botonCancelarReserva.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        botonCancelarReserva.addActionListener(this::botonCancelarReservaActionPerformed);

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane2.setViewportView(txtDescripcion);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 588, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonCancelarReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblFotos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(botonVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblFotos, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)
                        .addComponent(botonVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(botonCancelarReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

     private void actualizarPanelDetalles(Reserva reserva) {
    // 1. Descripción
        Actividad_Deportiva act = reserva.getActividad();
        
        // Es especial o no y su precio
        boolean esEspecial = act instanceof Actividad_Especial;
        String precio = esEspecial ? reserva.getPrecio_total() + "€" : "Incluido";
        
        java.time.format.DateTimeFormatter formato = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaExactaStr = reserva.getFechaReserva().format(formato);
        
        // Montamos el texto que se va a ver en el cuadro
        String texto = "Actividad: " + act.getTitulo() + "\n"
            + "Monitor: " + act.getMonitor_asignado() + "\n"
            + "Sala: " + act.getSala().getNombre() + "\n"
            + "Aforo: " + act.getSala().getAforo_maximo() + " personas.\n"
            + "Fecha exacta: " + fechaExactaStr + "\n" 
            + "Horario: " + act.getHorario().getHora_inicio() + " - " + act.getHorario().getHora_final() + "\n"
            + "¿Especial?: " + (esEspecial ? "Sí" : "No") + "\n"
            + "Precio: " + precio;
        // Si es una actividad especial, le ponemos la descripción delante
        if (esEspecial) {
            Actividad_Especial especial = (Actividad_Especial) act;
            texto = especial.getDescripcion() + "\n\n" + texto;
        }

        txtDescripcion.setText(texto);
        

    // 2. Imagen
    ImageIcon icono = act.getImagen();
    if (icono != null && icono.getImage() != null) {
        // Usamos tamaño fijo en lugar de getWidth()/getHeight()
        int ancho = 270;
        int alto = 120;

        java.awt.Image imgEscalada = icono.getImage()
                .getScaledInstance(ancho, alto, java.awt.Image.SCALE_SMOOTH);

        lblFotos.setIcon(new ImageIcon(imgEscalada));
        lblFotos.setText("");
    } else {
        lblFotos.setIcon(null);
        lblFotos.setText("Sin imagen");
    }
        }
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
        
        if (misReservasActuales == null || misReservasActuales.isEmpty()) {
            return; // Si no hay reservas, no hacemos nada más
        }
        
        // 2. Rellenamos la tabla
        for (Reserva r : misReservasActuales) {
            
            // Obtenemos la actividad de la reserva
            Actividad_Deportiva act = r.getActividad();
            
            // A. Calculamos el precio igual que en otras ventanas
            boolean esEspecial = r.getActividad() instanceof Logica.Actividad_Especial;
            String precioStr = esEspecial ? r.getPrecio_total() + "€" : "Incluido";
            
            // B. Preparamos los textos compuestos (Sala con aforo y Horario separado)
            String salaInfo = act.getSala().getNombre() + " (" + act.getSala().getAforo_maximo() + ")";
            String horarioInfo = act.getHorario().getHora_inicio() + " - " + act.getHorario().getHora_final();
            
            // C. Creamos la fila con los 6 datos en orden
            
            Object[] fila = {
            act.getTitulo(),                    
                act.getTipo_Actividad().toString(),
                salaInfo,        
                act.getHorario().getDia(),
                horarioInfo,
                act.getMonitor_asignado(),
                precioStr
            };
            modelo.addRow(fila);
        }
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonCancelarReserva;
    private javax.swing.JButton botonVolver;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblFotos;
    private javax.swing.JTable tablaMisReservas;
    private javax.swing.JTextArea txtDescripcion;
    // End of variables declaration//GEN-END:variables
}
