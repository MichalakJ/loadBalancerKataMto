package edu.iis.mto.serverloadbalancer;

/**
 * Created by Kuba on 2016-06-15.
 */
public class ServerBuilder implements Builder<Server>{

    private final Server server;
    private int capacity;
    private int initialLoad;

    public ServerBuilder() {
        server = new Server();
    }

    public ServerBuilder withCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    public Server build() {
        server.capacity = capacity;
        if(initialLoad > 0) {
            int vmSize = (int) (initialLoad / (double) capacity * 100.0d);
            Vm vm = new VmBuilder().withSize(vmSize).build();
            server.addVm(vm);
        }
        return server;
    }

    public ServerBuilder withInitialLoadOf(int initialLoad) {
        this.initialLoad = initialLoad;
        return this;
    }
}
