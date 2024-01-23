package com.kkraljic.shortener.dto;

import com.kkraljic.shortener.dto.validation.ValidateRedirectType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

    @NotBlank(message = "URL is required.")
    @Pattern(regexp = "^https?:\\/\\/(?:www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b(?:[-a-zA-Z0-9()@:%_\\+.~#?&\\/=]*)$", message = "URL must start with https:// or http://")
    private String url;

    @ValidateRedirectType({301, 302})
    private int redirectType = 302;

}
