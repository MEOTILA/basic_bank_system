package sat.basicbanksystem.mapper;

import sat.basicbanksystem.dto.TransactionResponseDTO;
import sat.basicbanksystem.entity.Transaction;

public class TransactionMapper {

    public static TransactionResponseDTO toResponseDTO(Transaction transaction) {
        return new TransactionResponseDTO(
                transaction.getFromCard().getCardNumber(),
                transaction.getToCard().getCardNumber(),
                transaction.getAmount(),
                transaction.getFee(),
                transaction.getTransactionStatus(),
                transaction.getCreatedAt()
        );
    }
}
