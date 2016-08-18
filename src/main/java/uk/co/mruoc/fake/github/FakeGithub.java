package uk.co.mruoc.fake.github;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

public class FakeGithub implements AutoCloseable {

    private static final int DEFAULT_PORT = 8099;

    private WireMockServer server;

    public FakeGithub() {
        this(DEFAULT_PORT);
    }

    public FakeGithub(int port) {
        this(new WireMockConfiguration()
                .usingFilesUnderClasspath("uk/co/mruoc/fake/github")
                .port(port));
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

}
