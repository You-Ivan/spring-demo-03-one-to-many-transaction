package com.example.springbootdemo03.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountNumber;
    private String accountHolderName;
    private Double balance;
    /*
    a: m=1000
    b: n=10000
    join: O(m*n)

        find the credit cards of a specific account
        select c.id, c.card_number, c.card_type, c.account_id
        from account a
        join credit_card c
        on  a.id = c.account_id
        where a.id = <id>;
     */
    @OneToMany(mappedBy = "account",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    private List<CreditCard> creditCards;

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountNumber='" + accountNumber + '\'' +
                ", accountHolderName='" + accountHolderName + '\'' +
                ", creditCards=" + (creditCards != null ? creditCards.stream().map(CreditCard::getId).collect(Collectors.toList()) : null) +
                '}';
    }
}
