package com.kkraljic.shortener.mapper;

import com.kkraljic.shortener.dto.AccountResponse;
import com.kkraljic.shortener.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mappings({
            @Mapping( source = "success", target = "success" ),
            @Mapping( source = "description", target = "description" )
    })
    AccountResponse toAccountResponse(Account account, boolean success, String description);

}
