package sat.basicbanksystem.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sat.basicbanksystem.service.CardService;
import sat.basicbanksystem.service.CardToCardService;
import sat.basicbanksystem.service.TransactionService;
import sat.basicbanksystem.service.UserService;

@RestController
@RequestMapping("/v1/payment")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final TransactionService transactionService;
    private final CardToCardService cardToCardService;
    private final CardService cardService;
}
