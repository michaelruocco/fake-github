package uk.co.mruoc.fake.github;

import org.junit.Rule;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class FakeGithubReplaceHostUrlTest {

    private static final int PORT = 8099;
    private static final String RESPONSE_HOST_URL = "http://test:" + PORT;

    @Rule
    public final FakeGithubRule githubRule = new FakeGithubRule(PORT, RESPONSE_HOST_URL);

    private final TestClient client = new TestClient("http://localhost:" + PORT);

    @Test
    public void getUserShouldResponseHostUrlInRepoUrl() {
        String expectedUrl = RESPONSE_HOST_URL + "/users/HackerYou/repos";

        Response response = client.doGet("/users/hackeryou");

        assertThat(extractRepoUrl(response.getBody())).isEqualTo(expectedUrl);
    }

    private static String extractRepoUrl(String json) {
        return JsonConverter.toJson(json).get("repos_url").asText();
    }

}
