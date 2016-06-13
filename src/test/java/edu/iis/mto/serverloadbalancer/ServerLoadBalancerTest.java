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
        Server theServer = a(server().withCapacity(1));

        ServerLoadBalancer balancer = new ServerLoadBalancer();
        balancer.balance(listWithServer(theServer), emptyVmList());

        assertThat(theServer, CurrentLoadPercentageMatcher.hasLoadPercentageOf(0.0d));
    }


    @Test
    public void balancingOneServerWithOneSlotCapacity_andOneSlotVm_fillsTheServerWithVm(){

        Server theServer = a(server().withCapacity(1));
        Vm theVm = a(vm().withSize(1));
        ServerLoadBalancer balancer = new ServerLoadBalancer();
        balancer.balance(listWithServer(theServer), listWithVm(theVm));

        assertThat(theServer, CurrentLoadPercentageMatcher.hasLoadPercentageOf(100.0d));
        assertThat("server should contain vm", theServer.contains(theVm));

    }

    private Vm a(VmBuilder vmBuilder) {
        return vmBuilder.build();
    }

    private VmBuilder vm() {
        return new VmBuilder();
    }

    private Vm[] listWithVm(Vm ... vms) {
        return vms;
    }

    private VmBuilder getVm() {
        return new VmBuilder().getDefaultVm();
    }

    private Vm[] emptyVmList() {
        return new Vm[0];
    }

    private Server[] listWithServer(Server ... servers) {
        return servers;
    }




    private Server a(ServerBuilder serverBuilder) {
        return serverBuilder.build();
    }

    private ServerBuilder server() {
        return new ServerBuilder();
    }
}
