package com.corbandalas.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class UpdatePostDTO {

    @NotBlank
    @NotNull
    private String text;

    @NotBlank
    @NotNull
    private String title;

    private LocalDate date;
    @NotNull
    private UUID uuid;

}
