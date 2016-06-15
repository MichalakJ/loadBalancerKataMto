package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kuba on 2016-06-15.
 */
public class ServerLoadBalancer {
    public void balance(Server[] serverList, Vm[] vmList) {
        for (Vm vm : vmList) {
            List<Server> capableServer = new ArrayList<Server>();
            for (Server server : serverList) {
                if(server.canFit(vm)){
                    capableServer.add(server);
                }
            }
            Server lessLoadedServer = findLeastLoadedServer(capableServer);
            if(lessLoadedServer != null){
                lessLoadedServer.addVm(vm);
            }

        }




    }

    private Server findLeastLoadedServer(List<Server> serverList) {
        Server lessLoadedServer = null;
        for (Server server : serverList) {
            if(lessLoadedServer == null || lessLoadedServer.loadPercentage > server.loadPercentage){
                lessLoadedServer = server;
            }
        }
        return lessLoadedServer;
    }
}
