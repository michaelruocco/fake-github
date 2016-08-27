package uk.co.mruoc.fake.github;

import org.junit.rules.ExternalResource;

import static uk.co.mruoc.fake.github.FakeGithub.*;

public class FakeGithubRule extends ExternalResource {

    private final FakeGithub fakeGithub;

    public FakeGithubRule(int port) {
        this(new FakeGithubBuilder().setPort(port).build());
    }

    public FakeGithubRule(int port, String responseHostUrl) {
        this(new FakeGithubBuilder()
                .setPort(port)
                .setResponseHostUrl(responseHostUrl)
                .build());
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
