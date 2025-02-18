/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cg.game;

import java.awt.Point;
import java.awt.event.MouseEvent;

/**
 * Creación de ventan de Reglas
 *
 * @author Juliana Castellanos García Christian Granada Joseph Hermann Muñoz
 * Camilo Sandoval Harrison Zapata
 *
 * @date Mayo de 2014
 * @version 1.0
 *
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
        super("Ins", "Reglas De Juego");
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
