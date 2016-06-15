package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Created by Kuba on 2016-06-15.
 */
public class ServerVmsCountMatcher extends TypeSafeMatcher<Server> {
    private int expectedVmCount;

    public ServerVmsCountMatcher(int expectedVmCount) {
        this.expectedVmCount = expectedVmCount;
    }

    static Matcher<? super Server> hasVmCountOf(int expectedVmCount) {
        return new ServerVmsCountMatcher(expectedVmCount);
    }

    protected boolean matchesSafely(Server server) {
        return server.countVms() == expectedVmCount;
    }

    public void describeTo(Description description) {
        description.appendText("server has vm count of ").appendValue(expectedVmCount);
    }

    @Override
    protected void describeMismatchSafely(Server item, Description mismatchDescription) {
        mismatchDescription.appendText("server has vm count of ").appendValue(item.countVms());
    }
}
