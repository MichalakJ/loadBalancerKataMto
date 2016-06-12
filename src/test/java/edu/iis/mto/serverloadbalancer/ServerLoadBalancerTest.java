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
    public void balancingAServer_noVms_serverStaysEmpty() {
        Server theServer = new Server();
        theServer.setCapacity(1);
        Vm[] vms = null;
        ServerLoadBalancer balancer = new ServerLoadBalancer();
        balancer.balance(theServer, vms);

        assertThat(theServer.getLoadPercetage(), equalTo(0.0d));
    }



}
