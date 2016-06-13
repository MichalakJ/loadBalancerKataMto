package edu.iis.mto.serverloadbalancer;

/**
 * Created by Kuba on 2016-06-12.
 */
public class ServerBuilder implements Builder<Server>{
    Server server;

    public ServerBuilder() {
        this.server = new Server();
    }

    public ServerBuilder getDefaultServer() {
        server.setCapacity(1);
        return this;
    }

    public ServerBuilder withCapacity(int i) {
        server.setCapacity(i);
        return this;
    }

    public Server build() {
        return server;
    }
}
