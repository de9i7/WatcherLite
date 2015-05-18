package com.sandbox.projects_parser;

import java.util.HashMap;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * This class prepares Map representation of servers list
 * and holds it.
 */
@XmlRootElement(name="config")
public class Servers {

    /** This map contains the list of parsed servers */
    private HashMap<String, List<IRMProcess>> servers;

    @XmlJavaTypeAdapter(Servers.ServersAdapter.class)
    @XmlElement
    public HashMap<String, List<IRMProcess>> getServers() {
        return servers;
    }

    public void setServers(HashMap<String, List<IRMProcess>> servers) {
        this.servers = servers;
    }

    /**
     * This adapter performs mapping between mappable and
     * non-mappable representation of servers list.
     */
    private static class ServersAdapter extends XmlAdapter<ServerMapEntryType, HashMap<String, List<IRMProcess>>> {

        @Override
        public HashMap<String, List<IRMProcess>> unmarshal(ServerMapEntryType serverList) throws Exception {
            HashMap<String, List<IRMProcess>> servers = new HashMap<String, List<IRMProcess>>();
            for (Server server : serverList.servers) {
                servers.put(server.getName(), server.getProcess());
            }
            return servers;
        }

        @Override
        public ServerMapEntryType marshal(HashMap<String, List<IRMProcess>> v) throws Exception {
            return null;
        }
    }

    /**
     * Inner class for mappable representation of HashMap.
     */
    private static class ServerMapEntryType {

        @XmlElement(name = "server")
        public List<Server> servers;
    }
}
