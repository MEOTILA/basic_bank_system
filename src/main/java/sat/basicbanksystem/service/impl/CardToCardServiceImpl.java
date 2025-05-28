package sat.basicbanksystem.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import sat.basicbanksystem.entity.Card;
import sat.basicbanksystem.entity.Transaction;
import sat.basicbanksystem.entity.enums.CardStatus;
import sat.basicbanksystem.entity.enums.TransactionStatus;
import sat.basicbanksystem.exception.CustomApiException;
import sat.basicbanksystem.exception.CustomApiExceptionType;
import sat.basicbanksystem.service.CardService;
import sat.basicbanksystem.service.CardToCardService;
import sat.basicbanksystem.service.TransactionService;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class CardToCardServiceImpl implements CardToCardService {
    private final CardService cardService;
    private final TransactionService transactionService;

    @Override
    public Transaction transfer(String fromCardNumber, String toCardNumber, Long amount,
                         String pin, String cvv2, LocalDate expireDate) {

        Transaction transaction;
        //TransactionStatus status = TransactionStatus.FAILED;
        final Long fee = 6000L;

        if (fromCardNumber.equals(toCardNumber)) {
            throw new CustomApiException("Cannot transfer to the same card",
                    CustomApiExceptionType.BAD_REQUEST);
        }

        Card fromCard = cardService.findByCardNumber(fromCardNumber);
        Card toCard = cardService.findByCardNumber(toCardNumber);

        if (fromCard.getCardStatus().equals(CardStatus.IN_ACTIVE)) {
            throw new CustomApiException("Your card is Inactive!",
                    CustomApiExceptionType.BAD_REQUEST);
        }
        if (toCard.getCardStatus().equals(CardStatus.IN_ACTIVE)) {
            throw new CustomApiException("Destination card is Inactive!",
                    CustomApiExceptionType.BAD_REQUEST);
        }

        if (fromCard.getExpireDate().isBefore(LocalDate.now())) {
            log.warn("Card '{}' is expired."
                    , fromCardNumber);
            fromCard.setCardStatus(CardStatus.IN_ACTIVE);
            fromCard.setFailedAttemptCount(3L);
            cardService.update(fromCard);
            transaction = Transaction.builder()
                    .fromCard(fromCard)
                    .toCard(toCard)
                    .amount(amount)
                    .fee(fee)
                    .transactionStatus(TransactionStatus.FAILED)
                    .build();
            transactionService.save(transaction);
            return transaction;
        }

        boolean authenticationFailed = false;

        if (!fromCard.getPin().equals(pin)) {
            authenticationFailed = true;
            log.warn("Incorrect PIN for card '{}'.", fromCardNumber);
        }
        if (!fromCard.getCvv2().equals(cvv2)) {
            authenticationFailed = true;
            log.warn("Incorrect CVV2 for card '{}'.", fromCardNumber);
        }
        if (!fromCard.getExpireDate().equals(expireDate)) {
            authenticationFailed = true;
            log.warn("Card '{}' expire date is incorrect.", fromCardNumber);
        }

        if (authenticationFailed) {
            fromCard.setFailedAttemptCount(fromCard.getFailedAttemptCount() + 1);
            if (fromCard.getFailedAttemptCount() >= 3) {
                fromCard.setCardStatus(CardStatus.IN_ACTIVE);
                log.warn("Card '{}' has been set to INACTIVE due to multiple failed attempts.",
                        fromCardNumber);
            }
            cardService.update(fromCard);
            transaction = Transaction.builder()
                    .fromCard(fromCard)
                    .toCard(toCard)
                    .amount(amount)
                    .fee(fee)
                    .transactionStatus(TransactionStatus.FAILED)
                    .build();

            transactionService.save(transaction);
            return transaction;
        }

        Long total = amount + fee;

        if (fromCard.getBalance() < total) {
            throw new CustomApiException("Insufficient balance",
                    CustomApiExceptionType.BAD_REQUEST);
        }

        if (fromCard.getWithdrawLimitation() < total) {
            throw new CustomApiException("Withdraw limitation exceeded.",
                    CustomApiExceptionType.UNAUTHORIZED);
        }

        fromCard.setBalance(fromCard.getBalance() - total);
        toCard.setBalance(toCard.getBalance() + amount);

        cardService.update(fromCard);
        cardService.update(toCard);

        transaction = Transaction.builder()
                .fromCard(fromCard)
                .toCard(toCard)
                .amount(amount)
                .fee(fee)
                .transactionStatus(TransactionStatus.SUCCESSFUL)
                .build();

        transactionService.save(transaction);

        log.info("Transferred {} from {} to {}", amount, fromCardNumber, toCardNumber);
        return transaction;
    }
}
