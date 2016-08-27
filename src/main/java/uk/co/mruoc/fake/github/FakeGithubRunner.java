package uk.co.mruoc.fake.github;

import static uk.co.mruoc.fake.github.FakeGithub.*;

public class FakeGithubRunner {

    public static void main(String[] args) {
        FakeGithub fakeGithub = new FakeGithubBuilder().build();
        fakeGithub.start();
    }

}
