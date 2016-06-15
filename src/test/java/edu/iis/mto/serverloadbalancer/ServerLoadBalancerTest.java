package edu.iis.mto.serverloadbalancer;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.hamcrest.Matcher;
import org.junit.Test;

public class ServerLoadBalancerTest {
	@Test
	public void itCompiles() {
		assertThat(true, equalTo(true));
	}

    @Test
    public void balancingServer_noVm_serverStaysEmpty() {
        Server theServer = new Server();

        balance(serverListWith(theServer), emptyVmList());

        assertThat(theServer.currentLoadPercentage, equalTo(0.0d));
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
