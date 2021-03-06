package uk.co.mruoc.fake.github;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.io.UncheckedIOException;

public class TestClient {

    private final String host;
    private final HttpClient client = HttpClientBuilder.create().build();

    public TestClient(String host) {
        this.host = host;
    }

    public Response doGet(String endpoint) {
        HttpGet get = new HttpGet(host + endpoint);
        return execute(get);
    }

    private Response execute(HttpRequestBase request) {
        try {
            return Response.fromApacheResponse(client.execute(request));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        } finally {
            request.releaseConnection();
        }
    }

}
