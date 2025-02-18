
import java.awt.Color;
import java.awt.Font;
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
 * Clase que hereda de Jpanel para pintar las componentes graficas del juego
 * @author juan_pab.aguirre
 */
public class Lienzo extends JPanel {

    private final LinkedList<Flecha> cositas;
    Jugador p;
    private BufferedImage fondoActual, f1, f2, f3, f4; /*,FondoFuego,FondoTierra,FondoAire */

    private int dibujo;

    public void setP(Jugador p) {
        this.p = p;
    }

    public Jugador getP() {
        return this.p;
    }

    /**
     * Constructor de la clase que se encarga de crear el jugador, una LinkedList de flechas, el fondo y 
     * las imagenes de las flechas estimulo
     * @param p
     * @param c 
     */
    public Lienzo(Jugador p, LinkedList<Flecha> c) {

        this.p = p;
        this.cositas = c;
        dibujo = 856;

        try {
            fondoActual = cambiarFondo();
            f1 = ImageIO.read(new File("FE izquierda.png"));
            f2 = ImageIO.read(new File("FE abajo.png"));
            f3 = ImageIO.read(new File("FE arriba.png"));
            f4 = ImageIO.read(new File("FE derecha.png"));

        } catch (IOException ex) {
            Logger.getLogger(Lienzo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Metodo que cambia el fondo dependiendo el puntaje del jugador
     * @return 
     */
    public final BufferedImage cambiarFondo() {

        BufferedImage tmp = null;

        if (p.getPuntos() < 500) {

            try {
                tmp = ImageIO.read(new File("Mundo Acuatico.png"));
            } catch (IOException ex) {
                Logger.getLogger(Lienzo.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (p.getPuntos() < 1000) {

            try {
                tmp = ImageIO.read(new File("Mundo aire.png"));
            } catch (IOException ex) {
                Logger.getLogger(Lienzo.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (p.getPuntos() < 1500) {

            try {
                tmp = ImageIO.read(new File("Mundo tierra.png"));
            } catch (IOException ex) {
                Logger.getLogger(Lienzo.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (p.getPuntos() < 2500) {

            try {
                tmp = ImageIO.read(new File("Mundo fuego.png"));
            } catch (IOException ex) {
                Logger.getLogger(Lienzo.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return tmp;
    }

    /**
     * Metodo que pinta las flechas estimulo, los puntos y la vida del jugador 
     * @param g 
     */
    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D gd = (Graphics2D) g;

        gd.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gd.drawImage(cambiarFondo(), 0, 0, this);

        switch (dibujo) {
            case 0:
                gd.drawImage(f1, 15, 18, null);

                break;
            case 1:
                gd.drawImage(f2, 165, 18, null);
                break;
            case 2:
                gd.drawImage(f3, 315, 18, null);
                break;
            case 3:
                gd.drawImage(f4, 465, 18, null);
                break;
        }

        for (Flecha o : cositas) {
            colision(o);

            g.setColor(Color.BLACK);
            g.setFont(new Font("Helvetica", Font.BOLD, 18));
            g.drawString(" " + p.getVida(), 240, 821);
            g.drawString(" " + p.getPuntos(), 240, 788);
            if (o.isVisible()) {
                o.dibujar(g);
            }
        }

    }

    /**
     * Metodo que llama los metodos de colision para tomar la acción necesaria, sumar y restar puntos y vidas 
     */
    public void colision(Flecha o) {

        boolean c;
        c = Colisiones.deteccion(o);

        if (c) {

            if (o instanceof FlechaJugable) {

                if (o.getX() == 0 && dibujo == 0 || o.getX() == 150 && dibujo == 1 || o.getX() == 300 && dibujo == 2 || o.getX() == 450 && dibujo == 3) {
                    sumarPuntos(o);

                }
            }

            if (o instanceof FlechaRoja) {

                if (o.getX() == 0 && dibujo == 0 || o.getX() == 150 && dibujo == 1 || o.getX() == 300 && dibujo == 2 || o.getX() == 450 && dibujo == 3) {

                    restarPuntos(o);
                }
            }

            if (o instanceof FlechaDorada) {

                if (o.getX() == 0 && dibujo == 0 || o.getX() == 150 && dibujo == 1 || o.getX() == 300 && dibujo == 2 || o.getX() == 450 && dibujo == 3) {

                    sumarVida(o);
                    sumarPuntosDorada(o);

                }
            }

        } else {

            if (o.getY() < 5) {

                if (o instanceof FlechaJugable) {
                    restarVida(o);
                }
                if (o instanceof FlechaDorada) {
                    restarVida(o);
                }
            }

        }
    }

    /**
     * Metodo que realiza acciones necesarias para la colision de una flecha dorada
     * @param f 
     */
    public void sumarPuntosDorada(Flecha f) {
        p.setPuntos(p.getPuntos() + 50);
        f.setVisible(false);
    }

    /**
     * Metodo que suma puntos para una colision
     * @param f 
     */
    public void sumarPuntos(Flecha f) {
        p.setPuntos(p.getPuntos() + 10);
        f.setVisible(false);
    }

    /**
     * Metodo que suma puntos para una colision
     * @param f 
     */
    public void sumarVida(Flecha f) {
        p.setVida(p.getVida() + 1);
        f.setVisible(false);
    }

    /**
     * Metodo que resta vidas para una colision
     * @param f 
     */
    public void restarVida(Flecha f) {
        p.setVida(p.getVida() - 1);
        f.setVisible(false);
    }

    /**
     * Metodo que resta puntos para una colision
     * @param f 
     */
    public void restarPuntos(Flecha f) {
        p.setPuntos(p.getPuntos() - 20);
        p.setVida(p.getVida() - 1);
        f.setVisible(false);
    }

    
    public void setDibujo(int dibujo) {
        this.dibujo = dibujo;
    }

}
