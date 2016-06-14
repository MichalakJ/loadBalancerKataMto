package edu.iis.mto.serverloadbalancer;

/**
 * Created by Kuba on 2016-06-12.
 */
public class ServerLoadBalancer {




    public ServerLoadBalancer() {

    }

    public void balance(Server[] servers, Vm[] vms) {

        for (Vm vm: vms) {
            servers[0].addVm(vm);
        }
    }
}
