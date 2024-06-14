package com.example.springbootdemo03.service;

import com.example.springbootdemo03.model.Account;
import com.example.springbootdemo03.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AccountService {
    private final  AccountRepository accountRepository;

    // constructor injection
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public boolean transferMoney(Long fromAccountId, Long toAccountId,
                                 Double amount) {
        if (amount <= 0) {
            return false;
        }
        Optional<Account> optionalFromAccount = accountRepository.findById(fromAccountId);
        if (optionalFromAccount.isEmpty()) {
            return false;
        }
        Optional<Account> optionalToAccount = accountRepository.findById(toAccountId);
        if (optionalToAccount.isEmpty()) {
            return false;
        }

        Account fromAccount = optionalFromAccount.get();
        Account toAccount = optionalToAccount.get();

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        accountRepository.save(fromAccount);

        if (fromAccount.getBalance() < 0) {
            throw new RuntimeException("the amount to transfer cannot be greater than your balance!");
        }
        toAccount.setBalance(toAccount.getBalance() + amount);
        accountRepository.save(toAccount);
        return true;
    }
}
