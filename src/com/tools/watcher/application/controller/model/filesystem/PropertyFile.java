package com.tools.watcher.application.controller.model.filesystem;

import org.apache.log4j.Logger;

import java.io.File;
import java.util.Map;

/**
 * Property file instance.
 */
public class PropertyFile implements IDirectoryItem {

    private static final Logger LOG = Logger.getLogger(PropertyFile.class);

    private File m_fileInstance;

    public PropertyFile(File file) {
        m_fileInstance = file;
    }

    @Override
    public IDirectoryItem getItem(String name) {
        LOG.info("This implementation does not return any internal items: " + name);
        return this;
    }

    @Override
    public void addItem(IDirectoryItem item) {
        LOG.info("This implementation does not contain any internal items. Couldn't be added.");
    }

    @Override
    public String getName() {
        return m_fileInstance.getName();
    }

    @Override
    public Map<String, IDirectoryItem> read() {
        return null;
    }

    @Override
    public int getItemType() {
        return PROPERTY;
    }
}
