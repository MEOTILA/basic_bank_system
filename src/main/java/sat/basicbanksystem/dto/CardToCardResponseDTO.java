package sat.basicbanksystem.dto;

import sat.basicbanksystem.entity.enums.TransactionStatus;

public record CardToCardResponseDTO(
    String fromCardNumber,
    String toCardNumber,
    Long amount,
    Long fee,
    TransactionStatus status
) {}
