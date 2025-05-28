package sat.basicbanksystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CardToCardRequestDTO(

    @NotBlank
    @Size(min = 16, max = 16)
    String fromCardNumber,

    @NotBlank
    @Size(min = 16, max = 16)
    String toCardNumber,

    @NotNull
    @Positive
    Long amount,

    @NotBlank
    @Size(min = 4, max = 4)
    String pin,

    @NotBlank
    @Size(min = 3, max = 3)
    String cvv2,

    @NotNull
    LocalDate expireDate
) {
}
