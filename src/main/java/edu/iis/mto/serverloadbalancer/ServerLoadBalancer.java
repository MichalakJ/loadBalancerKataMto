package edu.iis.mto.serverloadbalancer;

/**
 * Created by Kuba on 2016-06-16.
 */
public class ServerLoadBalancer {
    public void balance(Server[] serverList, Vm[] vmList) {
        for (Vm vm : vmList) {
            Server lessLoadedServer = null;
            for (Server server : serverList) {
                if(lessLoadedServer == null || lessLoadedServer.currentLoadPercentage > server.currentLoadPercentage){
                    lessLoadedServer=server;
                }
            }
            lessLoadedServer.addVm(vm);
        }


    }
}
