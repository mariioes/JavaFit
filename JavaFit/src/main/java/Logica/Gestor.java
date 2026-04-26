package Logica;

import java.util.ArrayList;
import java.io.*;

public class Gestor {
    
    // --- 1. ARCHIVOS DE GUARDADO ---
    private static final String FICHERO_ADMINS = "admins.dat";
    private static final String FICHERO_SOCIOS = "socios.dat";
    private static final String FICHERO_ACTIVIDADES = "actividades.dat";
    private static final String FICHERO_RESERVAS = "reservas.dat";

    // --- 2. LISTAS EN MEMORIA ---
    private static ArrayList<Administrador> admins = new ArrayList<>();
    private static ArrayList<Socio> socios = new ArrayList<>();
    private static ArrayList<Actividad_Deportiva> actividades = new ArrayList<>();
    private static ArrayList<Reserva> reservas = new ArrayList<>();

    // --- 3. INICIALIZACIÓN GLOBAL ---
    // ¡IMPORTANTE! Llama a este método al arrancar tu aplicación (en tu Main o Menú de Inicio)
   public static void inicializarTodo() {
        cargarAdmins();
        crearAdminJefe();
        cargarSocios();
    
        // FORZAMOS LOS DATOS DE PRUEBA PARA LIMPIAR ERRORES
        actividades.clear(); // Vaciamos por si acaso
        cargarDatosPrueba(); 
        guardarActividades(); // Esto creará el archivo .dat con las RUTAS NUEVAS
    
        cargarReservas();
        System.out.println("DEBUG: Actividades cargadas en memoria: " + actividades.size());
}

