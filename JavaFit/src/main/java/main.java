import Interfaz.VentanaPrincipal;
import Logica.Gestor;

public class Main {
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