import Interfaz.VentanaPrincipal;
import Logica.Gestor;

public class Main {
    public static void main(String args[]) {
        // 1. CARGAR DATOS ANTES DE LANZAR LA INTERFAZ
        // Cargamos lo que haya en disco
        Logica.Gestor.cargarSocios();
        Logica.Gestor.cargarAdmins();
        Logica.Gestor.crearAdminJefe();
        
        // Cargamos las actividades de prueba que escribimos antes
        Logica.Gestor.cargarDatosPrueba();
        
        // 2. CONFIGURACIÓN DEL ESTILO VISUAL (Look and Feel)
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
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VentanaPrincipal().setVisible(true);
            }
        });
    }
}