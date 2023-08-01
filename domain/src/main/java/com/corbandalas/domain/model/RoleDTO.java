package com.corbandalas.domain.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO extends BaseDTO {
    private String name;
}
