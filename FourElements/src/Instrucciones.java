import java.awt.Point;
import java.awt.event.MouseEvent;

/**
 * @author juan_pab.aguirre
 * Esta clase es hijo de pantalla, y se encarga de cargar la imagen de las instrucciones */
public class Instrucciones extends Pantalla {

    private FourElements pantalla;

    /**
     * Constructor que carga la imagen*/
    public Instrucciones(FourElements i) {

        super("Instrucciones", "Reglas De Juego");
        pantalla = i;
        this.setSize(597, 848);

    }
/**
 * Este metodo detecta la colision del boton "volver"*/
    public void mouseClicked(MouseEvent e) {

        if (Colisiones.deteccionBotones(e.getPoint(), new Point(435, 770), 46, 100)) {
            this.setVisible(false);
            pantalla.setVisible(true);

        }
    }
    

}
