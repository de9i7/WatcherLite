package com.sandbox.xml_parsing;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DKachurovskiy on 7/24/2015.
 */
public class CoordinatesParser {


    public static void main(String[] args) throws Exception {
        new CoordinatesParser().run();

    }

    private Document parseXML(InputStream stream)
            throws Exception {
        DocumentBuilderFactory objDocumentBuilderFactory = null;
        DocumentBuilder objDocumentBuilder = null;
        Document doc = null;
        try {
            objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
            objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();

            doc = objDocumentBuilder.parse(stream);
        } catch (Exception ex) {
            throw ex;
        }

        return doc;
    }

    private void run() throws Exception {
        String values = "Saudi Arabia\n" +
                "Riyadh\n" +
                "Jeddah\n" +
                "United Arab Emirates\n" +
                "Dubai\n" +
                "Tanzania\n" +
                "Dar Es Salaam\n" +
                "Kenya\n" +
                "Nairobi\n" +
                "Morocco\n" +
                "Casablanca\n" +
                "Egypt\n" +
                "Cairo\n" +
                "Lebanon\n" +
                "Lebanon\n" +
                "Algeria\n" +
                "Algiers\n" +
                "Jordan\n" +
                "Amman\n" +
                "South Africa\n" +
                "Cape Town\n" +
                "Johannesburg";

        String values2 = "Niger\n" +
                "Niamey\n" +
                "Angola\n" +
                "Luanda\n" +
                "Chad\n" +
                "N'Djamena\n" +
                "Ethiopia\n" +
                "Addis Ababa\n" +
                "Mali\n" +
                "Bamako\n" +
                "Namibia\n" +
                "Windhoek\n" +
                "Congo\n" +
                "Brazzaville\n" +
                "Sudan\n" +
                "Khartoum\n" +
                "Mauritania\n" +
                "Nouakchott\n" +
                "Mozambique\n" +
                "Maputo\n" +
                "Zimbabwe\n" +
                "Harare\n" +
                "Botswana\n" +
                "Gaborone\n" +
                "Cameroon\n" +
                "Yaounde\n" +
                "Gabon\n" +
                "Libreville\n" +
                "Guinea\n" +
                "Conakry\n" +
                "Madagascar\n" +
                "Antananarivo\n" +
                "Central African Republic\n" +
                "Bangui\n";

        String values3 = "Libya\n" +
                "Tripoli\n" +
                "Yemen\n" +
                "Sana'a\n" +
                "Oman\n" +
                "Muscat";

        String values4 = "Venezuela\n" +
                "Caracas\n" +
                "Panama\n" +
                "Panama City\n" +
                "Belize\n" +
                "Belize City\n" +
                "Brazil\n" +
                "Paraguay\n" +
                "Asuncion\n" +
                "El Salvador\n" +
                "San Salvador\n" +
                "Guatemala\n" +
                "Guatemala City\n" +
                "Guyana\n" +
                "Georgetown\n" +
                "Honduras\n" +
                "Tegucigalpa\n" +
                "Roatan\n" +
                "San Pedro Sula\n" +
                "Nicaragua\n" +
                "Managua\n" +
                "Ecuador\n" +
                "Quito\n" +
                "Guayaquil\n" +
                "Colombia\n" +
                "Rafael Nunez\n" +
                "Barranquilla\n" +
                "Cali\n" +
                "Bogota\n" +
                "Medellin\n" +
                "Peru\n" +
                "Cusco\n" +
                "Lima\n" +
                "Chile\n" +
                "Santiago\n" +
                "Costa Rica\n" +
                "San Jose\n" +
                "Liberia\n" +
                "Argentina\n" +
                "Buenos Aires";
        // Africa list
        List<String> list = new ArrayList<>();

        BufferedReader br = new BufferedReader(new StringReader(values4));
        String line;
        while ( (line = br.readLine()) != null) {
            URL url = new URL("http://api.geonames.org/search?q=" + line + "&maxRows=1&username=demo");
            URLConnection connection = url.openConnection();

            Document doc = parseXML(connection.getInputStream());
            NodeList latNodes = doc.getElementsByTagName("lat");
            NodeList longNodes = doc.getElementsByTagName("lng");
            NodeList nameNodes = doc.getElementsByTagName("name");
            NodeList codeCountryNodes = doc.getElementsByTagName("countryCode");
            NodeList countryNodes = doc.getElementsByTagName("countryName");

//        {
//            Region ae = createRegion(africa, "AE", "United Arab Emirates", 0, 0, 0, false, 6.179, 26.446);
//            List<Region> regions = new ArrayList<Region>();
//            addRegion(regions, ae, "DUBAI", "Dubai", 0, 0, 0, true, 5.179, 19.446);
//            worldRegions.put(ae, regions);
//        }
//            Map<String, List<String>> requests = new HashMap<>();
//
//            requests.put("Saudi Arabia", )
//            String resultTemplate = "{\n" +
//                    "            Region %s = createRegion(africa, \"%s\", \"%s\", 0, 0, 0, false, %s, %s);\n" +
//                    "            List<Region> regions = new ArrayList<Region>();\n" +
//                    "            addRegion(regions, ae, \"DUBAI\", \"Dubai\", 0, 0, 0, true, 5.179, 19.446);\n" +
//                    "            worldRegions.put(ae, regions);\n" +
//                    "        }";

            for (int i = 0; i < latNodes.getLength(); i++) {
                System.out.println(", " + latNodes.item(i).getTextContent() + ", " +
                        longNodes.item(i).getTextContent() + " | " +
                        nameNodes.item(i).getTextContent() + " | " +
                        codeCountryNodes.item(i).getTextContent() + " | " +
                        countryNodes.item(i).getTextContent());
            }
        }


    }
}
