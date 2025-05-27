package sat.basicbanksystem.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
@Validated
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;

    @Override
    public void save(Card card) {
        cardRepository.save(card);
    }

    @Override
    public void update(Long id) {
        Card updatingCard = findById(id);
        cardRepository.save(updatingCard);
    }

    @Override
    public void delete(Long id) {
        Card deletingCard = findById(id);
        cardRepository.delete(deletingCard);
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
}
