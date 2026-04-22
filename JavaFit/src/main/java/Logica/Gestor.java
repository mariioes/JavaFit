package Logica;

import java.util.ArrayList;
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