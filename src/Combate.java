import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase representa el combate.
 * Aquí se lleva a cabo y le notifica a los observadores
 */
public class Combate implements Sujeto {
    //Lista de los espectadores que están viendo el combate
    private List<Espectador> espectadores = new ArrayList<>();

    /**
     * Agrega un nuevo espectador a la lista para que reciba 
     * notificaciones.
     * @param obs El observador que quiere unirse a ver el combate
     */
    @Override
    public void registrarObservador(Espectador obs) {
        espectadores.add(obs);
    }

    /**
     * Cuando hay cambios en el combate, este método:
     * -Muestra el evento en la consola (ver: "en vivo")
     * -Contar el evento a cada espectador para que lo guarde en su historial
     * 
     * @param evento Lo que acaba de suceder en el combate
     */
    @Override
    public void notificar(String evento) {
        //Mostramos en pantalla lo que pasó 
        System.out.println(evento);
        
        //Le contamos a cada observador lo que pasó
        for (Espectador obs : espectadores) {
            obs.actualizar(evento);
        }
    }

    /**
     * Cuando un observador no quiere recibir notificaciones del combate
     * lo removemos de la lista de espectadores
     */
    @Override
    public void removerObservador(Espectador obs){
        espectadores.remove(obs);   
    }
}