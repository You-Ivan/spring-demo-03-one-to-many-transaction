package com.example.springbootdemo03.repository;

import com.example.springbootdemo03.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
}
