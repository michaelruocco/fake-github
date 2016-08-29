package uk.co.mruoc.fake.github;

import org.apache.commons.cli.*;

public class ArgumentToFakeGithubConverter {

    private static final String PORT_NAME = "PORT";
    private static final String RESPONSE_HOST_URL_NAME = "responseHostUrl";

    public FakeGithub toFakeGithub(String[] args) {
        CommandLine commandLine = parse(args);
        int port = getPort(commandLine);
        String responseHostUrl = getResponseHostUrl(commandLine);
        return new FakeGithub.FakeGithubBuilder()
                .setPort(port)
                .setResponseHostUrl(responseHostUrl)
                .build();
    }

    private Options buildOptions() {
        Options options = new Options();
        options.addOption(new Option("p", PORT_NAME, true, "port used to run mock server"));
        options.addOption(new Option("u", RESPONSE_HOST_URL_NAME, true, "host url used in responses that point at other mocked resources"));
        return options;
    }

    private CommandLine parse(String[] args) {
        Options options = buildOptions();
        try {
            CommandLineParser parser = new DefaultParser();
            return parser.parse(options, args);
        } catch (ParseException e) {
            throw new ArgumentException(options, e);
        }
    }

    private int getPort(CommandLine commandLine) {
        return Integer.parseInt(commandLine.getOptionValue(PORT_NAME, "8080"));
    }

    private String getResponseHostUrl(CommandLine commandLine) {
        return commandLine.getOptionValue(RESPONSE_HOST_URL_NAME, "http://localhost:8080");
    }

}
