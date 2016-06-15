package edu.iis.mto.serverloadbalancer;

import static edu.iis.mto.serverloadbalancer.Server.*;

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
            addInitialLoad();
        }
        return server;
    }

    private void addInitialLoad() {
        int vmSize = (int) (initialLoad / (double) capacity * MAXIMUM_LOAD);
        Vm vm = new VmBuilder().withSize(vmSize).build();
        server.addVm(vm);
    }

    public ServerBuilder withInitialLoadOf(int initialLoad) {
        this.initialLoad = initialLoad;
        return this;
    }
}
