import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.ListIterator;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *Juan Pablo Aguirre 2014
*/

/**
 *Clase que contiene una lista de flechas, inicializa los valores de los timers que controlan la velocidad
  de subida y la frecuencia con la que aparecen las flechas y sus respectivos metodos para agregarlas al lienzo
  */
public class Aplicacion extends JFrame implements KeyListener, ActionListener {

    private Lienzo lienzo;
    private Timer subida;
    private Timer salidaFlecha;
    private LinkedList<Flecha> flechas;
    private int contador;
    private int cambioSubida;
    private int cambios;
    private int cambioFlecha;
    private FourElements i;

    
    /** 
     * Constructor que inicializa los valores de cada variable */
    public Aplicacion(FourElements i) throws HeadlessException {

        super("Four Elements");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.i = i;
        setIconImage(Pantalla.getIcono());
       
        cambios = 0;
        cambioSubida = 50;
        cambioFlecha = 1500;

        subida = new Timer(cambioSubida, this);
        subida.setActionCommand("timeOut");
        subida.start();

        salidaFlecha = new Timer(cambioFlecha, this);
        salidaFlecha.setActionCommand("start");
        salidaFlecha.start();

        flechas = new LinkedList<Flecha>();

        lienzo = new Lienzo(i.getPlayer(), flechas);
        add(BorderLayout.CENTER, lienzo);
        lienzo.setToolTipText("GameArea");

        contador = 100;
        addKeyListener(this);

        setSize(597, 880);
        setResizable(false);
    }

    /** 
     * Metodo que crea un objeto tipo flecha de forma random para agergarlo a la lista de flechas y 
       posicionarlos en la pantalla mediante la implementacion del metodo posicionar */ 
    public synchronized void crearObjetos() {

        Point e = new Point(0, 0);

        double tipo;
        tipo = Math.random();

        Flecha f;
        if (tipo < 0.20) {
            f = new FlechaRoja(e);
            f.setPosicion(new Point(posicionarO(f.getImb()), 830));
            flechas.add(f);
        }
        if (tipo < 0.98 && tipo >= 0.2) {
            f = new FlechaJugable(e);
            f.setPosicion(new Point(posicionarO(f.getImb()), 830));
            flechas.add(f);
        }

        if (tipo >= 0.98 && tipo < 1) {
            f = new FlechaDorada(e);
            f.setPosicion(new Point(posicionarO(f.getImb()), 830));
            flechas.add(f);
        }

        lienzo.repaint();
    }

    /** 
     * Metodo que posiciona las  flechas en la pantalla y les asigna un carril dependiendo la direccion */
    public int posicionarO(int dire) {
        int salida = 0;
        switch (dire) {
            case 0:
                salida = 0;
                break;
            case 1:
                salida = 150;
                break;
            case 2:
                salida = 300;
                break;
            case 3:
                salida = 450;
                break;
        }
        return salida;
    }

    /** 
     * Metodo que se encarga de ejecutar la matriz de traslación a cada flecha de la lista */
    public synchronized void subidaFlechas() {

        for (Flecha o : flechas) {
            o.getPosicion().translate(0, -10);

           // Verificar si los objetos se salen de la pantalla
            if (o.getY() < -100) {
                o.setVisible(false);
                lienzo.repaint();
            }
        }
        lienzo.repaint();
    }

    /**
     * Metodo que remueve de la lista las flechas que salen de la pantalla*/
    public void eliminarFlecha() {
        boolean borrado = false;
        for (ListIterator<Flecha> it = flechas.listIterator(); !borrado && it.hasNext();) {
            Flecha f = it.next();
            if (!f.isVisible()) {
                flechas.remove(f);
                borrado = true;
            }
        }
    }

    /** 
     * Metodo que se encarga de terminar el juego cuando los puntos del jugador sea igual a 0 */
    public void muerte() {
        if (i.getPlayer().getVida() <= 0) {

            int puntos = i.getPlayer().getPuntos();
            guardarJugador(i.getPlayer());
            subida.stop();

            salidaFlecha.stop();
            JOptionPane.showMessageDialog(null, "Juego terminado " + i.getPlayer().getNombre() + " \n Su Puntaje es : " + puntos);

            dispose();

            i.setVisible(true);
        }
    }

    
    /** 
     * Metodo que se encarga de aumentar la dificultad(velocidad y frecuencia de salida de las flechas ) 
     a medida que el jugador obtiene mas puntos*/
    public void dificultad() {
        
 if(lienzo.p.getPuntos() < 500){
 
     
     cambioSubida = 100;
     cambioFlecha = 1500;
     
 }
 else if(lienzo.p.getPuntos() < 1000){

     cambioSubida = 50;
     cambioFlecha = 900;
     subida.setDelay(cambioSubida);
     salidaFlecha.setDelay(cambioFlecha);
 
 }

else if(lienzo.p.getPuntos() < 1500){

     cambioSubida = 10;
     cambioFlecha = 600;
     subida.setDelay(cambioSubida);
     salidaFlecha.setDelay(cambioFlecha);
 
 }
 
else if(lienzo.p.getPuntos() < 2500){

     cambioSubida = 1;
     cambioFlecha = 500;
     subida.setDelay(cambioSubida);
     salidaFlecha.setDelay(cambioFlecha);
 

        }
 
else if(lienzo.p.getPuntos() < 3000){

     cambioSubida = 1;
     cambioFlecha = 220;
     subida.setDelay(cambioSubida);
     salidaFlecha.setDelay(cambioFlecha);
 

        }
        
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Metodo que se encarga de dar los valores al lienzo para pintar las flechas de estimulo */
    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {


            case KeyEvent.VK_LEFT:
                lienzo.setDibujo(0);
                break;

            case KeyEvent.VK_DOWN:
                lienzo.setDibujo(1);
                break;
            case KeyEvent.VK_UP:
                lienzo.setDibujo(2);
                break;

            case KeyEvent.VK_RIGHT:
                lienzo.setDibujo(3);
                break;
        }

        lienzo.repaint();
    }

    /**
     * Metodo que se encarga de dar un valor al lienzo para remover las flechas de estimulo*/
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 37 || e.getKeyCode() == 40 || e.getKeyCode() == 38 || e.getKeyCode() == 39) {
            lienzo.setDibujo(456);
        }
        lienzo.repaint();
    }

    /**
     * Metodo que se encarga de ejecutar los metodos dependiendo del  evento "timeOut" y "start" */
    @Override
    public void actionPerformed(ActionEvent e) {
        String event = e.getActionCommand();

        if (event.equals("timeOut")) {
           
            subidaFlechas();
            eliminarFlecha();
            dificultad();
            muerte();
        }
        if (event.equals("start")) {
            crearObjetos();
        }
    }

    
    /** 
     * Metodo que se encarga de crear y agregar los jugadores a un ArrayList en la clase archivo */
    public void guardarJugador(Jugador j) {
        Archivo domR = new Archivo();
        ArrayList<Jugador> jugadores = domR.XMLtoJugador("jugadores.xml");

        jugadores.add(j);
        Collections.sort(jugadores);

        // creamos los procesadores en DOM y en SAX
        Archivo dom = new Archivo();
                               
        // escritura con DOM
        dom.jugadorToXML(jugadores, "jugadores.xml");
        jugadores = domR.XMLtoJugador("jugadores.xml");

    }
}
