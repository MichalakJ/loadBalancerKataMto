package edu.iis.mto.serverloadbalancer;

/**
 * Created by Kuba on 2016-06-15.
 */
public class ServerBuilder implements Builder<Server>{
    private final Server server;
    private int capacity;
    private double load;

    public ServerBuilder() {
        server = new Server();
    }

    public ServerBuilder withCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    public Server build() {
        server.capacity = capacity;
        if(load>0) {
            addInitialLoad();
        }
        return server;
    }

    private void addInitialLoad() {
        int initialVmSize = (int) (load * (double) capacity / Server.MAXIMUM_LOAD);
        Vm initialVm = new VmBuilder().withSize(initialVmSize).build();
        server.addVm(initialVm);
    }

    public ServerBuilder withLoadOf(double load) {
        this.load = load;
        return this;
    }
}
