import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimuladorBatalla {

    public static void main(String[] args) {
        Random rand = new Random();
        int casoDePrueba = rand.nextInt(3) + 1; // Genera 1, 2 o 3
        
        System.out.println("Ejecutando Caso de Prueba #" + casoDePrueba + "\n");
        ejecutarSimulacion(casoDePrueba);
    }

    public static void ejecutarSimulacion(int numCaso) {
        ArenaBatalla arena = new ArenaBatalla();
        
        // Crear espectadores
        Observador esp1 = new Observador("espectador123", "Korby");
        Observador esp2 = new Observador("espectador456", "MeganMan");
        Observador esp3 = new Observador("espectador789", "Dittuu");
        Observador esp4 = new Observador("VIP_User", "Korby");
        
        arena.registrarEspectador(esp1);
        arena.registrarEspectador(esp2);
        arena.registrarEspectador(esp3);
        arena.registrarEspectador(esp4);

        // Crear personajes
        Korby korby = new Korby();
        MeganMan meganMan = new MeganMan();
        Dittuu dittuu = new Dittuu();

        arena.notificarEspectadores("¡COMIENZA EL COMBATE EN LA EMPRESA DE LA ROSA!");

        // Lógica de los casos de prueba (Arreglando las peleas)
        if (numCaso == 1) {
            // Caso 1: Gana Korby
            korby.consumirObjeto(arena);
            korby.atacar(meganMan, arena);
            meganMan.atacar(dittuu, arena);
            korby.atacar(dittuu, arena); // Dittuu muere
            dittuu.hp = 0; 
            korby.atacar(meganMan, arena);
            korby.atacar(meganMan, arena); 
            meganMan.hp = 0; // MeganMan muere
        } else if (numCaso == 2) {
            // Caso 2: Gana MeganMan
            meganMan.consumirObjeto(arena);
            meganMan.atacar(korby, arena);
            dittuu.consumirObjeto(arena);
            dittuu.atacar(korby, arena);
            korby.hp = 0; // Korby muere
            meganMan.atacar(dittuu, arena);
            meganMan.atacar(dittuu, arena);
            dittuu.hp = 0; // Dittuu muere
        } else {
            // Caso 3: Gana Dittuu
            dittuu.consumirObjeto(arena);
            dittuu.atacar(meganMan, arena);
            korby.consumirObjeto(arena);
            korby.atacar(meganMan, arena);
            meganMan.hp = 0; // MeganMan muere
            dittuu.atacar(korby, arena);
            dittuu.atacar(korby, arena);
            korby.hp = 0; // Korby muere
        }

        // Determinar ganador
        Personaje ganador = null;
        if (korby.estaVivo()) ganador = korby;
        else if (meganMan.estaVivo()) ganador = meganMan;
        else if (dittuu.estaVivo()) ganador = dittuu;

        arena.notificarEspectadores("\n¡EL COMBATE HA TERMINADO!");
        if(ganador != null) {
            arena.notificarEspectadores("¡El ganador indiscutible es " + ganador.nombre + "!");
        }

        // Generar Bitácoras
        esp1.generarBitacora(ganador);
        esp2.generarBitacora(ganador);
        esp3.generarBitacora(ganador);
        esp4.generarBitacora(ganador);
        
        System.out.println("\nSe han generado los archivos .txt de las bitácoras correctamente.");
    }
}