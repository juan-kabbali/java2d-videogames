package com.cg.game;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;


/**
 * @author Juan Pablo Aguirre
 * @date Mayo de 2015
 * @version 1.0
 */
public abstract class Objeto {

    private Point posicion;
    private boolean visible;
    private BufferedImage i1, i2, i3, i4;

    /**
     * Getter de una imagen para representar el objeto
     *
     * @return devuleve la imagen correspondiente
     */
    public BufferedImage getI1() {
        return i1;
    }

    /**
     * Setter de una imagen para representar el objeto
     *
     * @param i1 recibe la imagen a asignar
     */
    public void setI1(BufferedImage i1) {
        this.i1 = i1;
    }

    /**
     * Getter de una imagen para representar el objeto
     *
     * @return devuleve la imagen correspondiente
     */
    public BufferedImage getI2() {
        return i2;
    }

    /**
     * Setter de una imagen para representar el objeto
     *
     * @param i1 recibe la imagen a asignar
     */
    public void setI2(BufferedImage i2) {
        this.i2 = i2;
    }

    /**
     * Getter de una imagen para representar el objeto
     *
     * @return devuleve la imagen correspondiente
     */
    public BufferedImage getI3() {
        return i3;
    }

    /**
     * Setter de una imagen para representar el objeto
     *
     * @param i1 recibe la imagen a asignar
     */
    public void setI3(BufferedImage i3) {
        this.i3 = i3;
    }

    /**
     * Getter de una imagen para representar el objeto
     *
     * @return devuleve la imagen correspondiente
     */
    public BufferedImage getI4() {
        return i4;
    }

    /**
     * Setter de una imagen para representar el objeto
     *
     * @param i1 recibe la imagen a asignar
     */
    public void setI4(BufferedImage i4) {
        this.i4 = i4;
    }

    /**
     * Constructor.Inicializa los Objetos asigna el punto de la posicion y
     * vuelve visible el objeto
     *
     * @param posicion punto que representa la coordenada del objeto (esquina
     * superior derecha)
     */
    public Objeto(Point posicion) {
        this.posicion = posicion;
        visible = true;

    }

    /**
     * Getter de la posicion del objeto
     *
     * @return Devuelve el punto con la coordenada de posicion
     */
    public Point getPosicion() {
        return posicion;
    }

    /**
     * Setter de la posicion del objeto
     *
     * @param posicion asigna la poscion del punto que recibe
     */
    public void setPosicion(Point posicion) {
        this.posicion = posicion;
    }

    /**
     * Devuelve el estado del objeto con respecto a su visibilidad
     *
     * @return verdadero si es visible, falso si no
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Asigna la visibilidad del objeto
     *
     * @param visible asigna dependiendo al booleano que recibe como parametro
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * Getter de la coordenada x del objeto como entero
     *
     * @return devuelve la coordenada en x
     */
    public int getX() {
        int equis;
        equis = (int) posicion.getX();
        return equis;
    }

    /**
     * Getter de la coordenada y del objeto como entero
     *
     * @return devuelve la coordenada en y
     */
    public int getY() {
        int ye;
        ye = (int) posicion.getY();
        return ye;
    }

    /**
     * metodo a ser implementado por las clases que heredan de objeto, para
     * poder dibujar los mismos
     *
     * @param d recibe una grafica para dibujar
     */
    public abstract void dibujar(Graphics d);

    /**
     * metodo a ser implementado por las clases que geredan de objeto, para
     * poder cargar las imagenes del mismo
     */
    public abstract void cargarImagenes();
}
