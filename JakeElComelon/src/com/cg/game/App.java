package com.cg.game;

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
import java.util.Random;
import javax.swing.JFrame;

import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * Creación de Ventanas de Juego
 *
 * @author Juliana Castellanos García Christian Granada Joseph Hermann Muñoz
 * Camilo Sandoval Harrison Zapata
 *
 * @date Mayo de 2014
 * @version 1.0
 *
 */
public class App extends JFrame implements KeyListener, ActionListener {

    private Lienzo lienzo;
    private Timer caida;
    private Timer ene;
    private Nino baby;
    private Random posO;
    private LinkedList<Objeto> objetos;
    private int contador;
    private int cambioCaida;
    private int cambioEnemigo;
    private int cambios;
    private Inicio i;

    /**
     * Constructor. Inicializa la ventana de juego y los atributos de la clase.
     * Asigna el tiempo para los Timers,crea un objeto tipo Niño, crea una lista
     * de objetos, instancia un lienzo, añade un keylistener y determina el
     * tamaño de la ventana.
     *
     * @param title Título que aparecerá en el marco de la ventana
     * @param ancho Número entero que será el ancho de la ventana en píxeles
     * @param largo Número entero que será el largo de la ventana en píxeles
     * @throws HeadlessException
     */
    public App(Inicio i) throws HeadlessException {

        super("Jake El Comelón");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.i = i;
        setIconImage(Ventana.getIcono());
        cambios = 0;
        cambioCaida = 100;
        cambioEnemigo = 1400;
        caida = new Timer(cambioCaida, this);
        caida.setActionCommand("timeOut");
        caida.start();

        ene = new Timer(cambioEnemigo, this);
        ene.setActionCommand("start");
        ene.start();

        posO = new Random();

        baby = new Nino(new Point(120, 480));
        baby.cargarImagenes();


        objetos = new LinkedList<Objeto>();

        lienzo = new Lienzo(i.getPlayer(), baby, objetos);
        add(BorderLayout.CENTER, lienzo);
        lienzo.setToolTipText("GameArea");

        contador = 10;

        addKeyListener(this);

        setSize(328, 600);
        setResizable(false);



    }

    /**
     * Crea un objeto al azar de tipo enemigo, comida o peluche, con el 45% de
     * probabilidades de obtener un enemigo, 45% de obtener comida y 10% de
     * obtener un peluche. Añade el objeto a la lista de objetos. Repinta el
     * lienzo.
     */
    public synchronized void crearObjetos() {

        Point e = new Point(posicionarO(), 18);
        double tipo;
        tipo = Math.random();



        if (tipo < 0.45) {
            objetos.add(new Enemigo(e));
        } else {
            if (tipo < 0.9) {
                objetos.add(new Comida(e));
            } else {
                objetos.add(new Peluche(e));
            }
        }


        lienzo.repaint();
    }

    /**
     * Se asegura de que el Nino no salga de los limites horizontales de la
     * ventana
     */
    public void limitarP() {
        if (baby.getX() < 0) {
            baby.setPosicion(new Point(0, 480));
        }
        if (baby.getX() > 280) {
            baby.setPosicion(new Point(280, 480));
        }
    }

    /**
     * Genera una posicion en x al azar, de 8 posibles, para soltar el objeto
     * desde ahi.
     *
     * @return devuelve un entero con la coordenada x
     */
    public int posicionarO() {

        int salida;
        salida = posO.nextInt(8);

        salida = salida * 40;
        return salida;
    }

    /*
     * Mueve los objetos desde la posicion mas alta hasta la mas baja en el eje y
     * y los vuelve invisibles en la posicion mas baja.Repinta el lienzo.
     */
    public synchronized void fall() {

        for (Objeto o : objetos) {
            o.getPosicion().translate(0, 10);

            //TODO: Verificar si los objetos se salen de la pantalla
            if (o.getY() > 500) {
                o.setVisible(false);
            }
        }

        lienzo.repaint();

    }

    /**
     * Elimina un objeto de la lista cuando es invisible, es decir cuando ha
     * llegado al suelo.
     */
    public void eliminarObjeto() {
        boolean borrado = false;
        for (ListIterator<Objeto> it = objetos.listIterator(); !borrado && it.hasNext();) {
            Objeto objeto = it.next();
            if (!objeto.isVisible()) {
                objetos.remove(objeto);
                borrado = true;
            }

        }
    }

    /**
     * Verifica la vida del jugador, cuando esta llega a cero detiene los timers
     * y la App, muestra el puntaje del jugador y lo devuelve a la pantalla de
     * inicio
     */
    public void muerte() {
        if (i.getPlayer().getVida() <= 0) {

            int puntos = i.getPlayer().getPuntos();
            guardarJugador(i.getPlayer());
            caida.stop();

            ene.stop();
            JOptionPane.showMessageDialog(null, "Juego terminado " + i.getPlayer().getNombre() + " \n Su Puntaje es : " + puntos);

            dispose();

            i.setVisible(true);
        }


    }

    /**
     * Ajusta la velocidad de caida de los enemigos y la frecuencia con la que
     * caen, cambiando el tiempo de accion de los respectivos timers deacuerdo
     * al puntaje del jugador
     */
    public void dificultad() {



        if (i.getPlayer().getPuntos() > contador) {


            if (cambios % 2 == 0) {
                cambioCaida = (int) (0.70 * (double) cambioCaida);

            } else {
                cambioEnemigo = (int) (0.60 * (double) cambioEnemigo);

            }

            if (cambioCaida <= 20) {
                cambioCaida = 50;

            }
            if (cambioEnemigo <= 200) {
                cambioEnemigo = 250;
            }

            cambios++;

            contador += 5;
            caida.setDelay(cambioCaida);
            ene.setDelay(cambioEnemigo);

        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Mueve al nino de derecha a izquierda en la pantalla de juego dependiendo
     * de la tecla presionada por el jugador
     *
     * @param e recibe el evento de teclado (tecla presionada)
     */
    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {

            case KeyEvent.VK_RIGHT:
                baby.mover(e);

                baby.getPosicion().translate(40, 0);

                break;

            case KeyEvent.VK_LEFT:
                baby.mover(e);

                baby.getPosicion().translate(-40, 0);
                break;
        }
        limitarP();

        lienzo.repaint();

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    /**
     * Administralas acciones a ser ralizadas cuando se dispara un evento
     * atraves de los comandos. En este caso llama los metodos necesarios cada
     * vez que se activa el timer que controla la velocdad del enemigo, y el
     * timer de frecuencia de creacion de enemigos.
     *
     * @param e recibe el evento lanzado por el comando
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String event = e.getActionCommand();

        if (event.equals("timeOut")) {
            fall();
            eliminarObjeto();
            muerte();
            dificultad();


        }
        if (event.equals("start")) {
            crearObjetos();
        }

    }

    /**
     * Guarda el jugador en el documento xml
     * @param j jugador a ser guardado
     */
    public void guardarJugador(Jugador j) {
        Archivo domR = new Archivo();
        ArrayList<Jugador> jugadores = domR.XMLtoJugador("jugadores.xml");

        jugadores.add(j);
        Collections.sort(jugadores);


        // creamos los procesadores en DOM y en SAX
        Archivo dom = new Archivo();
//                                
        // escritura con DOM
        dom.jugadorToXML(jugadores, "jugadores.xml");
        jugadores=domR.XMLtoJugador("jugadores.xml");

    }
}
