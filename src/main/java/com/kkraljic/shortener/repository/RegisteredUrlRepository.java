package com.kkraljic.shortener.repository;

import com.kkraljic.shortener.entity.RegisteredUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RegisteredUrlRepository extends JpaRepository<RegisteredUrl, UUID> {

    Optional<RegisteredUrl> findOneByAccountIdAndShortUrl(String accountId, String shortUrl);
    
    List<RegisteredUrl> findAllByAccountId(String accountId);

}
