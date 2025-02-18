package com.cg.game;

/**
 * @author Juan Pablo Aguirre
 * @date Mayo de 2015
 * @version 1.0
 */
public class Jugador implements Comparable<Jugador> {
    

    private String nombre;
    private int puntos;
    private int vida;

    /**
     * Constructor. Inicializa jugadores asignandoles un nombre, Dos vidas por
     * defecto y cero puntos para comenzar
     *
     * @param nombre nombre para identificar el jugador
     */
    public Jugador(String nombre) {
        this.nombre = nombre;
        puntos = 0;
        vida = 2;
    }
/**
 * Constructor. Inicializa al jugador con los puntos que ya ha hecho. se usa para guardar en el xml
 * @param nombre nombre del jugador
 * @param puntos pntos que hizo el jugador
 */
 public Jugador (String nombre, int puntos){
     this.nombre=nombre;
     this.puntos=puntos;
 }
/**
 * Pasa los atributos del jugador a String
 * @return Devuelve los atributos
 */
    @Override
    public String toString() {
        return  "nombre=" + nombre + ", puntos=" + puntos;
    }
    /**
     * Getter del nombre
     *
     * @return devuleve el nombre asignado al jugador
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Getter de los puntos
     *
     * @return Devuelve los punto que ha hecho el jugador
     */
    public int getPuntos() {
        return puntos;
    }

    /**
     * Setter de los puntos
     *
     * @param puntos recibe el numero de puntos que se le asignaran al jugador
     */
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    /**
     * Getter de la vida
     *
     * @return Devuelve la vida actual del jugador
     */
    public int getVida() {
        return vida;
    }

    /**
     * Stter de la vida
     *
     * @param vida Asigna el numero de vidas al jugador
     */
    public void setVida(int vida) {
        this.vida = vida;
    }

    @Override
    public int compareTo(Jugador o) {
        return o.puntos - puntos;
    }
}
