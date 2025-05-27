package sat.basicbanksystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sat.basicbanksystem.entity.Bank;
import sat.basicbanksystem.entity.Transaction;
import sat.basicbanksystem.entity.User;
import sat.basicbanksystem.service.*;

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



    }

}
