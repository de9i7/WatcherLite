package com.sandbox.xsd_checksum_updater.exception;

/**
 * The class is the root of business exceptions.
 * @author E.Balovnev
 */
public class XSDChecksumConverterException extends Exception {

	private static final long serialVersionUID = 8799988143769982009L;

	public static final String COMMON_EMAIL_HEADER = "XSD Checksum Converter Failure";
    public static final String MSG_UNEXPECTED_ERROR = "XSD Checksum Converter error";

	public XSDChecksumConverterException(final String message) {
		super(message);
	}

	public XSDChecksumConverterException(final Throwable cause) {
		super(cause);
	}

	/**
	 * @return the email subject
	 */
	public String getEmailSubject() {
		return COMMON_EMAIL_HEADER;
	}

	/**
	 * @return the email message
	 */
	public String getEmailMessage() {
		return MSG_UNEXPECTED_ERROR;
	}
}
