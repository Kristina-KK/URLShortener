package com.kkraljic.shortener.controller;

import com.kkraljic.shortener.dto.RedirectResponse;
import com.kkraljic.shortener.service.ClientService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@AllArgsConstructor
@RestController
public class ClientController {

    private final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

    private ClientService clientService;

    @GetMapping(value = "/{shortUrl}")
    public ResponseEntity<HttpStatus> redirectClient(@PathVariable final String shortUrl) {
        LOGGER.info("REST request to redirect short URL");

        final HttpHeaders headers = new HttpHeaders();
        final RedirectResponse redirectResponse = clientService.getRedirectUrl(shortUrl);

        headers.setLocation(URI.create(redirectResponse.getLongUrl()));

        // 301 -> move permanently
        // 302 -> redirect temporary

        if (redirectResponse.getRedirectType() == 301) {
            return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
        }

        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

}
