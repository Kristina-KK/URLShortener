package com.kkraljic.shortener.service;

import com.kkraljic.shortener.dto.RegisterRequest;
import com.kkraljic.shortener.entity.Account;
import com.kkraljic.shortener.entity.RegisteredUrl;

import java.util.Map;

public interface ConfigurationService {

    Account openAccount(String accountId);

    RegisteredUrl registerUrl(RegisterRequest registerRequest);

    Map<String, Long> getStatistic(String accountId);

}
