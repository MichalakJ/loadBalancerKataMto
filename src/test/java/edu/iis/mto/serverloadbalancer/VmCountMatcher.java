package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Created by Kuba on 2016-06-16.
 */
public class VmCountMatcher extends TypeSafeMatcher<Server> {
    private int expectedVmCount;

    public VmCountMatcher(int expectedVmCount) {
        this.expectedVmCount = expectedVmCount;
    }

    protected boolean matchesSafely(Server server) {
        return server.countVms() == expectedVmCount;
    }

    @Override
    protected void describeMismatchSafely(Server item, Description mismatchDescription) {
        mismatchDescription.appendText("server has current vm count of ").appendValue(item.countVms());
    }

    public void describeTo(Description description) {
        description.appendText("server has current vm count of ").appendValue(expectedVmCount);
    }
}
