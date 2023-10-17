package logicaof;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class WriteXML {
    public static void addXMLRecord(String tipo, String numerosCantados, String ganadores, String fecha, String hora) {
        try {
            // Obtener la ruta del escritorio del sistema
            String desktopPath = System.getProperty("user.home") + File.separator + "Desktop";

            // Crear una carpeta "BingoData" en el escritorio si no existe
            File bingoDataFolder = new File(desktopPath, "BingoData");
            if (!bingoDataFolder.exists()) {
                bingoDataFolder.mkdir();
            }

            // Obtener el archivo XML existente o crear uno nuevo si no existe
            File xmlFile = new File(bingoDataFolder, "partidas.xml");

            Document document;
            Element partidasJuego;
            if (!xmlFile.exists()) {
                // Si el archivo no existe, crea un nuevo documento XML y el elemento raíz
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                document = builder.newDocument();
                partidasJuego = document.createElement("partidasJuego");
                document.appendChild(partidasJuego);
            } else {
                // Si el archivo ya existe, abre el documento XML existente
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                document = builder.parse(xmlFile);
                partidasJuego = document.getDocumentElement();
            }

            // Crear un elemento "partida" para la nueva entrada
            Element partida = document.createElement("partida");
            partidasJuego.appendChild(partida);

            // Crear un elemento "tipo" y establecer su valor
            Element tipoElement = document.createElement("tipo");
            Text tipoText = document.createTextNode(tipo);
            tipoElement.appendChild(tipoText);
            partida.appendChild(tipoElement);

            // Crear un elemento "números Cantados" y establecer su valor
            Element numerosCantadosElement = document.createElement("númerosCantados");
            Text numerosText = document.createTextNode(numerosCantados);
            numerosCantadosElement.appendChild(numerosText);
            partida.appendChild(numerosCantadosElement);

            // Crear un elemento "ganadores" y establecer su valor
            Element ganadoresElement = document.createElement("ganadores");
            Text ganadoresText = document.createTextNode(ganadores);
            ganadoresElement.appendChild(ganadoresText);
            partida.appendChild(ganadoresElement);

            // Crear un elemento "fecha" y establecer su valor
            Element fechaElement = document.createElement("fecha");
            Text fechaText = document.createTextNode(fecha);
            fechaElement.appendChild(fechaText);
            partida.appendChild(fechaElement);

            // Crear un elemento "hora" y establecer su valor
            Element horaElement = document.createElement("hora");
            Text horaText = document.createTextNode(hora);
            horaElement.appendChild(horaText);
            partida.appendChild(horaElement);

            // Guardar el documento XML en el archivo
            FileOutputStream fos = new FileOutputStream(xmlFile);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(fos);
            transformer.transform(source, result);

            System.out.println("Entrada XML agregada exitosamente en: " + xmlFile.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
