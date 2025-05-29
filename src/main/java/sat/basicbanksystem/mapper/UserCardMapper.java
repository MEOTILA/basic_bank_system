package sat.basicbanksystem.mapper;

import sat.basicbanksystem.dto.UserCardResponseDTO;
import sat.basicbanksystem.entity.Card;

public class UserCardMapper {
    public static UserCardResponseDTO toResponse(Card card){
        return new UserCardResponseDTO(
                card.getCardNumber(),
                card.getBalance(),
                card.getWithdrawLimitation(),
                card.getCardStatus()
        );
    }
}
