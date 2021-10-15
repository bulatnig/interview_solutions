package org.bulatnig.supermarket;

public class SupermarketException extends RuntimeException {
    public SupermarketException() {
    }

    public SupermarketException(String message) {
        super(message);
    }

    public SupermarketException(String message, Throwable cause) {
        super(message, cause);
    }

    public SupermarketException(Throwable cause) {
        super(cause);
    }
}
