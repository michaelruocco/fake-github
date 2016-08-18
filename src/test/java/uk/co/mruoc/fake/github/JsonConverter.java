package uk.co.mruoc.fake.github;

import wiremock.com.fasterxml.jackson.databind.JsonNode;
import wiremock.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.UncheckedIOException;

public class JsonConverter {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static JsonNode toJson(String jsonText) {
        try {
            return mapper.readValue(jsonText, JsonNode.class);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}
