package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kuba on 2016-06-15.
 */
public class ServerLoadBalancer {
    public void balance(Server[] servers, Vm[] vms) {

        for (Vm vm : vms) {
            addToLessLoadedServer(servers, vm);
        }



    }

    private void addToLessLoadedServer(Server[] servers, Vm vm) {
        List<Server> capableServer = getServersWithEnoughCapacity(servers, vm);

        Server lessLoadedServer = getLessLoadedServer(capableServer);
        if(lessLoadedServer != null){
            lessLoadedServer.addVm(vm);
        }

    }

    private List<Server> getServersWithEnoughCapacity(Server[] servers, Vm vm) {
        List<Server> capableServer = new ArrayList<Server>();
        for (Server server : servers) {
            if(server.canFit(vm)){
                capableServer.add(server);
            }
        }
        return capableServer;
    }

    private Server getLessLoadedServer(List<Server> servers) {
        Server lessLoadedServer = null;
        for (Server server : servers) {
            if(lessLoadedServer == null || lessLoadedServer.currentLoadPercentage > server.currentLoadPercentage){
                lessLoadedServer = server;
            }
        }
        return lessLoadedServer;
    }
}
