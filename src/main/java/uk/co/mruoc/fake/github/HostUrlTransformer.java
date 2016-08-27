package uk.co.mruoc.fake.github;

import com.github.tomakehurst.wiremock.common.FileSource;
import com.github.tomakehurst.wiremock.extension.Parameters;
import com.github.tomakehurst.wiremock.extension.ResponseTransformer;
import com.github.tomakehurst.wiremock.http.Request;
import com.github.tomakehurst.wiremock.http.Response;

import static com.github.tomakehurst.wiremock.http.Response.*;

public class HostUrlTransformer extends ResponseTransformer {

    private final String replacementHostUrl;

    public HostUrlTransformer(String replacementHostUrl) {
        this.replacementHostUrl = replacementHostUrl;
    }

    @Override
    public Response transform(Request request, Response response, FileSource files, Parameters parameters) {
        return Builder
                .like(response)
                .body(replaceHostUrl(response.getBodyAsString()))
                .build();
    }

    @Override
    public String getName() {
        return "hostUrlTransformer";
    }

    private String replaceHostUrl(String input) {
        return input.replaceAll("https://api.github.com", replacementHostUrl);
    }

}
