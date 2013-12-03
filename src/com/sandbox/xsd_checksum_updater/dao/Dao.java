package com.sandbox.xsd_checksum_updater.dao;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The class contains the DAO methods. It does not control the connections. 
 * @author E.Balovnev
 */
public class Dao {

	/** Logger */
    private static final Logger LOG = Logger.getLogger(Dao.class);

    private static String  s_getXSDChecksumList;

    static {
        try {
            s_getXSDChecksumList = loadResource(Dao.class, "getXSDChecksumList.sql");
        } catch (IOException e) {
            LOG.error("Unable to load module resources.", e);
        }
    }

    /**
     * Loads SQL resource file
     * @param daoClass
     * @param filename
     * @return
     * @throws IOException
     */
    private static String loadResource(Class daoClass, String filename) throws IOException {
        InputStream io = daoClass.getResourceAsStream(filename);
        String result;
        try {
            result = IOUtils.toString(io);
        } finally {
            IOUtils.closeQuietly(io);
        }
        return result;
    }

    private static final int FETCH_SIZE = 5000;

	private final Connection connection;

	public Dao(Connection connection) {
		super();
		this.connection = connection;
	}

    /**
     *
     * @return
     * @throws SQLException
     */
    public List<String> getXSDChecksumList() throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;

		stmt = connection.prepareStatement(s_getXSDChecksumList);
		List<String> result = new ArrayList<String>();

		rs = stmt.executeQuery();
		while (rs.next()) {
//            rs.getString(0)
//			String rec = new String ();
//			result.add(rec);
		}

		if (rs != null) {
			rs.close();
		}
		if (stmt != null) {
			stmt.close();
		}
		return result;
    }

	public void updatingLastRunTS(Date endDate) throws SQLException {
		PreparedStatement stmt = null;
		stmt = connection.prepareStatement("UPDATE ET_CURRENCY_FEED_DATA SET ETCFD_LAST_RUN_TS = ?");
		stmt.setTimestamp(1, new Timestamp(endDate.getTime()));

		stmt.executeUpdate();

		if (stmt != null) {
			stmt.close();
		}
	}
}
