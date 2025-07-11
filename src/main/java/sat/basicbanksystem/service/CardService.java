package sat.basicbanksystem.service;

import sat.basicbanksystem.entity.Card;

import java.util.List;
import java.util.Optional;

public interface CardService {

    void save(Card card);

    void update(Card card);

    void delete(Long id);

    Card findById(Long id);

    Card findByCardNumber(String cardNumber);

    List<Card> findAll();

    List<Card> findAllUserCards(Long userId);
}
