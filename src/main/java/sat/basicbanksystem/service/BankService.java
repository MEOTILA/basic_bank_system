package sat.basicbanksystem.service;

import sat.basicbanksystem.entity.Bank;

import java.util.List;

public interface BankService {

    void save(Bank bank);

    void update(Bank bank);

    void delete(Long id);

    Bank findById(Long id);

    Bank findByName(String name);

    List<Bank> findAll();
}
