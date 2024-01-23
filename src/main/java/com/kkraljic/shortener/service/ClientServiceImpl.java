package com.kkraljic.shortener.service;

import com.kkraljic.shortener.config.IAuthenticationFacade;
import com.kkraljic.shortener.dto.RedirectResponse;
import com.kkraljic.shortener.entity.RegisteredUrl;
import com.kkraljic.shortener.exception.UrlNotRegisteredException;
import com.kkraljic.shortener.mapper.RedirectResponseMapper;
import com.kkraljic.shortener.repository.RegisteredUrlRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.atomic.LongAdder;

@AllArgsConstructor
@Service
public class ClientServiceImpl implements ClientService {

    private IAuthenticationFacade authenticationFacade;

    private RegisteredUrlRepository registeredUrlRepository;

    private RedirectResponseMapper redirectResponseMapper;

    public RedirectResponse getRedirectUrl(final String shortUrl) {

        final Authentication authentication = authenticationFacade.getAuthentication();
        final String accountId = authentication.getName();

        final Optional<RegisteredUrl> registeredUrl = registeredUrlRepository.findOneByAccountIdAndShortUrl(accountId, shortUrl);

        if (registeredUrl.isEmpty()) {
            throw new UrlNotRegisteredException("The URL is not registered. Please register your URL.");
        }

        final RegisteredUrl regUrl = registeredUrl.get();

        incrementStatisticCount(regUrl);

        return redirectResponseMapper.toRedirectResponse(regUrl);
    }

    private void incrementStatisticCount(final RegisteredUrl regUrl) {

        final Long lastCountValue = regUrl.getRequestCount();
        final LongAdder longAdder = new LongAdder();
        longAdder.add(lastCountValue);
        longAdder.increment();

        regUrl.setRequestCount(longAdder.longValue());

        registeredUrlRepository.save(regUrl);

    }

}
