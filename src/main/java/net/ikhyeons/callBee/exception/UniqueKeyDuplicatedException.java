package net.ikhyeons.callBee.exception;

public class UniqueKeyDuplicatedException extends RuntimeException{
    public UniqueKeyDuplicatedException() {
        super();
    }

    public UniqueKeyDuplicatedException(String message) {
        super(message);
    }

    public UniqueKeyDuplicatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UniqueKeyDuplicatedException(Throwable cause) {
        super(cause);
    }

}

