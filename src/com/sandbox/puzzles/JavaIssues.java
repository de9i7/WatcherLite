package com.sandbox.puzzles;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

/**
 *
 */
public class JavaIssues {

    public static void main(String[] args) throws IOException {
        new JavaIssues().run();
    }

    private void run() throws IOException {
        System.out.println("Task 2:");
        task_2();
        System.out.println("-------------------------------");
        System.out.println("Task 4:");
        task_4();
        System.out.println("-------------------------------");
        System.out.println("Print Address:");
        printAddress();
        disasseble();
    }

    private void disasseble() throws IOException {
        File targetFile = new File("D:\\disassemble.txt");

        Runtime rt = Runtime.getRuntime();
        Process pr = rt.exec("javap -c -verbose -classpath D:\\projects\\WatcherLite\\src\\com\\sandbox\\puzzles JavaIssues");
        System.out.println("Process: " + pr);
        BufferedReader reader = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        BufferedReader errorReader = new BufferedReader(new InputStreamReader(pr.getErrorStream()));

        String line = null;
        List<String> lines = new ArrayList<String>();
        while ((line = reader.readLine()) != null) {
            lines.add(line);
            System.out.println(line);
        }

        while ((line = errorReader.readLine()) != null) {
            lines.add(line);
            System.out.println(line);
        }

        FileUtils.writeLines(targetFile, lines);

    }

    /**
     *
     */
    private void task_2() {
        Float f1 = new Float(Float.NaN);
        Float f2 = new Float(Float.NaN);
        System.out.println( ""+ (f1 == f2)+" "+f1.equals(f2)+ " "+(Float.NaN == Float.NaN) );
    }

    /**
     *
     */
    private void task_4(){
        Integer a = 120;
        Integer b = 120;
        Integer c = 130;
        Integer d = 130;

        System.out.println(a == b);
        System.out.println(c == d);
    }

    private void printAddress() throws IOException {
        String s = "one";
        String s2 = "one";
        System.out.println(s);
        System.out.println(s2);
//        System.in.read();
    }


}

