import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class Personatge {
    private String nom;
    private int nivell;
    private int puntsDeVida;
    private int puntsDeMana;
    private String arma;
    private String armadura;

    public Personatge(String nom, int nivell, int puntsDeVida, int puntsDeMana, String arma, String armadura) {
        this.nom = nom;
        this.nivell = nivell;
        this.puntsDeVida = puntsDeVida;
        this.puntsDeMana = puntsDeMana;
        this.arma = arma;
        this.armadura = armadura;
    }
    
    public static void main(String[] args) {
        carregarPersonatgesDesDeXML("personatges.xml");
    }

    public static void carregarPersonatgesDesDeXML(String rutaArxiu) {
        try {
            File arxiuXML = new File(rutaArxiu);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(arxiuXML);
            doc.getDocumentElement().normalize();

            NodeList llistaPersonatges = doc.getElementsByTagName("personatge");

            for (int i = 0; i < llistaPersonatges.getLength(); i++) {
                Node nodePersonatge = llistaPersonatges.item(i);
                if (nodePersonatge.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementPersonatge = (Element) nodePersonatge;
                    String nom = elementPersonatge.getElementsByTagName("nom").item(0).getTextContent();
                    int nivell = Integer.parseInt(elementPersonatge.getElementsByTagName("nivell").item(0).getTextContent());
                    int puntsDeVida = Integer.parseInt(elementPersonatge.getElementsByTagName("puntsDeVida").item(0).getTextContent());
                    int puntsDeMana = Integer.parseInt(elementPersonatge.getElementsByTagName("puntsDeMana").item(0).getTextContent());
                    String arma = elementPersonatge.getElementsByTagName("arma").item(0).getTextContent();
                    String armadura = elementPersonatge.getElementsByTagName("armadura").item(0).getTextContent();

                    Personatge nouPersonatge = new Personatge(nom, nivell, puntsDeVida, puntsDeMana, arma, armadura);
                    System.out.println("Personatge carregat: " + nouPersonatge);
                }
            }
        } catch (Exception e) {
            gestionarError(e);
        }
    }

    public static void gestionarError(Exception e) {
        e.printStackTrace();
    }

    @Override
    public String toString() {
        return "Personatge{" +
                "nom='" + nom + '\'' +
                ", nivell=" + nivell +
                ", puntsDeVida=" + puntsDeVida +
                ", puntsDeMana=" + puntsDeMana +
                ", arma='" + arma + '\'' +
                ", armadura='" + armadura + '\'' +
                '}';
    }
}
