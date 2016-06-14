package edu.iis.mto.serverloadbalancer;

/**
 * Created by Kuba on 2016-06-12.
 */
public class Server {
    private int capacity;
    private double loadPercentage;

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getLoadPercentage() {
        return loadPercentage;
    }

    public boolean contains(Vm theVm) {
        return true;
    }

    public void setLoadPercentage(double loadPercentage) {
        this.loadPercentage = loadPercentage;
    }

    public int getCapacity() {
        return capacity;
    }
}
