/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cg.game;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javax.swing.JOptionPane;

/**
 * Creación de Ventanas de Inicio
 *
 * @author Juliana Castellanos García Christian Granada Joseph Hermann Muñoz
 * Camilo Sandoval Harrison Zapata
 *
 * @date Mayo de 2014
 * @version 1.0
 *
 */
public class Inicio extends Ventana {

    private int boton;
    private Reglas ins;
    private Jugador player;
    public static Player reproductor;

    /**
     * Contructor. Inicializa las ventanas de inicio. Le manda a la case ventana
     * el nombre de la imagen de fondo a cargar y el titulo de la ventana
     */
    public Inicio() {
        super("Pinicio", "Inicio");

        boton = 0;

        ins = new Reglas(this);

    }

    /**
     * Getter del jugador
     *
     * @return devuleve un jugador
     */
    public Jugador getPlayer() {
        return player;
    }

    /**
     * Carga y reproduce un sonido de ambientacion para el juego
     */
    public static void reproducir() {
        try {

            FileInputStream sonido = new FileInputStream("Cancion.mp3");
            BufferedInputStream sonidocar = new BufferedInputStream(sonido);

            reproductor = new Player(sonidocar);

            reproductor.play();
        } catch (JavaLayerException ev) {
            ev.printStackTrace();
        } catch (FileNotFoundException ev) {
            ev.printStackTrace();
        }
    }

    /**
     * Administra los clicks del mouse Identifica que boton de la pantalla ha
     * sido elegido y realiza la accion determinada para esa opcion
     *
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {


        boton = 0;
        double posi;
        posi = Colisiones.distancia(e.getPoint(), new Point(162, 289));

        if (posi < 52) {
            boton = 1;

        }
        if (Colisiones.deteccion(e.getPoint(), new Point(177, 363))) {
            boton = 2;
        }
        if (Colisiones.deteccion(e.getPoint(), new Point(178, 422))) {
            boton = 3;
        }
        if (Colisiones.deteccion(e.getPoint(), new Point(177, 479))) {
            boton = 4;
        }
        if (Colisiones.deteccion(e.getPoint(), new Point(178, 537))) {
            boton = 5;
        }


        switch (boton) {
            case 1:
                String nomjugador = JOptionPane.showInputDialog("Nombre del Jugador");
                player = new Jugador(nomjugador);
                App aplicacion = new App(this);
                aplicacion.setVisible(true);
                this.setVisible(false);
                break;
            case 2:
                ins.setVisible(true);
                this.setVisible(false);



                break;
            case 3:
                
                JOptionPane.showMessageDialog(null, "Puntos \n" +Archivo.getData());

                break;
            case 4:
                JOptionPane.showMessageDialog(null, "Realizado por Ingenieros MultiMedia de la UAO"
                        + "\n               Juliana Castellanos García"
                        + "\n                      Christian Granada"
                        + "\n                 Joseph Hermann Muñoz"
                        + "\n                      Camilo Sandoval"
                        + "\n                      Harrison Zapata");

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
        // TODO code application logic here

        Inicio i = new Inicio();
        i.setVisible(true);
        reproducir();



    }
}
