package com.sandbox.common.fileresolver;

import java.io.File;
import java.io.IOException;

/**
 * Created by DKachurovskiy on 9/16/2014.
 */
public class EntryPoint {

    public static void main(String[] args) throws IOException {
        new EntryPoint().run();
    }

    private void run() throws IOException {
//        File tmpDir = new File("\\\\ZWS8-EID-ETL-01\\dkachurovskiy\\to_download.txt");
        File tmpFile = FileSecurityHelper.resolveFile("\\\\ZWS8-EID-ETL-01\\dkachurovskiy\\to_download.txt");
        File tmpPath = FileSecurityHelper.resolveFile(tmpFile.getParent());
//        String fileName = "to_download2.txt";
//

        System.out.println("-- Exists: " + tmpPath.exists());
        try {
            File.createTempFile("check", null, tmpPath).delete();
            System.out.println("You have read/write permition to use : " + tmpPath.getPath());
        } catch (IOException e) {
            System.out.println("You have NOT read/write permition to use : " + tmpPath.getPath());
            return;
        }

        if (!tmpFile.exists()) {
            System.out.println("File DOES NOT exists.");
            return;
        } else {
            System.out.println("File exists.");
        }

    }
}
