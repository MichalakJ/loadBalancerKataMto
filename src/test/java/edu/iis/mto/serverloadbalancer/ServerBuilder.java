package edu.iis.mto.serverloadbalancer;

import static edu.iis.mto.serverloadbalancer.Server.*;

/**
 * Created by Kuba on 2016-06-12.
 */
public class ServerBuilder implements Builder<Server>{
    Server server;
    private double initialLoad;
    private int capacity;

    public ServerBuilder() {
        this.server = new Server();
    }



    public ServerBuilder withCapacity(int i) {
        server.setCapacity(i);
        this.capacity = i;
        return this;
    }

    public Server build() {
;
        return server;
    }

    public ServerBuilder withInitialLoad(double initialLoad) {
        initServerLoad(initialLoad);
        return this;
    }

    private void initServerLoad(double initialLoad) {
        int initialVmSize =  (int)( initialLoad / (double) capacity * MAXIMUM_LOAD);
        Vm initialVm = new VmBuilder().withSize(initialVmSize).build();
        server.addVm(initialVm);
    }
}
