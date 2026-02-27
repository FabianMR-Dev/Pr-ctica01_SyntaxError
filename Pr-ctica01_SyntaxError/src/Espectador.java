// -----Patrón Observer----- 
/**
 * Esta interfaz define el comportamiento del espectador durante el combate.
 * Los espectadores pueden:
 *  -recibir actualizaciones del combate y de cómo va el personaje que apoya
 *  -recibir una bitácora cuando la pelea finaliza con un mensaje sobre el 
 *      estado final de su personaje favorito.
 * @author SyntaxError
* */ 

public interface Espectador{
    /**
     * Este método se llama cada vez que ocurre algo en el combate.
     * 
     * @param evento descripción de lo que pasó en el combate.
     */
    public void actualizar(String evento);

    /**
     * Al finalizar el combate, el espectador guarda un archivo .txt
     * con todo lo ocurrido durante la pelea. 
     * @param ganador el personaje que ganó el combate. 
     */
    public void generarBitacora(Personaje ganador);
}