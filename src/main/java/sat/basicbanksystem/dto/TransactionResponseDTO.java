package sat.basicbanksystem.dto;

import sat.basicbanksystem.entity.enums.TransactionStatus;

import java.time.LocalDateTime;

public record TransactionResponseDTO(
    String fromCardNumber,
    String fromCardBank,
    String toCardNumber,
    String toCardBank,
    Long amount,
    Long fee,
    TransactionStatus status,
    LocalDateTime date
) {}
