package com.sandbox.projects_parser;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.stax.StAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.commons.io.FileUtils;
import org.xml.sax.SAXException;

/**
 *
 */
public class IRMProcessConfigParser {

    private static final String XML_SERVER = "server";
    private static final String XML_PROCESS = "process";
    private static final String XML_MODEL = "model";


    /**
     *
     */
    public IRMProcessConfigParser() {
    }

    public List<IRMProcess> parseJAXB(File xmlFile) {
        List<IRMProcess> result = null;
        try {
            System.out.println("File exists: " + xmlFile.exists());

//            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
//            Schema schema = factory.newSchema(new File("irm_processes.xsd"));


//            Processes servers = (Processes) JAXB.unmarshal(xmlFile, Processes.class);
//            result = servers.getProcesses();

            JAXBContext jaxbContext = JAXBContext.newInstance(Processes.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
//            JAXB.unmarshal(xmlFile, Processes.class);
            Processes servers = (Processes) unmarshaller.unmarshal(xmlFile);
            result = servers.getProcesses();

            System.out.println("REsult: " + result);
        } catch (JAXBException e) {
            e.printStackTrace();
            System.out.println("JAXB error.");
        }

        System.out.println("Result: " + printResult(result));
        return result;
    }

    private static String printResult(List<IRMProcess> processes) {

        StringBuilder sb = new StringBuilder();
        for (IRMProcess process : processes) {
            sb.append("     Servers: ").append(process.getServer()).append(System.getProperty("line.separator"));
            sb.append("     Path: ").append(process.getPath()).append(System.getProperty("line.separator"));
            sb.append("     Models: ").append(System.getProperty("line.separator"));
            List<String> models = process.getModel();
//            for (String mod : models) {
//                sb.append("             ").append(mod).append(System.getProperty("line.separator"));
//            }
        }

        return sb.toString();
    }


    public static final Map<String, List<IRMProcess>> parse(String configPath) throws XMLStreamException, IOException, SAXException {

        Map<String, List<IRMProcess>> servers = new HashMap<String, List<IRMProcess>>();

        final String configContent = FileUtils.readFileToString(new File(configPath));

        // Read PartType value from header "as is"
        StringReader headerReader = new StringReader(configContent);
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLEventReader reader = xmlInputFactory.createXMLEventReader(headerReader);

        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = factory.newSchema(new File("projects.xsd"));

        Validator validator = schema.newValidator();
        validator.validate(new StAXSource(reader));

        System.out.println("Schema is valid.");

        try {
            boolean isHeader = false;
            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();
                if (event.isStartElement()) {
                    if (isEvent(event, XML_SERVER)) {

                    } else if (isEvent(event, XML_PROCESS)) {

                    } else if (isEvent(event, XML_MODEL)) {

                    }

                    isEvent(event, XML_SERVER);
                    isHeader = true;
                } else if (event.isCharacters() && isHeader) {

                    break;
                }
            }
        } finally {
            reader.close();
            headerReader.close();
        }

        return servers;
    }

    /**
     *
     * @param event
     * @param xmlServer
     * @return
     */
    private static boolean isEvent(XMLEvent event, String xmlServer) {
        return event.asStartElement().getName().getLocalPart().equalsIgnoreCase(xmlServer);
    }

}