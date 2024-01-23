package com.kkraljic.shortener.entity;


import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "REGISTERED_URL")
@NoArgsConstructor
@Getter
@Setter
@Valid
public class RegisteredUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    @NotBlank
    @Size(max = 255)
    private String accountId;

    @Column(nullable = false, unique = true)
    @NotBlank
    @Size(max = 255)
    private String longUrl;

    @Column
    @Size(max = 255)
    private String shortUrl;

    @Column(nullable = false)
    @NotNull
    private Integer redirectType;

    @Column(nullable = false)
    @NotNull
    private Long requestCount;

}
