package uk.co.mruoc.fake.github;

import org.junit.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static uk.co.mruoc.fake.github.JsonExtractor.extractRepoUrl;

public class FakeGithubRunnerTest {

    private static final String HOST = "http://localhost:8080";

    @Test
    public void runShouldStartFakeGithubUsingDefaultPort() {
        FakeGithubRunner runner = new FakeGithubRunner();
        TestClient client = new TestClient(HOST);

        try {
            runner.run();

            Response response = client.doGet("/users/hackeryou");
            assertThat(response.getStatusCode()).isEqualTo(SC_OK);
        } finally {
            runner.stop();
        }
    }

    @Test
    public void runShouldStartFakeGithubUsingDefaultResponseHostUrl() {
        FakeGithubRunner runner = new FakeGithubRunner();
        TestClient client = new TestClient(HOST);

        try {
            runner.run();

            Response response = client.doGet("/users/hackeryou");
            assertThat(extractRepoUrl(response.getBody())).isEqualTo(HOST + "/users/HackerYou/repos");
        } finally {
            runner.stop();
        }
    }

    @Test
    public void runShouldStartFakeGithubUsingSpecifiedPort() {
        String port = "9999";
        String[] args = new String[] { "-p", port };
        FakeGithubRunner runner = new FakeGithubRunner(args);
        TestClient client = new TestClient("http://localhost:" + port);

        try {
            runner.run();

            Response response = client.doGet("/users/hackeryou");
            assertThat(response.getStatusCode()).isEqualTo(SC_OK);
        } finally {
            runner.stop();
        }
    }

    @Test
    public void runShouldStartFakeGithubUsingSpecifiedResponseHostUrl() {
        String responseHostUrl = "http://testOverrideHost:8080";
        String[] args = new String[] { "-u", responseHostUrl };
        FakeGithubRunner runner = new FakeGithubRunner(args);
        TestClient client = new TestClient(HOST);

        try {
            runner.run();

            Response response = client.doGet("/users/hackeryou");
            assertThat(extractRepoUrl(response.getBody())).isEqualTo(responseHostUrl + "/users/HackerYou/repos");
        } finally {
            runner.stop();
        }
    }

    @Test(expected = InvalidArgumentOptionException.class)
    public void runShouldThrowExceptionIfInvalidArgument() {
        String[] args = new String[] { "-i", "invalid" };
        FakeGithubRunner runner = new FakeGithubRunner(args);

        try {
            runner.run();
        } finally {
            runner.stop();
        }
    }

    @Test(expected = InvalidPortException.class)
    public void runShouldThrowExceptionIfInvalidPort() {
        String[] args = new String[] { "-p", "test" };
        FakeGithubRunner runner = new FakeGithubRunner(args);

        try {
            runner.run();
        } finally {
            runner.stop();
        }
    }

}
