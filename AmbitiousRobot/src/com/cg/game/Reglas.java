package com.cg.game;

import java.awt.Point;
import java.awt.event.MouseEvent;

/**
 * @author Juan Pablo Aguirre
 * @date Mayo de 2015
 * @version 1.0
 */
public class Reglas extends Ventana {

    private Inicio pantalla;

    /**
     * Constructor.Inicializa la ventana de regla Le pasa a la clase ventana el
     * nombre de la imagen de fondo y el titulo de la ventana
     *
     * @param i recibe una ventana de inicio
     */
    public Reglas(Inicio i) {
        super("Ins", "Instructions");
        pantalla = i;

    }

    /**
     * Se devuelve a la ventana de inicio cuando se da click en el boton de
     * volver
     *
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {

        if (Colisiones.deteccion(e.getPoint(), new Point(186, 541))) {
            this.setVisible(false);
            pantalla.setVisible(true);

        }
    }
}
