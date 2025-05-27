package sat.basicbanksystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sat.basicbanksystem.entity.*;
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
                .firstName("Sara")
                .lastName("Samimi")
                .username("sara.samimi")
                .password("Sara@1234")
                .nationalId("0012023513")
                .phoneNumber("09121206511")
                .birthday(LocalDate.of(1998, 12, 12))
                .email("sara@example.com")
                .userType(userType1)
                .build();
        //userService.save(user2);



    }

}
