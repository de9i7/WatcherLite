package com.tools.watcher.controller.model.filesystem;

import java.util.Map;

/**
 *
 */
public interface IDirectoryItem {

    public static final int FOLDER = 0;
    public static final int PROPERTY = 1;
    public static final int XML = 2;
    public static final int PROPS = 3;

    IDirectoryItem getItem(String name);
    void addItem(IDirectoryItem item);
    String getName();
    Map<String, IDirectoryItem> read();
    int getItemType();
}
