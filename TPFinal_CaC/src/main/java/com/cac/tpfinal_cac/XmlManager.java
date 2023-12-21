package com.cac.tpfinal_cac;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.SAXException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlManager {

    private static final String XML_FILE_PATH = "D:\\FABRIZIOUSER\\Documentos\\NetBeansProjects\\ProyectosWeb\\TPFinal_CaC\\oradores.xml";

    public static void insertData(int id, String nombre, String apellido, String email, Date fechaAlta, String tema)
            throws IOException, ParserConfigurationException, TransformerException, SAXException {

        Path path = Paths.get(XML_FILE_PATH);

        // Crear archivo XML si no existe
        if (!path.toFile().exists()) {
            createInitialXml();
        }

        // Abrir el archivo XML
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(XML_FILE_PATH);
        // Verificar si la etiqueta "oradores" ya existe
        NodeList oradoresList = doc.getElementsByTagName("oradores");
        Element rootElement;
        if (oradoresList.getLength() == 0) {
            // Si no existe, crearla y agregarla al documento
            rootElement = doc.createElement("oradores");
            doc.appendChild(rootElement);
        } else {
            // Si ya existe, obtenerla del documento
            rootElement = (Element) oradoresList.item(0);
        }

        // Crear un nuevo elemento para los datos
        Element oradorElement = doc.createElement("orador");

        Element idElement = doc.createElement("id");
        idElement.appendChild(doc.createTextNode(String.valueOf(id)));
        oradorElement.appendChild(idElement);

        Element nombreElement = doc.createElement("nombre");
        nombreElement.appendChild(doc.createTextNode(nombre));
        oradorElement.appendChild(nombreElement);

        Element apellidoElement = doc.createElement("apellido");
        apellidoElement.appendChild(doc.createTextNode(apellido));
        oradorElement.appendChild(apellidoElement);

        Element emailElement = doc.createElement("email");
        emailElement.appendChild(doc.createTextNode(email));
        oradorElement.appendChild(emailElement);

        if (fechaAlta != null) {
            // Convertir Date a LocalDate si es necesario

            // Formatear la fecha como una cadena
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            // String fechaAltaStr = localDate.format(formatter); // si usas LocalDate
            String fechaAltaStr = fechaAlta.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(formatter);

            // Crear el elemento fecha-alta en el XML
            Element fechaAltaElement = doc.createElement("fecha-alta");
            fechaAltaElement.appendChild(doc.createTextNode(fechaAltaStr));
            oradorElement.appendChild(fechaAltaElement);
        }

        Element temaElement = doc.createElement("tema");
        temaElement.appendChild(doc.createTextNode(tema));
        oradorElement.appendChild(temaElement);

        // Agregar el nuevo elemento al elemento raíz
        rootElement.appendChild(oradorElement);

        // Guardar los cambios en el archivo XML
        saveXml(doc);
    }

    public static void updateData(int id, String nombre, String apellido, String email, Date fechaAlta, String tema)
            throws IOException, ParserConfigurationException, TransformerException, org.xml.sax.SAXException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(XML_FILE_PATH);

        NodeList oradoresList = doc.getElementsByTagName("orador");

        for (int i = 0; i < oradoresList.getLength(); i++) {
            Node oradorNode = oradoresList.item(i);

            if (oradorNode.getNodeType() == Node.ELEMENT_NODE) {
                Element oradorElement = (Element) oradorNode;

                int oradorId = Integer.parseInt(oradorElement.getElementsByTagName("id").item(0).getTextContent());

                if (oradorId == id) {
                    // Actualizar datos del orador
                    Element nombreElement = (Element) oradorElement.getElementsByTagName("nombre").item(0);
                    nombreElement.setTextContent(nombre);

                    Element apellidoElement = (Element) oradorElement.getElementsByTagName("apellido").item(0);
                    apellidoElement.setTextContent(apellido);

                    Element emailElement = (Element) oradorElement.getElementsByTagName("email").item(0);
                    emailElement.setTextContent(email);

                    if (fechaAlta != null) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String fechaAltaStr = dateFormat.format(fechaAlta);

                        Element fechaAltaElement = (Element) oradorElement.getElementsByTagName("fecha-alta").item(0);
                        fechaAltaElement.setTextContent(fechaAltaStr);
                    }

                    Element temaElement = (Element) oradorElement.getElementsByTagName("tema").item(0);
                    temaElement.setTextContent(tema);

                    // Guardar los cambios en el archivo XML
                    saveXml(doc);
                    return; // Salir del bucle una vez que se ha actualizado el orador
                }
            }
        }
    }

    public static void deleteData(int id)
            throws IOException, ParserConfigurationException, TransformerException, org.xml.sax.SAXException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(XML_FILE_PATH);

        NodeList oradoresList = doc.getElementsByTagName("orador");

        for (int i = 0; i < oradoresList.getLength(); i++) {
            Node oradorNode = oradoresList.item(i);

            if (oradorNode.getNodeType() == Node.ELEMENT_NODE) {
                Element oradorElement = (Element) oradorNode;

                int oradorId = Integer.parseInt(oradorElement.getElementsByTagName("id").item(0).getTextContent());

                if (oradorId == id) {
                    // Eliminar el nodo del orador
                    oradorNode.getParentNode().removeChild(oradorNode);

                    // Guardar los cambios en el archivo XML
                    saveXml(doc);
                    return; // Salir del bucle una vez que se ha eliminado el orador
                }
            }
        }
    }

    private static void createInitialXml() throws IOException, ParserConfigurationException, TransformerException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();

        // Crear la estructura inicial del archivo XML
        Element rootElement = doc.createElement("oradores");
        doc.appendChild(rootElement);

        // Guardar la estructura inicial en el archivo XML
        saveXml(doc);
    }

    private static void saveXml(Document doc) throws TransformerException, IOException {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        // Configurar la salida para omitir la declaración XML (<?xml ...?>)
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");

        // Configurar la salida para no agregar espacios adicionales
        transformer.setOutputProperty(OutputKeys.INDENT, "no");

        // Crear un flujo de caracteres para escribir en el archivo
        StringWriter writer = new StringWriter();
        transformer.transform(new DOMSource(doc), new StreamResult(writer));

        // Eliminar los espacios adicionales entre etiquetas
        String compactXml = writer.toString().replaceAll(">\\s+<", "><");

        // Escribir el contenido en el archivo XML
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(XML_FILE_PATH))) {
            bufferedWriter.write(compactXml);
        }
    }
}
