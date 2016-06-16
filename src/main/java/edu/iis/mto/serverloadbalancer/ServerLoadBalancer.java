package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kuba on 2016-06-16.
 */
public class ServerLoadBalancer {
    public void balance(Server[] serverList, Vm[] vmList) {
        for (Vm vm : vmList) {
            addToCapableLeastLoadedServer(serverList, vm);

        }


    }

    private void addToCapableLeastLoadedServer(Server[] serverList, Vm vm) {
        List<Server> capableServer = findCapableServers(serverList, vm);
        Server lessLoadedServer = getLeastLoadedServer(capableServer);
        if(lessLoadedServer != null){
            lessLoadedServer.addVm(vm);
        }
    }

    private List<Server> findCapableServers(Server[] serverList, Vm vm) {
        List<Server> capableServer = new ArrayList<Server>();
        for (Server server : serverList) {
            if(server.canFit(vm)){
                capableServer.add(server);
            }
        }
        return capableServer;
    }

    private Server getLeastLoadedServer(List<Server> serverList) {
        Server lessLoadedServer = null;
        for (Server server : serverList) {
            if(lessLoadedServer == null || lessLoadedServer.getCurrentLoadPercentage() > server.getCurrentLoadPercentage()){
                lessLoadedServer=server;
            }
        }
        return lessLoadedServer;
    }
}
