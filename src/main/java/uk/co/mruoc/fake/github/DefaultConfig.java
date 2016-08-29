package uk.co.mruoc.fake.github;

public class DefaultConfig {

    private static final int DEFAULT_PORT = 8080;
    private static final String DEFAULT_RESPONSE_HOST_URL = "http://localhost:" + DEFAULT_PORT;

    public int getPort() {
        return DEFAULT_PORT;
    }

    public String getResponseHostUrl() {
        return DEFAULT_RESPONSE_HOST_URL;
    }

}
