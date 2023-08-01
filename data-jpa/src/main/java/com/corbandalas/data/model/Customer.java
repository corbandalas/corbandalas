package com.corbandalas.data.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "customer", indexes = {
        @Index(name = "idx_customer_email_unq", columnList = "email", unique = true)
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Customer extends BaseEntity implements UserDetails {


    @Column(name = "name", nullable = false)
    private String name;

    @NaturalId
    private String email;

    @Column
    private boolean active;

    @Column(name = "password", nullable = false)
    private String hashedPassword;

    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;

    @OneToMany(mappedBy = "customer")
    private Set<Post> posts;

    @OneToMany(mappedBy = "customer")
    private Set<Comment> comments;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "customer_roles", joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return hashedPassword;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return active;
    }

    @Override
    public boolean isAccountNonLocked() {
        return active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return active;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}