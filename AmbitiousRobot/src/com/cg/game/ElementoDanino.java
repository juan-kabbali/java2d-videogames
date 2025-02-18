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
 * @author Juan Pablo Aguirre
 * @date Mayo de 2015
 * @version 1.0
 */
public class ElementoDanino extends Objeto {

    private Random malo;
    private int imm;

    /**
     * Constructor. Inicializa objeto tipo Enemigo Le pasa el punto a la clase
     * Objeto, crea un nuevo Random, carga las imagenes correspondientes al
     * enemigo y asgina un valor aleatorio al atributo imm
     *
     * @param p recibe un punto que sera la coordenada de ubicacion del enemigo
     * (esquina superior izquierda)
     */
    public ElementoDanino(Point p) {
        super(p);
        malo = new Random();
        cargarImagenes();
        imm = malo.nextInt(4);
    }

    /**
     * Dibuja uno de los 4 enemigos existentes en el juego
     *
     * @param d recibe una grafica para dibujar
     */
    @Override
    public void dibujar(Graphics d) {

        switch (imm) {
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
     * Realiza la carga de las 4 imagenes posibles para los enemigos
     */
    @Override
    public void cargarImagenes() {

        BufferedImage a1, a2, a3, a4;
        a1 = null;
        a2 = null;
        a3 = null;
        a4 = null;

        try {
            a1 = ImageIO.read(new File("Tanque.png"));
            a2 = ImageIO.read(new File("Misil.png"));
            a3 = ImageIO.read(new File("Bomba.png"));
            a4 = ImageIO.read(new File("Sierra.png"));
        } catch (IOException ex) {
            Logger.getLogger(Lingote.class.getName()).log(Level.SEVERE, null, ex);
        }
        super.setI1(a1);
        super.setI2(a2);
        super.setI3(a3);
        super.setI4(a4);
    }
}
