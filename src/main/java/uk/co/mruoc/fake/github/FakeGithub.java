package uk.co.mruoc.fake.github;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

public class FakeGithub implements AutoCloseable {

    private WireMockServer server;

    private FakeGithub(FakeGithubBuilder builder) {
        this(new WireMockConfiguration()
                .extensions(new HostUrlTransformer(builder.responseHostUrl))
                .usingFilesUnderClasspath("uk/co/mruoc/fake/github")
                .port(builder.port));
    }

    private FakeGithub(WireMockConfiguration configuration) {
        server = new WireMockServer(configuration);
    }

    public void start() {
        server.start();
    }

    public void stop() {
        server.stop();
    }

    @Override
    public void close() throws Exception {
        stop();
    }

    public static class FakeGithubBuilder {

        private final DefaultConfig defaultConfig = new DefaultConfig();
        private int port = defaultConfig.getPort();
        private String responseHostUrl = defaultConfig.getResponseHostUrl();

        public FakeGithubBuilder setPort(int port) {
            this.port = port;
            return this;
        }

        public FakeGithubBuilder setResponseHostUrl(String responseHostUrl) {
            this.responseHostUrl = responseHostUrl;
            return this;
        }

        public FakeGithub build() {
            return new FakeGithub(this);
        }

    }

}
