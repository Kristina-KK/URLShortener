package com.kkraljic.shortener.config;

import com.kkraljic.shortener.entity.Account;
import com.kkraljic.shortener.service.AccountService;
import com.kkraljic.shortener.service.AccountServiceImpl;
import com.kkraljic.shortener.utils.PasswordHasher;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final String accountId = authentication.getName();
        final String password = authentication.getCredentials().toString();

        final AccountService accountService = (AccountService) SpringUtils.ctx.getBean(AccountServiceImpl.class);

        // user details
        final Optional<Account> accountOpt = accountService.getRegisteredAccount(accountId);

        if (accountOpt.isEmpty()) {
            throw new UsernameNotFoundException("The user is not registered. Please register a user.");
        }

        final Account account = accountOpt.get();
        final String passwordDb = account.getPassword();

        if (!passwordDb.equals(PasswordHasher.hashPassword(password))) {
            throw new BadCredentialsException("Incorrect password entry.") {
            };
        }

        // new ArrayList<>() -> roles not set
        return new UsernamePasswordAuthenticationToken(accountId, password, new ArrayList<>());

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
