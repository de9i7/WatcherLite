/*
 * @(#)IVTProcess.java
 *
 * This file contains Boeing intellectual property.  It may
 * contain information about Boeing processes that are part
 * of the Company's competitive advantage.  Release of this
 * file requires prior approval from Luxoft Management.
 *
 * Copyright (c)2007 The Boeing Company All rights reserved.
 */

package com.sandbox.projects_parser;


import java.util.List;
import javax.xml.bind.annotation.*;

/**
 * IVTProcess entity.
 *
 * @author ARomanov
 * @author DKachurovskiy
 * @version 2.0
 */
public class IRMProcess {

    private int irmProcessId;
    private String server;
    private String path;
    private List<String> model;

    private int taskId;

    public IRMProcess(){}

    /**
     * Custom constructor with parameters that are needed
     * for new instance definition.
     * @param processId process irmProcessId
     * @param server server name
     * @param path IVT folder path
     * @param taskId task irmProcessId value. 0 - task irmProcessId is not set
     */
    public IRMProcess(int processId, String server, String path, int taskId, List<String> model) {
        this.irmProcessId = processId;
        this.server = server;
        this.path = path;
        this.taskId = taskId;
        this.model = model;
    }

    /**
     * Custom constructor with parameters that are needed
     * for new instance definition.
     * @param processId process irmProcessId
     * @param server server name
     * @param path IVT folder path
     * @param taskId task irmProcessId value. 0 - task irmProcessId is not set
     */
    public IRMProcess(int processId, String server, String path, int taskId) {
        this.irmProcessId = processId;
        this.server = server;
        this.path = path;
        this.taskId = taskId;
    }

    /**
     * Returns irmProcessId.
     *
     * @return irmProcessId.
     */
    public Integer getId() {
        return irmProcessId;
    }

    /**
     * Sets irmProcessId.
     *
     * @param irmProcessId irmProcessId.
     */
    public void setId(Integer irmProcessId) {
        this.irmProcessId = irmProcessId;
    }

    /**
     * Returns server name.
     *
     * @return server name.
     */
    public String getServer() {
        return server;
    }

    /**
     * Sets server name.
     *
     * @param server server name.
     */
    @XmlAttribute(name = "server")
    public void setServer(String server) {
        this.server = server;
    }

    /**
     * Returns path of input-output directory.
     *
     * @return path of input-output directory.
     */
    public String getPath() {
        return this.path;
    }

    /**
     * Sets path of input-output directory.
     *
     * @param path path of input-output directory.
     */
    @XmlAttribute(name = "path")
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @param taskId the taskId to set
     */
    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    /**
     * @return the taskId
     */
    public int getTaskId() {
        return taskId;
    }

    /**
     * @param model the model to set
     */
    @XmlElement(name = "model")
    public void setModel(List<String> model) {
        this.model = model;
    }

    /**
     * @return the model
     */
    public List<String> getModel() {
        return this.model;
    }
}
