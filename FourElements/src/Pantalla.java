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
import static javax.swing.JFrame.EXIT_ON_CLOSE;


/**
 * Clase padre que se encarga de añadir los distintos Jframes del videojuego
 * @author juan_pab.aguirre
 */
public class Pantalla  extends JFrame implements MouseListener  {
       
    private BufferedImage fondo;
    private static BufferedImage icono;

    /**
     * Constructor que tiene la imagen de fondo que sera cambiada dependiendo la opcion elegida
     * @param nombre
     * @param titulo 
     */
    public Pantalla (String nombre, String titulo) {
        super(titulo);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setSize(597, 848);
        addMouseListener(this);


        try {
            fondo = ImageIO.read(new File(nombre + ".png"));
            icono = ImageIO.read(new File("FR abajo.png"));

        } catch (IOException ex) {
            Logger.getLogger(Pantalla.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.setIconImage(icono);
        setSize(330, 600);
        setResizable(false);
    }

   
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
