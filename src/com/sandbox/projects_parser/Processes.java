package com.sandbox.projects_parser;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 */
@XmlRootElement(name = "config")
public class Processes {

    private List<IRMProcess> processes;

    /**
     *
     * @return
     */
    @XmlElementWrapper(name = "processes")
    @XmlElement(name = "process")
    public List<IRMProcess> getProcesses() {
        return processes;
    }

    /**
     *
     * @param processes
     */
    public void setProcesses(List<IRMProcess> processes) {
        this.processes = processes;
    }
}
