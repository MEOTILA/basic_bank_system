package sat.basicbanksystem.service;

import sat.basicbanksystem.entity.Transaction;

import java.time.LocalDate;

public interface CardToCardService {
    Transaction transfer(String fromCardNumber, String toCardNumber, Long amount,
                         String pin, String cvv2, LocalDate expireDate);
}