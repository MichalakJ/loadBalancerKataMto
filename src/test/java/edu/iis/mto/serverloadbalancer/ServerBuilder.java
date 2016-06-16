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
        server.setCapacity(capacity);
        if(loadPercentage > 0){
            addInitialLoad();
        }

        return server;
    }

    private void addInitialLoad() {
        int initialSize = (int) ((double) loadPercentage * (double) capacity / Server.MAXIMUM_LOAD);
        Vm initialVm = new VmBuilder().withSize(initialSize).build();
        server.addVm(initialVm);
    }

    public ServerBuilder withLoadPercentage(int loadPercentage) {
        this.loadPercentage = loadPercentage;
        return this;
    }
}
