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
    public  void  balancingAServer_noVms_ServerStaysEmpty(){
        Server server= new Server();
        Server[] serverList = new Server[] {server};
        Vm[] vmList = new Vm[0];
        new ServerLoadBalancer().balance(serverList, vmList);
        assertThat(server.currentLoadPercentage, equalTo(0.0d));
    }


}
