package edu.iis.mto.serverloadbalancer;

/**
 * Created by Kuba on 2016-06-16.
 */
public class VmBuilder implements Builder<Vm>{
    Vm vm;
    private int size;

    public VmBuilder() {
        vm = new Vm();
    }

    public VmBuilder withSize(int size) {
        this.size = size;
        return this;
    }

    public Vm build() {
        vm.size = size;
        return vm;
    }
}
