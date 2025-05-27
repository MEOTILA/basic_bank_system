package sat.basicbanksystem.service;

import sat.basicbanksystem.entity.Bank;

import java.util.List;
import java.util.Optional;

public interface BankService {

    void save(Bank bank);

    void update(Long id);

    void delete(Long id);

    Bank findById(Long id);

    Bank findByName(String name);

    List<Bank> findAll();
}
