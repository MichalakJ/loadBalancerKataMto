package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 * Created by Kuba on 2016-06-16.
 */
public class CurrentLoadPercentageMatcher extends TypeSafeMatcher<Server> {
    public static final double EPSILON = 0.01d;
    private double expectedLoadPercentage;

    public CurrentLoadPercentageMatcher(double expectedLoadPercentage) {
        this.expectedLoadPercentage = expectedLoadPercentage;
    }

    protected boolean matchesSafely(Server server) {
        return expectedLoadPercentage == server.getCurrentLoadPercentage() || Math.abs(expectedLoadPercentage - server.getCurrentLoadPercentage()) < EPSILON;
    }

    @Override
    protected void describeMismatchSafely(Server item, Description mismatchDescription) {
        mismatchDescription.appendText("server has current percentage load of ").appendValue(item.getCurrentLoadPercentage());
    }

    public void describeTo(Description description) {
        description.appendText("server has current percentage load of ").appendValue(expectedLoadPercentage);
    }
}
