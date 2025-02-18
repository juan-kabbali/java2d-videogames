/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cg.game;

import java.awt.Graphics;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Creación de Peluches
 *
 * @author Juliana Castellanos García Christian Granada Joseph Hermann Muñoz
 * Camilo Sandoval Harrison Zapata
 *
 * @date Mayo de 2014
 * @version 1.0
 *
 */
public class Peluche extends Objeto {

    /**
     * Constructor. Inicializa objetos tipo pelcuhe manda el punto coordenada a
     * la clase Objetos y realiza la carga de imagenes
     *
     * @param p coordenada del peluche (esquina superior izquierda
     */
    public Peluche(Point p) {
        super(p);
        cargarImagenes();
    }

    /**
     * Dibuja la imagen de peluche y la posiciona
     *
     * @param d recibe una grafica para dibujar
     */
    @Override
    public void dibujar(Graphics d) {

        d.drawImage(super.getI1(), super.getX(), super.getY(), null);


    }

    /**
     * Carga la imagen que corresponde al peluche
     */
    @Override
    public void cargarImagenes() {

        BufferedImage peluche = null;

        try {
            peluche = ImageIO.read(new File("Oveja.png"));
        } catch (IOException ex) {
            Logger.getLogger(Peluche.class.getName()).log(Level.SEVERE, null, ex);
        }

        super.setI1(peluche);
    }
}
