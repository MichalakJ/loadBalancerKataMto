package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Created by Kuba on 2016-06-12.
 */
public class CurrentLoadPercentageMatcher extends TypeSafeMatcher<Server> {
    public static final double EPSILON = 0.01d;
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
        return v == server.getLoadPercentage() || Math.abs(v-server.getLoadPercentage()) < EPSILON;
    }



    @Override
    protected void describeMismatchSafely(Server item, Description mismatchDescription) {
        mismatchDescription.appendText("a server with load percentage of "+ item.getLoadPercentage());
    }



    public void describeTo(Description description) {
        description.appendText("a server with load percentage of "+ v);
    }
}
