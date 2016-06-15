package edu.iis.mto.serverloadbalancer;

/**
 * Created by Kuba on 2016-06-15.
 */
public class ServerLoadBalancer {
    public void balance(Server[] servers, Vm[] vms) {

        for (Vm vm : vms) {
            Server lessLoadedServer = null;
            for (Server server : servers) {
                if(lessLoadedServer == null || lessLoadedServer.currentLoadPercentage > server.currentLoadPercentage){
                    lessLoadedServer = server;
                }
            }
            lessLoadedServer.addVm(vm);
        }



    }
}
