package edu.iis.mto.serverloadbalancer;

/**
 * Created by Kuba on 2016-06-13.
 */
public class VmBuilder implements Builder<Vm>{

    private Vm vm;

    public VmBuilder() {
        vm = new Vm();
    }


    public VmBuilder getDefaultVm() {
        vm = new Vm();
        return this;
    }

    public VmBuilder withSize(int i) {
        vm.setSize(i);
        return this;
    }

    public Vm build() {
        return vm;
    }
}
