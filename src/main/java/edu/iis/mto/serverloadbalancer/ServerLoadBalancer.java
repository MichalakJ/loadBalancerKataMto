package edu.iis.mto.serverloadbalancer;

/**
 * Created by Kuba on 2016-06-15.
 */
public class ServerLoadBalancer {
    public void balance(Server[] serverList, Vm[] vmList) {
        for (Vm vm : vmList) {
            Server lessLoadedServer = null;
            for (Server server : serverList) {
                if(lessLoadedServer == null || lessLoadedServer.loadPercentage > server.loadPercentage){
                    lessLoadedServer = server;
                }
            }
            lessLoadedServer.addVm(vm);
        }




    }
}
