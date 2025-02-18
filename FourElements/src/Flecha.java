
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 * @author juan_pab.aguirre
 * Clase abstracta que contiene los atributos de una flecha con sus respectivos setters y getters, y ademas 
 contiene dos metodos abstractos para cargar y dibujar las imagenes*/
public abstract class Flecha {

    private Point posicion;
    private boolean visible;
    private BufferedImage i1, i2, i3, i4;
    protected int imb;

    public int getImb() {
        return imb;
    }

    public void setImb(int imb) {
        this.imb = imb;
    }

    public BufferedImage getI1() {
        return i1;
    }

    public void setI1(BufferedImage i1) {
        this.i1 = i1;
    }

    public BufferedImage getI2() {
        return i2;
    }

    public void setI2(BufferedImage i2) {
        this.i2 = i2;
    }

    public BufferedImage getI3() {
        return i3;
    }

    public void setI3(BufferedImage i3) {
        this.i3 = i3;
    }

    public BufferedImage getI4() {
        return i4;
    }

    public void setI4(BufferedImage i4) {
        this.i4 = i4;
    }

    public Flecha(Point posicion) {
        this.posicion = posicion;
        visible = true;

    }

    public Point getPosicion() {
        return posicion;
    }

    public void setPosicion(Point posicion) {
        this.posicion = posicion;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getX() {
        int equis;
        equis = (int) posicion.getX();
        return equis;
    }

    public int getY() {
        int ye;
        ye = (int) posicion.getY();
        return ye;
    }

    /**
     * Metodo abstracto que sera implementado por las clases hijas para pintar las imagenes en el lienzo*/
    public abstract void dibujar(Graphics d);

    /**
     * Metodo abstracto que sera implementado por las clases hijas*/
    public abstract void cargarImagenes();
}
