package personajes;

public class Arquero extends Personaje {

    private int flechas;

    public Arquero(String nombre, int nivel, int vidaMaxima, int ataque, int defensa, int flechas) {
        super(nombre, nivel, vidaMaxima, ataque, defensa);
        setFlechas(flechas);
    }

    public int getFlechas() {
        return flechas;
    }

    public void setFlechas(int flechas) {
        if (flechas < 0) {
            throw new IllegalArgumentException("Las flechas no pueden ser negativas.");
        }
        this.flechas = flechas;
    }

    public void dispararFlecha(Personaje enemigo) {
        if (enemigo == null) {
            throw new IllegalArgumentException("El enemigo no puede ser nulo.");
        }
        if (!estaVivo()) {
            System.out.println(getNombre() + " está derrotado y no puede disparar.");
            return;
        }
        if (!enemigo.estaVivo()) {
            System.out.println(enemigo.getNombre() + " ya está derrotado.");
            return;
        }
        if (flechas <= 0) {
            System.out.println(getNombre() + " no tiene flechas.");
            return;
        }
        flechas--;
        atacar(enemigo, 5);   // potencia normal + 5
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Flechas: " + flechas);
    }
}