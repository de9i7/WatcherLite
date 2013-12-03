package com.tools.watcher.framework.configuration.validator;

import com.tools.watcher.framework.configuration.Configuration;
import com.tools.watcher.framework.exceptions.InvalidPropertyException;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The class is property validator.
 * @author E.Balovnev
 */
public class Validator {

    /** Logger */
    private static final Logger LOG = Logger.getLogger(Validator.class);

    private static final int MAX_PASSWORD_LENGTH = 1024;

    public static final String PROPERTY_DATE_FORMAT = "MMddyyyy-HHmm";

    private Validator() {
    }

    public static String validateProperty(
		final Configuration configuration,
        final String key
    ) throws InvalidPropertyException {
    	final String result = optionalProperty(configuration, key);
        if (result.isEmpty()) {
            throw new InvalidPropertyException("Config file \"" + Configuration.CONFIG_FILE + "\", key = \"" + key + "\".", key);
        }
        LOG.debug("Validation is successful for configuration file \"" + Configuration.CONFIG_FILE + "\", key = \"" + key + "\". Result = " + result);
        return result;
    }

    public static String optionalProperty(
		final Configuration configuration,
        final String key
	) {
    	String result = configuration.getProperty(key);
        if (result == null) {
        	result =  "";
        }
        LOG.debug("Validation is successful for configuration file \"" + Configuration.CONFIG_FILE + "\", key = \"" + key + "\". Result = " + result);
        return result;
    }


	public static int validateIntProperty(
		final Configuration configuration,
		final String key
    ) throws InvalidPropertyException {
		final int result;
		try {
			result =  Integer.valueOf(validateProperty(configuration, key));
		} catch (final NumberFormatException e) {
			LOG.error(e, e);
            throw new InvalidPropertyException("Config file \"" + Configuration.CONFIG_FILE + "\", key = \"" + key + "\", message = \"" + e + "\"", key);
		}
        LOG.debug("Validation is successful for configuration file \"" + Configuration.CONFIG_FILE + "\", key = \"" + key + "\". Result = " + result);
        return result;
	}

	public static String validatePassword(
		final Configuration configuration,
		final String key
	) throws InvalidPropertyException {
		final String result;
	    final String passwordFile = optionalProperty(configuration, key);
	    if (passwordFile.isEmpty()) {
	    	result = "";
	    } else {
	    	result = readFile(key, passwordFile);
	    }
        LOG.debug("Validation is successful for configuration file \"" + Configuration.CONFIG_FILE + "\", key = \"" + key + "\".");
		return result;
	}

	private static String readFile(final String key, final String fileName) throws InvalidPropertyException {
		String result = "";
		try {
		InputStream stream = null;

		final File file = new File(fileName);
	         if (file.exists()) {
	             try {
	                 stream = new FileInputStream(file);
	                 final byte[] bytes = new byte[MAX_PASSWORD_LENGTH];
	                 final int bytesLength = stream.read(bytes);
	                 if (bytesLength > 0) {
	                	 result = new String(bytes, 0, bytesLength, Configuration.FILE_ENCODING).trim();
	                 }
	             } finally {
	            	 if (stream != null) {
	            		 stream.close();
	            	 }
	             }
	         }
		} catch (final Exception e) {
			LOG.error(e, e);
            throw new InvalidPropertyException("File \"" + fileName + "\"", key);
		}
        LOG.debug("The file was read \"" + fileName+ "\".");
		return result;
	}

	public static Class validateDriver(
		final Configuration configuration,
		final String key
	) throws InvalidPropertyException {
		final Class result;
		final String driverClassName = validateProperty(configuration, key);
		try {
			result = Class.forName(driverClassName);
		} catch (final ClassNotFoundException e) {
			LOG.error(e, e);
            throw new InvalidPropertyException("Driver =  \"" + driverClassName + "\", message = " + e + "\"", key);
		}
        LOG.debug("Validation is successful for configuration file \"" + Configuration.CONFIG_FILE + "\", key = \"" + key + "\".");
		return result;
	}

    public static Date validateDate(
		final Configuration configuration,
		final String key
	) throws InvalidPropertyException {
		Date result = null;
	    final String stringDate = optionalProperty(configuration, key);
        if (!stringDate.isEmpty()) {
            try {
                final DateFormat sdf = new SimpleDateFormat(PROPERTY_DATE_FORMAT);
                sdf.getCalendar().setLenient(false);
                result = sdf.parse(stringDate);
            } catch (final ParseException e) {
    			LOG.error(e, e);
                throw new InvalidPropertyException("Config file \"" + Configuration.CONFIG_FILE + "\", key = \"" + key + "\", message = \"" + e + "\"", key);
            }
        }
        LOG.debug("Validation is successful for configuration file \"" + Configuration.CONFIG_FILE + "\", key = \"" + key + "\". Result = " + result);
        return result;
    }
}

