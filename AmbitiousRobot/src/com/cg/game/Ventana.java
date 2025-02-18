package com.cg.game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
/**
 * @author Juan Pablo Aguirre
 * @date Mayo de 2015
 * @version 1.0
 */
public class Ventana extends JFrame implements MouseListener {

    private BufferedImage fondo;
    private static BufferedImage icono;

    /**
     * Constructor. Inicializa las ventanas asigna u titulo a la ventana, cierra
     * la ventana con la X, añade el mouse Listener ,carga las imagene del fondo
     * y del icono, ajusta las dimensiones de la ventana.
     *
     * @param nombre
     * @param titulo
     */
    public Ventana(String nombre, String titulo) {
        super(titulo);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        addMouseListener(this);


        try {
            fondo = ImageIO.read(new File(nombre + ".gif"));
            icono = ImageIO.read(new File("icono.png"));

        } catch (IOException ex) {
            Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.setIconImage(icono);
        setSize(330, 600);
        setResizable(false);
    }

    /**
     * Getter del icono
     *
     * @return devuelve la imagen del icono
     */
    public static BufferedImage getIcono() {
        return icono;
    }

    /**
     * Pinta el fondo en la ventana
     *
     * @param g recibe una grafica para dibujar
     */
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D gd = (Graphics2D) g;
        gd.drawImage(fondo, 0, 0, this);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
