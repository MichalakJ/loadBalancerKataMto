package edu.iis.mto.serverloadbalancer;

/**
 * Created by Kuba on 2016-06-16.
 */
public class ServerBuilder implements Builder<Server>{
    private int capacity;
    Server server;
    private int loadPercentage;

    public ServerBuilder() {
        server = new Server();
    }

    public ServerBuilder withCapacity(int capacity) {
        this.capacity = capacity;
        return  this;
    }

    public Server build() {
        server.capacity = capacity;
        if(loadPercentage > 0){
            int initialSize = (int) ((double) loadPercentage * (double) capacity / 100.0d);
            Vm initialVm = new VmBuilder().withSize(initialSize).build();
            server.addVm(initialVm);
        }

        return server;
    }

    public ServerBuilder withLoadPercentage(int loadPercentage) {
        this.loadPercentage = loadPercentage;
        return this;
    }
}
