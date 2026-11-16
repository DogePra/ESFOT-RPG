package personajes;

public class Paladin extends Guerrero {

    private static final int COSTO_CURACION = 10;
    private static final int CURACION = 20;
    private static final int REDUCCION_ESCUDO = 5;

    private int energiaSagrada;
    private boolean escudoActivo;

    public Paladin(String nombre, int nivel, int vidaMaxima, int ataque, int defensa,
                   int fuerza, int energiaSagrada) {
        super(nombre, nivel, vidaMaxima, ataque, defensa, fuerza);
        setEnergiaSagrada(energiaSagrada);
        this.escudoActivo = false;
    }

    public int getEnergiaSagrada() {
        return energiaSagrada;
    }

    public void setEnergiaSagrada(int energiaSagrada) {
        if (energiaSagrada < 0) {
            throw new IllegalArgumentException("La energía sagrada no puede ser negativa.");
        }
        this.energiaSagrada = energiaSagrada;
    }

    public boolean isEscudoActivo() {
        return escudoActivo;
    }

    public void curar(Personaje objetivo) {
        if (objetivo == null) {
            throw new IllegalArgumentException("El objetivo no puede ser nulo.");
        }
        if (!estaVivo()) {
            System.out.println(getNombre() + " está derrotado y no puede curar.");
            return;
        }
        if (!objetivo.estaVivo()) {
            System.out.println(objetivo.getNombre() + " está derrotado y no puede ser curado.");
            return;
        }
        if (objetivo.getVida() == objetivo.getVidaMaxima()) {
            System.out.println(objetivo.getNombre() + " ya tiene la vida completa.");
            return;
        }
        if (energiaSagrada < COSTO_CURACION) {
            System.out.println(getNombre() + " no tiene energía sagrada suficiente (necesita "
                    + COSTO_CURACION + ", tiene " + energiaSagrada + ").");
            return;
        }
        energiaSagrada -= COSTO_CURACION;
        int nuevaVida = Math.min(objetivo.getVidaMaxima(), objetivo.getVida() + CURACION);
        int recuperada = nuevaVida - objetivo.getVida();
        objetivo.setVida(nuevaVida);
        System.out.println(getNombre() + " cura a " + objetivo.getNombre() + " (+" + recuperada
                + " de vida). Vida actual: " + objetivo.getVida() + "/" + objetivo.getVidaMaxima());
    }

    public void activarEscudo() {
        if (!estaVivo()) {
            System.out.println(getNombre() + " está derrotado y no puede activar el escudo.");
            return;
        }
        if (escudoActivo) {
            System.out.println("El escudo de " + getNombre() + " ya está activo.");
            return;
        }
        escudoActivo = true;
        System.out.println(getNombre() + " activa su escudo: bloqueará " + REDUCCION_ESCUDO
                + " puntos del próximo ataque.");
    }

    // Sobrescritura: el escudo reduce la potencia entrante y luego se reutiliza el método del padre
    @Override
    public void recibirDanio(int cantidad) {
        if (escudoActivo) {
            cantidad = Math.max(0, cantidad - REDUCCION_ESCUDO);
            escudoActivo = false;
            System.out.println("El escudo de " + getNombre() + " absorbe " + REDUCCION_ESCUDO
                    + " puntos y se desactiva.");
        }
        super.recibirDanio(cantidad);
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Energía sagrada: " + energiaSagrada);
        System.out.println("Escudo activo: " + (escudoActivo ? "sí" : "no"));
    }
}