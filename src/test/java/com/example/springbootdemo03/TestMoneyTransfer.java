package com.example.springbootdemo03;

import com.example.springbootdemo03.model.Account;
import com.example.springbootdemo03.repository.AccountRepository;
import com.example.springbootdemo03.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class TestMoneyTransfer {
    @Autowired
    AccountService accountService;

    @Autowired
    AccountRepository accountRepository;

    @Test
    public void testSaveAccount() {
        Account lily = accountRepository.save(Account.builder()
                .balance(500.0)
                .accountNumber("123321")
                .accountHolderName("Lily")
                .build());
    }

    @Test
    public void testTransferSuccess() throws Exception {
//        Optional<Account> account1 = accountRepository.findById(1L);
//        Optional<Account> account2 = accountRepository.findById(2L);
        try {
            accountService.transferMoney(1L, 2L, 50.0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
