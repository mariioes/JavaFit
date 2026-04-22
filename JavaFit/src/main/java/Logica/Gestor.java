package Logica;

import Logica.Socio;
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
    
    public static void guardarAdmins() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FICHERO_ADMINS))) {
            oos.writeObject(admins); // Guardamos el ArrayList completo de golpe
        } catch (IOException e) {
            System.err.println("Error al guardar admins: " + e.getMessage());
        }
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
    
    public static void agregarAdmin(Socio nuevo) {
        socios.add(nuevo);
        guardarSocios(); // Cada vez que añadimos uno, actualizamos el fichero
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

