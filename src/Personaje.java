import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Clase para representar a un peleador de La Rosa. 
 * Cada personaje tiene un nombre, franquicia, puntos de vida (hp), un poder actual y una lista de poderes disponibles.
 */
public abstract class Personaje {
    
    protected String nombre;
    protected String franquicia;
    protected int hp = 100;
    protected EstrategiaPoder poderActual;
    protected List<EstrategiaPoder> poderesDisponibles = new ArrayList<>();
    
    /**
     * El peleador ataca a un objetivo usando su poder actual. Se notifica a los espectadores sobre el ataque y si el objetivo puede defenderse.
     * @param objetivo a quien se va a atacar.
     * @param combate para notificar a los espectadores sobre el ataque.
     */
    public void atacar(Personaje objetivo, Sujeto combate) {
        if (!this.estaVivo() || !objetivo.estaVivo()) return;
        
        int danioGenerado = poderActual.calcularDanioAtaque();
        combate.notificarObservador(this.nombre + " " + poderActual.obtenerMensajeAtaque() + " contra " + objetivo.nombre + "!");
        objetivo.defender(danioGenerado, combate);
    }

    /**
     * El peleador se defiende de un ataque entrante usando su poder actual para mitigar el daño. Se notifica a los espectadores sobre la defensa y el daño recibido.
     * @param objetivo de quien se recibe el ataque.
     * @param combate para notificar a los espectadores sobre el ataque.
     */
    public void defender(int danioEntrante, Sujeto combate) {
        int danioReal = poderActual.mitigarDanio(danioEntrante);
        if (danioReal < 0) danioReal = 0;
        this.hp -= danioReal;
        
        combate.notificarObservador("   -> " + this.nombre + " se defiende usando [" + poderActual.obtenerNombrePoder() + "]. Recibe " + danioReal + " de daño. HP restante: " + Math.max(0, this.hp));
    }

    /**
     * El peleador consume un objeto para obtener un nuevo poder de su lista de poderes disponibles. Se notifica a los espectadores sobre el nuevo poder obtenido.
     * @param combate para notificar a los espectadores sobre el nuevo poder obtenido.
     */
    public void consumirObjeto(Sujeto combate) {
        if (!this.estaVivo() || poderesDisponibles.isEmpty()) return;
        
        Random rand = new Random();
        EstrategiaPoder nuevoPoder = poderesDisponibles.get(rand.nextInt(poderesDisponibles.size()));
        this.poderActual = nuevoPoder;
        
        combate.notificarObservador("\n¡" + this.nombre + " ha consumido un objeto y obtiene el poder: " + nuevoPoder.obtenerNombrePoder() + "!");
    }

    /**
     * Verifica si el peleador sigue vivo.
     * @return true si el peleador tiene hp mayor a 0, false en caso contrario.
     */
    public boolean estaVivo() {
        return hp > 0;
    }
}

/**
 * Korby, el personaje de la franquicia Noentiendo. Tiene un poder base de succión y puede obtener poderes de fuego y hielo.
 *⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣠⣤⣤⣤⣄⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀
 *⠀⣠⡶⠒⠒⠶⣄⣠⡴⠚⠉⠁⠀⠀⠀⠀⠀⠉⠙⠳⢦⡀⠀⠀⠀⠀⠀⠀
 *⢠⡏⠀⠀⠀⠀⠘⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⢧⡀⠀⠀⠀⠀
 *⢸⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠋⢱⠀⠀⢠⠉⢡⠀⠀⠀⠀⠀⠻⡄⠀⠀⠀
 *⠀⣧⠀⠀⠀⠀⠀⠀⠀⠀⢸⣧⣾⠄⠀⢸⣦⣾⠀⠀⠀⠀⠀⠀⢻⡄⠀⠀
 *⠀⠘⢧⡀⠀⠀⠀⠀⠀⠀⠈⣿⣿⠀⠀⠸⣿⡿⠀⠀⠀⠀⠀⠀⠈⠳⣄⠀
 *⠀⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠈⠁⡴⠶⡆⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠹⡄
 *⠀⠀⠀⢷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠐⠒⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣷
 *⠀⠀⠀⠸⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠇
 *⠀⠀⠀⣀⡿⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡽⣿⡛⠁⠀
 *⠀⣠⢾⣭⠀⠈⠳⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡠⠊⠀⢠⣝⣷⡀
 *⢠⡏⠘⠋⠀⠀⠀⠈⠑⠦⣄⣀⠀⠀⠀⠀⠀⣀⡠⠔⠋⠀⠀⠀⠈⠛⠃⢻
 *⠈⠷⣤⣀⣀⣀⣀⣀⣀⣀⣀⣤⡽⠟⠛⠿⣭⣄⣀⣀⣀⣀⣀⣀⣀⣀⣤⠞
 *⠀⠀⠀⠀⠉⠉⠉⠉⠉⠉⠁⠀⠀⠀⠀⠀⠀⠀⠈⠉⠉⠉⠉⠉⠉⠀⠀⠀
 */
class Korby extends Personaje {
    public Korby() {
        this.nombre = "Korby";
        this.franquicia = "Noentiendo";
        this.poderActual = new PoderBaseKorby();
        this.poderesDisponibles.add(new PoderFuegoKorby());
    }
}

