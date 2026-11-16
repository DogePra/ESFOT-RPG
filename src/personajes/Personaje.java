package personajes;

public class Personaje {

    private String nombre;
    private int nivel;
    private int vida;
    private int vidaMaxima;
    private int ataque;
    private int defensa;

    public Personaje(String nombre, int nivel, int vidaMaxima, int ataque, int defensa) {
        setNombre(nombre);
        setNivel(nivel);
        setVidaMaxima(vidaMaxima);
        setAtaque(ataque);
        setDefensa(defensa);
        this.vida = vidaMaxima;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNivel() {
        return nivel;
    }

    public int getVida() {
        return vida;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setNombre(String nombre) {
        if ( nombre == null || nombre.trim().isEmpty()){
            throw new IllegalArgumentException("El nombre no puede ser vacio");
        }
        this.nombre = nombre;
    }

    public void setNivel(int nivel) {
        if(nivel <= 0 ){
            throw new IllegalArgumentException("El nivel no puede ser menor o igual a 0");
        }
        this.nivel = nivel;
    }


    public void setVidaMaxima(int vidaMaxima) {
        if (vidaMaxima <= 0){
            throw new IllegalArgumentException("La vida debe de ser positiva");}
        this.vidaMaxima = vidaMaxima;
    }

    public void setAtaque(int ataque) {
        if (ataque < 0 ){
            throw new IllegalArgumentException("El ataque no puede ser negativo");}
        this.ataque = ataque;
    }

    public void setDefensa(int defensa) {
        if(defensa < 0 ){
            throw new IllegalArgumentException("La defensa no puede ser negativa");
        }
        this.defensa = defensa;
    }


    public void setVida(int vida) {
        if (vida<0 || vida > vidaMaxima){
            throw new IllegalArgumentException("La vida no puede ser negativa, " +
                    "pero tampoco puede ser mas que "+vidaMaxima);
        }
        this.vida = vida;
    }

    public boolean estaVivo(){
        if (vida <= 0){
        return false;}else{
            return true;
        }
    }

    public void recibirDanio(int cantidad){
        int danio = Math.max(1, cantidad-defensa);
        vida = Math.max(0, vida-danio);
    }

    public void mostrarInformacion(){
        System.out.println("Nombre: "+nombre);
        System.out.println("Nivel: "+nivel);
        System.out.println("Vida: "+vida);
        System.out.println("Vida Maxima: "+vidaMaxima);
        System.out.println("Ataque: "+ataque);
        System.out.println("Defensa: "+defensa);
    }

    public void atacar(Personaje enemigo) {
        if (!estaVivo()) {
            System.out.println(nombre + " está derrotado y no puede atacar.");
            return;
        }
        if (!enemigo.estaVivo()) {
            System.out.println(enemigo.getNombre() + " ya está derrotado.");
            return;
        }
        System.out.println(nombre + " ataca a " + enemigo.getNombre());
        enemigo.recibirDanio(ataque);
    }

    public void atacar(Personaje enemigo, int bonificacion){
        if(!estaVivo()){
            System.out.println(nombre + " Esta derrotado no puede hacer el ataque.");
            return;}
        if (!enemigo.estaVivo()){
            System.out.println(enemigo.getNombre()+ " El enemigo ya esta derrotado.");
            return;
        }
        if(bonificacion<0){
            throw new IllegalArgumentException("El ataque de bonificacion no puede ser negativa");
        }else {
            System.out.println(nombre+" tira un ataque a "+ enemigo.getNombre());
            enemigo.recibirDanio(ataque+bonificacion);
        }

    }
}
