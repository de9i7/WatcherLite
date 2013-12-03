package com.sandbox.xsd_checksum_updater.dao;

import com.sandbox.xsd_checksum_updater.config.DatabaseConfiguration;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 */
public class DaoService {

    private static final Logger LOG = Logger.getLogger(DaoService.class);

    private DatabaseConfiguration configuration;

    public DaoService(DatabaseConfiguration config) {
        configuration = config;
    }

    public List<String> getXSDChecksumList() {
        Connection conn = null;
        List<String> result = null;

        try {
            conn = configuration.getConnection();

        } catch (SQLException e) {
            LOG.error(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (final SQLException e) {
                    LOG.error(e);
                }
            }
        }
        return null;
    }
}
