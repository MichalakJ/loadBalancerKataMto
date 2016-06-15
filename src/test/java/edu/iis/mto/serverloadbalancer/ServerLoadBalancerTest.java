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
    public void balancingServer_noVms_serverStaysEmpty(){
        Server server = new Server();

        balance(serverListWith(server), emptyVmList());

        assertThat(server, hasLoadPercentageOf(0.0d));
    }

    private void balance(Server[] servers, Vm[] vms) {
        new ServerLoadBalancer().balance(servers,vms);
    }

    private Vm[] emptyVmList(Vm... vms) {
        return vms;
    }

    private Server[] serverListWith(Server... servers) {
        return servers;
    }

    private Matcher<Server> hasLoadPercentageOf(double expectedLoadPercentage) {
        return new CurrentLoadPercentageMatcher(expectedLoadPercentage);
    }


}
