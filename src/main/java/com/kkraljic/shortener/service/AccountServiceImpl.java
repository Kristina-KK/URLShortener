package com.kkraljic.shortener.service;

import com.kkraljic.shortener.entity.Account;
import com.kkraljic.shortener.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
@Configurable
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    @Override
    public Optional<Account> getRegisteredAccount(String accountId) {
        return accountRepository.findAccountByAccountId(accountId);
    }

}
