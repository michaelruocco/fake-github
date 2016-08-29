package uk.co.mruoc.fake.github;

import org.apache.commons.cli.Options;

public class ArgumentException extends RuntimeException {

    private final Options options;

    public ArgumentException(Options options, Throwable cause) {
        super(cause);
        this.options = options;
    }

    public Options getOptions() {
        return options;
    }

}
