package edu.iis.mto.serverloadbalancer;

/**
 * Created by Kuba on 2016-06-15.
 */
public class Server {
    public static final double MAXIMUM_LOAD = 100.0d;
    public double loadPercentage;
    public int capacity;

    public void addVm(Vm vm) {
        loadPercentage = (double) vm.size / (double) capacity * MAXIMUM_LOAD;
    }
}
