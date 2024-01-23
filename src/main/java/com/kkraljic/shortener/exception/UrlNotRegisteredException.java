package com.kkraljic.shortener.exception;

import jakarta.persistence.EntityNotFoundException;

public class UrlNotRegisteredException extends EntityNotFoundException {
    public UrlNotRegisteredException(String msg) {
        super(msg);
    }

}
