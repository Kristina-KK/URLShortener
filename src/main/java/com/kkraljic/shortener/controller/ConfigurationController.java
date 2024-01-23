package com.kkraljic.shortener.controller;

import com.kkraljic.shortener.dto.AccountRequest;
import com.kkraljic.shortener.dto.AccountResponse;
import com.kkraljic.shortener.dto.RegisterRequest;
import com.kkraljic.shortener.dto.RegisterResponse;
import com.kkraljic.shortener.entity.Account;
import com.kkraljic.shortener.entity.RegisteredUrl;
import com.kkraljic.shortener.mapper.AccountMapper;
import com.kkraljic.shortener.mapper.RegisteredUrlMapper;
import com.kkraljic.shortener.service.ConfigurationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
public class ConfigurationController {

    private final Logger LOGGER = LoggerFactory.getLogger(ConfigurationController.class);

    private ConfigurationService configurationService;

    private AccountMapper accountMapper;

    private RegisteredUrlMapper registeredUrlMapper;

    @PostMapping(value = "/account", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountResponse> openAccount(@Valid @RequestBody final AccountRequest accountRequest) {
        LOGGER.info("REST request to register account");

        final Account account = configurationService.openAccount(accountRequest.getAccountId());
        final AccountResponse accountResponse = accountMapper.toAccountResponse(account, true, "Your account is opened");

        return ResponseEntity.status(HttpStatus.CREATED).body(accountResponse);
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegisterResponse> registerUrl(@Valid @RequestBody final RegisterRequest registerRequest) {
        LOGGER.info("REST request to register URL");

        final RegisteredUrl registerUrl = configurationService.registerUrl(registerRequest);
        final RegisterResponse registerResponse = registeredUrlMapper.toRegisteredResponse(registerUrl);

        return ResponseEntity.ok(registerResponse);
    }

    @GetMapping(value = "/statistic/{accountId}")
    public ResponseEntity<?> getStatistic(@PathVariable String accountId) {
        LOGGER.info("REST request to get URL statistic");

        return ResponseEntity.ok(configurationService.getStatistic(accountId));
    }

}
