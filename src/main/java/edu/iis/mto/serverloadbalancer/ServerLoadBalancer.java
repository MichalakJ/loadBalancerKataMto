package edu.iis.mto.serverloadbalancer;

/**
 * Created by Kuba on 2016-06-16.
 */
public class ServerLoadBalancer {
    public void balance(Server[] serverList, Vm[] vmList) {
        if(vmList.length > 0){
            serverList[0].currentLoadPercentage = 100.0d;
        }

    }
}
