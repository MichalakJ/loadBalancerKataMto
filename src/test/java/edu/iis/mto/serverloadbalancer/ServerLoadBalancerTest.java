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
    public void balancingAServer_noVms_serverStaysEmpty() {
        Server theServer = getServer().withCapacity(1).build();

        ServerLoadBalancer balancer = new ServerLoadBalancer();
        balancer.balance(listWithServer(theServer), emptyVmList());

        assertThat(theServer, CurrentLoadPercentageMatcher.hasLoadPercentageOf(0.0d));
    }

    @Test
    public void balancingOneServerWithOneSlotCapacity_andOneSlotVm_fillsTheServerWithVm(){

        Server theServer = getServer().withCapacity(1).build();
        Vm theVm = getVm().withSize(1).build();
        ServerLoadBalancer balancer = new ServerLoadBalancer();
        balancer.balance(listWithServer(theServer), listWithVm(theVm));

        assertThat(theServer, CurrentLoadPercentageMatcher.hasLoadPercentageOf(100.0d));
        assertThat("server should contain vm", theServer.contains(theVm));

    }

    private Vm[] listWithVm(Vm theVm) {
        return new Vm[] {theVm};
    }

    private VmBuilder getVm() {
        return new VmBuilder().getDefaultVm();
    }

    private Vm[] emptyVmList() {
        return new Vm[0];
    }

    private Server[] listWithServer(Server theServer) {
        return new Server[] {theServer};
    }

    private ServerBuilder getServer() {
        return new ServerBuilder().getDefaultServer();
    }


}
