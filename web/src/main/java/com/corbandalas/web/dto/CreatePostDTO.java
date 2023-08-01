package com.corbandalas.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class CreatePostDTO {

    @NotBlank
    @NotNull
    private String title;
    @NotBlank
    @NotNull
    private String text;
    @NotNull
    private Date date;
    private MultipartFile[] photos;
}
