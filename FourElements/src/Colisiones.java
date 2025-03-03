
import java.awt.Point;

/**
 * @author juan_pab.aguirre
 * Clase que contiene los metodos que se encargan de realizar las colisiones
 */
public class Colisiones {

    /**
     * Detecta colisión enter una flecha y un rectangulo transparente ubicado en
     * la parte superior del lienzo
     *
     * @param movil flecha indicando su coordenada en y
     * @return verdadero o falso dependiendo si hay contacto entre los objetos
     */
    public static boolean deteccion(Flecha movil) {

        boolean v1y = entre(movil.getY(), movil.getY() + 118, 18);
        boolean v2y = entre(movil.getY(), movil.getY() + 118, (1));

        return (v1y && v2y);
    }

    /**
     * Detecta colision entre un punto y un boton
     *
     * @param m punto indicando las coordenadas del mouse
     * @param o punto indicando la esquina superior izquierda del boton
     * @return devuelve verdadero o falso dependiendo si hay contacto entre los
     * objetos
     */
    public static boolean deteccionBotones(Point m, Point o, int ancho, int largo) {

        boolean vx = entre(o.getX(), o.getX() + largo, m.getX());
        boolean vy = entre(o.getY(), o.getY() + ancho, m.getY());
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
