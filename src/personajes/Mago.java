package personajes;

public class Mago extends Personaje {

    private static final int COSTO_HECHIZO = 10;
    private static final int POTENCIA_HECHIZO = 20;

    private int mana;

    public Mago(String nombre, int nivel, int vidaMaxima, int ataque, int defensa, int mana) {
        super(nombre, nivel, vidaMaxima, ataque, defensa);
        setMana(mana);
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        if (mana < 0) {
            throw new IllegalArgumentException("El maná no puede ser negativo.");
        }
        this.mana = mana;
    }

    public void lanzarHechizo(Personaje enemigo) {
        if (enemigo == null) {
            throw new IllegalArgumentException("El enemigo no puede ser nulo.");
        }
        if (!estaVivo()) {
            System.out.println(getNombre() + " está derrotado y no puede lanzar hechizos.");
            return;
        }
        if (!enemigo.estaVivo()) {
            System.out.println(enemigo.getNombre() + " ya está derrotado.");
            return;
        }
        if (mana < COSTO_HECHIZO) {
            System.out.println(getNombre() + " no tiene maná suficiente (necesita " + COSTO_HECHIZO
                    + ", tiene " + mana + ").");
            return;
        }
        mana -= COSTO_HECHIZO;
        System.out.println(getNombre() + " lanza un hechizo a " + enemigo.getNombre()
                + " con potencia " + POTENCIA_HECHIZO);
        enemigo.recibirDanio(POTENCIA_HECHIZO);
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Maná: " + mana);
    }
}