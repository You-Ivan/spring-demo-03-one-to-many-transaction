package com.example.springbootdemo03.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cardNumber;
    private String cardType;

    /*
    find the credit cards of a specific account
        select c.id, c.card_number, c.card_type, c.account_id
        from account a
        join credit_card c
        on  a.id = c.account_id
        where a.id = <id>;
     */

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Override
    public String toString() {
        return "CreditCard{" +
                "id=" + id +
                ", cardNumber='" + cardNumber + '\'' +
                ", cardType='" + cardType + '\'' +
                '}';
    }

}
