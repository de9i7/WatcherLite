package com.sandbox.emptyness_checker;

import org.apache.commons.io.IOUtils;

import java.io.*;

/**
 *
 */
public class Launch {
    public static final String REPORT_XML_ENCODING = "UTF-8";
    private static final String FILE_TO_TEST = "D:\\Issues\\CR15290\\055921_DELMPL_20080709160055001_empty.xml";

    private static final String HEADER_TAG = "<Header>";
    private static final String REVISION_TAG = "<Revisions>";

    public static void main(String[] args) throws IOException {
        new Launch().run();
    }

    private void run() throws IOException {

        InputStream is = new FileInputStream(FILE_TO_TEST);
        StringWriter sw = new StringWriter();

        try {
            IOUtils.copy(is, sw, REPORT_XML_ENCODING);
        } finally {
            IOUtils.closeQuietly(is);
        }

        BlankReportChecker checker = BlankReportChecker.getInstance();
//        if (checker.isReportBlank(sw.toString(), HEADER_TAG, REVISION_TAG)) {
//            System.out.println("File is empty: " + FILE_TO_TEST + "!");
//        } else {
//            System.out.println("File is NOT empty: " + FILE_TO_TEST + "!");
//        }
    }
}
