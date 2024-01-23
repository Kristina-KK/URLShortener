package com.kkraljic.shortener.mapper;

import com.kkraljic.shortener.dto.RedirectResponse;
import com.kkraljic.shortener.entity.RegisteredUrl;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RedirectResponseMapper {

    RedirectResponse toRedirectResponse(RegisteredUrl registeredUrl);

}
