package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Created by Kuba on 2016-06-12.
 */
public class CurrentLoadPercentageMatcher extends TypeSafeMatcher<Server> {
    private double v;

    public CurrentLoadPercentageMatcher(double v) {

        this.v = v;
    }

    static Matcher<? super Server> hasLoadPercentageOf(double v) {
        return new CurrentLoadPercentageMatcher(v);
    }

    protected boolean matchesSafely(Server server) {
        return isDoubleEqual(server);
    }

    private boolean isDoubleEqual(Server server) {
        return v == server.getLoadPercetage() || Math.abs(v-server.getLoadPercetage()) < 0.01d;
    }

    public void describeTo(Description description) {

    }
}
