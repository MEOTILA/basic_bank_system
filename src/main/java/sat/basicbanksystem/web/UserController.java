package sat.basicbanksystem.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sat.basicbanksystem.dto.CardToCardRequestDTO;
import sat.basicbanksystem.dto.TransactionResponseDTO;
import sat.basicbanksystem.entity.Transaction;
import sat.basicbanksystem.mapper.TransactionMapper;
import sat.basicbanksystem.service.CardService;
import sat.basicbanksystem.service.CardToCardService;
import sat.basicbanksystem.service.TransactionService;
import sat.basicbanksystem.service.UserService;

@RestController
@RequestMapping("/v1/payment")
@RequiredArgsConstructor
@Validated
public class UserController {
    private final UserService userService;
    private final TransactionService transactionService;
    private final CardToCardService cardToCardService;
    private final CardService cardService;
    @PostMapping("/transfer")
    public ResponseEntity<TransactionResponseDTO> transfer(@Valid @RequestBody CardToCardRequestDTO request) {
        Transaction transaction = cardToCardService.transfer(
                request.fromCardNumber(),
                request.toCardNumber(),
                request.amount(),
                request.pin(),
                request.cvv2(),
                request.expireDate()
        );
        return ResponseEntity.ok(TransactionMapper.toResponseDTO(transaction));
    }
}
