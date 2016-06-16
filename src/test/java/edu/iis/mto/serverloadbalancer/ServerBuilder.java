package edu.iis.mto.serverloadbalancer;

/**
 * Created by Kuba on 2016-06-16.
 */
public class ServerBuilder implements Builder<Server>{
    private int capacity;
    Server server;

    public ServerBuilder() {
        server = new Server();
    }

    public ServerBuilder withCapacity(int capacity) {
        this.capacity = capacity;
        return  this;
    }

    public Server build() {
        server.capacity = capacity;
        return server;
    }
}
