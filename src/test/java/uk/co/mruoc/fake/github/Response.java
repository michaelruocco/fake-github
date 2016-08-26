package uk.co.mruoc.fake.github;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

class Response {

    private final int statusCode;
    private final String body;

    private Response(int statusCode, String body) {
        this.statusCode = statusCode;
        this.body = body;
    }

    protected String getBody() {
        return body;
    }

    public int getStatusCode() {
        return statusCode;
    }

    protected static Response fromApacheResponse(HttpResponse response) throws IOException {
        int statusCode = response.getStatusLine().getStatusCode();
        String body = extractBody(response);
        return new Response(statusCode, body);
    }

    private static String extractBody(HttpResponse response) throws IOException {
        if (response.getEntity() == null)
            return "";
        return EntityUtils.toString(response.getEntity());

    }

}

