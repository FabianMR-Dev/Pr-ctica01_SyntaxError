// =================
// PATRÓN STRATEGY
// =================
public interface EstrategiaPoder {
    int calcularDanioAtaque();
    int mitigarDanio(int danioRecibido);
    String obtenerNombrePoder();
    String obtenerMensajeAtaque();
}

// KorbyPoderes
class PoderBaseKorby implements EstrategiaPoder {
    public int calcularDanioAtaque() { return 12; }
    public int mitigarDanio(int danio) { return danio - 7; }
    public String obtenerNombrePoder() { return "Inhalación"; }
    public String obtenerMensajeAtaque() { return "da un golpe estándar"; }
}

class PoderFuegoKorby implements EstrategiaPoder {
    public int calcularDanioAtaque() { return 25; }
    public int mitigarDanio(int danio) { return danio - 5; }
    public String obtenerNombrePoder() { return "Fuego"; }
    public String obtenerMensajeAtaque() { return "lanza una llamarada ardiente"; }
}

class PoderHieloKorby implements EstrategiaPoder {
    public int calcularDanioAtaque() { return 20; }
    public int mitigarDanio(int danio) { return danio - 10; }
    public String obtenerNombrePoder() { return "Hielo"; }
    public String obtenerMensajeAtaque() { return "Lanza una fuerte ventisca de hielo"; }
}

// MeganManPoderes
class PoderBaseMeganMan implements EstrategiaPoder {
    public int calcularDanioAtaque() { return 12; }
    public int mitigarDanio(int danio) { return danio - 8; }
    public String obtenerNombrePoder() { return "Buster Normal"; }
    public String obtenerMensajeAtaque() { return "dispara un pequeño proyectil de energía"; }
}

class PoderEscudoMeganMan implements EstrategiaPoder {
    public int calcularDanioAtaque() { return 5; }
    public int mitigarDanio(int danio) { return 15; } 
    public String obtenerNombrePoder() { return "Escudo de Hojas"; }
    public String obtenerMensajeAtaque() { return "lanza hojas afiladas"; }
}

class PoderDeslizarMeganMan implements EstrategiaPoder {
    public int calcularDanioAtaque() { return 10; }
    public int mitigarDanio(int danio) { return 20; } 
    public String obtenerNombrePoder() { return "Deslizamiento"; }
    public String obtenerMensajeAtaque() { return "hace un movimiento de barrida por el suelo"; }
}

// DittuPoderes
class PoderBaseDittuu implements EstrategiaPoder {
    public int calcularDanioAtaque() { return 5; }
    public int mitigarDanio(int danio) { return danio - 1; }
    public String obtenerNombrePoder() { return "Gelatina"; }
    public String obtenerMensajeAtaque() { return "da un ligero empujón"; }
}

class PoderRatonElectricoDittuu implements EstrategiaPoder {
    public int calcularDanioAtaque() { return 20; }
    public int mitigarDanio(int danio) { return danio - 4; }
    public String obtenerNombrePoder() { return "Ratón Eléctrico"; }
    public String obtenerMensajeAtaque() { return "lanza un impactrueno"; }
}

class PoderImpactruenoDittuu implements EstrategiaPoder {
    public int calcularDanioAtaque() { return 45; }
    public int mitigarDanio(int danio) { return danio - 2; }
    public String obtenerNombrePoder() { return "Impactrueno"; }
    public String obtenerMensajeAtaque() { return "impacta un trueno contra sus enemigos"; }
}