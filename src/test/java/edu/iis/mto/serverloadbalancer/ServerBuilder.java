package edu.iis.mto.serverloadbalancer;

/**
 * Created by Kuba on 2016-06-15.
 */
public class ServerBuilder implements Builder<Server>{
    private final Server server;
    private int capacity;

    public ServerBuilder() {
        server = new Server();
    }

    public ServerBuilder withCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    public Server build() {
        server.capacity = capacity;
        return server;
    }
}
