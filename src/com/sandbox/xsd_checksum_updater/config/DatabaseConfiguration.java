package com.sandbox.xsd_checksum_updater.config;

import com.sandbox.xsd_checksum_updater.config.validator.InvalidPropertyException;
import com.sandbox.xsd_checksum_updater.config.validator.Validator;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The class contains the database properties and connection builder.
 * @author E.Balovnev
 */
public class DatabaseConfiguration {

    /** Logger */
    private static final Logger LOG = Logger.getLogger(DatabaseConfiguration.class);

    /** SMTP connection configuration */
    public static final String BASE_DRIVER = "base.driver";
    public static final String BASE_URL = "base.url";
    public static final String BASE_USERNAME = "base.username";
    public static final String BASE_PWD = "base.pwd";

    private final String url;
    private final String username;
    private final String pwd;

    private DatabaseConfiguration(
	    final String url,
	    final String username,
	    final String pwd
	) {
    	this.url = url;
    	this.username = username;
    	this.pwd = pwd;
    }

    public static DatabaseConfiguration build(final Configuration configuration) throws InvalidPropertyException {
    	Validator.validateDriver(configuration, BASE_DRIVER);
    	return new DatabaseConfiguration(
	    	Validator.validateProperty(configuration, BASE_URL),
	    	Validator.optionalProperty(configuration, BASE_USERNAME),
	    	Validator.validatePassword(configuration, BASE_PWD)
    	);
    }

	public Connection getConnection() throws SQLException {
		// TODO Auto-generated method stub
		return DriverManager.getConnection(this.url, this.username, this.pwd);
	}
}
