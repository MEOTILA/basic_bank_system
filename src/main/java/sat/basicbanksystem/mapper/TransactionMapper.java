package sat.basicbanksystem.mapper;

import sat.basicbanksystem.dto.TransactionResponseDTO;
import sat.basicbanksystem.entity.Transaction;

public class TransactionMapper {

    public static TransactionResponseDTO toResponseDTO(Transaction transaction) {
        return new TransactionResponseDTO(
                transaction.getFromCard().getCardNumber(),
                transaction.getFromCard().getBank().getName(),
                transaction.getToCard().getUser().getFirstName() + " " +
                        transaction.getToCard().getUser().getLastName(),
                transaction.getToCard().getCardNumber(),
                transaction.getToCard().getBank().getName(),
                transaction.getAmount(),
                transaction.getFee(),
                transaction.getTransactionStatus(),
                transaction.getCreatedAt()
        );
    }
}
