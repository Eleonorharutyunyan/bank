package com.bdg.bank.transaction.Bank.repository;

import com.bdg.bank.transaction.Bank.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {
}
