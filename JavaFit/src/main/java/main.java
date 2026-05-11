import Interfaz.VentanaPrincipal;
import Logica.Gestor;

/**
 * Clase principal de la aplicación JavaFit.
 * Punto de entrada del programa. Se encarga de inicializar los datos del sistema, configurar el estilo gráfico y lanzar la ventana principal.
 */
public class Main {
    
    /**
     * Método principal que arranca la aplicación JavaFit.
     * Inicializa todos los datos del sistema (socios, admins y actividades), aplica el estilo visual Nimbus si está disponible, y lanza la VentanaPrincipal en el hilo de eventos de Swing.
     * @param args Argumentos de línea de comandos (no se usan).
     */
    public static void main(String args[]) {
        // 1. LLAMADA ÚNICA: Esto gestiona socios, admins y actividades sin duplicar
        Logica.Gestor.inicializarTodo();
        
        // 2. CONFIGURACIÓN DEL ESTILO VISUAL
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        // 3. LANZAR LA VENTANA PRINCIPAL
        java.awt.EventQueue.invokeLater(() -> {
            new VentanaPrincipal().setVisible(true);
        });
    }
}