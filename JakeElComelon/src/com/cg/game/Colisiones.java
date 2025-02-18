package com.cg.game;

import java.awt.Point;

/**
 * Posee los metodos necesarios para identificar colisiones
 *
 * @author Juliana Castellanos García Christian Granada Joseph Hermann Muñoz
 * Camilo Sandoval Harrison Zapata
 *
 * @date Mayo de 2014
 * @version 1.0
 *
 */
public class Colisiones {

    /**
     * Detecta colosiones entre un Objeto (peluche, comida, enemigo, nino) y un
     * nino
     *
     * @param movil recibe el objeto a ser verificado
     * @param fijo recibe el nino a ser verificado
     * @return devuelve verdadero o falso dependiendo si hay contacto entre los
     * objetos
     */
    public static boolean deteccion(Objeto movil, Nino fijo) {

        boolean v1x = entre(movil.getX(), movil.getX() + 40, fijo.getX());
        boolean v2x = entre(movil.getX(), movil.getX() + 40, fijo.getX() + 10);

        boolean v1y = entre(movil.getY(), movil.getY() + 40, fijo.getY());
        boolean v2y = entre(movil.getY(), movil.getY() + 40, fijo.getY() + 10);

        return (v1x || v2x) && (v1y || v2y);
    }

    /**
     * Detecta colision entre un punto y un boton
     *
     * @param m punto indicando las coordenadas del mouse
     * @param o punto indicando la esquina superior izquierda del boton
     * @return devuelve verdadero o falso dependiendo si hay contacto entre los
     * objetos
     */
    public static boolean deteccion(Point m, Point o) {
        boolean vx = entre(o.getX(), o.getX() + 136, m.getX());
        boolean vy = entre(o.getY(), o.getY() + 45, m.getY());


        return (vx && vy);
    }

    /**
     * Verifica si un valor decimal esta entre un rango de valores
     *
     * @param v1 limite inferior del rango
     * @param v2 limite superior del rango
     * @param v valor a verificar dentro del rango
     * @return devuelve verdadadero o falso dependiendo si el valor si se
     * encuentra en el rango o no
     */
    private static boolean entre(double v1, double v2, double v) {
        return v1 < v && v < v2;
    }

    /**
     * Verifica si un valor entero esta entre un rango de valores
     *
     * @param v1 limite inferior del rango
     * @param v2 limite superior del rango
     * @param v valor a verificar dentro del rango
     * @return devuelve verdadadero o falso dependiendo si el valor si se
     * encuentra en el rango o no
     */
    private static boolean entre(int v1, int v2, int v) {
        return v1 < v && v < v2;
    }

    /**
     * Calcula la distancia entre dos puntos
     *
     * @param a primer punto
     * @param b segundo punto
     * @return devuelve un decimal que es la distancia entre los dos puntos
     */
    public static double distancia(Point a, Point b) {
        double d;
        double deltax = a.getX() - b.getX();
        double deltay = a.getY() - b.getY();
        d = Math.sqrt(deltax * deltax + deltay * deltay);
        return d;
    }
}
