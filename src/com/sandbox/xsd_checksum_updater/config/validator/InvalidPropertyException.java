package com.sandbox.xsd_checksum_updater.config.validator;

import com.sandbox.xsd_checksum_updater.exception.XSDChecksumConverterException;

/**
 * This class is validation property exception
 * @author E.Balovnev
 */
public class InvalidPropertyException extends XSDChecksumConverterException {

    public static final String MSG_VALIDATION_ERROR = "Input parameters are not found or failed validation";

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1297095365353980275L;
    
    private final String key;

    /**
     * Constructor with custom message
     * @param details message
     */
    public InvalidPropertyException(final String details) {
    	this(details, null);
    }
    /**
     * Constructor with custom message
     * @param details message
     * @param key key
     */
    public InvalidPropertyException(final String details, final String key) {
        super(getMessage(key) + ".Details: " + details);
        this.key = key;
    }

	public String getEmailMessage() {
		return getMessage(key);
	}
	
	private static String getMessage(final String key) {
		return MSG_VALIDATION_ERROR + (key != null ? System.getProperty("line.separator") + System.getProperty("line.separator") + key : "");
	}
}