/**
 * MeganMan, el personaje de la franquicia CopCam. Tiene un poder base de disparo de proyectiles de energía y puede obtener poderes de escudo y deslizamiento.
 * ⠀⠀⠀⠀⠀⠀⠀⠀⣀⡠⠤⠤⣄⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
 *⠀⠀⠀⠀⠀⠀⡠⠮⠈⡪⢙⠠⠐⠒⠹⢦⡀⠀⠀⠀⠀⠀⠀⠀⠀
 *⠀⠀⠀⠀⠀⢼⠀⠤⢔⠁⡘⠀⠀⠀⠀⠀⠱⡄⠀⠀⠀⠀⠀⠀⠀
 *⠀⠀⠀⠀⡘⠈⠂⠒⠒⣋⣀⣀⡀⠀⠀⠀⠀⢹⠀⠀⠀⠀⠀⠀⠀
 *⠀⠀⠀⠀⡗⣿⢉⡔⠈⠘⠈⣶⣿⢢⠀⡔⢁⣙⠀⠀⠀⠀⠀⠀⠀
 *⠀⢀⡀⠀⢿⣸⣿⠇⠀⠰⣰⣙⠏⢸⢰⢀⣻⢸⠀⠀⠀⠀⠀⠀⠀
 *⠾⠟⠉⡏⠚⢧⡀⠀⠄⠤⠀⠀⢀⠌⢘⠬⡗⠁⠀⠀⠀⠀⠀⠀⠀
 *⡇⠂⠈⠀⠀⠀⢫⢐⢢⠤⠄⠐⠿⣨⡥⠮⠉⢢⠀⠀⠀⠀⠀⠀⠀
 *⠰⡀⠀⠀⠀⠀⠸⣡⠦⠤⠤⢖⣼⢏⠑⠀⠑⠀⡇⠀⠀⠀⠀⠀⠀
 *⠀⠈⠒⠠⠤⠔⠋⡜⠢⣠⠒⠙⢌⠢⣁⣀⡠⠊⠀⠀⠀⠀⠀⠀⠀
 *⠀⠀⠀⠀⠀⢀⠜⠉⠢⡏⢂⡐⠈⠓⢤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀
 *⠀⠀⠀⡠⠄⠊⠀⠀⠀⢰⠈⠀⠀⠀⠀⠙⢦⡀⠀⠀⠀⠀⠀⠀⠀
 *⠀⢠⠎⠀⠀⠀⠀⠀⠀⠈⡄⡇⠀⠀⠀⠀⠀⠉⠲⡀⠀⠀⠀⠀⠀
 *⠀⠸⠄⣀⣀⣀⣀⣀⡀⠤⠃⠣⣀⠀⠀⠀⠀⠀⠀⡹⠀⠀⠀⠀⠀
 *⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠐⠒⠒⠒⠊⢡⡄⠤⣤⠀⠠
 */
class MeganMan extends Personaje {
    public MeganMan() {
        this.nombre = "MeganMan";
        this.franquicia = "CopCam";
        this.poderActual = new PoderBaseMeganMan();
        this.poderesDisponibles.add(new PoderEscudoMeganMan());
        this.poderesDisponibles.add(new PoderDeslizarMeganMan());
    }
}

/**
 * Dittuu, el personaje de la franquicia ChinPokemon. Tiene un poder base de gelatina y puede obtener poderes de ratón eléctrico, impactrueno.
 * ⢰⣶⣤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀
 *⠀⣿⣿⣿⣷⣤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⣶⣾⣿
 *⠀⠘⢿⣿⣿⣿⣿⣦⣀⣀⣀⣄⣀⣀⣠⣀⣤⣶⣿⣿⣿⣿⣿⠇
 *⠀⠀⠈⠻⣿⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠋⠀
 *⠀⠀⠀⠀⣰⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣟⠋⠀⠀⠀
 *⠀⠀⠀⢠⣿⣿⡏⠆⢹⣿⣿⣿⣿⣿⣿⠒⠈⣿⣿⣿⣇⠀⠀⠀
 *⠀⠀⠀⣼⣿⣿⣷⣶⣿⣿⣛⣻⣿⣿⣿⣶⣾⣿⣿⣿⣿⡀⠀⠀
 *⠀⠀⠀⡁⠀⠈⣿⣿⣿⣿⢟⣛⡻⣿⣿⣿⣟⠀⠀⠈⣿⡇⠀⠀
 *⠀⠀⠀⢿⣶⣿⣿⣿⣿⣿⡻⣿⡿⣿⣿⣿⣿⣶⣶⣾⣿⣿⠀⠀
 *⠀⠀⠀⠘⣿⣿⣿⣿⣿⣿⣿⣷⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡆⠀
 *⠀⠀⠀⠀⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀
 */
class Dittuu extends Personaje {
    public Dittuu() {
        this.nombre = "Dittuu";
        this.franquicia = "ChinPokemon";
        this.poderActual = new PoderBaseDittuu();
        this.poderesDisponibles.add(new PoderRatonElectricoDittuu());
        this.poderesDisponibles.add(new PoderImpactruenoDittuu());
    }
}