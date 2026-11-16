package juego;

import personajes.Personaje;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestorJuego {

    private final List<Personaje> personajes = new ArrayList<>();
    private final Map<String, Personaje> registro = new HashMap<>();

    public boolean existe(String nombre) {
        return registro.containsKey(nombre);
    }

    // Mantiene las dos colecciones sincronizadas
    public boolean registrar(Personaje p) {
        if (registro.containsKey(p.getNombre())) {
            System.out.println("Ya existe un personaje con el nombre " + p.getNombre() + ".");
            return false;
        }
        personajes.add(p);
        registro.put(p.getNombre(), p);
        System.out.println("Personaje registrado: " + p.getNombre()
                + " (" + p.getClass().getSimpleName() + ")");
        return true;
    }

    public Personaje buscar(String nombre) {
        return registro.get(nombre);
    }

    // Polimorfismo: cada personaje ejecuta SU versión de mostrarInformacion()
    public void mostrarTodos() {
        if (personajes.isEmpty()) {
            System.out.println("No hay personajes registrados.");
            return;
        }
        for (Personaje p : personajes) {
            System.out.println("--- " + p.getClass().getSimpleName() + " ---");
            p.mostrarInformacion();
            System.out.println();
        }
    }

    public void mostrarVivos() {
        boolean hayVivos = false;
        for (Personaje p : personajes) {
            if (p.estaVivo()) {
                System.out.println("--- " + p.getClass().getSimpleName() + " ---");
                p.mostrarInformacion();
                System.out.println();
                hayVivos = true;
            }
        }
        if (!hayVivos) {
            System.out.println("No hay personajes vivos.");
        }
    }

    public void atacar(Personaje atacante, Personaje objetivo) {
        if (atacante == objetivo) {
            System.out.println("Un personaje no puede atacarse a sí mismo.");
            return;
        }
        int vidaAntes = objetivo.getVida();
        atacante.atacar(objetivo);
        int danio = vidaAntes - objetivo.getVida();
        if (danio > 0) {
            System.out.println("Daño efectivo: " + danio + " | Vida de " + objetivo.getNombre()
                    + ": " + objetivo.getVida() + "/" + objetivo.getVidaMaxima());
        }
    }

    public void combatir(Personaje a, Personaje b) {
        if (a == b) {
            System.out.println("Debes elegir dos personajes distintos.");
            return;
        }
        if (!a.estaVivo() || !b.estaVivo()) {
            System.out.println("No se puede iniciar un combate con un personaje derrotado.");
            return;
        }

        System.out.println("===== COMBATE RPG =====");
        System.out.println(a.getNombre() + " (" + a.getClass().getSimpleName() + ") vs "
                + b.getNombre() + " (" + b.getClass().getSimpleName() + ")");

        Personaje atacante = a;
        Personaje defensor = b;
        int turno = 1;

        while (a.estaVivo() && b.estaVivo()) {
            System.out.println("--- Turno " + turno + " ---");
            int vidaAntes = defensor.getVida();
            atacante.atacar(defensor);   // polimorfismo: el Guerrero/Paladin suma su fuerza
            int danio = vidaAntes - defensor.getVida();
            System.out.println("Daño efectivo: " + danio + " | Vida de " + defensor.getNombre()
                    + ": " + defensor.getVida() + "/" + defensor.getVidaMaxima());

            Personaje temporal = atacante;
            atacante = defensor;
            defensor = temporal;
            turno++;
        }

        Personaje ganador = a.estaVivo() ? a : b;
        System.out.println("GANADOR: " + ganador.getNombre());
    }
}