package com.corbandalas.launcher.config;

import com.corbandalas.domain.ports.api.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderAdapter implements PasswordEncoder, org.springframework.security.crypto.password.PasswordEncoder {

    private BCryptPasswordEncoder passwordEncoder;

    public PasswordEncoderAdapter() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }


    @Override
    public String encode(CharSequence rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    @Override
    public boolean upgradeEncoding(String encodedPassword) {
        return PasswordEncoder.super.upgradeEncoding(encodedPassword);
    }
}
