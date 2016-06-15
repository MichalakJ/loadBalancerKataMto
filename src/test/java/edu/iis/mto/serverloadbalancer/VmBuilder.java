package edu.iis.mto.serverloadbalancer;

/**
 * Created by Kuba on 2016-06-15.
 */
public class VmBuilder {
    private int size;
    private Vm vm;

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
