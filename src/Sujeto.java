/**
 * Esta interfaz nos dice cómo se lleva a cabo el combate, ya que
 * pueden tener observadores y notificarles lo que pasa.
 */
public interface Sujeto {
    /**
     * Agrega un nuevo observador para que pueda unirse 
     * a la transmisión de los combates
     * @param obs El observador que quiere ver el combate 
     */
    public void registrarObservador(Espectador obs);

    /**
     * Quita a los observadores que ya no quieran unirse 
     * a la transmisión de los combates
     * @param obs El observador que ya no quiere ver el combate 
     */
    public void removerObservador(Espectador obs);
    
    /**
     * Notifica a los observadores qué ha pasado en el combate 
     * @param evento El evento que se ha producido en el combate. 
     */
    public void notificar(String evento);
}