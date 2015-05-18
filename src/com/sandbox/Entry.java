package com.sandbox;

import java.io.IOException;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.apache.commons.lang.StringUtils;

/**
 * Created with IntelliJ IDEA.
 * User: DKachurovskiy
 * Date: 12/18/13
 * Time: 2:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class Entry {

    private static final String TRIPLE_DASH_REVISION = "---";
    private static final String DASH_REGEX = "(?=[A-Z\\-]{3})[\\-]{1,2}";

    public static void main(String... args) {
        new Entry().run();
    }

    private void run() {
        System.out.println("Prepare zip: ");
        newZip();
        System.out.println(StringUtils.isBlank(null));
        String input = "-A-";
        System.out.println("INPUT: " + removeDashes(input));
        input = "-A-";
        System.out.println("INPUT: " + removeDashes(input));
        input = "FAB";
        System.out.println("INPUT: " + removeDashes(input));
        input = "-AB";
        System.out.println("INPUT: " + removeDashes(input));
        input = "-AD";
        System.out.println("INPUT: " + removeDashes(input));
        input = "--U";
        System.out.println("INPUT: " + removeDashes(input));
        input = "-P";
        System.out.println("INPUT: " + removeDashes(input));
        input = "---";
        System.out.println("INPUT: " + removeDashes(input));
    }

    private void newZip() {
        ZipFile zipIS = null;
        try {
            zipIS = new ZipFile("");
        } catch (ZipException e) {
            System.out.println("ZIP");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IO");
            e.printStackTrace();
        }
    }

    private static String removeDashes(final String version) {

//        Pattern pattern = Pattern.compile(DASH_REGEX);
//        Matcher matcher = pattern.matcher(version);

        return version.replaceAll(DASH_REGEX, "-");
    }
}
