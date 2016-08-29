package uk.co.mruoc.fake.github;

public class ArgumentException extends RuntimeException {

    public ArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public ArgumentException(Throwable cause) {
        super(cause);
    }

}
