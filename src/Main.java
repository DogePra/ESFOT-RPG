import juego.GestorJuego;
import personajes.Arquero;
import personajes.Guerrero;
import personajes.Mago;
import personajes.Paladin;
import personajes.Personaje;

import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static final GestorJuego gestor = new GestorJuego();

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");
            switch (opcion) {
                case 1: crearGuerrero(); break;
                case 2: crearMago(); break;
                case 3: crearArquero(); break;
                case 4: crearPaladin(); break;
                case 5: gestor.mostrarTodos(); break;
                case 6: buscarPersonaje(); break;
                case 7: atacarPersonaje(); break;
                case 8: usarHabilidad(); break;
                case 9: simularCombate(); break;
                case 10: gestor.mostrarVivos(); break;
                case 11: System.out.println("¡Hasta pronto!"); break;
                default: System.out.println("Opción no válida. Elige un número del 1 al 11.");
            }
        } while (opcion != 11);
    }

    private static void mostrarMenu() {
        System.out.println();
        System.out.println("====================================");
        System.out.println(" ESFOT RPG");
        System.out.println("1. Crear Guerrero");
        System.out.println("2. Crear Mago");
        System.out.println("3. Crear Arquero");
        System.out.println("4. Crear Paladín");
        System.out.println("5. Mostrar personajes");
        System.out.println("6. Buscar personaje por nombre");
        System.out.println("7. Atacar a otro personaje");
        System.out.println("8. Utilizar habilidad especial");
        System.out.println("9. Simular combate por turnos");
        System.out.println("10. Mostrar personajes vivos");
        System.out.println("11. Salir");
    }


    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String linea = sc.nextLine().trim();
            try {
                return Integer.parseInt(linea);
            } catch (NumberFormatException e) {
                System.out.println("Ingresa un número entero válido.");
            }
        }
    }

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine().trim();
    }

    private static String pedirNombreNuevo() {
        String nombre = leerTexto("Nombre: ");
        if (nombre.isEmpty()) {
            System.out.println("El nombre no puede estar vacío.");
            return null;
        }
        if (gestor.existe(nombre)) {
            System.out.println("Ya existe un personaje con el nombre " + nombre + ".");
            return null;
        }
        return nombre;
    }

    private static Personaje pedirPersonaje(String mensaje) {
        String nombre = leerTexto(mensaje);
        Personaje p = gestor.buscar(nombre);
        if (p == null) {
            System.out.println("No existe ningún personaje llamado " + nombre + ".");
        }
        return p;
    }

    private static void crearGuerrero() {
        String nombre = pedirNombreNuevo();
        if (nombre == null) return;
        try {
            int nivel = leerEntero("Nivel: ");
            int vidaMax = leerEntero("Vida máxima: ");
            int ataque = leerEntero("Ataque: ");
            int defensa = leerEntero("Defensa: ");
            int fuerza = leerEntero("Fuerza: ");
            gestor.registrar(new Guerrero(nombre, nivel, vidaMax, ataque, defensa, fuerza));
        } catch (IllegalArgumentException e) {
            System.out.println("No se pudo crear el personaje: " + e.getMessage());
        }
    }

    private static void crearMago() {
        String nombre = pedirNombreNuevo();
        if (nombre == null) return;
        try {
            int nivel = leerEntero("Nivel: ");
            int vidaMax = leerEntero("Vida máxima: ");
            int ataque = leerEntero("Ataque: ");
            int defensa = leerEntero("Defensa: ");
            int mana = leerEntero("Maná: ");
            gestor.registrar(new Mago(nombre, nivel, vidaMax, ataque, defensa, mana));
        } catch (IllegalArgumentException e) {
            System.out.println("No se pudo crear el personaje: " + e.getMessage());
        }
    }

    private static void crearArquero() {
        String nombre = pedirNombreNuevo();
        if (nombre == null) return;
        try {
            int nivel = leerEntero("Nivel: ");
            int vidaMax = leerEntero("Vida máxima: ");
            int ataque = leerEntero("Ataque: ");
            int defensa = leerEntero("Defensa: ");
            int flechas = leerEntero("Flechas: ");
            gestor.registrar(new Arquero(nombre, nivel, vidaMax, ataque, defensa, flechas));
        } catch (IllegalArgumentException e) {
            System.out.println("No se pudo crear el personaje: " + e.getMessage());
        }
    }

    private static void crearPaladin() {
        String nombre = pedirNombreNuevo();
        if (nombre == null) return;
        try {
            int nivel = leerEntero("Nivel: ");
            int vidaMax = leerEntero("Vida máxima: ");
            int ataque = leerEntero("Ataque: ");
            int defensa = leerEntero("Defensa: ");
            int fuerza = leerEntero("Fuerza: ");
            int energia = leerEntero("Energía sagrada: ");
            gestor.registrar(new Paladin(nombre, nivel, vidaMax, ataque, defensa, fuerza, energia));
        } catch (IllegalArgumentException e) {
            System.out.println("No se pudo crear el personaje: " + e.getMessage());
        }
    }


    private static void buscarPersonaje() {
        Personaje p = pedirPersonaje("Nombre a buscar: ");
        if (p != null) {
            System.out.println("--- " + p.getClass().getSimpleName() + " ---");
            p.mostrarInformacion();
        }
    }

    private static void atacarPersonaje() {
        Personaje atacante = pedirPersonaje("Nombre del atacante: ");
        if (atacante == null) return;
        Personaje objetivo = pedirPersonaje("Nombre del objetivo: ");
        if (objetivo == null) return;
        gestor.atacar(atacante, objetivo);
    }

    private static void usarHabilidad() {
        Personaje p = pedirPersonaje("Nombre del personaje que usará la habilidad: ");
        if (p == null) return;

        // IMPORTANTE: Paladin se revisa ANTES que Guerrero (un Paladin también es un Guerrero)
        if (p instanceof Paladin) {
            Paladin paladin = (Paladin) p;
            System.out.println("1. Curar a un personaje");
            System.out.println("2. Activar escudo");
            int op = leerEntero("Elige la habilidad: ");
            if (op == 1) {
                Personaje objetivo = pedirPersonaje("Nombre del personaje a curar: ");
                if (objetivo != null) {
                    paladin.curar(objetivo);
                }
            } else if (op == 2) {
                paladin.activarEscudo();
            } else {
                System.out.println("Habilidad no válida.");
            }
        } else if (p instanceof Mago) {
            Mago mago = (Mago) p;
            Personaje objetivo = pedirPersonaje("Nombre del objetivo del hechizo: ");
            if (objetivo == null) return;
            if (objetivo == mago) {
                System.out.println("Un mago no puede lanzarse un hechizo a sí mismo.");
                return;
            }
            int vidaAntes = objetivo.getVida();
            mago.lanzarHechizo(objetivo);
            mostrarResultado(objetivo, vidaAntes);
        } else if (p instanceof Arquero) {
            Arquero arquero = (Arquero) p;
            Personaje objetivo = pedirPersonaje("Nombre del objetivo del disparo: ");
            if (objetivo == null) return;
            if (objetivo == arquero) {
                System.out.println("Un arquero no puede dispararse a sí mismo.");
                return;
            }
            int vidaAntes = objetivo.getVida();
            arquero.dispararFlecha(objetivo);
            mostrarResultado(objetivo, vidaAntes);
        } else {
            System.out.println(p.getNombre() + " (" + p.getClass().getSimpleName()
                    + ") no tiene habilidad especial.");
        }
    }

    private static void mostrarResultado(Personaje objetivo, int vidaAntes) {
        int danio = vidaAntes - objetivo.getVida();
        if (danio > 0) {
            System.out.println("Daño efectivo: " + danio + " | Vida de " + objetivo.getNombre()
                    + ": " + objetivo.getVida() + "/" + objetivo.getVidaMaxima());
        }
    }

    private static void simularCombate() {
        Personaje a = pedirPersonaje("Nombre del primer personaje: ");
        if (a == null) return;
        Personaje b = pedirPersonaje("Nombre del segundo personaje: ");
        if (b == null) return;
        gestor.combatir(a, b);
    }
}