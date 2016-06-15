package edu.iis.mto.serverloadbalancer;


import static edu.iis.mto.serverloadbalancer.CurrentLoadPercentageMatcher.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

public class ServerLoadBalancerTest {
	@Test
	public void itCompiles() {
		assertThat(true, equalTo(true));
	}

    @Test
    public void balancingServer_noVm_serverStaysEmpty() {
        Server theServer = a(server().withCapacity(100));

        balance(serverListWith(theServer), emptyVmList());

        assertThat(theServer, hasCurrentLoadPercentageOf(0.0d));
    }

    private Server a(ServerBuilder serverBuilder) {
        return serverBuilder.build();
    }

    private ServerBuilder server() {
        return new ServerBuilder();
    }

    private void balance(Server[] servers, Vm[] vms) {
        new ServerLoadBalancer().balance(servers, vms);
    }

    private Vm[] emptyVmList() {
        return new Vm[0];
    }

    private Server[] serverListWith(Server... servers) {
        return servers;
    }


}
