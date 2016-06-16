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
    public  void  balancingAServer_noVms_ServerStaysEmpty(){
        Server server = a(server().withCapacity(1));
        balance(serverListWith(server), emptyVmList());

        assertThat(server, hasCurrentPercentageLoadOf(0.0d));
    }

    @Test
    public void  balancingOneServerWithOneSlotCapacity_andOneSlotVm_fillsTheServerWithVm(){
        Server server = a(server().withCapacity(1));
        Vm vm = a(vm().withSize(1));
        balance(serverListWith(server), vmListWith(vm));

        assertThat(server, hasCurrentPercentageLoadOf(100.0d));
    }

    @Test
    public void balancingOneServerWithTenSlotsCapacity_andOneSlotVm_fillServerWithTenPercent(){
        Server server = a(server().withCapacity(10));
        Vm vm = a(vm().withSize(1));
        balance(serverListWith(server), vmListWith(vm));

        assertThat(server, hasCurrentPercentageLoadOf(10.0d));
    }

    @Test
    public void balancingServerWithEnoughRoom_getsFilledWithAllVm(){
        Server server = a(server().withCapacity(10));
        Vm vm1 = a(vm().withSize(5));
        Vm vm2 = a(vm().withSize(3));
        balance(serverListWith(server), vmListWith(vm1,vm2));

        assertThat(server, hasVmCountOf(2));
        assertThat("server contains vm1", server.contains(vm1));
        assertThat("server contains vm2", server.contains(vm2));

    }

    private Matcher<? super Server> hasVmCountOf(int expectedVmCount) {
        return new VmCountMatcher(expectedVmCount);
    }

    private Vm a(VmBuilder vmBuilder) {
        return vmBuilder.build();
    }

    private VmBuilder vm() {
        return new VmBuilder();
    }

    private Vm[] vmListWith(Vm... vms) {
        return  vms;
    }

    private Matcher<? super Server> hasCurrentPercentageLoadOf(double expectedLoadPercentage) {
        return new CurrentLoadPercentageMatcher(expectedLoadPercentage);
    }

    public void balance(Server[] serverList, Vm[] vmList){
        new ServerLoadBalancer().balance(serverList, vmList);
    }

    private Server a(ServerBuilder serverBuilder) {
        return serverBuilder.build();
    }

    private ServerBuilder server() {
        return new ServerBuilder();
    }

    private Vm[] emptyVmList() {
        return new Vm[0];
    }

    private Server[] serverListWith(Server... servers) {
        return servers;
    }


}
