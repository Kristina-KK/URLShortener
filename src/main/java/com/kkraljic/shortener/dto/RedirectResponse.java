package com.kkraljic.shortener.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RedirectResponse {

    private String longUrl;

    private int redirectType;

}
