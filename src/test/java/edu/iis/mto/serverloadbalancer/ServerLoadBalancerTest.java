package edu.iis.mto.serverloadbalancer;


import static edu.iis.mto.serverloadbalancer.CurrentLoadPercentageMatcher.*;
import static edu.iis.mto.serverloadbalancer.ServerVmsCountMatcher.*;
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

    @Test
    public void balancingOneServerWithOneSlotCapacity_andOneSlotVm_fillsTheServerWithVm(){

        Server theServer = a(server().withCapacity(100));
        Vm theVm = a(vm().withSize(100));
        balance(serverListWith(theServer), vmListWith(theVm));

        assertThat(theServer, hasCurrentLoadPercentageOf(100.0d));

    }

    @Test
    public void balancingOneServerWithTenSlotsCapacity_andOneSlotVm_fillsServerWithTenPercent(){

        Server theServer = a(server().withCapacity(10));
        Vm theVm = a(vm().withSize(1));
        balance(serverListWith(theServer), vmListWith(theVm));

        assertThat(theServer, hasCurrentLoadPercentageOf(10.0d));
    }

    @Test
    public void balancingServerWithEnoughRoom_getFilledWithAllVMs(){
        Server theServer = a(server().withCapacity(10));
        Vm vm1 = a(vm().withSize(2));
        Vm vm2 = a(vm().withSize(3));
        balance(serverListWith(theServer), vmListWith(vm1,vm2));

        assertThat(theServer, hasVmCountOf(2));
        assertThat("server contains vm1 ", theServer.contains(vm1));
        assertThat("server contains vm2 ", theServer.contains(vm2));

    }

    @Test
    public void aVm_shouldBeBalanced_onLessLoadedServerFirst(){

        Server server1 = a(server().withCapacity(10).withInitialLoadOf(45));
        Server server2 = a(server().withCapacity(10).withInitialLoadOf(50));
        Vm vm = a(vm().withSize(2));

        balance(serverListWith(server1, server2), vmListWith(vm));


        assertThat("server contains vm1 ", server1.contains(vm));
        assertThat("server does not contain vm2 ", !server2.contains(vm));

    }

    @Test
    public void balanceAServerWithNotEnoughRoom_shouldNotBeFilledWithVm(){

        Server server1 = a(server().withCapacity(10).withInitialLoadOf(50));
        Vm vm = a(vm().withSize(6));

        balance(serverListWith(server1), vmListWith(vm));

        assertThat("server contains vm1 ", !server1.contains(vm));
    }

    private Vm[] vmListWith(Vm... vms) {
        return vms;
    }

    private Vm a(VmBuilder vmBuilder) {
        return vmBuilder.build();
    }

    private VmBuilder vm() {
        return new VmBuilder();
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
