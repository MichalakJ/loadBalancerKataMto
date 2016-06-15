package edu.iis.mto.serverloadbalancer;

/**
 * Created by Kuba on 2016-06-15.
 */
public class Server {
    public static final double MAXIMUM_LOAD = 100.0d;
    public double currentLoadPercentage;
    public int capacity;

    public void addVm(Vm vm) {
        currentLoadPercentage =  (double) vm.size * MAXIMUM_LOAD / (double) capacity;
    }

    public int countVms() {
        return 0;
    }

    public boolean contains(Vm vm1) {
        return true;
    }
}
