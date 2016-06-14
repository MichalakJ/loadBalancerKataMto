package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kuba on 2016-06-12.
 */
public class Server {
    public static final double MAXIMUM_LOAD = 100.0d;
    private int capacity;
    private double loadPercentage;

    private List<Vm> vmsList = new ArrayList<Vm>();

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getLoadPercentage() {
        return loadPercentage;
    }

    public boolean contains(Vm theVm) {
        return vmsList.contains(theVm);
    }

    public void setLoadPercentage(double loadPercentage) {
        this.loadPercentage = loadPercentage;
    }

    public int getCapacity() {
        return capacity;
    }

    public void addVm(Vm vm) {
        loadPercentage += getLoadOfVm(vm);
        vmsList.add(vm);
    }

    private double getLoadOfVm(Vm vm) {
        return (double) vm.getSize() / (double) capacity * MAXIMUM_LOAD;
    }

    public int countVms() {
        return vmsList.size();
    }


    public boolean canFit(Vm vm) {
        return (double) (vm.getSize() / (double) capacity)* 100.0d <= MAXIMUM_LOAD - loadPercentage;
    }
}
