import Interfaz.VentanaPrincipal;

public static void main(String args[]) {
    // 1. CARGAR ANTES DE NADA
    Logica.Gestor.cargarSocios();
    Logica.Gestor.cargarAdmins();
    Logica.Gestor.crearAdminJefe();
    
    // 2. Luego el código que ya tienes de "java.awt.EventQueue.invokeLater..."
    java.awt.EventQueue.invokeLater(new Runnable() {
        @Override
        public void run() {
            new VentanaPrincipal().setVisible(true);
        }
    });
}