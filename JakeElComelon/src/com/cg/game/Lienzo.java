/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cg.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.util.LinkedList;


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Creación de Lienzos
 *
 * @author Juliana Castellanos García Christian Granada Joseph Hermann Muñoz
 * Camilo Sandoval Harrison Zapata
 *
 * @date Mayo de 2014
 * @version 1.0
 *
 */
public class Lienzo extends JPanel {

    private Nino b;
    private LinkedList<Objeto> cositas;
    private Jugador p;
    private BufferedImage fondo;

    /**
     * Constrcutor. Inicializa el lienzo. Craga la imagen del fondo.
     *
     * @param p Recibe el jugador actual
     * @param b Recibe un nino
     * @param c recibe una lista de objetos
     */
    public Lienzo(Jugador p, Nino b, LinkedList<Objeto> c) {
        this.p = p;
        this.b = b;
        this.cositas = c;

        try {
            fondo = ImageIO.read(new File("Cocina.png"));
        } catch (IOException ex) {
            Logger.getLogger(Lienzo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Convierte la grafica a una grafica 2D, dibuja el fondo, el niño, el valor
     * de la vida y de los puntos del jugador y dibuja los objetos.
     *
     * @param g recibe una grafica para dibujar
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D gd = (Graphics2D) g;

        gd.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gd.drawImage(fondo, 0, 0, this);
        b.dibujar(gd);

        for (Objeto o : cositas) {
            colision(o);
            g.setColor(Color.BLACK);
            g.drawString(" " + p.getVida(), 94, 28);
            g.drawString(" " + p.getPuntos(), 277, 28);
            if (o.isVisible()) {
                o.dibujar(g);
            }
        }

    }

    /**
     * Usa metodoso de la clase Colisiones para detectar colisiones entre un
     * objeto y el niño ademas, suma puntos si hay colision con un peluche o una
     * comida, y resta vidas s hay colision con un enemigo
     *
     * @param o recibe el objeto a ser comparado para la colision
     */
    public void colision(Objeto o) {
        boolean c;
        c = Colisiones.deteccion(o, b);
        if (c) {
            o.setVisible(false);

            if (o instanceof Enemigo) {
                p.setVida(p.getVida() - 1);
            } else {
                if (o instanceof Peluche) {
                    p.setPuntos(p.getPuntos() + 2);
                } else {
                    p.setPuntos(p.getPuntos() + 1);
                }
            }
        }
    }
}
