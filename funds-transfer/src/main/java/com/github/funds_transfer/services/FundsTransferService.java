package com.github.funds_transfer.services;

import com.github.funds_transfer.entities.Transaction;
import com.github.funds_transfer.repositories.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Slf4j
@Service
public class FundsTransferService {

    private final TransactionRepository transactionRepository;
    public FundsTransferService(TransactionRepository repository) {
        this.transactionRepository = repository;
    }

    public boolean transferFunds(Transaction transaction) {
        // check enough funds
        // deduct amount from sender
        // add amount to receiver
        boolean success = false;
        transaction.setTimestamp(LocalDateTime.now(ZoneId.of("UTC")));
        try {
            transactionRepository.save(transaction);
            success = true;
        } catch (Exception e) {
            log.debug(e.getMessage());
        }
        return success;
    }

    public List<Transaction> getTransactionHistory(int phoneNumber) {
        return transactionRepository.
                findByFromPhoneNumberEqualsOrderByTimestampDesc(phoneNumber);
    }
}
