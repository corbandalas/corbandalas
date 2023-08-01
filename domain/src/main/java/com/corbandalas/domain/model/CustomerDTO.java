package com.corbandalas.domain.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO extends BaseDTO {
    private UUID uuid = UUID.randomUUID();
    private String email;
    private String name;
    private boolean active;
    private LocalDateTime date;
    private String hashedPassword;
    private Set<RoleDTO> roles;

    public boolean isUserAdmin() {
        if (roles != null) {
            return getRoles().stream().map(RoleDTO::getName).anyMatch(t -> t.equalsIgnoreCase("ROLE_ADMIN"));
        }

        return false;
    }

}
