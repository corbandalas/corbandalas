package com.corbandalas.data.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;

import java.util.UUID;

@Entity
@Table(name = "customer_role")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Role extends BaseEntity implements GrantedAuthority {

    @Column(nullable = false)
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}