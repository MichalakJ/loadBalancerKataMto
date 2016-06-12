package edu.iis.mto.serverloadbalancer;

/**
 * Created by Kuba on 2016-06-12.
 */
public class Server {
    private int capacity;
    private double loadPercetage;

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getLoadPercetage() {
        return loadPercetage;
    }
}
