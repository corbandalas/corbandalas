package com.corbandalas.data.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Data
@SuperBuilder
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity {

    @Id
    @Column(name = "id", nullable = false)
    private UUID uuid;
}
