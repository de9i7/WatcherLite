/*
 * @(#)FileInfo.java
 *
 * This file contains Boeing intellectual property.  It may
 * contain information about Boeing processes that are part
 * of the Company's competitive advantage.  Release of this
 * file requires prior approval from Luxoft Management.
 *
 * Copyright (c)2007 The Boeing Company All rights reserved.
 */

package com.sandbox.common;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * File or folder information.
 *
 * @author Evstratov Michael
 * @version 1.0
 * @deprecated
 */
public class FileInfo implements Serializable {

    private static final long serialVersionUID = 7178857329599705503L;

    private static final String SEPARATOR = "/";

    private String name;
    private long timestamp;
    private boolean directory;
    private long size;
    private FileInfo parent;

    /**
     * Creates file information.
     * Symbol '/' will be interpreted as folder separator.
     *
     * @param name file name
     */
    public FileInfo(String name) {
        this(name, 0, true, 0);
    }

    /**
     * Creates file information.
     * Symbol '/' will be interpreted as folder separator.
     *
     * @param name        file name
     * @param timestamp   file timestamp
     * @param isDirectory directory flag
     * @param size        file size
     */
    public FileInfo(String name, long timestamp, boolean isDirectory, long size) {
        name = name.replace("\\", SEPARATOR);
        String[] names = name.split(SEPARATOR);
        if (names.length > 1) {
            this.name = names[names.length - 1];
            FileInfo child = this;
            for (int i = names.length - 2; i >= 0; i--) {
                FileInfo parent = new FileInfo(names[i]);
                child.parent = parent;
                child = parent;
            }
        } else {
            this.name = name;
        }

        this.timestamp = timestamp;
        this.directory = isDirectory;
        this.size = size;
    }

    /**
     * Combine parent and child file.
     *
     * @param parent parent folder
     * @param file   file
     */
    public FileInfo(FileInfo parent, FileInfo file) {
        this.parent = parent;
        this.name = file.name;
        this.timestamp = file.timestamp;
        this.directory = file.directory;
        this.size = file.size;
    }

    /**
     * Determine if the file is a directory.
     *
     * @return <code>true</code> if file is a directory
     */
    public boolean isDirectory() {
        return directory;
    }

    /**
     * Returns file size in bytes.
     *
     * @return file size
     */
    public long getSize() {
        return size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return ((name == null) ? 0 : name.hashCode());
    }

    /**
     * Returns file name.
     *
     * @return file name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns file timestamp.
     *
     * @return file timestamp
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * Returns parent file information.
     *
     * @return parent file
     */
    public FileInfo getParent() {
        return parent;
    }

    /**
     * Build file path from root to this.
     *
     * @return path
     */
    public List<FileInfo> getPath() {
        List<FileInfo> path = new LinkedList<FileInfo>();
        FileInfo current = this;
        while (current != null) {
            path.add(0, current);
            current = current.getParent();
        }
        return path;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && obj instanceof FileInfo) {
            final FileInfo other = (FileInfo) obj;
            return other.toPathString().equals(toPathString());
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Returns file path string.
     *
     * @return file path string
     */
    public String toPathString() {
        StringBuilder builder = new StringBuilder();
        FileInfo current = this;
        while (current.getParent() != null) {
            builder.insert(0, current.getName());
            builder.insert(0, SEPARATOR);
            current = current.getParent();
        }
        builder.insert(0, current.getName());
        return builder.toString();
    }
}
