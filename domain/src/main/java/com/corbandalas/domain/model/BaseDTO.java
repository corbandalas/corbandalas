package com.corbandalas.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Data
@SuperBuilder
@NoArgsConstructor
public class BaseDTO {
    public UUID uuid;
}
