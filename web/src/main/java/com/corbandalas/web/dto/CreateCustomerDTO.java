package com.corbandalas.web.dto;

import com.corbandalas.domain.model.RoleDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
public class CreateCustomerDTO {

    @NotBlank(message = "Invalid Name: Empty name")
    @NotNull(message = "Invalid Name: Name is NULL")
    @Size(min = 3, max = 15, message = "Invalid Name: Must be of 3 - 30 characters")
    private String userName;
    @Email
    @NotBlank(message = "Invalid email: Empty email")
    @NotNull(message = "Invalid email: Email is NULL")
    private String email;

    private Set<RoleDTO> roles;
}
