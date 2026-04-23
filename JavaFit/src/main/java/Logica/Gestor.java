package Logica;

import java.util.ArrayList;
import Logica.Actividad_Deportiva;
import java.io.*;

public class Gestor {
    private static final String FICHERO_ADMINS = "admins.dat";
    private static final String FICHERO_SOCIOS = "socios.dat";
    private static ArrayList<Administrador> admins = new ArrayList<>();
    private static ArrayList<Actividad_Deportiva> actividades = new ArrayList<>();
    private static ArrayList<Socio> socios = new ArrayList<>();
    private static ArrayList<Reserva> reservas = new ArrayList<>();

    public static void guardarSocios() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FICHERO_SOCIOS))) {
            oos.writeObject(socios); // Guardamos el ArrayList completo de golpe
        } catch (IOException e) {
            System.err.println("Error al guardar socios: " + e.getMessage());
        }
    }
    
    // --- CÓDIGO TUYO QUE HEMOS MANTENIDO ---
    public static void guardarAdmins() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FICHERO_ADMINS))) {
            oos.writeObject(admins); // Guardamos el ArrayList completo de golpe
        } catch (IOException e) {
            System.err.println("Error al guardar admins: " + e.getMessage());
        }
    }
    
    // --- CÓDIGO DE TU AMIGO QUE HEMOS MANTENIDO ---
    public static ArrayList<Actividad_Deportiva> filtrarActividades(String tipoBusqueda, String diaBusqueda, String monitorBusqueda) {
        ArrayList<Actividad_Deportiva> filtradas = new ArrayList<>();
        
        for (Actividad_Deportiva act : actividades) {
            
            // Filtro 1: Tipo (Yoga, Musculación...)
            boolean coincideTipo = tipoBusqueda.equals("Cualquiera") || 
                                   act.getTipo_Actividad().toString().equalsIgnoreCase(tipoBusqueda);
            
            // Filtro 2: DÍA (Si busca "Cualquiera", entran todos los días de la semana)
            boolean coincideDia = diaBusqueda.equals("Cualquiera") || 
                                  act.getHorario().getDia().equalsIgnoreCase(diaBusqueda);
            
            // Filtro 3: MONITOR
            boolean coincideMonitor = monitorBusqueda.equals("Cualquiera") || 
                                      act.getMonitor_asignado().equalsIgnoreCase(monitorBusqueda);
            
            // Solo si la actividad supera los 3 filtros se añade a la lista de resultados
            if (coincideTipo && coincideDia && coincideMonitor) {
                filtradas.add(act);
            }
        }
        return filtradas;
    }

    public static void cargarSocios() {
        File f = new File(FICHERO_SOCIOS);
        
        if (!f.exists()) {
            System.out.println("--- ALERTA: El archivo " + FICHERO_SOCIOS + " NO existe en el disco ---");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            Object objetoLeido = ois.readObject();
            if (objetoLeido instanceof ArrayList) {
                socios = (ArrayList<Socio>) objetoLeido;
                System.out.println("--- ÉXITO: Cargados " + socios.size() + " socios desde el disco ---");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("--- ERROR CRÍTICO AL CARGAR: " + e.getMessage() + " ---");
            e.printStackTrace();
        }
    }

    public static void cargarAdmins() {
        File f = new File(FICHERO_ADMINS);
        
        if (!f.exists()) {
            System.out.println("--- ALERTA: El archivo " + FICHERO_ADMINS + " NO existe en el disco ---");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            Object objetoLeido = ois.readObject();
            if (objetoLeido instanceof ArrayList) {
                admins = (ArrayList<Administrador>) objetoLeido;
                System.out.println("--- ÉXITO: Cargados " + admins.size() + " admins desde el disco ---");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("--- ERROR CRÍTICO AL CARGAR: " + e.getMessage() + " ---");
            e.printStackTrace();
        }
    }
    
    // Prueba inicial:
   public static void cargarDatosPrueba() {
    // 1. Creamos algunas Salas
    Sala sala1 = new Sala("Sala Zen", 20);
    Sala sala2 = new Sala("Sala Fitness", 30);
    
    // 2. Creamos algunos Horarios
    Horario h1 = new Horario("Lunes", "09:00", "10:30");
    Horario h2 = new Horario("Jueves", "18:00", "19:30");
    Horario h3 = new Horario("Martes", "11:00", "12:00");
    
    // 3. Creamos tipos de actividad
    Tipo_Actividad yoga = Tipo_Actividad.YOGA; 
    Tipo_Actividad cardio = Tipo_Actividad.CARDIO;

    // 4. Añadimos Actividades Deportivas normales
    // Creamos la primera actividad de yoga
    Actividad_Deportiva act1 = new Actividad_Deportiva("Yoga Matinal", yoga, sala1, h1, "Monitor 1", 0);
    act1.setImagen("/Imagenes_Actividades/foto_yoga_1.jpg"); // Se lo ponemos a act1
    actividades.add(act1);
    
    // Creamos la segunda actividad de yoga
    Actividad_Deportiva act2 = new Actividad_Deportiva("Yoga Tarde", yoga, sala1, h2, "Monitor 2", 0);
    act2.setImagen("/Imagenes_Actividades/foto_yoga_2.jpg"); // Otra foto diferente
    actividades.add(act2);

    // Creamos zumba
    Actividad_Deportiva act3 = new Actividad_Deportiva("Zumba", cardio, sala2, h3, "Monitor 3", 0);
    // act3.setImagen("/Imagenes_Actividades/zumba.jpg"); // Opcional si tienes la foto
    actividades.add(act3);

    // 5. Añadimos una Actividad Especial
    // Nota: Asegúrate de que el constructor de Actividad_Especial coincide con este orden
    Actividad_Especial especial1 = new Actividad_Especial(15.0, "Sesión intensiva de fin de semana", "Masterclass Yoga", yoga, sala2, h2, "Monitor 4", 0);
    especial1.setImagen("/Imagenes_Actividades/foto_yoga_1.jpg");
    actividades.add(especial1);
}

    public static void agregarSocio(Socio nuevo) {
        socios.add(nuevo);
        guardarSocios(); // Cada vez que añadimos uno, actualizamos el fichero
    }
    
    // --- BUG CORREGIDO AQUÍ: Antes pedía Socio y guardaba en socios ---
    public static void agregarAdmin(Administrador nuevo) {
        admins.add(nuevo);
        guardarAdmins(); // Guardamos en el fichero de admins
    }
    
    public static ArrayList<Actividad_Deportiva> getActividades() {
        return actividades;
    }

    public static void setActividades(ArrayList<Actividad_Deportiva> nuevasActividades) {
        actividades = nuevasActividades;
    }
    
    public static void agregar(Actividad_Deportiva actividad) {
        actividades.add(actividad);
    }
    
    public static void eliminar(Actividad_Deportiva actividad) {
        actividades.remove(actividad);
    }
    
    public static ArrayList<Socio> getSocios() {
        return socios;
    }

    public static ArrayList<Reserva> getReservas() {
        return reservas;
    }

    public static ArrayList<Administrador> getAdmins() {
        return admins;
    }
    
    public static void crearAdminJefe() {
        // Solo lo creamos si la lista está vacía
        if (admins.isEmpty()) {
            System.out.println("No hay administradores. Creando Admin Jefe por defecto...");
            
            // Creamos el objeto (usa el correo y clave que prefieras)
            Administrador jefe = new Administrador("admin@javafit.com", "admin");
            
            // Lo añadimos a la lista
            admins.add(jefe);
            
            // Guardamos la lista en el archivo inmediatamente
            guardarAdmins(); 
        }
    }
}