package com.sandbox.common;

import org.apache.commons.lang.StringUtils;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.sql.Date;

/**
 * Created by DKachurovskiy on 8/21/2014.
 */
public class RegExpCheck {


    public static void main(String[] args) {
        RegExpCheck firstTest = new RegExpCheck();
        firstTest.run();
        firstTest.amadeusFault();

    }

    private void amadeusFault() {
        String content = "<soap:Fault xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "  <faultcode>soap:Client</faultcode>\n" +
                "  <faultstring>18|Presentation|Fusion DSC found an exception !  The data does not match the minimum length  For data element: departureDate  Data length should be at least 6 and at most 6  Current position in buffer: 12345&lt;/departureDate&gt;      &lt;/  Last 15 characters before error: &lt;departureDate&gt;  Current path in the grammar : /requestSection#1/availabilityProductInfo#1/availabilityDetails#1/departureDate#1</faultstring>\n" +
                "  <faultactor>SI:APA</faultactor>\n" +
                "</soap:Fault>";

        System.out.println("Amadeus Matches: " + content.contains("soap:Fault"));

        String time = "710";
        System.out.println("Mintes: " + StringUtils.substring(time, time.length() - 2, time.length()));
        System.out.println("Hours: " + StringUtils.substring(time, 0, time.length() - 2));

        String totalPrice = "sometxt";
        System.out.println(totalPrice.substring(0, 3));
        totalPrice = "sometxt";
        System.out.println(totalPrice.substring(3));

        DateTimeFormatter dateTimeFormatter =  DateTimeFormat.forPattern("yyyy-MM-dd");
        try {
            Date date = new Date(dateTimeFormatter.parseDateTime("9999-99-99").getMillis());
            System.out.println("Parsed date: " + date.toString());
        } catch (Exception e) {
            System.out.println("SOme: " + e.getMessage());
        }

    }

    private void run() {
        String content = "sd# Datastore:737 - US Persons\r\n" +
                "# Created: Wed Mar 26 2014\r\n" +
                "ff051b54b88683f95ee93e6f164409ee\r\n" +
                "#feb2a50aab8d35a0822009a99dab73f3\r\n" +
                "fe9921d361d27f055a4dc46265aad4c2\r\n" +
                "fe2aed4fb136#50a84812346af5e313ed\r\n" +
                "fe1fead21e2d44e24dba08d7662c7dfc#\r\n" +
                "#fe0aa36e7abc446b78295b6692a9e50b\r\n" +
                "fd14db0c93d5eabd24a110ee176a7f5d";
        String replaced = content.replaceAll("(\\r\\n#|^#).*", "");
//        String replaced = content.replaceAll("#.*", "\r\n");
//        String replaced = content.replaceAll("(#.*|(\"(?:#[^\\\"]|#\"|.)*?\"))\\r\\n", "\\r\\n");
        System.out.println("Replaced: " + replaced);

        String content2 = "ERROR: attribute \"path\" not available";
        String replaced2 = content2.toString().trim().replace("\"", "");
        System.out.println("REPLACED: "  + replaced2);
    }


}
