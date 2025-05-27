package sat.basicbanksystem.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import sat.basicbanksystem.entity.Bank;
import sat.basicbanksystem.exception.CustomApiException;
import sat.basicbanksystem.exception.CustomApiExceptionType;
import sat.basicbanksystem.repository.BankRepository;
import sat.basicbanksystem.service.BankService;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
@Validated
public class BankServiceImpl implements BankService {
    private final BankRepository bankRepository;

    @Override
    public void save(Bank bank) {
        bankRepository.save(bank);
    }

    @Override
    public void update(Long id) {
        Bank updatingBank = findById(id);
        bankRepository.save(updatingBank);
    }

    @Override
    public void delete(Long id) {
        Bank delitingBank = findById(id);
        bankRepository.delete(delitingBank);
    }

    @Override
    public Bank findById(Long id) {
        return bankRepository.findById(id)
                .orElseThrow(() -> new CustomApiException("Bank with ID {"
                        + id +"} is not found!", CustomApiExceptionType.NOT_FOUND));
    }

    @Override
    public Bank findByName(String name) {
        return bankRepository.findByName(name)
                .orElseThrow(() -> new CustomApiException("Bank with name {"
                        + name +"} is not found!", CustomApiExceptionType.NOT_FOUND));
    }

    @Override
    public List<Bank> findAll(){
        return bankRepository.findAll();
    }
}
