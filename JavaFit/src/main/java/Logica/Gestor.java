package Logica;

/**
 * Importamos los ArrayList y Collectors para guardar la información de socios, admins, reservas y actividades.
 * 
 */
import java.util.ArrayList;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

/**
 * Clase que se encarga de toda la lógica funcional del sistema JavaFit.
 * Contiene los métodos de arranque, escritura y lectura de ficheros, se encarga de la persistencia de datos (ficheros.dat).
 * Es la clase base para toda la parte de lógica y todos sus métodos y atributos son estáticos, no hace falta instanciarla.
 */
public class Gestor {
    
    // --- 1. ARCHIVOS DE GUARDADO ---
    /** Ruta del fichero donde se guardan los administradores.*/
    private static final String FICHERO_ADMINS = "admins.dat";
    
    /** Ruta del fichero donde se guardan los socios.*/
    private static final String FICHERO_SOCIOS = "socios.dat";
    
    /** Ruta del fichero donde se guardan las actividades.*/
    private static final String FICHERO_ACTIVIDADES = "actividades.dat";
    
    /** Ruta del fichero donde se guardan las reservas.*/
    private static final String FICHERO_RESERVAS = "reservas.dat";

    // --- 2. LISTAS EN MEMORIA ---
    /** Lista de administradores cargados en memoria. */
    private static ArrayList<Administrador> admins = new ArrayList<>();
    
    /** Lista de socios cargados en memoria. */
    private static ArrayList<Socio> socios = new ArrayList<>();
    
    /** Lista de actividades cargadas en memoria. */
    private static ArrayList<Actividad_Deportiva> actividades = new ArrayList<>();
    
    /** Lista de reservas cargadas en memoria. */
    private static ArrayList<Reserva> reservas = new ArrayList<>();

    // --- 3. INICIALIZACIÓN GLOBAL ---
    /**
     * Inicializa el sistema al arrancar la aplicación.
     * Carga administradores, socios, actividades y reservas desde sus ficheros.
     * Si no existe el fichero de actividades, carga los datos de prueba iniciales.
     */
   public static void inicializarTodo() {
        cargarAdmins();
        crearAdminJefe();
        cargarSocios();
        
        File f = new File(FICHERO_ACTIVIDADES);
        if (!f.exists()) {
            cargarDatosPrueba();
            guardarActividades();
        } else {
            cargarActividades();
        if (actividades.isEmpty()) { // solo si la carga falló
            cargarDatosPrueba();
            guardarActividades();
    }
        cargarReservas();
        System.out.println("DEBUG: Actividades cargadas en memoria: " + actividades.size());
        System.out.println("DEBUG - Reservas cargadas: " + reservas.size());
        System.out.println("Administradores cargados en memoria: "+admins.size());
}
   }

