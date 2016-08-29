package uk.co.mruoc.fake.github;

import org.junit.Rule;
import org.junit.Test;
import uk.co.mruoc.fake.github.FakeGithubRule.FakeGithubRuleBuilder;

import static org.apache.http.HttpStatus.SC_OK;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class FakeGithubReplaceHostUrlTest {

    private static final int PORT = 8099;
    private static final String RESPONSE_HOST_URL = "http://test:" + PORT;

    @Rule
    public final FakeGithubRule githubRule = new FakeGithubRuleBuilder()
            .setPort(PORT)
            .setResponseHostUrl(RESPONSE_HOST_URL)
            .build();

    private final TestClient client = new TestClient("http://localhost:" + PORT);

    @Test
    public void getUserShouldOverrideResponseHostUrlInRepoUrl() {
        String expectedUrl = RESPONSE_HOST_URL + "/users/HackerYou/repos";

        Response response = client.doGet("/users/hackeryou");

        assertThat(extractRepoUrl(response.getBody())).isEqualTo(expectedUrl);
    }

    private static String extractRepoUrl(String json) {
        return JsonConverter.toJson(json).get("repos_url").asText();
    }

}
