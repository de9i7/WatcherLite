package com.tools.watcher.application;

import com.tools.watcher.application.gui.GuiBuilder;
import com.tools.watcher.framework.ApplicationContext;
import com.tools.watcher.framework.exceptions.InitializationException;
import org.apache.log4j.Logger;

/**
 *
 */
public class EnoviaApplication {

    /** Logger instance */
    private static final Logger LOG = Logger.getLogger(EnoviaApplication.class);

    private GuiBuilder m_view;

    /**
     *
     */
    public EnoviaApplication() {
    }

    public static void launch() throws InitializationException {
        ApplicationContext ctx = ApplicationContext.build();
        GuiBuilder.build(ctx);
//        File confFile = new File("d:\\prj\\tools\\etl\\trunk\\SourceCode\\EID_ETL\\ETL_ENOVIA\\conf");
//        if (confFile.exists()) {
//            m_propertyHandler = new DefaultLaunchSettingsHandler(configuration);
//            LOG.info("Config path exists.");
//            result = true;
//        }
    }
}
