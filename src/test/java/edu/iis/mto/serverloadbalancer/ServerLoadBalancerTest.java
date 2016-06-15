package edu.iis.mto.serverloadbalancer;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

public class ServerLoadBalancerTest {
	@Test
	public void itCompiles() {
		assertThat(true, equalTo(true));
	}

    @Test
    public void balancingServer_noVms_serverStaysEmpty(){
        Server server = new Server();
        Vm[] emptyVmList = new Vm[0];
        Server[] serverList = new Server[] {server};
        new ServerLoadBalancer().balance(serverList, emptyVmList);

        assertThat(server.loadPercentage, equalTo(0.0d));
    }




}
