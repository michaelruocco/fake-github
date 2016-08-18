package uk.co.mruoc.fake.github;

import org.junit.Rule;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class FakeGithubTest {

    @Rule
    public final FakeGithubRule githubRule = new FakeGithubRule();

    private final TestClient client = new TestClient();

    @Test
    public void shouldGetUser() {
        Response response = client.doGet("http://localhost:8099/users/hackeryou");

        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat(extractLogin(response.getBody())).isEqualTo("HackerYou");
    }

    @Test
    public void shouldGetUserRepos() {
        Response response = client.doGet("http://localhost:8099/users/HackerYou/repos");

        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat(extractArraySize(response.getBody())).isEqualTo(30);
        assertThat(extractRepoName(response.getBody(), 0)).isEqualTo("amazon");
        assertThat(extractRepoName(response.getBody(), 1)).isEqualTo("assigner");
        assertThat(extractRepoName(response.getBody(), 29)).isEqualTo("twitter-sinatra");
    }

    @Test
    public void shouldGetUserRepoLanguages() {
        Response response = client.doGet("http://localhost:8099/repos/HackerYou/amazon/languages");

        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo("{\n" +
                "  \"Ruby\": 21246,\n" +
                "  \"CSS\": 1924,\n" +
                "  \"JavaScript\": 664,\n" +
                "  \"CoffeeScript\": 422\n" +
                "}\n");
    }

    private static String extractLogin(String json) {
        return JsonConverter.toJson(json).get("login").asText();
    }

    private static int extractArraySize(String json) {
        return JsonConverter.toJson(json).size();
    }

    private static String extractRepoName(String json, int index) {
        return JsonConverter.toJson(json).get(index).get("name").asText();
    }

}
