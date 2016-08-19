package uk.co.mruoc.fake.github;

import com.github.tomakehurst.wiremock.common.FileSource;
import com.github.tomakehurst.wiremock.extension.Parameters;
import com.github.tomakehurst.wiremock.extension.ResponseTransformer;
import com.github.tomakehurst.wiremock.http.Request;
import com.github.tomakehurst.wiremock.http.Response;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.URIBuilder;

import java.net.URISyntaxException;

import static com.github.tomakehurst.wiremock.http.Response.*;

public class HostTransformer extends ResponseTransformer {

    private static final String DEFAULT_HOST = "localhost";

    private final int port;

    public HostTransformer(int port) {
        this.port = port;
    }

    @Override
    public Response transform(Request request, Response response, FileSource files, Parameters parameters) {
        return Builder
                .like(response)
                .body(replaceHostReferences(response.getBodyAsString()))
                .build();
    }

    @Override
    public String getName() {
        return "hostTransformer";
    }

    private String replaceHostReferences(String input) {
        return input.replaceAll("https://api.github.com", getMockHostUrl());
    }

    private String getMockHostUrl() {
        if (isOverrideHostSet())
            return getOverrideHostUrl();
        return getDefaultHostUrl();
    }

    private boolean isOverrideHostSet() {
        return StringUtils.isNotEmpty(getOverrideHost());
    }

    private String getOverrideHostUrl() {
        return buildUrl(getOverrideHost(), port);
    }

    private String getOverrideHost() {
        return System.getProperty("overrideHost");
    }

    private String getDefaultHostUrl() {
        return buildUrl(DEFAULT_HOST, port);
    }

    private String buildUrl(String host, int port) {
        try {
            return new URIBuilder()
                    .setScheme("http")
                    .setHost(host)
                    .setPort(port)
                    .build().toString();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

}
