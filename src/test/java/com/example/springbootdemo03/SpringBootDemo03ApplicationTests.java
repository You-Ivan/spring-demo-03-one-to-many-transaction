package com.example.springbootdemo03;

import com.example.springbootdemo03.model.Account;
import com.example.springbootdemo03.model.CreditCard;
import com.example.springbootdemo03.repository.AccountRepository;
import com.example.springbootdemo03.repository.CreditCardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@SpringBootTest
class SpringBootDemo03ApplicationTests {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CreditCardRepository creditCardRepository;

    @Test
    void testSaveAccount() {
        Account account = Account.builder()
                .accountHolderName("Jack")
                .accountNumber("123123")
                .balance(1000.0)
                .build();
        CreditCard card1 = CreditCard.builder().
                account(account)
                .cardType("Visa")
                .cardNumber("1111-2222-3333-4444").build();
        CreditCard card2 = CreditCard.builder().
                account(account)
                .cardType("Mastercard")
                .cardNumber("5555-6666-7777-8888").build();
        account.setCreditCards(List.of(card1, card2));
        accountRepository.save(account);
    }

    @Test
    public void testGetCreditCardsOfAccount() {
        Optional<Account> optionalAccount = accountRepository.findById(1L);

        optionalAccount.ifPresent(a -> {
            a.getCreditCards().forEach(System.out::println);
        });
        System.out.println();
    }

    @Test
    public void testGetAccountByCreditCard() {
        Optional<CreditCard> optionalCreditCard = creditCardRepository.findById(1L);
        optionalCreditCard.ifPresent(a -> {
            System.out.println(a.getAccount());
        });
    }

}
