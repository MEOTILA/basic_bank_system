package sat.basicbanksystem.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sat.basicbanksystem.dto.UserCardResponseDTO;
import sat.basicbanksystem.entity.Card;
import sat.basicbanksystem.entity.User;
import sat.basicbanksystem.entity.enums.CardStatus;
import sat.basicbanksystem.exception.CustomApiException;
import sat.basicbanksystem.exception.CustomApiExceptionType;
import sat.basicbanksystem.mapper.UserCardMapper;
import sat.basicbanksystem.service.UserCardService;
import sat.basicbanksystem.service.UserService;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class UserCardServiceImpl implements UserCardService {
    private final CardServiceImpl cardService;
    private final UserService userService;

    @Override
    public List<UserCardResponseDTO> getUserCardsInfo(Long userId){
        User user = userService.findById(userId);
        List<Card> cards = cardService.findAllUserCards(user.getId());
        return cards.stream()
                .map(UserCardMapper::toResponse)
                .toList();
    }

    @Override
    public void updateWithdrawLimitation(String cardNumber, Long newLimit, Long userId) {
        Card card = cardService.findByCardNumber(cardNumber);

        if (!card.getUser().getId().equals(userId))
            throw new CustomApiException("Card does not belong to the current user",
                    CustomApiExceptionType.UNAUTHORIZED);

        if (card.getCardStatus() != CardStatus.ACTIVE)
            throw new IllegalStateException("Card is not active");

        card.setWithdrawLimitation(newLimit);
        cardService.update(card);
    }
}
