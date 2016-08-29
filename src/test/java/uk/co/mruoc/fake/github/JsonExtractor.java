package uk.co.mruoc.fake.github;

public class JsonExtractor {

    public static String extractLogin(String json) {
        return JsonConverter.toJson(json).get("login").asText();
    }

    public static String extractRepoUrl(String json) {
        return JsonConverter.toJson(json).get("repos_url").asText();
    }

    public static int extractArraySize(String json) {
        return JsonConverter.toJson(json).size();
    }

    public static String extractRepoName(String json, int index) {
        return JsonConverter.toJson(json).get(index).get("name").asText();
    }

}
