package sat.basicbanksystem.exception;

import lombok.Getter;

@Getter
public class CustomApiException extends RuntimeException {
    private final CustomApiExceptionType type;

    public CustomApiException(String message, CustomApiExceptionType type) {
        super(message);
        this.type = type;
    }

}
