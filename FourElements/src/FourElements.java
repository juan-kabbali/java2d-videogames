import java.awt.Point;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.swing.JOptionPane;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 * @author juan_pab.aguirre
 * Clase hija de pantalla que ademas contiene a la clase jugador e
 * instrucciones, las cuales son ejecutadas dependiendo del valor que tome
 * boton, esta es la clase principal la cual despliega el menu de opciones
 */
public class FourElements extends Pantalla {

    private int comando;
    private final Instrucciones instrucciones;
    private Jugador player;
    public static Player reproductor;

    /**
     * Constructor que inicializa los atributos
     */
    public FourElements() {
        super("Inicio", "Four Elements");
        comando = 0;
        instrucciones = new Instrucciones(this);
        this.setSize(597, 848);
    }

    public Jugador getPlayer() {
        return player;
    }

    /**
     * Metodo que se encarga de reproducir el archivo mp.3
     */
    public static void reproducir() {
        try {

            FileInputStream sonido = new FileInputStream("Cancion.mp3");
            BufferedInputStream sonidoCargado = new BufferedInputStream(sonido);

            reproductor = new Player(sonidoCargado);

            reproductor.play();
        } catch (JavaLayerException ev) {
            ev.printStackTrace();
        } catch (FileNotFoundException ev) {
            ev.printStackTrace();
        }
    }

    @Override

    /**
     * Este metodo recibe la coordenada del mouse al hacer click, y con esta
     * información se asigna un valor al atributo boton para realizar su
     * respectiva acción
     */
    public void mouseClicked(MouseEvent e) {

        comando = 0;

        if (Colisiones.deteccionBotones(e.getPoint(), new Point(201, 184), 56, 209)) {
            comando = 1;
        }

        if (Colisiones.deteccionBotones(e.getPoint(), new Point(143, 271), 89, 303)) {
            comando = 2;
        }
        if (Colisiones.deteccionBotones(e.getPoint(), new Point(145, 413), 100, 374)) {
            comando = 3;
        }
        if (Colisiones.deteccionBotones(e.getPoint(), new Point(142, 566), 104, 328)) {
            comando = 4;
        }
        if (Colisiones.deteccionBotones(e.getPoint(), new Point(142, 720), 97, 324)) {
            comando = 5;
        }

        switch (comando) {

            case 1:
                String nombreJugador = JOptionPane.showInputDialog("¿Tu nombre?");
                player = new Jugador(nombreJugador);
                Aplicacion aplicacion = new Aplicacion(this);
                aplicacion.setVisible(true);
                this.setVisible(false);
                break;

            case 2:
                instrucciones.setVisible(true);
                this.setVisible(false);
                break;

            case 3:
                JOptionPane.showMessageDialog(null, "Puntos \n" + Archivo.getData());
                break;

            case 4:
                JOptionPane.showMessageDialog(null, "VideoJuego FOUR ELEMENTS"
                        + "\n                        creado por:"
                        + "\n                        Juan Pablo Aguirre Martinez"
                );
                break;

            case 5:
                System.exit(0);
                break;

            case 6:
                this.setVisible(true);
        }
    }

    /**
     * Main. Corre la ventana de inicio y reproduce el sonido.
     *
     * @param args
     */
    public static void main(String[] args) {

        FourElements i = new FourElements();
        i.setVisible(true);
        reproducir();

    }
}
