package sat.basicbanksystem.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import sat.basicbanksystem.entity.Transaction;
import sat.basicbanksystem.exception.CustomApiException;
import sat.basicbanksystem.exception.CustomApiExceptionType;
import sat.basicbanksystem.repository.TransactionRepository;
import sat.basicbanksystem.service.TransactionService;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
@Validated
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;

    @Override
    public void save(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    @Override
    public void update(Long id) {
        Transaction updatingTransaction = findById(id);
        transactionRepository.save(updatingTransaction);
    }

    @Override
    public void delete(Long id) {
        Transaction deletingTransaction = findById(id);
        transactionRepository.delete(deletingTransaction);
    }

    @Override
    public Transaction findById(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new CustomApiException("Transaction with ID {"
                        + id + "} is not found!", CustomApiExceptionType.NOT_FOUND));
    }

    @Override
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }
}
