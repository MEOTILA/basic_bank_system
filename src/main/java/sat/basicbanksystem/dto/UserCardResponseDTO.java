package sat.basicbanksystem.dto;

import sat.basicbanksystem.entity.enums.CardStatus;

public record UserCardResponseDTO(
        String cardNumber,
        Long balance,
        Long withdrawLimitation,
        CardStatus cardStatus
) {
}
