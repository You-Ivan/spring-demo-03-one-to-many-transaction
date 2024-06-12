package com.example.springbootdemo03.repository;

import com.example.springbootdemo03.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
