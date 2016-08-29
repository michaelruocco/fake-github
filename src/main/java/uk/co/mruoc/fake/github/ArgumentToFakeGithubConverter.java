package uk.co.mruoc.fake.github;

import org.apache.commons.cli.*;

public class ArgumentToFakeGithubConverter {

    private static final String PORT_NAME = "PORT";
    private static final String RESPONSE_HOST_URL_NAME = "responseHostUrl";

    private final DefaultConfig defaultConfig = new DefaultConfig();

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
            throw new InvalidArgumentOptionException(options, e);
        }
    }

    private int getPort(CommandLine commandLine) {
        String defaultPort = Integer.toString(defaultConfig.getPort());
        String port = commandLine.getOptionValue(PORT_NAME, defaultPort);
        try {
            return Integer.parseInt(port);
        } catch (NumberFormatException e) {
            throw new InvalidPortException("invalid port " + port, e);
        }
    }

    private String getResponseHostUrl(CommandLine commandLine) {
        return commandLine.getOptionValue(RESPONSE_HOST_URL_NAME, defaultConfig.getResponseHostUrl());
    }

}
