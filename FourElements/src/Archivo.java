import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 * @author juan_pab.aguirre
 * Esta clase se encarga de guardar los jugadores en un archivo Document .xml */
public class Archivo {
    
     private Document dom;
     private static String data;
    
/**
 * Constructor
 * Inicializa el archivo con un documento vacio y un String igualemente vacio
 */
    public Archivo() {
        dom = null;
        data="";
        
    }
    /**
     * Crea al jugador como un elemento del arbol y lo añade al mismo, asignandole sus respectios tributos (nombre y puntos)
     * @param player jugador a ser añadido
     */
    private void addJugador (Jugador player){
        
        Element root=dom.getDocumentElement();
        Element jugador =dom.createElement("Jugador");
        
        Element nombre=dom.createElement("Nombre");
        
        Element puntos=dom.createElement("Puntos");
        
        
        jugador.setAttribute("Nombre", player.getNombre());
        jugador.setAttribute("Puntos", String.valueOf(player.getPuntos()));
        
        root.appendChild(jugador);
       
    }
    
    /**
     * Escribe el arbol en el xml
     * @param sw
     * @param path 
     */
    private void write(StringWriter sw, String path) {
                try {
                        // creamos fichero para escribir en modo texto
                        PrintWriter writer = new PrintWriter(new FileWriter(path));
                        
                        // escribimos todo el arbol en XML
                        writer.println(sw.toString());
                        
                        // cerramos el fichero
                        writer.close();
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }
    
    /**
     * Establece la ruta del jugador a ser guardado
     * @param ruta 
     */
    private void toFile(String ruta) {
                try {
                        // volcamos el XML al fichero
                        TransformerFactory transFact = TransformerFactory.newInstance();
                
                        // añadimos sangrado
                        transFact.setAttribute("indent-number", new Integer(3));
                        Transformer trans = transFact.newTransformer();
                        // incluimos la cabecera XML y el sangrado
                        trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
                        trans.setOutputProperty(OutputKeys.INDENT, "yes");
                
                        // hacemos la transformacion
                        StringWriter sw = new StringWriter();
                        StreamResult sr = new StreamResult(sw);
                        DOMSource domSource = new DOMSource(dom);
                        trans.transform(domSource, sr);
                        
                        // escribimos en el fichero
                        write(sw, ruta);
                } catch(Exception ex) {
                        ex.printStackTrace();
                }
        }
    
    /**
     * Agrega cada jugador a un arrayList 
     * @param jugadores
     * @param ruta 
     */
    public void jugadorToXML(ArrayList<Jugador> jugadores, String ruta) {
//                 creamos un nuevo Document donde vamos a guardar toda la estructura
                try {
                        dom = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
                        Element root = dom.createElement("Jugadores");
                        dom.appendChild(root);
                        
                        
                } catch (ParserConfigurationException e) {
                        e.printStackTrace();
                }
                
                for(int i = 0; i < jugadores.size(); i++)
                        addJugador(jugadores.get(i));
                
                // volcamos por pantalla
                toFile(ruta);
        }
    
    /**
     *  Metodo que busca un jugador
     * @param nodoJugador
     * @return Nombre y puntos de los jugadores
     */
     private Jugador readJugador(Node nodoJugador) {
                Element elementoPedido = (Element)nodoJugador;
                
                String nombreJugador=elementoPedido.getAttribute("Nombre");
                int puntosJugador=Integer.valueOf(elementoPedido.getAttribute("Puntos"));
                
                Jugador j=new Jugador (nombreJugador, puntosJugador);
                

                return j;
        }

     /**
      * Metodo que retorna todos los jugadores que han sido agregados a la lista
      * @param ruta
      * @return Lista de jugadores
      */
     
     public ArrayList<Jugador> XMLtoJugador(String ruta) {
                ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
                
                try {
                        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                        DocumentBuilder db = dbf.newDocumentBuilder();
                        dom = db.parse(new File(ruta));
                        dom.getDocumentElement().normalize();
                        
                        // seleccionamos todos los pedidos y vamos leyendo
                        NodeList listaJugadores = dom.getDocumentElement().getElementsByTagName("Jugador");
                        for(int i = 0; i < listaJugadores.getLength(); i++) {
                                jugadores.add(readJugador(listaJugadores.item(i)));
                                data +=jugadores.get(i)+"\n " ;
                                
//                                System.out.print(jugadores.get(i));
                        }
                } catch (SAXException e) {
                        e.printStackTrace();
                } catch (IOException e) {
                        e.printStackTrace();
                } catch (ParserConfigurationException e) {
                        e.printStackTrace();
                }
                
                return jugadores;
     }

    public static String getData() {
        return data;
    }
        
   
}