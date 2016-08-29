package uk.co.mruoc.fake.github;

import org.apache.commons.cli.*;

public class FakeGithubRunner {

    public static void main(String[] args) {
        try {
            FakeGithubRunner runner = new FakeGithubRunner(args);
            runner.run();
        } catch (InvalidArgumentOptionException e) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("fake-github", e.getOptions());
        }
    }

    private final ArgumentToFakeGithubConverter converter = new ArgumentToFakeGithubConverter();

    private final FakeGithub fakeGithub;

    public FakeGithubRunner(String... args) {
        this.fakeGithub = converter.toFakeGithub(args);
    }

    public void run() {
        fakeGithub.start();
    }

    public void stop() {
        fakeGithub.stop();
    }

}
