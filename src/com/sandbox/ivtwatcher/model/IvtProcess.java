package com.sandbox.ivtwatcher.model;

/**
 *
 */
public class IvtProcess {
    private String m_name;
    private Integer m_taskId;
    private String m_serverName;
    private String m_path;

    public String getName() {
        return m_name;
    }

    public void setName(String m_name) {
        this.m_name = m_name;
    }

    public Integer getTaskId() {
        return m_taskId;
    }

    public void setTaskId(Integer m_taskId) {
        this.m_taskId = m_taskId;
    }

    public String getServerName() {
        return m_serverName;
    }

    public void setServerName(String m_serverName) {
        this.m_serverName = m_serverName;
    }

    public String getPath() {
        return m_path;
    }

    public void setPath(String m_path) {
        this.m_path = m_path;
    }
}
