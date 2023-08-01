package com.corbandalas.web.security;

import com.corbandalas.web.views.LoginView;
import com.vaadin.flow.spring.security.VaadinWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends VaadinWebSecurity {

    public static final String LOGOUT_URL = "/";

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests().requestMatchers(new AntPathRequestMatcher("/images/*.png")).permitAll();
        http.authorizeHttpRequests().requestMatchers(new AntPathRequestMatcher("/img/*.png")).permitAll();
        // Icons from the line-awesome addon
        http.authorizeHttpRequests().requestMatchers(new AntPathRequestMatcher("/line-awesome/**/*.svg")).permitAll();
        super.configure(http);


        setLoginView(http, LoginView.class, LOGOUT_URL);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
//        web.ignoring().antMatchers("/api/confirm");
//        web.ignoring().antMatchers("/sitemap.xml");
//        web.ignoring().antMatchers(
//                "/images/**");
    }
}
