package com.tools.watcher;

import com.tools.watcher.application.EnoviaApplication;
import com.tools.watcher.framework.exceptions.InitializationException;

/**
 *
 */
public class EntryPoint {

    public static void main(String[] args) {
        new EntryPoint().run();
    }

    private void run() {
        // TODO: Define instance controller dynamically
        //Read configuration from properties

        //Construct Gui controller based on configuration
        try {
            EnoviaApplication.launch();
        } catch (InitializationException e) {
            e.printStackTrace();
        }
//
//        GuiController.build()
//        if (guiController.build()){
//            // Set controller to view
//
//            GuiBuilder builder = ;
//
//        }
    }
}
