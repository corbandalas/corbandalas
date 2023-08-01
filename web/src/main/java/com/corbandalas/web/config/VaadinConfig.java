package com.corbandalas.web.config;

import com.vaadin.flow.spring.annotation.EnableVaadin;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableVaadin({"com.corbandalas.web.views"})
@EnableCaching
public class VaadinConfig {
}
