package uk.co.mruoc.fake.github;

import org.apache.commons.cli.*;

import static uk.co.mruoc.fake.github.FakeGithub.*;

public class FakeGithubRunner {

    public static void main(String[] args) {
        FakeGithubRunner runner = new FakeGithubRunner();
        runner.run(args);
    }

    private final ArgumentToFakeGithubConverter converter = new ArgumentToFakeGithubConverter();

    public void run(String[] args) {
        try {
            FakeGithub fakeGithub = converter.toFakeGithub(args);
            fakeGithub.start();
        } catch (ArgumentException e) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("fake-github", e.getOptions());
        }
    }

}
