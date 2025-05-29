package sat.basicbanksystem.dto;

import jakarta.validation.constraints.*;

public record UpdateWithdrawLimitDTO(
        @NotBlank
        @Size(min = 16, max = 16)
        String cardNumber,

        @Positive
        Long newLimit
) {
}
