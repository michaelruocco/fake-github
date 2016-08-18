package uk.co.mruoc.fake.github;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

    int getStatusCode() {
        return statusCode;
    }

    static Response fromApacheResponse(HttpResponse response) throws IOException {
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

