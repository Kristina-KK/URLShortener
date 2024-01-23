package com.kkraljic.shortener.mapper;

import com.kkraljic.shortener.dto.RegisterResponse;
import com.kkraljic.shortener.entity.RegisteredUrl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface RegisteredUrlMapper {

    @Mappings({@Mapping(source = "shortUrl", target = "shortUrl", qualifiedByName = "shortUrlCustom")})
    RegisterResponse toRegisteredResponse(RegisteredUrl registeredUrl);

    @Named("shortUrlCustom")
    static String shortUrlCustom(String shortUrl) {
        return "localhost:8080/" + shortUrl;
    }

}
