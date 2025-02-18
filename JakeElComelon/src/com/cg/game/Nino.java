/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cg.game;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Creación de Nino
 *
 * @author Juliana Castellanos García Christian Granada Joseph Hermann Muñoz
 * Camilo Sandoval Harrison Zapata
 *
 * @date Mayo de 2014
 * @version 1.0
 *
 */
public class Nino extends Objeto {

    private int dir;
    private LinkedList<BufferedImage> listaDer;
    private LinkedList<BufferedImage> listaIzq;
    private LinkedList<BufferedImage> listaActiva;
    private Iterator<BufferedImage> it;

    /**
     * Constructor. Inicializa al nino Le manda el punto a la clase Objeto e
     * inicializa la direccion del nino como la derecha por defecto.
     *
     * @param p recibe un punto que sera la coordenada del nino( esquina
     * superior izquierda)
     */
    public Nino(Point p) {
        super(p);


        dir = KeyEvent.VK_RIGHT;

    }

    /**
     * Constructor. Inicializa al nino. Asina los parametro de las lista al nino
     * al igual que la coordenada de posicion y asigna la direccion como la
     * derecha por defecto.
     *
     * @param listaDer lista con imagenes de movimiento hacia la derecha
     * @param listaIzq lista con imagenes de movimiento hacia la izquierda
     * @param posicion coordenadas del niño (esquina superior izquierda)
     */
    public Nino(LinkedList<BufferedImage> listaDer, LinkedList<BufferedImage> listaIzq, Point posicion) {
        super(posicion);

        this.listaDer = listaDer;
        this.listaIzq = listaIzq;
        listaActiva = listaDer;
        it = listaActiva.iterator();
        dir = KeyEvent.VK_RIGHT;
    }

    /**
     * Dibuja al niño a aprtir de la imagen y suposicion
     *
     * @param d grafica para dibujar
     */
    @Override
    public void dibujar(Graphics d) {
        d.drawImage(super.getI1(), super.getX(), super.getY(), null);


    }

    /**
     * Craga las imagenes del niño y sus respectivos movimientos hacia la
     * derecha e izquierda
     */
    public void cargarImagenes() {

        BufferedImage imaD = null;
        BufferedImage imaI = null;

        listaDer = new LinkedList<>();

        listaIzq = new LinkedList<>();

        for (int i = 1; i < 3; i++) {
            String nomArchivo = "Der/0" + i + ".png";
            try {
                imaD = ImageIO.read(new File(nomArchivo));
            } catch (IOException ex) {
                Logger.getLogger(Nino.class.getName()).log(Level.SEVERE, null, ex);
            }
            listaDer.add(imaD);
        }
        for (int j = 1; j < 3; j++) {
            String nomArchivo = "Izq/0" + j + ".png";
            try {
                imaI = ImageIO.read(new File(nomArchivo));
            } catch (IOException ex) {
                Logger.getLogger(Nino.class.getName()).log(Level.SEVERE, null, ex);
            }
            listaIzq.add(imaI);
        }
        listaActiva = listaDer;
        it = listaActiva.iterator();
        BufferedImage imageI = (BufferedImage) it.next();

        setI1(imageI);

    }

    /**
     * Activa las listas que corresponden a las imagenes de movimiento hacia la
     * derecha o izquierda, dependiendo de lo que teclee el jugador
     *
     * @param e evento que representa la tecla presionada por e jugador
     */
    public void mover(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (dir == KeyEvent.VK_LEFT) {


                listaActiva = listaDer;
                it = listaActiva.iterator();
                dir = KeyEvent.VK_RIGHT;
            }
            if (!it.hasNext()) {
                it = listaActiva.iterator();
            }
            BufferedImage imageD = (BufferedImage) it.next();

            setI1(imageD);

        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (dir == KeyEvent.VK_RIGHT) {


                listaActiva = listaIzq;
                it = listaActiva.iterator();
                dir = KeyEvent.VK_LEFT;
            }
            if (!it.hasNext()) {
                it = listaActiva.iterator();
            }
            BufferedImage imageI = (BufferedImage) it.next();

            setI1(imageI);


        }

    }
}
