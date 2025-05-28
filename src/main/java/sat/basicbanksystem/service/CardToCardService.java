package sat.basicbanksystem.service;

import sat.basicbanksystem.entity.enums.TransactionStatus;

import java.time.LocalDate;

public interface CardToCardService {
    TransactionStatus transfer(String fromCardNumber, String toCardNumber, Long amount,
                               String pin, String cvv2, LocalDate expireDate);
}