package com.kkraljic.shortener.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "ACCOUNT")
@NoArgsConstructor
@Getter
@Setter
@Valid
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    @NotBlank
    @Size(max = 255)
    private String accountId;

    @Column(nullable = false)
    @NotBlank
    @Size(max = 255)
    private String password;

}
