package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 * Created by Kuba on 2016-06-15.
 */
public class CurrentLoadPercentageMatcher extends TypeSafeMatcher<Server> {
    public static final double EPSILON = 0.01d;
    private double expectedLoadPercentage;

    public CurrentLoadPercentageMatcher(double expectedLoadPercentage) {
        this.expectedLoadPercentage = expectedLoadPercentage;
    }

    protected boolean matchesSafely(Server server) {
        double d1 = server.getLoadPercentage();
        double d2 = this.expectedLoadPercentage;
        return isDoubleEqual(d1, d2);
    }

    private boolean isDoubleEqual(double d1, double d2) {
        return d1 == d2 || Math.abs(d2 - d1) < EPSILON;
    }

    public void describeTo(Description description) {
        description.appendText("server with load percentage of ").appendValue(expectedLoadPercentage);
    }

    @Override
    protected void describeMismatchSafely(Server item, Description mismatchDescription) {
        mismatchDescription.appendText("server with load percentage of ").appendValue(item.getLoadPercentage());
    }
}
