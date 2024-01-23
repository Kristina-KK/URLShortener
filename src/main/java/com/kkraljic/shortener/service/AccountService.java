package com.kkraljic.shortener.service;

import com.kkraljic.shortener.entity.Account;

import java.util.Optional;

public interface AccountService {

    Optional<Account> getRegisteredAccount(String accountId);

}
