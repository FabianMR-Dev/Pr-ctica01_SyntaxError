import java.util.ArrayList;
import java.util.List;

public class ArenaBatalla implements Sujeto {
    private List<Espectador> espectadores = new ArrayList<>();
    
    @Override
    public void registrarObservador(Espectador obs) {
        espectadores.add(obs);
    }
    
    @Override
    public void removerObservador(Espectador obs) {
        espectadores.remove(obs);
    }
    
    @Override
    public void notificar(String evento) {
        System.out.println(evento);
        for (Espectador obs : espectadores) {
            obs.actualizar(evento);
        }
    }
    
    // Método adicional para mantener compatibilidad con el código existente
    public void notificarEspectadores(String evento) {
        notificar(evento);
    }
    
    public void registrarEspectador(Espectador obs) {
        registrarObservador(obs);
    }
}