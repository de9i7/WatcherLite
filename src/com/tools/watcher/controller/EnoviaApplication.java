package com.tools.watcher.controller;

import com.tools.watcher.gui.GuiBuilder;
import com.sandbox.xsd_checksum_updater.config.Configuration;
import org.apache.log4j.Logger;

import java.io.File;

/**
 *
 */
public class EnoviaApplication extends Application {

    /** Logger instance */
    private static final Logger LOG = Logger.getLogger(EnoviaApplication.class);

    private GuiBuilder m_view;

    /**
     *
     */
    public EnoviaApplication() {
    }

    @Override
    public Application build(Configuration configuration) {
        Application result = new EnoviaApplication();

        File confFile = new File("d:\\prj\\tools\\etl\\trunk\\SourceCode\\EID_ETL\\ETL_ENOVIA\\conf");
        if (confFile.exists()) {
//            m_propertyHandler = new DefaultLaunchSettingsHandler(configuration);
//            LOG.info("Config path exists.");
//            result = true;
        }

//        m_view.set
        return result;
    }
}
