package uk.co.mruoc.fake.github;

import org.apache.commons.cli.Options;

public class InvalidArgumentOptionException extends ArgumentException {

    private final Options options;

    public InvalidArgumentOptionException(Options options, Throwable cause) {
        super(cause);
        this.options = options;
    }

    public Options getOptions() {
        return options;
    }

}
