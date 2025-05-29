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
        /*Bank bank1 = Bank.builder().name("Pasargad").build();
        bankService.save(bank1);

        Bank bank2 = Bank.builder().name("Saman").build();
        bankService.save(bank2);*/

        //todo: UserType initializer
        UserType userType = UserType.builder().userType("USER").build();
        //userTypeService.save(userType);
        UserType userType1 = userTypeService.findByUserType("USER");

        //todo: User initializer
        User user1 = User.builder()
                .firstName("Ali")
                .lastName("Piroozfar")
                .username("ali.piroozfar")
                .password("Ali@1234")
                .nationalId("0012023512")
                .phoneNumber("09121206510")
                .birthday(LocalDate.of(1990, 1, 1))
                .email("ali@example.com")
                .userType(userType1)
                .build();

        //userService.save(user1);

        User user2 = User.builder()
                .firstName("Mohsen")
                .lastName("Abbasi")
                .username("mohesn.abbasi")
                .password("Mohsen@1234")
                .nationalId("0012023514")
                .phoneNumber("09121206515")
                .birthday(LocalDate.of(1998, 12, 12))
                .email("mohsen@example.com")
                .userType(userType1)
                .build();
        //userService.save(user2);

        //todo: Card initializer
        Bank bank1 = bankService.findById(1L);
        Bank bank2 = bankService.findById(3L);
        User foundedUser1 = userService.findById(1L);
        User foundedUser2 = userService.findById(2L);
        User foundedUser3 = userService.findById(3L);

        Card card1 = Card.builder()
                .cardNumber("1111220233334441")
                .balance(500000L)
                .withdrawLimitation(1_000_000L)
                .pin("1234")
                .cvv2("456")
                .failedAttemptCount(0L)
                .expireDate(LocalDate.of(2026, 12, 31))
                .cardStatus(CardStatus.ACTIVE)
                .bank(bank1)
                .user(foundedUser3)
                .build();

        Card card2 = Card.builder()
                .cardNumber("555561667778888")
                .balance(200000L)
                .withdrawLimitation(500_000L)
                .pin("5678")
                .cvv2("789")
                .failedAttemptCount(0L)
                .expireDate(LocalDate.of(2025, 10, 15))
                .cardStatus(CardStatus.ACTIVE)
                .bank(bank2)
                .user(foundedUser2)
                .build();
        cardService.save(card1);
        //cardService.save(card2);

        Card foundedCard1 = cardService.findById(2L);
        Card foundedCard2 = cardService.findById(3L);

        /*CardToCardService.transfer("1111222233334444",
                "5555666677778888",
                31575L,
                "1234",
                "456",
                LocalDate.of(2026, 12, 31)
        );*/
    }

}
