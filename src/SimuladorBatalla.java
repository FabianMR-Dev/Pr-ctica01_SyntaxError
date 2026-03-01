import java.util.Random;

public class SimuladorBatalla {

    public static void main(String[] args) {
        Random rand = new Random();
        int casoDePrueba = rand.nextInt(3) + 1; // Genera 1, 2 o 3
        
        System.out.println("Ejecutando Caso de Prueba #" + casoDePrueba + "\n");
        ejecutarSimulacion(casoDePrueba);
    }

    public static void ejecutarSimulacion(int numCaso) {
        Combate combate = new Combate();
        
        // Crear observadores
        Observador esp1 = new Observador("espectador123", "Korby");
        Observador esp2 = new Observador("espectador456", "MeganMan");
        Observador esp3 = new Observador("espectador789", "Dittuu");
        Observador esp4 = new Observador("espectador321", "Korby");
        
        combate.registrarObservador(esp1);
        combate.registrarObservador(esp2);
        combate.registrarObservador(esp3);
        combate.registrarObservador(esp4);

        // Crear personajes
        Korby korby = new Korby();
        MeganMan meganMan = new MeganMan();
        Dittuu dittuu = new Dittuu();

        combate.notificarObservador("¡COMIENZA EL COMBATE EN LA EMPRESA DE LA ROSA!");

        // Lógica de los casos de prueba (Arreglando las peleas)
        if (numCaso == 1) {
            // Caso 1: Gana Korby
            korby.consumirObjeto(combate);
            korby.atacar(meganMan, combate);
            meganMan.atacar(dittuu, combate);
            korby.atacar(dittuu, combate); // Dittuu muere
            dittuu.hp = 0; 
            korby.atacar(meganMan, combate);
            korby.atacar(meganMan, combate); 
            meganMan.hp = 0; // MeganMan muere
        } else if (numCaso == 2) {
            // Caso 2: Gana MeganMan
            meganMan.consumirObjeto(combate);
            meganMan.atacar(korby, combate);
            dittuu.consumirObjeto(combate);
            dittuu.atacar(korby, combate);
            korby.hp = 0; // Korby muere
            meganMan.atacar(dittuu, combate);
            meganMan.atacar(dittuu, combate);
            dittuu.hp = 0; // Dittuu muere
        } else {
            // Caso 3: Gana Dittuu
            dittuu.consumirObjeto(combate);
            dittuu.atacar(meganMan, combate);
            korby.consumirObjeto(combate);
            korby.atacar(meganMan, combate);
            meganMan.hp = 0; // MeganMan muere
            dittuu.atacar(korby, combate);
            dittuu.atacar(korby, combate);
            korby.hp = 0; // Korby muere
        }

        // Determinar ganador
        Personaje ganador = null;
        if (korby.estaVivo()) ganador = korby;
        else if (meganMan.estaVivo()) ganador = meganMan;
        else if (dittuu.estaVivo()) ganador = dittuu;

        combate.notificarObservador("\n¡EL COMBATE HA TERMINADO!");
        if(ganador != null) {
            combate.notificarObservador("¡El ganador indiscutible es " + ganador.nombre + "!");
        }

        // Generar Bitácoras
        esp1.generarBitacora(ganador);
        esp2.generarBitacora(ganador);
        esp3.generarBitacora(ganador);
        esp4.generarBitacora(ganador);
        
        System.out.println("\nSe han generado los archivos .txt de las bitácoras correctamente.");
    }
}