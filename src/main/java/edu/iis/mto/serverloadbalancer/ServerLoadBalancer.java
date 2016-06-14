package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kuba on 2016-06-12.
 */
public class ServerLoadBalancer {




    public ServerLoadBalancer() {

    }

    public void balance(Server[] servers, Vm[] vms) {

        for (Vm vm: vms) {
            addToLessLoadedServer(servers, vm);
        }
    }

    private void addToLessLoadedServer(Server[] servers, Vm vm) {
        List<Server> capableServers = findCapableServers(servers, vm);

        Server lessLoadedServer = findLessLoadedServer(capableServers);
        if(lessLoadedServer!=null){
            lessLoadedServer.addVm(vm);
        }

    }

    private List<Server> findCapableServers(Server[] servers, Vm vm) {
        List<Server> capableServers = new ArrayList<Server>();
        for (Server server : servers) {
            if(server.canFit(vm)){
                capableServers.add(server);
            }
        }
        return capableServers;
    }

    private Server findLessLoadedServer(List<Server> servers) {
        Server lessLoadedServer = null;
        for (Server server: servers) {

            if(lessLoadedServer == null || lessLoadedServer.getLoadPercentage() > server.getLoadPercentage()){
                lessLoadedServer = server;
            }
        }
        return lessLoadedServer;
    }
    //hermetized fields in previous steps
}
