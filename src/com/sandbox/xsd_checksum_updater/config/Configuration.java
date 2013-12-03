package com.sandbox.xsd_checksum_updater.config;

import com.sandbox.xsd_checksum_updater.config.validator.InvalidPropertyException;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.Properties;

/**
 * The class reads the configuration.
 * @author E.Balovnev
 */
public class Configuration {

    /** Logger */
    private static final Logger LOG = Logger.getLogger(Configuration.class);

    public static final String CONFIG_FILE = "properties/converter.properties";

    public static final String FILE_ENCODING = "US-ASCII";


    /** ModuleConfiguration instance */
    private static Configuration instance;

    private final Properties props = new Properties();

    /**
     * Instance getter.
     * @return
     * @throws InvalidPropertyException
     */
    public static synchronized Configuration getInstance() throws InvalidPropertyException {
        if (instance == null) {
            instance = new Configuration();
            try {
            	final Reader reader = new FileReader(new File(CONFIG_FILE));
				instance.props.load(reader);
				reader.close();
                instance.trimSpaceInProps();
			} catch (final Exception e) {
				LOG.error(e, e);
	            throw new InvalidPropertyException("Config file =  \"" + CONFIG_FILE + "\", message = " + e + "\"");			}
        }
        return instance;
    }

    /**
     * Trims characters with a code greater than '\u0020' with leading and trailing whitespace in properties
     */
    private void trimSpaceInProps() {

        for(String property : props.stringPropertyNames()){

            props.setProperty(property,props.getProperty(property).trim());
        }
    }

    private Configuration() {
    }

    public String getProperty(final String key) {
        final Object property = this.props.get(key);
        if(property != null) {
            return property.toString();
        }
        return null;
    }
}
