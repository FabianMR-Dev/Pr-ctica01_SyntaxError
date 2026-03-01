import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase representa a un observador que está viendo el combate.
 * Cada observador apoya a un personaje específico y guarda un historial
 * de todo lo que pasa durante la pelea.
 * 
 * @author SyntaxError
 */
public class Observador implements Espectador{
    private String id; //id de los observadores 
    private String personajeApoyado;
    private List<String> historialCombate;

    /**
     * Constructor -creamos un nuevo espectador que verá la transmisión del combate
     * @param id identificador del observador 
     * @param personajeApoyado el personaje al que apoyará
     */
    public Observador(String id, String personajeApoyado) {
        this.id = id;
        this.personajeApoyado = personajeApoyado;
        this.historialCombate = new ArrayList<>(); //Iniciamos con historial vacío
    }

    /**
     * Cada que hay cambios en el combate, se guardan estos cambios en 
     * el historial de combate de cada observador 
     * @param evento lo ocurrido en el combate
     */
    @Override
    public void actualizar(String evento) {
        historialCombate.add(evento);
    }

    /**
     * Al terminar el combate se crea el archivo .txt para cada observador
     * diciendo si su personaje apoyado ganó o perdió
     * @param ganador Personaje que ganó el combate
     */
    @Override
    public void generarBitacora(Personaje ganador) {
        //Utilizamos try-with-resources para asegurar que el archivo se cierra automáticamente
        try (PrintWriter out = new PrintWriter(new FileWriter("Bitácora_" + id + ".txt"))) {
            //Encabezado de bitácora
            System.out.println("------- Bitácora del observador: " + id + " -------");
            System.out.println("Personaje apoyado: " + personajeApoyado );
            System.out.println("-----------------------------------------");

            //Escribimos los eventos del historial de combate
            for(String linea: historialCombate) {
                System.out.println(linea);
            }

            System.out.println("-----------------------------------------");

            //Mensaje sobre si su personaje apoyado fue ganador o perdedor
            if (ganador != null && ganador.nombre.equals(personajeApoyado)) {
                System.out.println("¡Felicidades! Tu personaje: " + personajeApoyado + "resultó GANADOR del combate ");
            } else if (ganador != null ){
                System.out.println("Lo sentimos, tu personaje: " + personajeApoyado + "resultó PERDEDOR del combate. El ganador fue: " + ganador.nombre + ". Suerte para la próxima.");
            } else {
                System.out.println("El combate terminó en empate");
            }
        } catch (IOException e) {
            //Si algo sale mal al escribir el archivo, mostramos mensaje de error
            System.out.println("Error al escribir la bitácora de " + id);
        }
    }
}