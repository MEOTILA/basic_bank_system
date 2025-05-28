package sat.basicbanksystem.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
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
public class BankServiceImpl implements BankService {
    private final BankRepository bankRepository;

    @Override
    public void save(Bank bank) {
        isBankExist(bank.getName());
        Bank savedBank = bankRepository.save(bank);
        log.info("Bank with ID {} is saved", savedBank.getId());
    }

    @Override
    public void update(Bank bank) {
        Bank updatingBank = findById(bank.getId());

        if (StringUtils.hasText(bank.getName()) &&
                !bank.getName().equals(updatingBank.getName())) {

            isBankExist(bank.getName());
            updatingBank.setName(bank.getName());
        }

        bankRepository.save(updatingBank);
        log.info("Bank with ID {} is updated", updatingBank.getId());
    }

    @Override
    public void delete(Long id) {
        Bank deletingBank = findById(id);
        bankRepository.delete(deletingBank);
        log.info("Bank with ID {} is deleted", id);
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

    private void isBankExist(String name) {
        if (bankRepository.existsByName(name)) {
            throw new CustomApiException("Bank with name {"
                    + name + "} already exists!",
                    CustomApiExceptionType.UNPROCESSABLE_ENTITY);
        }
    }
}
