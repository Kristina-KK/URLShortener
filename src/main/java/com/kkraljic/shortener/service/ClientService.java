package com.kkraljic.shortener.service;

import com.kkraljic.shortener.dto.RedirectResponse;

public interface ClientService {

    RedirectResponse getRedirectUrl(final String shortUrl);

}
