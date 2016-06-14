package edu.iis.mto.serverloadbalancer;

/**
 * Created by Kuba on 2016-06-12.
 */
public class ServerLoadBalancer {




    public ServerLoadBalancer() {

    }

    public void balance(Server[] servers, Vm[] vms) {
        if(vms.length > 0 ){
            servers[0].setLoadPercentage((double) vms[0].getSize() / (double) servers[0].getCapacity() * 100.0d);
        }
    }
}
