package com.sandbox.ivtwatcher;

/**
 * Created with IntelliJ IDEA.
 * User: DKachurovskiy
 * Date: 5/20/14
 * Time: 2:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class EntryPoint {

    public static void main(String[] args) {
        new EntryPoint().run();
    }

    private void run() {
        System.out.println("Launched");
        MainPage mainPage = MainPage.build();

        DataMonitor dataMonitor = new DataMonitor(mainPage);
        dataMonitor.launch();

    }
}
