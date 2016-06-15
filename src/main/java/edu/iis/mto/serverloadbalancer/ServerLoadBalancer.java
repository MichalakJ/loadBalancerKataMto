package edu.iis.mto.serverloadbalancer;

/**
 * Created by Kuba on 2016-06-15.
 */
public class ServerLoadBalancer {
    public void balance(Server[] serverList, Vm[] vmList) {
        for (Vm vm : vmList) {
            serverList[0].addVm(vm);
        }




    }
}
