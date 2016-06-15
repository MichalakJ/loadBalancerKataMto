package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Created by Kuba on 2016-06-15.
 */
public class CurrentLoadPercentageMatcher extends TypeSafeMatcher<Server> {
    private double expectedLoadPercentage;

    public CurrentLoadPercentageMatcher(double expectedLoadPercentage) {
        this.expectedLoadPercentage = expectedLoadPercentage;
    }

    static Matcher<? super Server> hasCurrentLoadPercentageOf(double expectedLoadPercentage) {
        return new CurrentLoadPercentageMatcher(expectedLoadPercentage);
    }

    protected boolean matchesSafely(Server server) {
        double d1 = server.currentLoadPercentage;
        double d2 = this.expectedLoadPercentage;
        return doubleAreEqual(d1, d2);
    }

    private boolean doubleAreEqual(double d1, double d2) {
        return d1 == d2 || Math.abs(d1 - d2) < 0.01d;
    }

    public void describeTo(Description description) {
        description.appendText("server current load percentage of ").appendValue(expectedLoadPercentage);
    }
}
