package com.sandbox.xml_parsing;

import org.apache.commons.io.IOUtils;
import org.xml.sax.InputSource;

/**
 * Created by DKachurovskiy on 8/22/2014.
 */
public class SaxTest {

    public static void main(String[] args) {
        new SaxTest().run();
    }

    /**
     *
     */
    private void run() {
        String fileName = "";
        String content = "<ivt-conn>" +
                "<result-code>0</result-code>" +
                "<message>ERROR \"path\" some</message>" +
                "</ivt-conn>";
        IRMResult result = IRMResultParser.parse(new InputSource(IOUtils.toInputStream(content)));
        System.out.println("RESULT: " + IOUtils.toInputStream(content));
        System.out.println("RESULT CONTENT: " + result.getErrorMessage());
    }


}
