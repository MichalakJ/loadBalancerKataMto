package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Created by Kuba on 2016-06-14.
 */
public class ServerVmsCountMatcher extends TypeSafeMatcher<Server> {

    private int expectedVmsCount;

    public ServerVmsCountMatcher(int expectedVmsCount) {
        this.expectedVmsCount = expectedVmsCount;
    }

    static Matcher<? super Server> hasVmsCountOf(int expectedVmsCount) {
        return new ServerVmsCountMatcher(expectedVmsCount);
    }

    protected boolean matchesSafely(Server server) {
        return expectedVmsCount == server.countVms();
    }

    @Override
    protected void describeMismatchSafely(Server item, Description mismatchDescription) {
        mismatchDescription.appendText("a server with vms count of"+ item.countVms());
    }

    public void describeTo(Description description) {
        description.appendText("a server with vms count of "+expectedVmsCount);
    }
}
