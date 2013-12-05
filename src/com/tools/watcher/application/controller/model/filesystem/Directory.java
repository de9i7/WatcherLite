package com.tools.watcher.application.controller.model.filesystem;

import org.apache.log4j.Logger;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class Directory implements IDirectoryItem {

    /** Logger instance */
    private static final Logger LOG = Logger.getLogger(Directory.class);

    private Map<String, IDirectoryItem> content;
    private File m_fileInstance;

    public Directory(File dir) {
        m_fileInstance = dir;
        init(dir);
    }

    private void init(File dir) {
        content = new HashMap<String, IDirectoryItem>();
        File[] files = dir.listFiles();

        for (File file : files) {
            if (file.isDirectory()) {
                content.put(file.getName(), new Directory(file));
            } else if (file.isFile()) {
                if (file.getName().endsWith("properties")) {
                    content.put(file.getName(), new PropertyFile(file));
                }
//                else if (file.getName().endsWith("xml")) {
//                    content.put(file.getName(), new XmlFile(file));
//                    LOG.info("XML file detected.");
//                } else if (file.getName().endsWith("props")) {
//                    content.put(file.getName(), new PropsFile(file));
//                    LOG.info("Props file detected.");
//                }
            }
        }
    }

    @Override
    public IDirectoryItem getItem(String name) {
        return content.get(name);
    }

    @Override
    public void addItem(IDirectoryItem item) {
        content.put(item.getName(), item);
    }

    @Override
    public String getName() {
        return m_fileInstance.getName();
    }

    @Override
    public Map<String, IDirectoryItem> read() {
        return content;
    }

    @Override
    public int getItemType() {
        return FOLDER;
    }
}
