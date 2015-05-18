package com.sandbox.projects_parser;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

/**
 * Created with IntelliJ IDEA.
 * User: DKachurovskiy
 * Date: 5/27/14
 * Time: 11:41 AM
 * To change this template use File | Settings | File Templates.
 */
public class EntryPoint {

    public static void main(String[] args) {
        new EntryPoint().run();
    }

    /**
     *
     */
    private void run() {
        IRMProcessConfigParser parser = new IRMProcessConfigParser();
        parser.parseJAXB(new File("D:/irm_context.xml"));

        try {
            String content = FileUtils.readFileToString(new File("d:\\Issues\\CR15829\\288A4727-2TEST~---~Approved~20140401170807 extracted\\4727-2.ivtm.txt"));
            content = content.replaceAll("(#.*|(\"(?:#[^\\\"]|#\"|.)*?\"))\\r\\n", "");
            // //.*|("(?:#[^\"]|#"|.)*?")|(?s)/\*.*?\*/
            // ^#[\w\s]*\r\n
            System.out.println("CONTENT: " + content);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0L);
        System.out.println(cal.getTime());
        cal.setTimeInMillis(720000L);
        System.out.println(cal.getTime());

        String var1 = "288A";
        String var2 = "288B";
        List<String> items = new ArrayList<String>();
        items.add("288A");
        items.add("288Bsdf-=sdf=-=");
        System.out.println("__CONTAINS: " + items.contains(var1));

        List<String> items2 = new ArrayList<String>();
        items2.add("288A");
        items2.add("288Bsdf-=sdf=-=-");
//        items2.add("288C");

        List<Pattern> patterns = createPIPatternsList(items);
        System.out.println("Result: " + isIrmPA("Something288AGsomeothertext", patterns));
//d:\projects\WatcherLite\config\
        checkPath("..\\..\\14.0_devplan.xlsx");

        System.out.println("Equal: " + isEqualPaths("..\\..\\14.0_devplan.xlsx", "D:\\14.0_devplan.xlsx"));

        System.out.println("EQUAL: " + isModelListsEqual(items, items2));
    }

    private boolean isModelListsEqual(List<String> models, List<String> models1) {
        if (models1 == null) {
            return false;
        }

        if (models.size() != models1.size()) {
            return false;
        }

        Collections.sort(models);
        Collections.sort(models1);

        for (int i = 0; i < models.size(); i++) {
            if (!models.get(i).equalsIgnoreCase(models1.get(i))) {
                return false;
            }
        }
        return true;
    }

    private void checkPath(String s) {
        File file1 = new File("..\\..\\14.0_devplan.xlsx");
        File file2 = new File("D:\\14.0_devplan.xlsx");
        System.out.println("File1: " + file1.exists());
        System.out.println("File2: " + file2.exists());
        if(file1.compareTo(file2) == 0) {
            System.out.println("Both paths are same!");
        } else {
            System.out.println("Paths are not same!");
        }
    }

    public static boolean isPiPartNumber(final String partNumber, final List<String> mask) {
        List<Pattern> list = new LinkedList<Pattern>();
        for (String p : mask) {
            p = ".*" + p + ".*";
            list.add(Pattern.compile(p, Pattern.CASE_INSENSITIVE));
        }


        final Pattern pattern = Pattern.compile(partNumber, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        final Matcher m = pattern.matcher(partNumber);
        return m.matches();
    }

    public static boolean isIrmPA(
            String partNumber,
            List<Pattern> caPartClassifies
    ) {
        return StringUtils.isNotBlank(partNumber)
                && checkForPatterns(caPartClassifies, partNumber);
    }

    public static List<Pattern> createPIPatternsList(List<String> patterns) {
        List<Pattern> list = new LinkedList<Pattern>();
        for (String p : patterns) {
            p = ".*" + p + ".*";
            list.add(Pattern.compile(p, Pattern.CASE_INSENSITIVE));
        }
        return list;
    }

    public static boolean checkForPatterns(List<Pattern> patterns, String s) {
        if (StringUtils.isBlank(s)) {
            return false;
        }

        for (Pattern p : patterns) {
            if (p.matcher(s).matches()) {
                return true;
            }
        }

        return false;
    }

    private boolean isEqualPaths(final String pathLeft, final String pathRight) {
        boolean result = false;

        final File fileLeft = new File(pathLeft);
        System.out.println("Left file: " + fileLeft.getAbsolutePath());
        final File fileRight = new File(pathRight);
        System.out.println("Left file: " + fileRight.getAbsolutePath());

        if (fileLeft.exists() &&
                fileRight.exists() &&
                fileLeft.getAbsolutePath().equalsIgnoreCase(
                        fileRight.getAbsolutePath())
                ) {
            result = true;
        }

        return result;
    }
}
