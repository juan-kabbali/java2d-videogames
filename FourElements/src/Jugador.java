
/**
 * @author juan_pab.aguirre
 * Clase que contiene los atributos de jugador con sus respectivos setters y getters */
public class Jugador implements Comparable<Jugador>{
    
    private String nombre;
    private int puntos;
    private int vida;

   /**
    * Constructor que inicializa los atributos de jugador una vez iniciada la partida*/
    public Jugador(String nombre) {
        this.nombre = nombre;
        puntos = 0;
        vida = 5;
    }

   /**
    * Constructor que inicializa los atributos de jugador una vez finalizada la partida*/
 public Jugador (String nombre, int puntos){
     this.nombre=nombre;
     this.puntos=puntos;
 }

    @Override
    public String toString() {
        return  "nombre=" + nombre + ", puntos=" + puntos;
    }
    
    public String getNombre() {
        return nombre;
    }

    public int getPuntos() {
        return puntos;
    }

   
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    
    public int getVida() {
        return vida;
    }

   
    public void setVida(int vida) {
        this.vida = vida;
    }

    @Override
    
    /**
     * Metodo que ordena los jugadores con el atributo puntos, de mayor a menor 
     */
    public int compareTo(Jugador o) {
        return o.puntos - puntos;
    }
    
}
