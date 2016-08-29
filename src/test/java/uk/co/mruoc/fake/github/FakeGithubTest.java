package uk.co.mruoc.fake.github;

import org.junit.Rule;
import org.junit.Test;
import uk.co.mruoc.fake.github.FakeGithubRule.FakeGithubRuleBuilder;

import static org.apache.http.HttpStatus.SC_OK;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class FakeGithubTest {

    private static final String HOST = "http://localhost:8080";

    @Rule
    public final FakeGithubRule githubRule = new FakeGithubRuleBuilder().build();

    private final TestClient client = new TestClient(HOST);

    @Test
    public void getUserShouldReturnOk() {
        Response response = client.doGet("/users/hackeryou");

        assertThat(response.getStatusCode()).isEqualTo(SC_OK);
    }

    @Test
    public void getUserShouldReturnLogin() {
        Response response = client.doGet("/users/hackeryou");

        assertThat(extractLogin(response.getBody())).isEqualTo("HackerYou");
    }

    @Test
    public void getUserShouldReturnMockHostInRepoUrl() {
        Response response = client.doGet("/users/hackeryou");

        assertThat(extractRepoUrl(response.getBody())).isEqualTo(HOST + "/users/HackerYou/repos");
    }

    @Test
    public void getUserReposShouldReturnOk() {
        Response response = client.doGet("/users/HackerYou/repos");

        assertThat(response.getStatusCode()).isEqualTo(SC_OK);
    }

    @Test
    public void getUserReposShouldReturnThirtyRepos() {
        Response response = client.doGet("/users/HackerYou/repos");

        assertThat(extractArraySize(response.getBody())).isEqualTo(30);
    }

    @Test
    public void getUserReposShouldReturnAmazonFirst() {
        Response response = client.doGet("/users/HackerYou/repos");

        assertThat(extractRepoName(response.getBody(), 0)).isEqualTo("amazon");
    }

    @Test
    public void getUserReposShouldReturnTwitterSinatraLast() {
        Response response = client.doGet("/users/HackerYou/repos");

        assertThat(extractRepoName(response.getBody(), 29)).isEqualTo("twitter-sinatra");
    }

    @Test
    public void getUserRepoLanguagesShouldReturnOk() {
        Response response = client.doGet("/repos/HackerYou/amazon/languages");

        assertThat(response.getStatusCode()).isEqualTo(SC_OK);
        assertThat(response.getBody()).isEqualTo("{\n" +
                "  \"Ruby\": 21246,\n" +
                "  \"CSS\": 1924,\n" +
                "  \"JavaScript\": 664,\n" +
                "  \"CoffeeScript\": 422\n" +
                "}\n");
    }

    @Test
    public void getUserRepoLanguagesShouldReturnLanguages() {
        Response response = client.doGet("/repos/HackerYou/amazon/languages");

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

    private static String extractRepoUrl(String json) {
        return JsonConverter.toJson(json).get("repos_url").asText();
    }

    private static int extractArraySize(String json) {
        return JsonConverter.toJson(json).size();
    }

    private static String extractRepoName(String json, int index) {
        return JsonConverter.toJson(json).get(index).get("name").asText();
    }

}
