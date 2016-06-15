package edu.iis.mto.serverloadbalancer;


import static edu.iis.mto.serverloadbalancer.VmCountMatcher.*;
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
        Server server = a(server().withCapacity(10));

        balance(serverListWith(server), emptyVmList());

        assertThat(server, hasLoadPercentageOf(0.0d));
    }

    @Test
    public void balancingOneServerWithOneSlotCapacity_andOneSlotVm_fillsServerWithVm(){
        Server server = a(server().withCapacity(1));
        Vm vm = a(vm().withSize(1));
        balance(serverListWith(server), vmListWith(vm));

        assertThat(server, hasLoadPercentageOf(100.0d));
    }

    @Test
    public void balancingOneServerWithTenSlotsCapacity_andOneSlotVm_fillServerWithTenPercent(){
        Server server = a(server().withCapacity(10));
        Vm vm = a(vm().withSize(1));
        balance(serverListWith(server), vmListWith(vm));
        assertThat(server, hasLoadPercentageOf(10.0d));

    }

    @Test
    public void balancingAServerWithEnoughRoom_getsFilledWithAllVms(){
        Server server = a(server().withCapacity(10));
        Vm vm1 = a(vm().withSize(1));
        Vm vm2 = a(vm().withSize(5));
        balance(serverListWith(server), vmListWith(vm1,vm2));
        assertThat(server, hasVmCountOf(2));
    }

    @Test
    public void aVm_shouldBeBalanced_onLessLoadedServer(){
        Server lessLoadedserver = a(server().withCapacity(10).withLoadOf(50.0d));
        Server moreLoadedserver = a(server().withCapacity(10).withLoadOf(60.0d));
        Vm vm = a(vm().withSize(1));
        balance(serverListWith(lessLoadedserver, moreLoadedserver), vmListWith(vm));

        assertThat("less loaded server contains Vm", lessLoadedserver.contains(vm));
        assertThat("more loaded server does not contain Vm", !moreLoadedserver.contains(vm));

    }

    @Test
    public void balanceAServerWithNotEnoughRoom_shouldNotBeFilledWithAVm(){
        Server server = a(server().withCapacity(10));
        Vm vm = a(vm().withSize(20));
        balance(serverListWith(server), vmListWith(vm));

        assertThat("server does not contain vm ", !server.contains(vm));
    }

    @Test
    public void balance_serversAndVms(){
        Server server1 = a(server().withCapacity(10));
        Server server2 = a(server().withCapacity(12));

        Vm vm1 = a(vm().withSize(2));
        Vm vm2 = a(vm().withSize(6));
        Vm vm3 = a(vm().withSize(1));

        balance(serverListWith(server1, server2), vmListWith(vm1, vm2, vm3));
        assertThat("server1 contains vm1 ", server1.contains(vm1));
        assertThat("server2 contains vm2 ", server2.contains(vm2));
        assertThat("server1 contains vm3 ", server1.contains(vm1));

        assertThat(server1, hasLoadPercentageOf(33.33d));
        assertThat(server2, hasLoadPercentageOf(50.0d));
    }

    private Vm a(VmBuilder vmBuilder) {
        return vmBuilder.build();
    }

    private VmBuilder vm() {
        return new VmBuilder();
    }

    private Vm[] vmListWith(Vm... vms) {
        return vms;
    }

    private Server a(ServerBuilder serverBuilder) {
        return serverBuilder.build();
    }

    private ServerBuilder server() {
        return new ServerBuilder();
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