    // --- 4. MÉTODOS DE GUARDADO (Persistencia) ---
    /**
     * Guarda la lista de socios en el fichero socios.dat.
     */
    public static void guardarSocios() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FICHERO_SOCIOS))) {
            oos.writeObject(socios);
        } catch (IOException e) { System.err.println("Error al guardar socios: " + e.getMessage()); }
    }

    /**
     * Guarda la lista de admins en el fichero admins.dat.
     */
    public static void guardarAdmins() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FICHERO_ADMINS))) {
            oos.writeObject(admins);
        } catch (IOException e) { System.err.println("Error al guardar admins: " + e.getMessage()); }
    }

    /**
     * Guarda la lista de actividades en el fichero actividades.dat.
     */
    public static void guardarActividades() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FICHERO_ACTIVIDADES))) {
            oos.writeObject(actividades);
        } catch (IOException e) { System.err.println("Error al guardar actividades: " + e.getMessage()); }
    }

    /**
     * Guarda la lista de reservas en el fichero reservas.dat.
     */
    public static void guardarReservas() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FICHERO_RESERVAS))) {
            oos.writeObject(reservas);
        } catch (IOException e) { System.err.println("Error al guardar reservas: " + e.getMessage()); }
    }

    // --- 5. MÉTODOS DE CARGA ---
    
    /**
     * Carga la lista de socios desde el fichero socios.dat.
     */
    public static void cargarSocios() {
        File f = new File(FICHERO_SOCIOS);
        if (!f.exists()) return;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            socios = (ArrayList<Socio>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) { System.err.println("Error al cargar socios: " + e.getMessage()); }
    }

    /**
     * Carga la lista de admins desde el fichero admins.dat.
     */
    public static void cargarAdmins() {
        File f = new File(FICHERO_ADMINS);
        if (!f.exists()) return;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            admins = (ArrayList<Administrador>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) { System.err.println("Error al cargar admins: " + e.getMessage()); }
    }

    /**
     * Carga la lista de actividades desde el fichero actividades.dat.
     * Tras cargar, recarga las imágenes de cada actividad desde su ruta.
     */
    public static void cargarActividades() {
        File f = new File(FICHERO_ACTIVIDADES);
        if (!f.exists()) return;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            actividades = (ArrayList<Actividad_Deportiva>) ois.readObject();
            for (Actividad_Deportiva act : actividades) {
                //act.recargarImagen();
        }
        System.out.println("DEBUG - cargarActividades OK: " + actividades.size()); // ✅
    } catch (IOException | ClassNotFoundException e) {
        System.err.println("Error al cargar actividades: " + e.getMessage()); // ✅ mira este mensaje
    }
    }

    /**
     * Carga la lista de reservas desde el fichero reservas.dat.
     */
    public static void cargarReservas() {
        File f = new File(FICHERO_RESERVAS);
        if (!f.exists()) return;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            reservas = (ArrayList<Reserva>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) { System.err.println("Error al cargar reservas: " + e.getMessage()); }
    }

    // --- 6. DATOS DE PRUEBA INICIALES ---
    /**
     * Carga un conjunto de actividades de prueba en memoria, si se elimina una actividad no se reescribe.
     * Solo se ejecuta la primera vez que arranca la aplicación, cuando no existe el fichero de actividades.
     */
    public static void cargarDatosPrueba() {
        Sala sala1 = new Sala("Sala Zen", 20);
        Sala sala2 = new Sala("Sala Fitness", 30);
        Sala sala3 = new Sala("Piscina climatizada", 18);
        Sala sala4 = new Sala("Sala de elípticas", 35);
        Sala sala5 = new Sala("Sala adaptada MR", 10);
        Sala sala6 = new Sala("Sala Fitness reducida", 5);
        Horario h1 = new Horario("Lunes", "09:00", "10:30");
        Horario h2 = new Horario("Jueves", "18:00", "19:30");
        Horario h3 = new Horario("Martes", "11:00", "12:00");

        Tipo_Actividad yoga = Tipo_Actividad.YOGA; 
        Tipo_Actividad cardio = Tipo_Actividad.CARDIO;

        Actividad_Deportiva act1 = new Actividad_Deportiva("Yoga Matinal", yoga, sala1, h1, "Iago Palazón", 0, "/imagenes/foto_yoga_1.jpg");
        actividades.add(act1);
        
        Actividad_Deportiva act2 = new Actividad_Deportiva("Yoga Tarde", yoga, sala1, h2, "Lorena Menéndez", 0, "/imagenes/foto_yoga_2.jpg");
        actividades.add(act2);

        Actividad_Deportiva act3 = new Actividad_Deportiva("Zumba", cardio, sala2, h3, "Paco Díaz", 0, "/imagenes/foto_zumba_1.jpg");
        actividades.add(act3);

        Actividad_Especial especial1 = new Actividad_Especial(15.0, "Sesión intensiva de fin de semana", "Masterclass Yoga", yoga, sala2, h2, "Natalia Romareda", 0, "/imagenes/foto_yoga_especial_1");
        actividades.add(especial1);
    }

    // --- 7. LÓGICA DE NEGOCIO (RESERVAS Y FILTROS) ---
    
    /**
     * Intenta realizar una reserva para un socio en una actividad.
     * Comprueba que no haya duplicados y que haya aforo disponible.
     * @param socio Socio que quiere reservar.
     * @param actividad Actividad que se quiere reservar.
     * @return "DUPLICADO" si ya tiene reserva, "LLENO" si no hay plazas, "EXITO" si se realiza correctamente.
     */
    public static String realizarReserva(Socio socio, Actividad_Deportiva actividad) {
        for (Reserva r : reservas) {
            if (r.getSocio().getCorreo().equals(socio.getCorreo()) && 
                r.getActividad().getTitulo().equals(actividad.getTitulo())) {
                return "DUPLICADO"; 
            }
        }

        long inscritos = reservas.stream()
                .filter(r -> r.getActividad().getTitulo().equals(actividad.getTitulo()))
                .count();

        if (inscritos >= actividad.getSala().getAforo_maximo()) {
            return "LLENO";
        }

        Reserva nueva = new Reserva(socio, actividad);
        reservas.add(nueva);
        
        guardarReservas();
        generarReciboTxt(nueva);
        return "EXITO";
    }
    
    /**
     * Cancela una reserva existente de un socio para una actividad.
     * @param socio Socio que cancela la reserva.
     * @param actividad Actividad de la que se cancela la reserva.
     * @return "EXITO" si se canceló correctamente, "ERROR" si no se encontró la reserva.
     */
    public static String cancelarReserva(Socio socio, Actividad_Deportiva actividad) {
        int indiceABorrar = -1;
        for (int i = 0; i < reservas.size(); i++) {
        Reserva r = reservas.get(i);
        
        if (r.getSocio().getCorreo().equals(socio.getCorreo()) && r.getActividad().getTitulo().equals(actividad.getTitulo())) {
            indiceABorrar = i;
            break;
        }
    }
    if (indiceABorrar != -1) {
        reservas.remove(indiceABorrar);
        guardarReservas();
        return "EXITO";
    } else {
        return "ERROR";
    }
}

    /**
     * Filtra las actividades según tipo, día y monitor.
     * @param tipoBusqueda Tipo de actividad a filtrar, o "Cualquiera" para no filtrar.
     * @param diaBusqueda Día de la semana a filtrar, o "Cualquiera" para no filtrar.
     * @param monitorBusqueda Monitor a filtrar, o "Cualquiera" para no filtrar.
     * @return Lista de actividades que cumplen los filtros.
     */
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
    
    public static ArrayList<Socio> filtrarSocios(String nombre, String correo, boolean soloVip) {
        return socios.stream()
                .filter(s -> nombre.isEmpty() || (s.getNombre() != null && s.getNombre().toLowerCase().contains(nombre.toLowerCase())))
                .filter(s -> correo.isEmpty() || (s.getCorreo() != null && s.getCorreo().toLowerCase().contains(correo.toLowerCase())))
                .filter(s -> !soloVip || s.esVip())
                .collect(Collectors.toCollection(ArrayList::new));
    }
    
    /**
     * Busca actividades por nombre, tipo y día usando streams.
     * @param nombreBusqueda Texto a buscar en el título, puede estar vacío para no filtrar.
     * @param tipoBusqueda Tipo de actividad, o "Cualquiera" para no filtrar.
     * @param diaBusqueda Día de la semana, o "Cualquiera" para no filtrar.
     * @return Lista de actividades que coinciden con la búsqueda.
     */
    public static ArrayList<Actividad_Deportiva> buscarActividadesPorNombre(String nombreBusqueda, String tipoBusqueda, String diaBusqueda) {
        return actividades.stream()
        .filter(act -> nombreBusqueda.isEmpty() || act.getTitulo().toLowerCase().contains(nombreBusqueda.toLowerCase()))
        .filter(act -> tipoBusqueda.equals("Cualquiera") || act.getTipo_Actividad().toString().equalsIgnoreCase(tipoBusqueda))
        .filter(act -> diaBusqueda.equals("Cualquiera") || act.getHorario().getDia().equalsIgnoreCase(diaBusqueda))
        .collect(Collectors.toCollection(ArrayList::new));
    }
    
    /**
     * Elimina una reserva de un socio para una actividad concreta.
     * @param socio Socio cuya reserva se quiere eliminar.
     * @param actividad Actividad de la que se elimina la reserva.
     * @return true si se eliminó correctamente, false si no se encontró.
     */
    public static boolean eliminarReserva(Socio socio, Actividad_Deportiva actividad) {
        Reserva aEliminar = null;
        for (Reserva r : reservas) {
            if (r.getSocio().getCorreo().equalsIgnoreCase(socio.getCorreo()) && r.getActividad().getTitulo().equals(actividad.getTitulo())) {
                aEliminar = r;
                break;
            }
        }
        if (aEliminar != null) {
            reservas.remove(aEliminar);
            guardarReservas();
            return true;
        }
        return false;
}
    
    /**
     * Devuelve todas las reservas asociadas a un socio concreto.
     * @param socio Socio del que se quieren obtener las reservas.
     * @return Lista de reservas del socio.
     */
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
    /**
     * Añade un nuevo socio al sistema y lo guarda en el fichero.
     * @param nuevo Socio a añadir.
     */
    public static void agregarSocio(Socio nuevo) {
        socios.add(nuevo);
        guardarSocios();
    }
    
    /**
     * Añade un nuevo admin al sistema y lo guarda en el fichero.
     * @param nuevo Administrador a añadir.
     */
    public static void agregarAdmin(Administrador nuevo) {
        admins.add(nuevo);
        guardarAdmins();
    }

    /**
     * Añade una nueva actividad al sistema y la guarda en el fichero.
     * @param actividad Actividad a añadir.
     */
    public static void agregar(Actividad_Deportiva actividad) {
        actividades.add(actividad);
        guardarActividades();
    }
    
    /**
     * Elimina una actividad del sistema y reescribe el fichero.
     * @param actividad Actividad a eliminar.
     */
    public static void eliminar(Actividad_Deportiva actividad) {
        actividades.remove(actividad);
        guardarActividades();
    }
    
    /**
     * Devuelve la lista de todas las actividad disponibles.
     * @return Lista de todas las actividades deportivas. 
     */
    public static ArrayList<Actividad_Deportiva> getActividades() { return actividades; }
    
    /**
     * Reemplaza la lista de actividades y la guarda en el fichero.
     * @param nuevasActividades Nueva lista de actividades.
     */
    public static void setActividades(ArrayList<Actividad_Deportiva> nuevasActividades) { actividades = nuevasActividades; guardarActividades(); }
    
    /**
     * Devuelve la lista de todos los socios registrados.
     * @return Lista de todos los socios registrados. 
    */
    public static ArrayList<Socio> getSocios() { return socios; }
    
    /**
     * Devuelve la lista de todas las reservas actualmente activas.
     * @return Lista de todas las reservas activas. 
    */
    public static ArrayList<Reserva> getReservas() { return reservas; }
    
    /** 
     * Devuelve la lista de los administradores.
     * @return Lista de todos los administradores. 
     */
    public static ArrayList<Administrador> getAdmins() { return admins; }
    
    /**
     * Crea el administrador principal del sistema si no existe ninguno.
     * El administrador por defecto tiene correo "admin@javafit.com" y contraseña "admin".
     */
    public static void crearAdminJefe() {
        if (admins.isEmpty()) {
            Administrador jefe = new Administrador("admin@javafit.com", "admin");
            admins.add(jefe);
            guardarAdmins(); 
        }
    }
    
    public static void generarReciboTxt(Logica.Reserva reserva) {
        // Creamos un nombre de archivo único para que no se sobrescriban. 
        // Ejemplo: Recibo_juan_Yoga.txt
        String nombreFichero = "Recibo_" + reserva.getSocio().getNombre().replace(" ", "") + "_" 
                             + reserva.getActividad().getTitulo().replace(" ", "") + ".txt";

        // Usamos try-with-resources para que el archivo se cierre automáticamente al terminar
        try (PrintWriter out = new PrintWriter(new FileWriter(nombreFichero))) {
            
            out.println("=========================================");
            out.println("         RECIBO DE RESERVA - JAVAFIT     ");
            out.println("=========================================");
            out.println("Fecha de emisión: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
            out.println("-----------------------------------------");
            out.println("DATOS DEL SOCIO:");
            out.println("  Nombre: " + reserva.getSocio().getNombre());
            out.println("  Correo: " + reserva.getSocio().getCorreo());
            out.println("  VIP: "+reserva.getSocio().esVip());
            // out.println("  Tipo: " + (reserva.getSocio().isVip() ? "Socio VIP" : "Socio Básico"));
            out.println("-----------------------------------------");
            out.println("DATOS DE LA ACTIVIDAD:");
            out.println("  Actividad: " + reserva.getActividad().getTitulo());
            out.println("  Sala:      " + reserva.getActividad().getSala().getNombre());
            out.println("  Día:       " + reserva.getActividad().getHorario().getDia());
            out.println("  Horario:   " + reserva.getActividad().getHorario().getHora_inicio() + " - " + reserva.getActividad().getHorario().getHora_final());
            out.println("-----------------------------------------");
            
            // Comprobamos si es especial para poner el precio
            if (reserva.getActividad() instanceof Logica.Actividad_Especial) {
                out.println("Tipo de clase: ACTIVIDAD ESPECIAL");
                out.println("TOTAL CARGADO EN TARJETA: " + reserva.getPrecio_total() + " euros");
            } else {
                out.println("Tipo de clase: ACTIVIDAD ESTÁNDAR");
                out.println("Precio: INCLUIDO EN LA CUOTA MENSUAL");
            }
            
            out.println("=========================================");
            out.println("    ¡Gracias por confiar en JavaFit!     ");
            out.println("=========================================");

            System.out.println("Recibo generado con éxito: " + nombreFichero);

        } catch (IOException e) {
            System.err.println("Error crítico al generar el recibo .txt: " + e.getMessage());
        }
    }
}