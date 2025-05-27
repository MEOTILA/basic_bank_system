package sat.basicbanksystem.service;

import sat.basicbanksystem.entity.Transaction;

import java.util.List;

public interface TransactionService {

    void save(Transaction transaction);

    void update(Long id);

    void delete(Long id);

    Transaction findById(Long id);

    List<Transaction> findAll();
}
