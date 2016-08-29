package uk.co.mruoc.fake.github;

import org.junit.rules.ExternalResource;

import static uk.co.mruoc.fake.github.FakeGithub.*;

public class FakeGithubRule extends ExternalResource {

    private final FakeGithub fakeGithub;

    private FakeGithubRule(FakeGithub fakeGithub) {
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


    public static class FakeGithubRuleBuilder {

        private final FakeGithubBuilder builder = new FakeGithubBuilder();

        public FakeGithubRuleBuilder setPort(int port) {
            this.builder.setPort(port);
            return this;
        }

        public FakeGithubRuleBuilder setResponseHostUrl(String responseHostUrl) {
            this.builder.setResponseHostUrl(responseHostUrl);
            return this;
        }

        public FakeGithubRule build() {
            return new FakeGithubRule(builder.build());
        }

    }
}
