package com.sandbox.xsd_checksum_updater;

import com.sandbox.xsd_checksum_updater.config.validator.InvalidPropertyException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class XSDChecksumUpdater {

    /** Logger instance */
    private static final Logger LOG = Logger.getLogger(XSDChecksumUpdater.class);

    private static final String TARGET_FOLDER = "d:\\prj\\tools\\etl\\branches\\checksums\\";

    public static void main(String[] args) {
        new XSDChecksumUpdater().run();
    }

    /**
     *
     */
    public void run() {
        convertMd5ToSha256();
    }

    /**
     *
     * @throws InvalidPropertyException
     */
    private void convertMd5ToSha256(){
        LOG.info("Reading configuration...");
        File targetFolder = new File(TARGET_FOLDER);
        if (targetFolder.exists()) {

            String[] folders = targetFolder.list(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return StringUtils.startsWith(name, "ETL");
                }
            });

            Map<String, String> checksumsMap = prepareChecksumsMap(folders);


            LOG.info("Map size : " + checksumsMap.size());
            for (Map.Entry<String, String> entry : checksumsMap.entrySet()) {
                LOG.info("WHEN BEUNR_XSD_CHECKSUM = '" + entry.getKey() + "'  THEN '" + entry.getValue() + "'");
            }
            LOG.info("----- Reverse -----");
            for (Map.Entry<String, String> entry : checksumsMap.entrySet()) {
                LOG.info("WHEN BEUNR_XSD_CHECKSUM = '" + entry.getValue() + "'  THEN '" + entry.getKey() + "'");
            }
        }

    }

    /**
     *
     * @param folders
     * @return
     */
    private Map<String, String> prepareChecksumsMap(String[] folders) {
        Map<String, String> checksumsMap = new HashMap<String, String>();
        for (String folder : folders) {
            File xsdFolder = new File(TARGET_FOLDER + folder);
            processXsdFolder(checksumsMap, xsdFolder);
        }
        return checksumsMap;
    }

    /**
     *
     * @param checksumsMap
     * @param xsdFolder
     */
    private void processXsdFolder(
        Map<String, String> checksumsMap,
        File xsdFolder
    ) {
        List<File> files = (List<File>) FileUtils.listFiles(xsdFolder, new String[]{"xsd_checksum_updater"}, false);
        for (File xsdFile : files) {
            try {
                String md5Checksum = Checksum.getChecksumMd5(xsdFile);
                if (!checksumsMap.containsKey(md5Checksum)) {
                    String shaCHecksum = Checksum.getChecksumSha256(xsdFile);
                    checksumsMap.put(md5Checksum, shaCHecksum);
                }
            } catch (IOException e) {
                LOG.error("Could not read xsd_checksum_updater file. " + xsdFile.getName());
            }
        }

    }
}
