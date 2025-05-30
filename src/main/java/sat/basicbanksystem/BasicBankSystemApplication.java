package sat.basicbanksystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sat.basicbanksystem.entity.*;
import sat.basicbanksystem.entity.enums.CardStatus;
import sat.basicbanksystem.service.*;

import java.time.LocalDate;
import java.util.Collections;

@SpringBootApplication(scanBasePackages = "sat.basicbanksystem")
public class BasicBankSystemApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(BasicBankSystemApplication.class, args);
        var bankService = context.getBean(BankService.class);
        var cardService = context.getBean(CardService.class);
        var transactionService = context.getBean(TransactionService.class);
        var CardToCardService = context.getBean(CardToCardService.class);
        var userService = context.getBean(UserService.class);
        var userTypeService = context.getBean(UserTypeService.class);

        //todo: Bank initializer
        Bank bank1 = Bank.builder().name("Pasargad").build();
        //bankService.save(bank1);

        Bank bank2 = Bank.builder().name("Saman").build();
        //bankService.save(bank2);

        //todo: UserType initializer
        UserType userType = UserType.builder().userType("USER").build();
        //userTypeService.save(userType);
        UserType userType1 = userTypeService.findByUserType("USER");

        //todo: User initializer
        User user1 = User.builder()
                .firstName("Ali")
                .lastName("Piroozfar")
                .username("Ali")
                .password("Ali@1234")
                .nationalId("0012023512")
                .phoneNumber("09121206510")
                .birthday(LocalDate.of(1990, 9, 23))
                .email("ali@example.com")
                .userType(userType1)
                .build();

        //userService.save(user1);

        User user2 = User.builder()
                .firstName("Sara")
                .lastName("Samimi")
                .username("Sara")
                .password("Sara@1234")
                .nationalId("0084132069")
                .phoneNumber("09124120365")
                .birthday(LocalDate.of(1998, 10, 10))
                .email("sara@example.com")
                .userType(userType1)
                .build();
        //userService.save(user2);

        //todo: Card initializer
        Bank bank3 = bankService.findById(1L);
        Bank bank4 = bankService.findById(2L);
        User foundedUser1 = userService.findById(1L);
        User foundedUser2 = userService.findById(2L);

        Card card1 = Card.builder()
                .cardNumber("5022291032301596")
                .balance(50000000L)
                .withdrawLimitation(1000000L)
                .pin("1234")
                .cvv2("456")
                .failedAttemptCount(0L)
                .expireDate(LocalDate.of(2026, 12, 31))
                .cardStatus(CardStatus.ACTIVE)
                .bank(bank3)
                .user(foundedUser1)
                .build();

        Card card2 = Card.builder()
                .cardNumber("1466021066913210")
                .balance(20000000L)
                .withdrawLimitation(500000L)
                .pin("1234")
                .cvv2("456")
                .failedAttemptCount(0L)
                .expireDate(LocalDate.of(2025, 10, 15))
                .cardStatus(CardStatus.ACTIVE)
                .bank(bank4)
                .user(foundedUser2)
                .build();

        Card card3 = Card.builder()
                .cardNumber("5022291063201699")
                .balance(20000000L)
                .withdrawLimitation(500000L)
                .pin("1234")
                .cvv2("456")
                .failedAttemptCount(0L)
                .expireDate(LocalDate.of(2025, 10, 15))
                .cardStatus(CardStatus.ACTIVE)
                .bank(bank3)
                .user(foundedUser2)
                .build();
        //cardService.save(card1);
        //cardService.save(card2);
        //cardService.save(card3);

        //Card foundedCard1 = cardService.findById(2L);
        //Card foundedCard2 = cardService.findById(3L);

        /*CardToCardService.transfer("1111222233334444",
                "5555666677778888",
                31575L,
                "1234",
                "456",
                LocalDate.of(2026, 12, 31)
        );*/
    }

}
