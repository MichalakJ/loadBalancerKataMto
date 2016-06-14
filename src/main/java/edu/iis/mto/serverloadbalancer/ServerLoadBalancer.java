package edu.iis.mto.serverloadbalancer;

/**
 * Created by Kuba on 2016-06-12.
 */
public class ServerLoadBalancer {




    public ServerLoadBalancer() {

    }

    public void balance(Server[] servers, Vm[] vms) {

        for (Vm vm: vms) {
            Server lessLoadedServer = null;
            for (Server server: servers) {

                if(lessLoadedServer == null || lessLoadedServer.getLoadPercentage() > server.getLoadPercentage()){
                    lessLoadedServer = server;
                }
            }
            lessLoadedServer.addVm(vm);
        }
    }
}
