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
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Creación de Comida
 *
 * @author Juliana Castellanos García Christian Granada Joseph Hermann Muñoz
 * Camilo Sandoval Harrison Zapata
 *
 * @date Mayo de 2014
 * @version 1.0
 *
 */
public class Comida extends Objeto {

    private Random bueno;
    private int imb;

    /**
     * Constructor. Inicializa objeto tipo Comida Le pasa el punto a la clase
     * Objeto, crea un nuevo Random, carga las imagenes correspondientes a la
     * comida y asgina un valor aleatorio al atributo imb
     *
     * @param p recibe un punto que sera la coordenada de ubicacion de la comida
     * (esquina superior izquierda)
     */
    public Comida(Point p) {
        super(p);
        bueno = new Random();
        cargarImagenes();
        imb = bueno.nextInt(4);
    }

    /**
     * Dibuja una de las cuatro comidas existentes en el juego
     *
     * @param d recibe una grafica para dibujar
     */
    @Override
    public void dibujar(Graphics d) {
        switch (imb) {
            case 0:
                d.drawImage(super.getI1(), super.getX(), super.getY(), null);

                break;
            case 1:
                d.drawImage(super.getI2(), super.getX(), super.getY(), null);
                break;
            case 2:
                d.drawImage(super.getI3(), super.getX(), super.getY(), null);
                break;
            case 3:
                d.drawImage(super.getI4(), super.getX(), super.getY(), null);
                break;

        }
    }

    /**
     * Realiza la carga de las 4 imagenes posibles para la comida
     */
    @Override
    public void cargarImagenes() {

        BufferedImage a1, a2, a3, a4;
        a1 = null;
        a2 = null;
        a3 = null;
        a4 = null;

        try {
            a1 = ImageIO.read(new File("Zanahoria.png"));
            a2 = ImageIO.read(new File("Dona.png"));
            a3 = ImageIO.read(new File("Pollo.png"));
            a4 = ImageIO.read(new File("Choco.png"));
        } catch (IOException ex) {
            Logger.getLogger(Peluche.class.getName()).log(Level.SEVERE, null, ex);
        }
        super.setI1(a1);
        super.setI2(a2);
        super.setI3(a3);
        super.setI4(a4);
    }
}
