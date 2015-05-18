package com.sandbox.projects_parser;

import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * This inner class represents Map entry where the name of the server
 * is a key and processes list is a value.
 * @author Denis Kachurovskiy 5/28/2014
  */
public class Server {

    private String name;
    private List<IRMProcess> process;

    @XmlAttribute
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name="process")
    public List<IRMProcess> getProcess() {
        return process;
    }

    public void setProcess(List<IRMProcess> process) {
        this.process = process;
    }
}