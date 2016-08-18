package uk.co.mruoc.fake.github;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

public class FakeGithub {

    private WireMockServer server;

    public FakeGithub() {
        String path = "uk/co/mruoc/fake/github";
        WireMockConfiguration config = new WireMockConfiguration()
                .usingFilesUnderClasspath(path)
                .port(8099);
        server = new WireMockServer(config);
    }

    public void start() {
        server.start();
    }

    public void stop() {
        server.stop();
    }

}
