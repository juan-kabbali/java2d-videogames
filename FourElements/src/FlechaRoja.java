
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
public class FlechaRoja extends Flecha{
    
     private Random malo;
   

   /**
    * Constructor que se encarga de cargar las imagenes de las flechas rojas y crear el random*/
    public FlechaRoja(Point p) {
        
        super(p);
        malo = new Random();
        cargarImagenes();
        imb = malo.nextInt(4);
    }

   /**
    *pinta las flechas rojas  @param d recibe una grafica para dibujar
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
            a1 = ImageIO.read(new File("FR izquierda.png"));
            a2 = ImageIO.read(new File("FR abajo.png"));
            a3 = ImageIO.read(new File("FR arriba.png"));
            a4 = ImageIO.read(new File("FR derecha.png"));
        } catch (IOException ex) {
            Logger.getLogger(FlechaDorada.class.getName()).log(Level.SEVERE, null, ex);
        }
        super.setI1(a1);
        super.setI2(a2);
        super.setI3(a3);
        super.setI4(a4);
    }
    
}
