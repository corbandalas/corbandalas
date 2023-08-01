package com.corbandalas.launcher.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.corbandalas.data.adapters.CustomerJPAAdapter;
import com.corbandalas.data.adapters.PostJPAAdapter;
import com.corbandalas.data.adapters.RoleJPAAdapter;
import com.corbandalas.domain.ports.api.*;
import com.corbandalas.domain.ports.spi.CustomerPersistencePort;
import com.corbandalas.domain.ports.spi.PostPersistencePort;
import com.corbandalas.domain.ports.spi.RolePersistencePort;
import com.corbandalas.service.CustomerServiceImpl;
import com.corbandalas.service.PostServiceImpl;
import com.corbandalas.service.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public CustomerPersistencePort customerPersistence() {
        return new CustomerJPAAdapter();
    }

    @Bean
    public PostPersistencePort postPersistencePort() {
        return new PostJPAAdapter();
    }

    @Bean
    public RolePersistencePort rolePersistencePort() {
        return new RoleJPAAdapter();
    }

    @Bean
    public CustomerServicePort customerService() {
        return new CustomerServiceImpl(customerPersistence(), passwordEncoder());
    }

    @Bean
    public PostServicePort postServicePort() {
        return new PostServiceImpl(postPersistencePort());
    }

    @Bean
    public RoleServicePort roleServicePort() {
        return new RoleServiceImpl(rolePersistencePort());

    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoderAdapter();
    }
}

