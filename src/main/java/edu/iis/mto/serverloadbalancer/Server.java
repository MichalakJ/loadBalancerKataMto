package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kuba on 2016-06-16.
 */
public class Server {
    public static final double MAXIMUM_LOAD = 100.0d;
    public double currentLoadPercentage;
    public int capacity;

    private List<Vm> vmList = new ArrayList<Vm>();

    public void addVm(Vm vm) {
        vmList.add(vm);
        currentLoadPercentage += getVmLoad(vm);
    }

    public int countVms() {
        return vmList.size();
    }

    public boolean contains(Vm vm) {
        return vmList.contains(vm);
    }

    public boolean canFit(Vm vm) {
        return getVmLoad(vm) <= MAXIMUM_LOAD - currentLoadPercentage;
    }

    private double getVmLoad(Vm vm) {
        return (double) vm.size * MAXIMUM_LOAD / (double) capacity;
    }
}
