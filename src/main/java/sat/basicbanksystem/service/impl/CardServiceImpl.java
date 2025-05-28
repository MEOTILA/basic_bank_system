package sat.basicbanksystem.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import sat.basicbanksystem.entity.Card;
import sat.basicbanksystem.exception.CustomApiException;
import sat.basicbanksystem.exception.CustomApiExceptionType;
import sat.basicbanksystem.repository.CardRepository;
import sat.basicbanksystem.service.CardService;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;

    @Override
    public void save(Card card) {
        checkCardNumberExists(card.getCardNumber());
        Card savedCard = cardRepository.save(card);
        log.info("Card with ID {} and number {} is saved",
                savedCard.getId(), savedCard.getCardNumber());
    }

    @Override
    public void update(Card card) {
        Card updatingCard = findById(card.getId());

        if (StringUtils.hasText(card.getCardNumber()) &&
                !card.getCardNumber().equals(updatingCard.getCardNumber())) {
            checkCardNumberExists(card.getCardNumber());
            updatingCard.setCardNumber(card.getCardNumber());
        }

        if (card.getBalance() != null &&
                !card.getBalance().equals(updatingCard.getBalance())) {
            updatingCard.setBalance(card.getBalance());
        }

        if (card.getBank() != null &&
                !card.getBank().equals(updatingCard.getBank())) {
            updatingCard.setBank(card.getBank());
        }

        if (card.getUser() != null &&
                !card.getUser().equals(updatingCard.getUser())) {
            updatingCard.setUser(card.getUser());
        }

        if (card.getWithdrawLimitation() != null &&
                !card.getWithdrawLimitation().equals(updatingCard.getWithdrawLimitation())) {
            updatingCard.setWithdrawLimitation(card.getWithdrawLimitation());
        }

        if (StringUtils.hasText(card.getPin()) &&
                !card.getPin().equals(updatingCard.getPin())) {
            updatingCard.setPin(card.getPin());
        }

        if (StringUtils.hasText(card.getCvv2()) &&
                !card.getCvv2().equals(updatingCard.getCvv2())) {
            updatingCard.setCvv2(card.getCvv2());
        }

        if (card.getExpireDate() != null &&
                !card.getExpireDate().equals(updatingCard.getExpireDate())) {
            updatingCard.setExpireDate(card.getExpireDate());
        }

        if (card.getCardStatus() != null &&
                !card.getCardStatus().equals(updatingCard.getCardStatus())) {
            updatingCard.setCardStatus(card.getCardStatus());
        }

        if (card.getFailedAttemptCount() != null &&
                !card.getFailedAttemptCount().equals(updatingCard.getFailedAttemptCount())) {
            updatingCard.setFailedAttemptCount(card.getFailedAttemptCount());
        }

        cardRepository.save(updatingCard);
        log.info("Card with ID {} is updated", updatingCard.getId());
    }

    @Override
    public void delete(Long id) {
        Card deletingCard = findById(id);
        cardRepository.delete(deletingCard);
        log.info("Card with ID {} is deleted", id);
    }

    @Override
    public Card findById(Long id) {
        return cardRepository.findById(id)
                .orElseThrow(() -> new CustomApiException("Card with ID {"
                        + id + "} is not found!", CustomApiExceptionType.NOT_FOUND));
    }

    @Override
    public Card findByCardNumber(String cardNumber) {
        return cardRepository.findByCardNumber(cardNumber)
                .orElseThrow(() -> new CustomApiException("Card with number {"
                        + cardNumber + "} is not found!", CustomApiExceptionType.NOT_FOUND));
    }

    @Override
    public List<Card> findAll() {
        return cardRepository.findAll();
    }

    private void checkCardNumberExists(String cardNumber) {
        if (cardRepository.existsByCardNumber(cardNumber)) {
            throw new CustomApiException("Card with number {"
                    + cardNumber + "} already exists!",
                    CustomApiExceptionType.UNPROCESSABLE_ENTITY);
        }
    }
}
