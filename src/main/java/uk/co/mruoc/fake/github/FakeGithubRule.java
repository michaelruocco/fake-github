package uk.co.mruoc.fake.github;

import org.junit.rules.ExternalResource;

public class FakeGithubRule extends ExternalResource {

    private final FakeGithub fakeGithub;

    public FakeGithubRule() {
        this(new FakeGithub());
    }

    public FakeGithubRule(int port) {
        this(new FakeGithub(port));
    }

    public FakeGithubRule(FakeGithub fakeGithub) {
        this.fakeGithub = fakeGithub;
    }

    @Override
    protected void before() throws Throwable {
        fakeGithub.start();
    }

    @Override
    protected void after() {
        fakeGithub.stop();
    }

}
