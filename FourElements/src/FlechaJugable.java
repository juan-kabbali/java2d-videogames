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
 * @author juan_pab.aguirre
 * Hijo de la clase Flecha, implementa los metodos abstractos*/
public class FlechaJugable extends Flecha {
    
     private Random bueno;

    /**
     * Constructor que se encarga de cargar las imagenes de las flechas doradas y crear el random*/
    public FlechaJugable(Point p) {
        super(p);
        bueno = new Random();
        cargarImagenes();
        imb = bueno.nextInt(4);
    }

    /**
      * pinta las flechas azules @param d recibe una grafica para dibujar
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
     * Carga las imagenes de cada direccion   */
    @Override
    public void cargarImagenes() {

        BufferedImage a1, a2, a3, a4;
        a1 = null;
        a2 = null;
        a3 = null;
        a4 = null;

        try {
            a1 = ImageIO.read(new File("FJ izquierda.png"));
            a2 = ImageIO.read(new File("FJ abajo.png"));
            a3 = ImageIO.read(new File("FJ arriba.png"));
            a4 = ImageIO.read(new File("FJ derecha.png"));
        } catch (IOException ex) {
            Logger.getLogger(FlechaDorada.class.getName()).log(Level.SEVERE, null, ex);
        }
        super.setI1(a1);
        super.setI2(a2);
        super.setI3(a3);
        super.setI4(a4);
    }
    
}
