package com.sandbox.common;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Created by DKachurovskiy on 10/9/2014.
 */
public class Renamer {

    private static final String DIR_TO_RENAME = "d:\\docs\\music\\from_vk\\";

    public static void main(String[] args) {
        new Renamer().run();
    }

    private void run() {
        File dir = new File(DIR_TO_RENAME);
        String[] dirs = dir.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return !name.endsWith(".covers");
            }
        });
        for (String item : dirs) {
            String basicPath = DIR_TO_RENAME + item;
            File file = new File(basicPath);
            File fileTo = new File(basicPath + ".mp3");
            file.renameTo(fileTo);
            System.out.println("Item: " + fileTo.getName());
        }
    }
}
