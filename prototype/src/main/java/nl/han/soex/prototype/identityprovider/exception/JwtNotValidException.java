package nl.han.soex.prototype.identityprovider.exception;

public class JwtNotValidException extends RuntimeException {
    public JwtNotValidException() {
        super("De JWT token is niet geldig.");
    }
}