    // --- 4. MÉTODOS DE GUARDADO (Persistencia) ---
    public static void guardarSocios() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FICHERO_SOCIOS))) {
            oos.writeObject(socios);
        } catch (IOException e) { System.err.println("Error al guardar socios: " + e.getMessage()); }
    }

    public static void guardarAdmins() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FICHERO_ADMINS))) {
            oos.writeObject(admins);
        } catch (IOException e) { System.err.println("Error al guardar admins: " + e.getMessage()); }
    }

    public static void guardarActividades() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FICHERO_ACTIVIDADES))) {
            oos.writeObject(actividades);
        } catch (IOException e) { System.err.println("Error al guardar actividades: " + e.getMessage()); }
    }

    public static void guardarReservas() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FICHERO_RESERVAS))) {
            oos.writeObject(reservas);
        } catch (IOException e) { System.err.println("Error al guardar reservas: " + e.getMessage()); }
    }

    // --- 5. MÉTODOS DE CARGA ---
    public static void cargarSocios() {
        File f = new File(FICHERO_SOCIOS);
        if (!f.exists()) return;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            socios = (ArrayList<Socio>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) { System.err.println("Error al cargar socios: " + e.getMessage()); }
    }

    public static void cargarAdmins() {
        File f = new File(FICHERO_ADMINS);
        if (!f.exists()) return;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            admins = (ArrayList<Administrador>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) { System.err.println("Error al cargar admins: " + e.getMessage()); }
    }

    public static void cargarActividades() {
        File f = new File(FICHERO_ACTIVIDADES);
        if (!f.exists()) return;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            actividades = (ArrayList<Actividad_Deportiva>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) { System.err.println("Error al cargar actividades: " + e.getMessage()); }
    }

    public static void cargarReservas() {
        File f = new File(FICHERO_RESERVAS);
        if (!f.exists()) return;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            reservas = (ArrayList<Reserva>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) { System.err.println("Error al cargar reservas: " + e.getMessage()); }
    }

    // --- 6. DATOS DE PRUEBA INICIALES ---
    public static void cargarDatosPrueba() {
        Sala sala1 = new Sala("Sala Zen", 20);
        Sala sala2 = new Sala("Sala Fitness", 30);
        Horario h1 = new Horario("Lunes", "09:00", "10:30");
        Horario h2 = new Horario("Jueves", "18:00", "19:30");
        Horario h3 = new Horario("Martes", "11:00", "12:00");

        Tipo_Actividad yoga = Tipo_Actividad.YOGA; 
        Tipo_Actividad cardio = Tipo_Actividad.CARDIO;

        Actividad_Deportiva act1 = new Actividad_Deportiva("Yoga Matinal", yoga, sala1, h1, "Monitor 1", 0);
        act1.setImagen("/Imagenes/foto_yoga_1.jpg");
        actividades.add(act1);
        
        Actividad_Deportiva act2 = new Actividad_Deportiva("Yoga Tarde", yoga, sala1, h2, "Monitor 2", 0);
        act2.setImagen("/Imagenes/foto_yoga_2.jpg");
        actividades.add(act2);

        Actividad_Deportiva act3 = new Actividad_Deportiva("Zumba", cardio, sala2, h3, "Monitor 3", 0);
        actividades.add(act3);

        Actividad_Especial especial1 = new Actividad_Especial(15.0, "Sesión intensiva de fin de semana", "Masterclass Yoga", yoga, sala2, h2, "Monitor 4", 0);
        actividades.add(especial1);
    }

    // --- 7. LÓGICA DE NEGOCIO (RESERVAS Y FILTROS) ---
    
    public static String realizarReserva(Socio socio, Actividad_Deportiva actividad) {
        // 1. REGLA: Comprobar si ya está inscrito (Evitar duplicados)
        for (Reserva r : reservas) {
            if (r.getSocio().getCorreo().equals(socio.getCorreo()) && 
                r.getActividad().getTitulo().equals(actividad.getTitulo())) {
                return "DUPLICADO"; 
            }
        }

        // 2. REGLA: Comprobar si la sala está llena (Aforo)
        long inscritos = reservas.stream()
                .filter(r -> r.getActividad().getTitulo().equals(actividad.getTitulo()))
                .count();

        if (inscritos >= actividad.getSala().getAforo_maximo()) {
            return "LLENO";
        }

        // 3. EXITO: Crear la reserva y guardarla
        Reserva nueva = new Reserva(socio, actividad);
        reservas.add(nueva);
        
        guardarReservas(); // Guardado persistente instantáneo
        
        return "EXITO";
    }

    public static ArrayList<Actividad_Deportiva> filtrarActividades(String tipoBusqueda, String diaBusqueda, String monitorBusqueda) {
        ArrayList<Actividad_Deportiva> filtradas = new ArrayList<>();
        for (Actividad_Deportiva act : actividades) {
            boolean coincideTipo = tipoBusqueda.equals("Cualquiera") || act.getTipo_Actividad().toString().equalsIgnoreCase(tipoBusqueda);
            boolean coincideDia = diaBusqueda.equals("Cualquiera") || act.getHorario().getDia().equalsIgnoreCase(diaBusqueda);
            boolean coincideMonitor = monitorBusqueda.equals("Cualquiera") || act.getMonitor_asignado().equalsIgnoreCase(monitorBusqueda);
            if (coincideTipo && coincideDia && coincideMonitor) {
                filtradas.add(act);
            }
        }
        return filtradas;
    }
    
    public static ArrayList<Reserva> obtenerReservasPorSocio(Socio socio) {
        ArrayList<Reserva> filtradas = new ArrayList<>();
        for (Reserva r : reservas) {
        // Comparamos los correos para estar 100% seguros de que es el mismo socio
            if (r.getSocio().getCorreo().equalsIgnoreCase(socio.getCorreo())) {
                filtradas.add(r);
        }
    }
    return filtradas;
}

    // --- 8. GETTERS, SETTERS Y GESTIÓN BÁSICA ---
    public static void agregarSocio(Socio nuevo) {
        socios.add(nuevo);
        guardarSocios();
    }
    
    public static void agregarAdmin(Administrador nuevo) {
        admins.add(nuevo);
        guardarAdmins();
    }

    public static void agregar(Actividad_Deportiva actividad) {
        actividades.add(actividad);
        guardarActividades();
    }
    
    public static void eliminar(Actividad_Deportiva actividad) {
        actividades.remove(actividad);
        guardarActividades();
    }
    
    public static ArrayList<Actividad_Deportiva> getActividades() { return actividades; }
    public static void setActividades(ArrayList<Actividad_Deportiva> nuevasActividades) { actividades = nuevasActividades; guardarActividades(); }
    public static ArrayList<Socio> getSocios() { return socios; }
    public static ArrayList<Reserva> getReservas() { return reservas; }
    public static ArrayList<Administrador> getAdmins() { return admins; }
    
    public static void crearAdminJefe() {
        if (admins.isEmpty()) {
            Administrador jefe = new Administrador("admin@javafit.com", "admin");
            admins.add(jefe);
            guardarAdmins(); 
        }
    }
}