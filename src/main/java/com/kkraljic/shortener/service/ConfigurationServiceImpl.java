package com.kkraljic.shortener.service;

import com.kkraljic.shortener.config.IAuthenticationFacade;
import com.kkraljic.shortener.dto.RegisterRequest;
import com.kkraljic.shortener.entity.Account;
import com.kkraljic.shortener.entity.RegisteredUrl;
import com.kkraljic.shortener.repository.AccountRepository;
import com.kkraljic.shortener.repository.RegisteredUrlRepository;
import com.kkraljic.shortener.utils.PasswordGenerator;
import com.kkraljic.shortener.utils.PasswordHasher;
import com.kkraljic.shortener.utils.ShortUrlConverter;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
public class ConfigurationServiceImpl implements ConfigurationService {

    private IAuthenticationFacade authenticationFacade;

    private AccountRepository accountRepository;

    private RegisteredUrlRepository registeredUrlRepository;

    public Account openAccount(final String accountId) {

        final String originalPassword = PasswordGenerator.generatePassword();
        final String hashedPassword = PasswordHasher.hashPassword(originalPassword);

        final Account account = new Account();
        account.setAccountId(accountId);

        // save hashed password in db
        account.setPassword(hashedPassword);

        final Account savedAccount = accountRepository.save(account);

        // return original password to user
        savedAccount.setPassword(originalPassword);

        return savedAccount;

    }

    public RegisteredUrl registerUrl(final RegisterRequest registerRequest) {

        final Authentication auth = authenticationFacade.getAuthentication();
        final String accountId = auth.getName();

        final String longUrl = registerRequest.getUrl();
        final int redirectType = registerRequest.getRedirectType();

        return registerNewUrl(accountId, longUrl, redirectType);

    }

    private RegisteredUrl registerNewUrl(final String accountId, final String originalUrl, final int redirectType) {

        final RegisteredUrl newUrl = new RegisteredUrl();
        newUrl.setAccountId(accountId);
        newUrl.setLongUrl(originalUrl);
        newUrl.setRedirectType(redirectType);
        newUrl.setRequestCount(0L);

        registeredUrlRepository.save(newUrl);

        final Long id = newUrl.getId();
        final String shortUrl = ShortUrlConverter.INSTANCE.createUniqueShortUrl(id);

        newUrl.setShortUrl(shortUrl);

        // update row with unique short url according to ID
        registeredUrlRepository.save(newUrl);

        return newUrl;

    }

    public Map<String, Long> getStatistic(String accountId) {

        final List<RegisteredUrl> registeredUrls = registeredUrlRepository.findAllByAccountId(accountId);

        final Map<String, Long> statistics = new HashMap<>();

        registeredUrls.forEach(url -> {
            statistics.put(url.getLongUrl(), url.getRequestCount());
        });

        return statistics;

    }

}
