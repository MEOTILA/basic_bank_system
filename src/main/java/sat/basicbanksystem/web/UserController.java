package sat.basicbanksystem.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sat.basicbanksystem.dto.CardToCardRequestDTO;
import sat.basicbanksystem.dto.TransactionResponseDTO;
import sat.basicbanksystem.dto.UpdateWithdrawLimitDTO;
import sat.basicbanksystem.dto.UserCardResponseDTO;
import sat.basicbanksystem.entity.Transaction;
import sat.basicbanksystem.mapper.TransactionMapper;
import sat.basicbanksystem.security.CustomUserDetails;
import sat.basicbanksystem.service.*;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
@Validated
public class UserController {
    private final UserService userService;
    private final TransactionService transactionService;
    private final CardToCardService cardToCardService;
    private final CardService cardService;
    private final UserCardService userCardService;

    @PostMapping("/transfer")
    public ResponseEntity<TransactionResponseDTO> transfer(
            @Valid @RequestBody CardToCardRequestDTO request,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        Transaction transaction = cardToCardService.transfer(
                request.fromCardNumber(),
                request.toCardNumber(),
                request.amount(),
                request.pin(),
                request.cvv2(),
                request.expireDate(),
                userDetails.getId()
        );
        return ResponseEntity.ok(TransactionMapper.toResponseDTO(transaction));
    }

    @GetMapping("/userCard")
    public ResponseEntity<List<UserCardResponseDTO>> getUserCardsInfo(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        Long userId = userDetails.getId();
        List<UserCardResponseDTO> userCards = userCardService.getUserCardsInfo(userId);
        return ResponseEntity.ok(userCards);
    }

    @PostMapping("/card/setLimit")
    public ResponseEntity<Void> updateWithdrawLimit(
            @RequestBody @Valid UpdateWithdrawLimitDTO request,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        userCardService.updateWithdrawLimitation(request.cardNumber(),
                request.newLimit(), userDetails.getId());
        return ResponseEntity.ok().build();
    }
}
