package com.sandbox.common.static_inner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by DKachurovskiy on 11/12/2014.
 */
public class EntryPoint {

    public static void main(String[] args) {
        new EntryPoint().run();
    }

    private void run() {
//        ToolingDataParser.ToolingRecord tr1 = ToolingDataParser.calculateToolingNumberRevisionSheetId("String1");
//        ToolingDataParser.ToolingRecord tr2 = ToolingDataParser.calculateToolingNumberRevisionSheetId("String2");
//        ToolingDataParser.ToolingRecord tr3 = ToolingDataParser.calculateToolingNumberRevisionSheetId("String3");
        String dataToParse = "SOME_sdfsdfsdfsdfs123END";
        Pattern pattern = Pattern.compile("^([^_]+)_(.+)_([^_]+)$");
        Matcher matcher = pattern.matcher(dataToParse);

        if(dataToParse.indexOf("_") ==
                dataToParse.lastIndexOf("_")){
            String[] items = dataToParse.split("_");
            System.out.println("Single >>");
            System.out.println(items[0]);
            System.out.println(items[1]);
        } else {
            System.out.println("Double >>");
            if (matcher.matches()) {
                System.out.println(matcher.group(1));
                System.out.println(matcher.group(2));
                System.out.println(matcher.group(3));
            }
        }



        System.out.println("End...");
    }
}
