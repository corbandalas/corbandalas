package com.corbandalas.web.security;

import com.corbandalas.domain.model.CustomerDTO;
import com.corbandalas.domain.ports.api.CustomerServicePort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final CustomerServicePort customerServicePort;

    public UserDetailsServiceImpl(CustomerServicePort customerServicePort) {
        this.customerServicePort = customerServicePort;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomerDTO user = customerServicePort.retrieveByEmail(username).orElseThrow(() -> new UsernameNotFoundException("No user present with username: " + username));


        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getHashedPassword(),
                getAuthorities(user));
    }

    private static List<GrantedAuthority> getAuthorities(CustomerDTO user) {
        return user.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());

    }

}
