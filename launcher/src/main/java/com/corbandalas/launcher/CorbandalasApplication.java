package com.corbandalas.launcher;

import com.corbandalas.data.mapper.RoleMapper;
import com.corbandalas.domain.model.CustomerDTO;
import com.corbandalas.domain.model.RoleDTO;
import com.corbandalas.domain.ports.api.CustomerServicePort;
import com.corbandalas.domain.ports.api.RoleServicePort;
import com.corbandalas.domain.ports.spi.RolePersistencePort;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SpringBootApplication(scanBasePackages = "com.corbandalas")
@EnableJpaRepositories("com.corbandalas.data.repository")
@EntityScan("com.corbandalas.data.model")
@PropertySource(value = {"application.properties", "db.properties", "web.properties"})

@Theme(value = "myapp", variant = Lumo.DARK)
public class CorbandalasApplication implements AppShellConfigurator {

    public static void main(String[] args) {
        SpringApplication.run(CorbandalasApplication.class, args);
    }

    @Bean
    @Profile("!test")
    public CommandLineRunner loadData(RoleServicePort roleServicePort, CustomerServicePort userService) {
        return (args) -> {

            List<RoleDTO> roleList = List.of("ROLE_USER", "ROLE_ADMIN").stream().map(role ->
                            roleServicePort.getRoleByName(role).or(() -> Optional.of(roleServicePort.create(new RoleDTO(role)))).get())
                    .collect(Collectors.toList());


            List.of("corban.dalas@me.com").stream().forEach(userName ->
					userService.retrieveByEmail(userName).or(() -> Optional.of(userService.create(
					CustomerDTO.builder()
							.date(LocalDateTime.now())
							.active(true)
                            .hashedPassword("x13jkw34")
							.email(userName)
							.name("admin")
							.roles(new HashSet<>(roleList))
							.build()))));


        };

    }
}
